package com.example.hadideknache.financeapp;


import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;

import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


/**
 * This class is the MainActivity of the application which handles the start of the application
 * Also handles the swapping of the fragments
 */

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private Controller controller;
    private DrawerLayout drawerLayout;
    private Button btnSignOut;
    private TextView name,email;
    private String emails,names;

    /**
     * Overriden method for saving the name and email when rotating the phone
     * @param outState the bundle to save to
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("name",name.getText().toString());
        outState.putString("email",email.getText().toString());
        super.onSaveInstanceState(outState);
    }

    /**
     * Overriden method for restoring the data again
     * @param savedInstanceState bundle to get data from
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        names=savedInstanceState.getString("name");
        emails=savedInstanceState.getString("email");
        setUserInfo(names,emails);
    }

    /**
     * Overridden method which is called on start, Initiates the components and starts the process of application
     * @param savedInstanceState the bundle containing data if saved
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        regButton();
        controller = new Controller(this,getIntent());
        setUserInfo(controller.getName(),controller.getEmail());

    }

    /**
     * Methid initiates the drawers,buttons and headers of the drawer
     */
    private void init(){
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        btnSignOut = (Button) findViewById(R.id.logout);


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View navHeader = navigationView.getHeaderView(0);

        name = (TextView) navHeader.findViewById(R.id.user);
        email = (TextView) navHeader.findViewById(R.id.email);
    }

    /**
     * Register buttons when clicked
     */
    private void regButton() {
        ButtonListener listener = new ButtonListener();
        btnSignOut.setOnClickListener(listener);
    }


    /**
     * This method handles the swapping of the fragments
     * @param fragment the fragment that is wanted to be swapped to
     * @param tag the tag of the fragment
     */
    public void setFragment(android.support.v4.app.Fragment fragment, String tag){
        android.support.v4.app.FragmentManager fragmanager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaction = fragmanager.beginTransaction();
        transaction.replace(R.id.frameLayout, fragment,tag);
        transaction.commit();
    }

    /**
     * Overridden method handling the item in list, navdrawer
     * @param item which item was clicked
     * @return click was handled = true
     */
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

    /**
     * Method overriden to close drawer when clicking back if opened else dont care
     */
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    /**
     * Method for setting the information
     * @param username the username of the logged in user
     * @param usermail the usermail of the logged in user
     */
    public void setUserInfo(String username, String usermail) {
        name.setText(username);
        email.setText(usermail);
    }


    /**
     * This inner class handles the button click and operations that should be executed when clicked
     */
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
