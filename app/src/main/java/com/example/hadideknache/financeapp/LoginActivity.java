package com.example.hadideknache.financeapp;

/**
 * Created by hadideknache on 2017-09-13.
 */

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;



public class LoginActivity extends AppCompatActivity {
    LoginController controller;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init() {
        controller = new LoginController(this);
    }
    public void setFragment(Fragment fragment){
        FragmentManager fragmanager = getFragmentManager();
        FragmentTransaction transaction = fragmanager.beginTransaction();
        transaction.replace(R.id.startFrame, fragment);
        transaction.commit();
    }


}
