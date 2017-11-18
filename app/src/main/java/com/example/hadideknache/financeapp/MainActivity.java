package com.example.hadideknache.financeapp;


import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;

import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import android.widget.Button;

import android.widget.TextView;

import com.example.hadideknache.financeapp.Fragments.ExpenditureFragment;
import com.example.hadideknache.financeapp.Fragments.IncomeFragment;
import com.example.hadideknache.financeapp.Fragments.MainViewFragment;
import com.example.hadideknache.financeapp.Fragments.OverViewFragment;
import com.example.hadideknache.financeapp.Fragments.SearchFramgent;
import com.example.hadideknache.financeapp.Fragments.SettingsFragment;




public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    //private String[] item;
    private Controller controller;
    private DrawerLayout drawerLayout;
    private Button btnSignOut;
    private MainViewFragment mainViewFragment;
    private SearchFramgent searchFramgent;
    private SettingsFragment settingsFragment;
    private OverViewFragment overViewFragment;
    private IncomeFragment incomeFragment;
    private ExpenditureFragment expenditureFragment;
    private TextView name,email;
    private String emails,names;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("name",name.getText().toString());
        outState.putString("email",email.getText().toString());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        names=savedInstanceState.getString("name");
        emails=savedInstanceState.getString("email");
        setUserInfo(names,emails);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        regButton();

        if (savedInstanceState == null) {
            controller = new Controller(this,getIntent());
        }
        else {
            controller = new Controller(this,getIntent(),true);

        }
        setUserInfo(controller.getName(),controller.getEmail());

    }
    private void init(){
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        btnSignOut = (Button) findViewById(R.id.logout);


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View navHeader = navigationView.getHeaderView(0);

        name = (TextView) navHeader.findViewById(R.id.user);
        email = (TextView) navHeader.findViewById(R.id.email);
    }

    private void regButton() {
        ButtonListener listener = new ButtonListener();
        btnSignOut.setOnClickListener(listener);
    }


    public void setFragment(android.support.v4.app.Fragment fragment, String tag){
        android.support.v4.app.FragmentManager fragmanager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaction = fragmanager.beginTransaction();
        transaction.replace(R.id.frameLayout, fragment);
        transaction.commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.home:
                controller.switchFragment(id);
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.scan:
                controller.switchFragment(id);
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.settings:
                controller.switchFragment(id);
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    public void setUserInfo(String username, String usermail) {
        name.setText(username);
        email.setText(usermail);
    }


    private class ButtonListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.logout:
                    controller.signOut();
                    break;
            }
        }
    }



}
