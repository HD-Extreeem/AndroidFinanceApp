package com.example.hadideknache.financeapp;

/**
 * This Class handles the login/sign up process for the application on first start
 * Login contains two fragments, login and signup
 * Created by Hadi Deknache on 2017-09-13.
 */

import android.app.Fragment;
import android.app.FragmentTransaction;
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

    /**
     * Method saves the state of what fragment last was active
     * @param outState bundle where to put saved info when i.ex rotating the phone
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putBoolean("state",controller.loginState);
        controller.onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    /**
     * Method initialize the controller and checks if previous runs where made,i.ex phone rotated etc..
     * Also fetches fragments again and initiates the controller
     * @param savedInstanceState the bundle where the saved data is put i.ex rotation
     */
    private void init(Bundle savedInstanceState) {
        if (savedInstanceState!=null){
            signUpFragment = (SignUpFragment) getFragmentManager().findFragmentByTag("signUpFragment");
            if (signUpFragment==null){
                signUpFragment=new SignUpFragment();
            }
            loginFragment = (LoginFragment) getFragmentManager().findFragmentByTag("loginFragment");
        }
        controller = new LoginController(this,savedInstanceState);
    }

    /**
     * Method is used for swapping the fragments and poping fragment back
     * if previous fragment was changed it pops back the loginfragment
     * else replace the fragment and put it to backstack
     * @param fragment the fragment that should be set
     * @param id the id of the fragment that should saved with its fragment, in other words the TAG
     * @param backstack boolean for checking if need to put to backstack or not
     * @param restoredState boolean for checking if recently something occurred, i.ex rotation
     */
    public void switchFragment(Fragment fragment,String id,Boolean backstack,boolean restoredState){

        if (getFragmentManager().getBackStackEntryCount()>0 && !restoredState) {
            getFragmentManager().popBackStack();
        }
        else {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.startFrame, fragment,id);
            if (backstack) {
                transaction.addToBackStack(null);
            }
            transaction.commit();
        }

    }

    /**
     * Method for getting instance to loginfragment
     * @return instance to loginfragment
     */
    public LoginFragment getFragmentLogin() {
        return loginFragment;
    }

    /**
     * Method for getting instance to signupfragment
     * @return instance to signupfragment
     */
    public SignUpFragment getFragmentSignUp() {
        return signUpFragment;
    }

    /**
     * Method for getting instance to loginController
     * @return instance to loginController
     */
    public LoginController getController() {
        return controller;
    }
}
