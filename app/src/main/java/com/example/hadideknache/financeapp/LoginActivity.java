package com.example.hadideknache.financeapp;

/**
 * This Class handles the login/sign up process for the application on first start
 * Login contains two fragments, login and signup
 * Created by Hadi Deknache on 2017-09-13.
 */

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.hadideknache.financeapp.Fragments.LoginFragment;
import com.example.hadideknache.financeapp.Fragments.SignUpFragment;


public class LoginActivity extends AppCompatActivity {
    LoginController controller;
    private LoginFragment loginFragment;
    private SignUpFragment signUpFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putBoolean("state",controller.loginState);
        controller.onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    private void init(Bundle savedInstanceState) {
        controller = new LoginController(this,savedInstanceState);
    }

    public void setFragment(Fragment fragment,String id,boolean savedInstanceState){

        if (!savedInstanceState) {
            FragmentManager fragmanager = getFragmentManager();
            fragmanager
                    .beginTransaction()
                    .add(R.id.startFrame, fragment, id)
                    .commit();
        } else {
            if (id.equals("loginFragment")){
                loginFragment = (LoginFragment) getFragmentManager()
                        .findFragmentByTag(id);
            }
            else{
                signUpFragment = (SignUpFragment) getFragmentManager().findFragmentByTag(id);
            }

        }
    }


    public LoginFragment getFragmentLogin() {
        return loginFragment;
    }

    public SignUpFragment getFragmentSignUp() {
        return signUpFragment;
    }

    public LoginController getController() {
        return controller;
    }
}
