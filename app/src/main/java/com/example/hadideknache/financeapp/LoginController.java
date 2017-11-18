package com.example.hadideknache.financeapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hadideknache.financeapp.Fragments.LoginFragment;
import com.example.hadideknache.financeapp.Fragments.SignUpFragment;

import java.util.ArrayList;

/**
 * This class handles the logic for the login/signup process
 *
 * Created by Hadi Deknache on 2017-09-13.
 */

public class LoginController {
    private LoginActivity ui;
    private LoginFragment loginFragment;
    private SignUpFragment signUpFragment;
    private String email="",pass="";
    public final static String USERS = "com.example.hadideknache.financeapp.USERS";
    private UserDBHelper dbHelper;
    public Boolean loginState=true;
    private ArrayList<String> save;

    /**
     * This is the constructor of controller,
     * Checks if process already earlier was created and started to restore
     * else creates fragments and initiate them
     * @param loginActivity instance to the loginActivty
     * @param savedInstanceState instance to bundle for accessing saved information i.ex rotation
     */
    public LoginController(LoginActivity loginActivity, Bundle savedInstanceState) {
        this.ui=loginActivity;
        dbHelper = new UserDBHelper(loginActivity);
        save = new ArrayList<>();

        if (savedInstanceState!=null){
            loginState = savedInstanceState.getBoolean("state");
            signUpFragment = ui.getFragmentSignUp();
            loginFragment = ui.getFragmentLogin();
            signUpFragment.setController(this);
            loginFragment.setController(this);

            if (loginState){
                ui.switchFragment(loginFragment,"loginFragment",false,true);
                loginFragment.setController(this);
            }
            else{
                ui.switchFragment(signUpFragment,"signUpFragment",false,true);
                signUpFragment.setController(this);
            }

        }
        else{
            loginFragment = new LoginFragment();
            signUpFragment = new SignUpFragment();
            ui.switchFragment(signUpFragment,"signUpFragment",false,false);
            ui.switchFragment(loginFragment,"loginFragment",false,false);
            loginState=true;
            signUpFragment.setController(this);
            loginFragment.setController(this);
        }


    }

    /**
     * Method for prepering the loginprocess when successfully logging in
     * @param user instance to the userobject that is forwarded
     */
    public void login(User[] user) {
        Intent intent = new Intent(ui.getBaseContext(), MainActivity.class);
        intent.putExtra(USERS,user);
        ui.startActivity(intent);
        ui.finish();
    }

    /**
     * Method is used for displaying a toast when user successfully created a login
     */
    public void success() {
        Toast.makeText(ui.getApplicationContext(),"Account successfully created!",Toast.LENGTH_SHORT).show();
    }

    /**
     * Method swaps the current fragment to other fragment
     * @param fragment instance of the fragment that is being swapped to
     */
    public void switchFragment(String fragment) {
        switch (fragment){
            case "Login":
                loginState=true;
                ui.switchFragment(loginFragment,"loginFragment",false,false);
                break;
            case "Register":
                loginState=false;
                ui.switchFragment(signUpFragment,"signUpFragment",true,false);
                break;
        }
    }

    /**
     * Method for adding(signing up) a user and adding the user to the database
     * @param user userobject containing the information of the user
     */
    public void addUser(User user) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UserDBHelper.COLUMN_NAME, user.getName());
        values.put(UserDBHelper.COLUMN_SURNAME, user.getSurname());
        values.put(UserDBHelper.COLUMN_EMAIL, user.getEmail());
        values.put(UserDBHelper.COLUMN_PASS, user.getPass());
        db.insert(UserDBHelper.TABLE_NAME, "", values); // Prevent from -1 exception
        db.close();
    }

    /**
     * Method checks if the usermail/password match the one that is registered to the database
     * @param etEmailReg the email that user typed for login
     * @param etPassReg the password that user typed for login
     */
    public void checkLogin(EditText etEmailReg, EditText etPassReg) {
        email = etEmailReg.getText().toString().toLowerCase();
        pass  = etPassReg.getText().toString();
        User[] user = null;

        String[] selectionArgs = {email,pass};
        String selectString = "SELECT * FROM " + UserDBHelper.TABLE_NAME + " WHERE " + UserDBHelper.COLUMN_EMAIL + " = ?" + " AND " + UserDBHelper.COLUMN_PASS + " = ?" ;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectString,selectionArgs);
        int cursorCount = cursor.getCount();
        user = new User[cursorCount];
        if (cursorCount > 0) {
            cursor.moveToFirst();
            int id = cursor.getInt(cursor.getColumnIndex(UserDBHelper.COLUMN_ID));
            String name = cursor.getString(cursor.getColumnIndex(UserDBHelper.COLUMN_NAME));
            String surname = cursor.getString(cursor.getColumnIndex(UserDBHelper.COLUMN_SURNAME));
            String email = cursor.getString(cursor.getColumnIndex(UserDBHelper.COLUMN_EMAIL));
            String pass = cursor.getString(cursor.getColumnIndex(UserDBHelper.COLUMN_PASS));

            user[0] = new User(id,name,surname,email,pass);
            login(user);
        }
        else {
            etEmailReg.setError("Check your email if correct!");
            etPassReg.setError("Check your password if correct!");
        }
        db.close();
        cursor.close();
    }

    /**
     * Method for checking if existing mail already is registered
     * @param mail the mail that is wanted to be registred
     * @return a boolean if available or not
     */
    public boolean checkUser(String mail) {
        String[] selectionArgs = {mail};
        String selectionString = "SELECT * FROM " + UserDBHelper.TABLE_NAME + " WHERE " + UserDBHelper.COLUMN_EMAIL + " = ?";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectionString,selectionArgs);

        if (cursor.getCount() > 0) {
            cursor.close();
            db.close();
            return true;
        }
        else{
            cursor.close();
            db.close();
            return false;
        }

    }

    /**
     * Method used for saving the information i.ex when phone rotated
     * @param outState bundle to save information to
     */
    public void onSaveInstanceState(Bundle outState) {
        if (loginState){
            save = loginFragment.getSaveInformation();
            outState.putStringArrayList("saveLogin",save);
        }
        else{
            save = signUpFragment.getSaveInformation();
            outState.putStringArrayList("saveLogin",save);
        }

    }


}