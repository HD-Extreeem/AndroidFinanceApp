package com.example.hadideknache.financeapp;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Parcelable;

import android.support.v7.app.AlertDialog;
import android.util.Log;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.hadideknache.financeapp.Databases.BarCodeDBHelper;
import com.example.hadideknache.financeapp.Databases.ExpenditureDBHelper;
import com.example.hadideknache.financeapp.Databases.IncomeDBHelper;
import com.example.hadideknache.financeapp.Databases.UserDBHelper;
import com.example.hadideknache.financeapp.Fragments.ExpenditureFragment;
import com.example.hadideknache.financeapp.Fragments.IncomeFragment;
import com.example.hadideknache.financeapp.Fragments.MainViewFragment;
import com.example.hadideknache.financeapp.Fragments.OverViewFragment;
import com.example.hadideknache.financeapp.Fragments.SearchFramgent;
import com.example.hadideknache.financeapp.Fragments.SettingsFragment;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * This class is the brain of the whole application
 * This controller class handles all logic and communication with gui to fragments
 * Created by hadideknache on 2017-09-13.
 */

public class Controller {
    private MainActivity main;
    private MainViewFragment mainViewFragment;
    private SearchFramgent searchFramgent;
    private SettingsFragment settingsFragment;
    private OverViewFragment overViewFragment;
    private IncomeFragment incomeFragment;
    private ExpenditureFragment expenditureFragment;
    private User[] user;
    private String dateFrom,dateTo;
    private UserDBHelper userDbHelper;
    private IncomeDBHelper incomeDBHelper;
    private BarCodeDBHelper barCodeDBHelper;
    private ExpenditureDBHelper expenditureDBHelper;
    private Parcelable[] parcelables;
    private String MAINVIEWFRAGMENT= "mainviewfragment";
    private String SEARCHFRAGMENT= "searchfragment";
    private String SETTINGSFRAGMENT= "settingsfragment";
    private String OVERVIEWFRAGMENT= "overviewfragment";
    private String INCOMEFRAGMENT= "incomefragment";
    private String EXPENDITUREFRAGMENT= "expenditurefragment";
    private int mY, mM, mD, mH, mMin;

    /**
     * Controller constructor
     * @param mainActivity instance to main
     * @param intent reference to the intent
     */
    public Controller(MainActivity mainActivity, Intent intent) {
        this.main=mainActivity;
        parcelables = intent.getParcelableArrayExtra(LoginController.USERS);
        user = new User[parcelables.length];
        for(int i=0; i<user.length; i++) {
            user[i] = (User)parcelables[i];
        }
        initFragments();
        dataBaseInit();

    }


    /*public Controller(MainActivity main,Intent intent,Boolean bool) {
        this.main=main;
        parcelables = intent.getParcelableArrayExtra(LoginController.USERS);
        user = new User[parcelables.length];
        for(int i=0; i<user.length; i++) {
            user[i] = (User)parcelables[i];
        }
        fetchFragments();
        dataBaseInit();
    }*/

    /*private void fetchFragments() {
        mainViewFragment = (MainViewFragment) main.getSupportFragmentManager().findFragmentByTag(MAINVIEWFRAGMENT);
        settingsFragment = (SettingsFragment) main.getSupportFragmentManager().findFragmentByTag(SETTINGSFRAGMENT);
        searchFramgent = (SearchFramgent) main.getSupportFragmentManager().findFragmentByTag(SEARCHFRAGMENT);
        incomeFragment = (IncomeFragment) main.getSupportFragmentManager().findFragmentByTag(INCOMEFRAGMENT);
        overViewFragment  =(OverViewFragment) main.getSupportFragmentManager().findFragmentByTag(OVERVIEWFRAGMENT);
        expenditureFragment = (ExpenditureFragment) main.getSupportFragmentManager().findFragmentByTag(EXPENDITUREFRAGMENT);
    }*/


    /**
     * Method initialize all fragments as tabfragments and other fragments used
     * Sends then reference to controller for each fragment
     */
    private void initFragments(){
        mainViewFragment = new MainViewFragment();
        searchFramgent = new SearchFramgent();
        settingsFragment = new SettingsFragment();
        overViewFragment = new OverViewFragment();
        incomeFragment = new IncomeFragment();
        expenditureFragment = new ExpenditureFragment();

        mainViewFragment.setController(this);
        searchFramgent.setController(this);
        settingsFragment.setController(this);
        expenditureFragment.setController(this);
        incomeFragment.setController(this);
        overViewFragment.setController(this);
        searchFramgent.setController(this);

        /*main.getSupportFragmentManager().beginTransaction().add(mainViewFragment, MAINVIEWFRAGMENT).commit();
        main.getSupportFragmentManager().beginTransaction().add(searchFramgent, SEARCHFRAGMENT).commit();
        main.getSupportFragmentManager().beginTransaction().add(settingsFragment, SETTINGSFRAGMENT).commit();
        main.getSupportFragmentManager().beginTransaction().add(expenditureFragment, EXPENDITUREFRAGMENT).commit();
        main.getSupportFragmentManager().beginTransaction().add(incomeFragment, INCOMEFRAGMENT).commit();*/

        main.setFragment(mainViewFragment,MAINVIEWFRAGMENT);
        main.setUserInfo(user[0].getName(),user[0].getEmail());
    }

    /**
     * Initiates the database
     */
    private void dataBaseInit(){
        userDbHelper = new UserDBHelper(main);
        incomeDBHelper = new IncomeDBHelper(main);
        expenditureDBHelper = new ExpenditureDBHelper(main);
        barCodeDBHelper = new BarCodeDBHelper(main);
    }

    /**
     * Method used for swapping the fragments in the navigation drawer
     * @param id the id of the fragment that should be swapped to
     */
    public void switchFragment(int id) {

        switch (id){
            case R.id.scan:
                SearchFramgent barcode = new SearchFramgent();
                barcode.setController(this);
                main.setFragment(barcode,SEARCHFRAGMENT);
                break;
            case R.id.settings:
                SettingsFragment set = new SettingsFragment();
                set.setController(this);
                main.setFragment(set,SETTINGSFRAGMENT);

                break;
            case R.id.home:
                MainViewFragment mainViewFragment = new MainViewFragment();
                mainViewFragment.setController(this);
                main.setFragment(mainViewFragment,MAINVIEWFRAGMENT);
                break;
        }
    }

    /**
     * This method shows a toast to the user
     * @param str string that is wanted to be displayed
     */
    public void chartInfo(String str) {
        Toast.makeText(main.getApplicationContext(),str,Toast.LENGTH_SHORT).show();
    }

    /**
     * Method used when user logs out
     * Finish the current activity and start login again
     */
    public void signOut() {
        Intent intent = new Intent(main.getBaseContext(), LoginActivity.class);
        main.startActivity(intent);
        main.finish();
    }

    /**
     * Method update the logged in users info with users info before in the database
     * @param newUser user objecrt that need to be updated with
     */
    public void updateUser(User newUser) {
        SQLiteDatabase db = userDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UserDBHelper.COLUMN_NAME, newUser.getName());
        values.put(UserDBHelper.COLUMN_SURNAME, newUser.getSurname());
        values.put(UserDBHelper.COLUMN_EMAIL, newUser.getEmail());
        values.put(UserDBHelper.COLUMN_PASS, newUser.getPass());
        Log.d("Updated", newUser.getId() + "=" + UserDBHelper.COLUMN_ID);
        db.update(UserDBHelper.TABLE_NAME, values, UserDBHelper.COLUMN_ID + "=" + newUser.getId(), null);
    }

    /**
     * Method is used for adding an income to the database
     * @param income object of the income that was created
     */
    public void addIncome(Income income) {
        SQLiteDatabase db = incomeDBHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        //values.put(IncomeDBHelper.COLUMN_ID, income.getId());
        values.put(IncomeDBHelper.COLUMN_CATEGORY, income.getCategory());
        values.put(IncomeDBHelper.COLUMN_TIME, income.getTime());
        values.put(IncomeDBHelper.COLUMN_DATE, income.getDate());
        values.put(IncomeDBHelper.COLUMN_EARN, income.getEarn());
        values.put(IncomeDBHelper.COLUMN_TITLE, income.getTitle());
        values.put(IncomeDBHelper.COLUMN_USERID, user[0].getId());
        db.insert(IncomeDBHelper.TABLE_NAME, "", values); // Prevent from -1 exception
        db.close();

    }

    /**
     * Method is used for adding an expanditure to the database
     * @param expenditure object of the expanditure that was created
     */
    public void addExpanditure(Expenditure expenditure) {
        SQLiteDatabase db = expenditureDBHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        //values.put(ExpenditureDBHelper.COLUMN_ID, expenditure.getId());
        values.put(ExpenditureDBHelper.COLUMN_CATEGORY, expenditure.getCategory());
        values.put(ExpenditureDBHelper.COLUMN_TIME, expenditure.getTime());
        values.put(ExpenditureDBHelper.COLUMN_DATE, expenditure.getDate());
        values.put(ExpenditureDBHelper.COLUMN_COST, expenditure.getCost());
        values.put(ExpenditureDBHelper.COLUMN_TITLE, expenditure.getTitle());
        values.put(ExpenditureDBHelper.COLUMN_USERID, user[0].getId());
        db.insert(ExpenditureDBHelper.TABLE_NAME, "", values); // Prevent from -1 exception
        db.close();

    }

    /**
     * Method is used for storing the barcode info in the database,
     * Makes future scanning of same object return information directly without having to input again
     * @param category category of the item
     * @param cost price of the item
     * @param itemId itemid of the item
     * @param itemName name of the item
     */
    public void addBarcode(String category, String cost, String itemId, String itemName){
        SQLiteDatabase db = barCodeDBHelper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(BarCodeDBHelper.COLUMN_CATEGORY,category);
        values.put(BarCodeDBHelper.COLUMN_COST,cost);
        values.put(BarCodeDBHelper.COLUMN_ITEMID,itemId);
        values.put(BarCodeDBHelper.COLUMN_ITEMNAME,itemName);
        db.insert(BarCodeDBHelper.TABLE_NAME, "", values); // Prevent from -1 exception
        db.close();
    }

    /**
     * Method is used to search the database if scanning have been made before
     * Then returns information
     * @param itemId itemid that is asking for
     * @return the iteminformation if available
     */
    public BarCode[] getBarcodeItem(String itemId){
        int idIndex, categoryIndex,costIndex,itemNameIndex;
        BarCode[] barCode = null;
        String[] selectionArgs = { itemId};
        SQLiteDatabase db = barCodeDBHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + BarCodeDBHelper.TABLE_NAME + " where "+BarCodeDBHelper.COLUMN_ITEMID + " = ? ",selectionArgs);
        barCode  = new BarCode[cursor.getCount()];
        idIndex = cursor.getColumnIndex(BarCodeDBHelper.COLUMN_ID);
        //itemIdIndex = cursor.getColumnIndex(BarCodeDBHelper.COLUMN_ITEMID);
        categoryIndex = cursor.getColumnIndex(BarCodeDBHelper.COLUMN_CATEGORY);
        costIndex = cursor.getColumnIndex(BarCodeDBHelper.COLUMN_COST);
        itemNameIndex = cursor.getColumnIndex(BarCodeDBHelper.COLUMN_ITEMNAME);
        for (int i =0; i<barCode.length;i++){
            cursor.moveToPosition(i);
            barCode[i] = new BarCode(
                    cursor.getString(categoryIndex),
                    cursor.getString(idIndex),
                    cursor.getString(costIndex),
                    cursor.getString(itemNameIndex));
        }
        cursor.close();
        db.close();
        return barCode;
    }

    /**
     * Method for getting all incomes for the specific user that is logged in between 2 dates
     * @param from date that is asked from
     * @param to date that is asked to
     * @return all incomes between these dates if available
     */
    public Income[] getIncomeBetween(String from,String to){
        int idIndex, categoryIndex, timeIndex,dateIndex,earnIndex,titleIndex;
        Income[] income = null;
        String[] selectionArgs = {String.valueOf(getId())};
        SQLiteDatabase db = incomeDBHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + IncomeDBHelper.TABLE_NAME + " where "+ IncomeDBHelper.COLUMN_USERID + " = ? " + " and " + IncomeDBHelper.COLUMN_DATE + " between " + "'"+from+"'" + " and " + "'"+to+"'",selectionArgs);
        income  = new Income[cursor.getCount()];
        idIndex = cursor.getColumnIndex(IncomeDBHelper.COLUMN_ID);
        categoryIndex = cursor.getColumnIndex(IncomeDBHelper.COLUMN_CATEGORY);
        timeIndex = cursor.getColumnIndex(IncomeDBHelper.COLUMN_TIME);
        dateIndex = cursor.getColumnIndex(IncomeDBHelper.COLUMN_DATE);
        earnIndex = cursor.getColumnIndex(IncomeDBHelper.COLUMN_EARN);
        titleIndex = cursor.getColumnIndex(IncomeDBHelper.COLUMN_TITLE);
        for (int i = 0; i<income.length;i++){
            cursor.moveToPosition(i);
            income[i] = new Income(
                    cursor.getInt(idIndex),
                    cursor.getString(categoryIndex),
                    cursor.getString(timeIndex),
                    cursor.getString(dateIndex),
                    cursor.getString(earnIndex),
                    cursor.getString(titleIndex));
        }
        db.close();
        cursor.close();
        return income;

    }
    /**
     * Method for getting all expenditure for the specific user that is logged in between 2 dates
     * @param from date that is asked from
     * @param to date that is asked to
     * @return all expenditures between these dates if available
     */
    public Expenditure[] getExpanditureBetween(String from,String to){
        int idIndex, categoryIndex, timeIndex,dateIndex,costIndex,titleIndex;
        Expenditure[] expenditure = null;
        String[] selectionArgs = {String.valueOf(getId())};
        SQLiteDatabase db = expenditureDBHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + ExpenditureDBHelper.TABLE_NAME + " where "+ ExpenditureDBHelper.COLUMN_USERID + " = ? " + " and " + ExpenditureDBHelper.COLUMN_DATE + " between " + "'"+from+"'" + " and " + "'"+to+"'",selectionArgs);
        expenditure  = new Expenditure[cursor.getCount()];
        idIndex = cursor.getColumnIndex(ExpenditureDBHelper.COLUMN_ID);
        categoryIndex = cursor.getColumnIndex(ExpenditureDBHelper.COLUMN_CATEGORY);
        timeIndex = cursor.getColumnIndex(ExpenditureDBHelper.COLUMN_TIME);
        dateIndex = cursor.getColumnIndex(ExpenditureDBHelper.COLUMN_DATE);
        costIndex = cursor.getColumnIndex(ExpenditureDBHelper.COLUMN_COST);
        titleIndex = cursor.getColumnIndex(ExpenditureDBHelper.COLUMN_TITLE);
        for (int i = 0; i<expenditure.length;i++){
            cursor.moveToPosition(i);
            expenditure[i] = new Expenditure(
                    cursor.getInt(idIndex),
                    cursor.getString(categoryIndex),
                    cursor.getString(timeIndex),
                    cursor.getString(dateIndex),
                    cursor.getString(costIndex),
                    cursor.getString(titleIndex));
        }
        db.close();
        cursor.close();
        return expenditure;

    }

    /**
     * This method fetches all incomes from the database for the specific user that is logged in
     * @return incomes for the user
     */
    public Income[] getAllIncomes(){
        int idIndex, categoryIndex, timeIndex,dateIndex,earnIndex,titleIndex;
        Income[] income = null;
        String[] selectionArgs = {String.valueOf(getId())};
        SQLiteDatabase db = incomeDBHelper.getReadableDatabase();
        Cursor  cursor = db.rawQuery("select * from " + IncomeDBHelper.TABLE_NAME+ " where "+ ExpenditureDBHelper.COLUMN_USERID+ " = ?" , selectionArgs);
        income = new Income[cursor.getCount()];
        idIndex = cursor.getColumnIndex(IncomeDBHelper.COLUMN_ID);
        categoryIndex = cursor.getColumnIndex(IncomeDBHelper.COLUMN_CATEGORY);
        timeIndex = cursor.getColumnIndex(IncomeDBHelper.COLUMN_TIME);
        dateIndex = cursor.getColumnIndex(IncomeDBHelper.COLUMN_DATE);
        earnIndex = cursor.getColumnIndex(IncomeDBHelper.COLUMN_EARN);
        titleIndex = cursor.getColumnIndex(IncomeDBHelper.COLUMN_TITLE);

        for (int i = 0; i < income.length; i++) {
            cursor.moveToPosition(i);
            income[i] = new Income(
                    cursor.getInt(idIndex),
                    cursor.getString(categoryIndex),
                    cursor.getString(timeIndex),
                    cursor.getString(dateIndex),
                    cursor.getString(earnIndex),
                    cursor.getString(titleIndex));
        }
        db.close();
        cursor.close();
        return income;
    }
    /**
     * This method fetches all expenditures from the database for the specific user that is logged in
     * @return expenditures for the user
     */
    public Expenditure[] getAllExpenditures(){
        int idIndex, categoryIndex, timeIndex,dateIndex,costIndex,titleIndex;
        Expenditure[] expenditures = null;
        String[] selectionArgs = {String.valueOf(getId())};
        SQLiteDatabase db = expenditureDBHelper.getReadableDatabase();
        Cursor  cursor = db.rawQuery("select * from " + ExpenditureDBHelper.TABLE_NAME + " where "+ ExpenditureDBHelper.COLUMN_USERID+ " = ?" , selectionArgs );
        expenditures = new Expenditure[cursor.getCount()];
        idIndex = cursor.getColumnIndex(ExpenditureDBHelper.COLUMN_ID);
        categoryIndex = cursor.getColumnIndex(ExpenditureDBHelper.COLUMN_CATEGORY);
        timeIndex = cursor.getColumnIndex(ExpenditureDBHelper.COLUMN_TIME);
        dateIndex = cursor.getColumnIndex(ExpenditureDBHelper.COLUMN_DATE);
        costIndex = cursor.getColumnIndex(ExpenditureDBHelper.COLUMN_COST);
        titleIndex = cursor.getColumnIndex(ExpenditureDBHelper.COLUMN_TITLE);
        for (int i = 0; i < expenditures.length; i++) {
            cursor.moveToPosition(i);
            expenditures[i] = new Expenditure(
                    cursor.getInt(idIndex),
                    cursor.getString(categoryIndex),
                    cursor.getString(timeIndex),
                    cursor.getString(dateIndex),
                    cursor.getString(costIndex),
                    cursor.getString(titleIndex));
        }
        db.close();
        cursor.close();
        return expenditures;
    }


    /**
     * This method is used for updating the user information when updating it in the settingsfragment
     * @param name the new name that is wanted to be updated
     * @param surname the new surname that is wanted to be updated
     * @param pass1 old password for securing that user knows
     * @param pass2 new password that is wanted
     * @return the result of the changeprocess
     */
    public boolean UpdateUserSettings(String name, String surname, String pass1, String pass2) {
        if (pass1.equals(user[0].getPass())){
            User userInfo = new User(name,surname,user[0].getEmail(),pass2);
            user[0].setPass(pass2);
            user[0].setName(name);
            user[0].setSurname(surname);
            userInfo.setPass(pass2);
            userInfo.setSurname(surname);
            userInfo.setName(name);
            userInfo.setEmail(user[0].getEmail());
            userInfo.setId(user[0].getId());
            updateUser(userInfo);
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Method is used for filling information of the user on the overview and drawer
     * @param name the name of the user that is logged in
     * @param surName the surname of the user that is logged in
     * @param email the email of the user that is logged in
     */
    public void fillInformation(EditText name, EditText surName, EditText email) {
        name.setText(user[0].getName());
        surName.setText(user[0].getSurname());
        email.setText(user[0].getEmail());
    }

    /**
     * This method shows a dialog when the user successfully scanned an object/showing the addincome/expenditure
     * @param inputType the type of input, income/expenditure
     * @param contents used for the barcode only if id available
     */
    public void showDialog(Boolean inputType, String contents) {
        BarCode[] barCode = new BarCode[0];
        if (contents==null){

        }
        else{
             barCode = getBarcodeItem(contents);
        }
        
        Boolean isAvailable = false;
        if (barCode.length>=1){
            isAvailable=true;
        }
       showDialog(inputType,barCode,contents,isAvailable);
    }

    /**
     * This method sets the welcome information at the overview fragment
     * @param tvCardName the name of the user
     */
    public void setWelcomeName(TextView tvCardName) {
        tvCardName.setText(user[0].getName() + " " + user[0].getSurname());
    }

    /**
     * This method updates the piechart on the overview fragment
     * @param entries the entries for the chart
     * @param tvBalance reference to the textview of balance
     * @param tvSpent reference to the textview of spent
     * @param tvTot reference to the textview of total
     * @param labels arraylist containing the labels(category)
     */
    public void updateChartView(ArrayList<PieEntry> entries, TextView tvBalance, TextView tvSpent, TextView tvTot, ArrayList<String> labels) {
        double incomeTot = 0;
        double expenditureTot = 0;
        Income[] income = getAllIncomes();
        Float [] categorySum = {0f,0f,0f,0f,0f};

        for (int i =0; i<income.length; i++){
            incomeTot += Double.parseDouble(income[i].getEarn());
        }
        tvTot.setText(String.valueOf(String.format("%.2f",incomeTot)));
        Expenditure[] exp = getAllExpenditures();

        for (int i =0;i<exp.length;i++){
            expenditureTot += Double.parseDouble(exp[i].getCost());
            switch (exp[i].getCategory()){
                case "Food":
                    categorySum[0]+=(Float.valueOf(exp[i].getCost()));
                    break;
                case "Leisure":
                    categorySum[1]+=(Float.valueOf(exp[i].getCost()));
                    break;
                case "Travel":
                    categorySum[2]+=(Float.valueOf(exp[i].getCost()));
                    break;
                case "Accommodation":
                    categorySum[3]+=(Float.valueOf(exp[i].getCost()));
                    break;
                case "Other":
                    categorySum[4]+=(Float.valueOf(exp[i].getCost()));
                    break;
            }
        }
        tvSpent.setText(String.valueOf(String.format("%.2f",expenditureTot)));
        tvBalance.setText(String.valueOf(String.format("%.2f",incomeTot-expenditureTot)));
        if (incomeTot-expenditureTot<0){
            tvBalance.setTextColor(Color.parseColor("#FF0000"));
        }
        else {
            tvBalance.setTextColor(Color.parseColor("#228B22"));
        }
        for (int i = 0; i < categorySum.length; i++) {
            entries.add(new PieEntry(categorySum[i], labels.get(i)));
        }
    }

    /**
     * This method shows the datepicker when searching
     * @param etFrom the date that is wanted to search from
     */
    public void showDatePickFrom(final EditText etFrom) {
        final Calendar cal = Calendar.getInstance();
        int mY = cal.get(Calendar.YEAR);
        int mM = cal.get(Calendar.MONTH);
        int mD = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this.main,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int month, int day) {
                        if((month+1) < 10 && day <10){

                            etFrom.setText(year+"-"+"0"+(month+1)+"-"+"0"+day);
                        }
                        else if(day < 10){
                            etFrom.setText(year+"-"+(month+1)+"-"+"0"+day);
                        }
                        else if ((month+1) <10){
                            etFrom.setText(year+"-"+"0"+(month+1)+"-"+day);
                        }
                        else {
                            etFrom.setText(year + "-" + (month+1)+ "-" + day);
                        }

                    }
                }, mY, mM, mD);
        datePickerDialog.show();
    }

    /**
     * This method shows the datepicker when searching,same as above
     * @param etTo the date that is wanted to search to
     */
    public void showDatePickTo(final EditText etTo) {
        final Calendar cal = Calendar.getInstance();
        int mY = cal.get(Calendar.YEAR);
        int mM = cal.get(Calendar.MONTH);
        int mD = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this.main,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int month, int day) {
                        if((month+1) < 10 && day <10){

                            dateFrom=String.valueOf(year+"-"+"0"+(month+1)+"-"+"0"+day);
                        }
                        else if(day < 10){
                            dateFrom=String.valueOf(year+"-"+(month+1)+"-"+"0"+day);
                        }
                        else if ((month+1) <10){
                            dateFrom=String.valueOf(year+"-"+"0"+(month+1)+"-"+day);
                        }
                        else {
                            dateFrom = String.valueOf(year + "-" + (month+1) + "-" + day);
                        }
                        etTo.setText(dateFrom);
                    }
                }, mY, mM, mD);
        datePickerDialog.show();
    }

    /**
     * Method returns the name of logged in user
     * @return name of the logged in user
     */
    public String getName() {
        return user[0].getName();
    }

    /**
     * Method returns the email of logged in user
     * @return emailof the logged in user
     */
    public String getEmail() {
        return user[0].getEmail();
    }

    /**
     * Method sets the header in the navdrawer with the username
     */
    public void setHeaderInfo(){
        main.setUserInfo(user[0].getName(),user[0].getEmail());
    }

    /**
     * Method for getting the id of the user, used for updating information database
     * @return id of the user
     */
    private int getId(){
        return user[0].getId();
    }

    /**
     * Method for showing the dialog when user i.ex wants to add income/expenditure
     * @param input if expenditure or income
     * @param barcode barcode if the it was scanned
     * @param itemId id of item
     * @param isAvailable boolean for if scanned barcode item or not
     */
    public void showDialog(final Boolean input, final BarCode[] barcode, final String itemId, Boolean isAvailable) {
        final int inputType;
        String[] items = {"Food","Leisure","Travel","Accommodation","Other"};
        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(this.main);
        if (input){
            inputType = R.layout.inputincome;
        }
        else{
            inputType = R.layout.input;
        }
        final View mView = layoutInflaterAndroid.inflate(inputType, null);
        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(this.main);
        alertDialogBuilderUserInput.setView(mView);
        final Income [] income = new Income[0];
        final Button btnDate = (Button) mView.findViewById(R.id.btnDate);
        final Button btnTime = (Button) mView.findViewById(R.id.btnTime);
        final EditText etDate = (EditText) mView.findViewById(R.id.etDate);
        final EditText etTime = (EditText) mView.findViewById(R.id.etTime);
        final EditText etCost = (EditText) mView.findViewById(R.id.etCost);
        final Spinner spinner = (Spinner) mView.findViewById(R.id.spinner);
        final EditText etDescription  = (EditText) mView.findViewById(R.id.etDescription);
        final String[] spinnerSelected = new String[1];
        boolean newItem = false;
        if (isAvailable){
            etCost.setText(barcode[0].getCost());
            etDescription.setText(barcode[0].getItemName());
            for (int i=0;i<items.length;i++){
                if (items[i].equals(barcode[0].getCategory())){
                    spinner.setSelection(i);
                    break;
                }
            }
        }
        else if(itemId!=null){
            newItem=true;
        }

        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Calendar cal = Calendar.getInstance();
                mY = cal.get(Calendar.YEAR);
                mM = cal.get(Calendar.MONTH);
                mD = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(view.getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int month, int day) {
                                if((month+1) < 10 && day <10){
                                    etDate.setText(year+"-"+"0"+(month+1)+"-"+"0"+day);
                                }
                                else if(day < 10){
                                    etDate.setText(year+"-"+(month+1)+"-"+"0"+day);
                                }
                                else if ((month+1) <10){
                                    etDate.setText(year+"-"+"0"+(month+1)+"-"+day);
                                }
                                else {
                                    etDate.setText(year + "-" + (month+1) + "-" + day);
                                }

                            }
                        }, mY, mM, mD);
                datePickerDialog.show();
            }
        });
        btnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Calendar cal = Calendar.getInstance();
                mH = cal.get(Calendar.HOUR_OF_DAY);
                mMin = cal.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(view.getContext(),new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hour, int minute) {

                        if (hour<10&&minute<10){
                            etTime.setText("0"+hour + ":" +"0"+ minute);
                        }
                        else if(hour<10){
                            etTime.setText("0"+hour+":"+minute);
                        }
                        else if(minute<10){
                            etTime.setText(hour+":"+"0"+minute);
                        }
                        else{
                            etTime.setText(hour+":"+minute);
                        }

                    }
                }, mH, mMin, false);
                timePickerDialog.show();
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            /**
             *Listener of the spinner inside the dialog, when selecting category
             */
            public void onItemSelected(
                    AdapterView<?> adapterView, View view,
                    int i, long l) {
                spinnerSelected[0] = spinner.getItemAtPosition(i).toString();
            }

            public void onNothingSelected(
                    AdapterView<?> adapterView) {
            }
        });

        final boolean finalNewItem = newItem;
        alertDialogBuilderUserInput
                .setTitle("Enter your following details")
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        if (etDate.getText().toString().length()==0||etTime.getText().toString().length()==0 || etCost.getText().toString().length()==0||etDescription.getText().toString().length()==0){
                            chartInfo("Some fields were empty try again");

                        }
                        else{
                            if (input){
                                Income income = new Income(spinnerSelected[0],etTime.getText().toString(),etDate.getText().toString(),etCost.getText().toString(),etDescription.getText().toString());
                                addIncome(income);
                                incomeFragment.fillRecyclerView(getAllIncomes());
                            }
                            else{
                                Expenditure expanditure = new Expenditure(spinnerSelected[0],etTime.getText().toString(),etDate.getText().toString(),etCost.getText().toString(),etDescription.getText().toString());
                                if (finalNewItem){
                                    addBarcode(spinnerSelected[0],etCost.getText().toString(),itemId,etDescription.getText().toString());
                                }
                                addExpanditure(expanditure);
                                expenditureFragment.fillRecyclerView(getAllExpenditures());
                            }
                        }
                    }
                })

                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogBox, int id) {

                                dialogBox.cancel();
                            }
                        });

        AlertDialog alertDialogAndroid = alertDialogBuilderUserInput.create();
        alertDialogAndroid.show();
    }


    /**
     * This method pops up a dialog with the info of the object when clicked on it
     * @param inputType never used
     * @param dates the date when set
     * @param times the time when set
     * @param costs the price of item
     * @param categories the category of the information provided
     * @param titles the name of the item
     */
    public void showDescriptionDialog(boolean inputType, TextView dates, TextView times, TextView costs, TextView categories, TextView titles) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this.main);
        alert.setTitle("Information:");
        alert.setMessage("Item Description: "+titles.getText().toString());

        final TextView time = new TextView (this.main);
        final TextView date = new TextView (this.main);
        final TextView cost = new TextView (this.main);
        final TextView category = new TextView(this.main);

        LinearLayout layout = new LinearLayout(this.main);
        layout.setOrientation(LinearLayout.VERTICAL);

        layout.addView(time);
        layout.addView(date);
        layout.addView(cost);
        layout.addView(category);

        time.setTextSize(20);
        date.setTextSize(20);
        cost.setTextSize(20);
        category.setTextSize(20);

        alert.setView(layout);


        time.setText("  Time: "+times.getText().toString());
        date.setText("  Date: "+dates.getText().toString());
        cost.setText("  Amount: "+costs.getText().toString());
        category.setText("  Category: "+categories.getText().toString());

        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

            }
        });
        alert.show();
    }

    /**
     * Method is used for setting the dates again after a rotation
     * @param etFrom the editText reference
     * @param etTo the editText reference
     * @param from date that was set from
     * @param to date that was set to
     */
    public void setEditText(EditText etFrom,EditText etTo,String from,String to){
        etFrom.setText(from);
        etTo.setText(to);
    }

    /**
     * Method for setting the instance to incomefragment
     * @param instance instance to the incomefragment
     */
    public void setInstance(IncomeFragment instance) {
        this.incomeFragment = instance;
    }
}
