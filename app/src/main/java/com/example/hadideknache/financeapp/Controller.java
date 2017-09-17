package com.example.hadideknache.financeapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Parcelable;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.github.mikephil.charting.data.PieEntry;

/**
 * Created by hadideknache on 2017-09-13.
 */

public class Controller {
    MainActivity main;
    MainViewFragment mainViewFragment;
    SearchFramgent searchFramgent;
    SettingsFragment settingsFragment;
    private User[] user;
    private UserDBHelper dbHelper;
    private IncomeDBHelper incomeDBHelper;
    private ExpenditureDBHelper expenditureDBHelper;
    private Parcelable[] parcelables;

    public Controller(MainActivity mainActivity,Intent intent) {
        this.main=mainActivity;
        parcelables = intent.getParcelableArrayExtra(LoginController.USERS);
        user = new User[parcelables.length];
        for(int i=0; i<user.length; i++) {
            user[i] = (User)parcelables[i];
        }

        dbHelper = new UserDBHelper(main);
        incomeDBHelper = new IncomeDBHelper(main);
        expenditureDBHelper = new ExpenditureDBHelper(main);

        mainViewFragment = new MainViewFragment();
        searchFramgent = new SearchFramgent();
        settingsFragment = new SettingsFragment();
        mainViewFragment.setController(this, main.getSupportFragmentManager());
        searchFramgent.setController(this);
        settingsFragment.setController(this);
        main.setFragment(mainViewFragment);
        main.setUserInfo(user[0].getName(),user[0].getEmail());
    }



    public void switchFragment(int id) {
        switch (id){
            case R.id.search:
                main.setFragment(searchFramgent);
                break;
            case R.id.settings:
                main.setFragment(settingsFragment);
                break;
            case R.id.home:
                main.setFragment(mainViewFragment);
                break;
        }
    }

    public void chartInfo(String str) {
        Toast.makeText(main.getApplicationContext(),str,Toast.LENGTH_SHORT).show();
    }

    public void signOut() {
        Intent intent = new Intent(main.getBaseContext(), LoginActivity.class);
        main.startActivity(intent);
        main.finish();

    }
    public void updateUser(User user) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UserDBHelper.COLUMN_NAME, user.getName());
        values.put(UserDBHelper.COLUMN_SURNAME, user.getSurname());
        values.put(UserDBHelper.COLUMN_EMAIL, user.getEmail());
        values.put(UserDBHelper.COLUMN_PASS, user.getPass());
        Log.d("Updated", user.getId() + "=" + UserDBHelper.COLUMN_ID);
        db.update(UserDBHelper.TABLE_NAME, values, UserDBHelper.COLUMN_ID + "=" + user.getId(), null);
    }

    public boolean UpdateUserSettings(String name, String surname, String pass1, String pass2) {
        if (pass1.equals(user[0].getPass())){
            User userInfo = new User(name,surname,user[0].getEmail(),pass2);
            updateUser(userInfo);
            return true;
        }
        else{
            return false;
        }
    }

    public void fillInformation(EditText name, EditText surName, EditText email) {
        name.setText(user[0].getName());
        surName.setText(user[0].getSurname());
        email.setText(user[0].getEmail());
    }
}
