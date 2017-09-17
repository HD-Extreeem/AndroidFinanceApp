package com.example.hadideknache.financeapp;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.design.widget.NavigationView;

import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private String[] item;
    private Controller controller;
    private DrawerLayout drawerLayout;
    private Button btnSignOut;
    TextView name,email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        item = getResources().getStringArray(R.array.item);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        btnSignOut = (Button) findViewById(R.id.logout);
        regButton();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View navHeader = navigationView.getHeaderView(0);

        name = (TextView) navHeader.findViewById(R.id.user);
        email = (TextView) navHeader.findViewById(R.id.email);

        Intent intent = getIntent();
        init(intent);
    }
    private void regButton() {
        ButtonListener listener = new ButtonListener();
        btnSignOut.setOnClickListener(listener);
    }
    private void init(Intent intent) {
        controller = new Controller(this,intent);
    }

    public void setFragment(Fragment fragment){
        FragmentManager fragmanager = getFragmentManager();
        FragmentTransaction transaction = fragmanager.beginTransaction();
        transaction.replace(R.id.frameLayout, fragment);
        transaction.commit();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.home:
                controller.switchFragment(id);
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.search:
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
