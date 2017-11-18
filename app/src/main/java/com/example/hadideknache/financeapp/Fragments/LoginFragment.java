package com.example.hadideknache.financeapp.Fragments;

import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import com.example.hadideknache.financeapp.LoginController;
import com.example.hadideknache.financeapp.R;

import java.util.ArrayList;


/**
 * This fragment handles the login of the app
 * Created by Hadi Deknache on 2017-09-13.
 */
public class LoginFragment extends Fragment {
    LoginController controller;
    private EditText etEmailReg,etPassReg;
    private Button btnSignUp,btnLogIn;



    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Method is overriden for creating the view and registering the components used for the gui
     * @param inflater which inflates the view on the screen
     * @param container viewgroup of the view
     * @param savedInstanceState bundle containing the save info
     * @return the view for the loginFragment
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        setRetainInstance(true);
        compInit(view);
        regButton();

        return view;
    }

    /**
     * Override method for restoring the typed in pass and email
     * @param view view that is created
     * @param savedInstanceState bundle containing the save info
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState!=null) {
            etEmailReg.setText(savedInstanceState.getString("email"));
            etPassReg.setText(savedInstanceState.getString("pass"));
        }
    }

    /**
     * Method for registering the actions caused when clicking on the buttons
     */
    private void regButton() {
        btnClickListener listener = new btnClickListener();
        btnLogIn.setOnClickListener(listener);
        btnSignUp.setOnClickListener(listener);
    }

    /**
     * This method initiates the components of this fragment
     * @param view viewReference that includes all components
     */
    private void compInit(View view) {
        etEmailReg = (EditText) view.findViewById(R.id.etEmailReg);
        etPassReg = (EditText) view.findViewById(R.id.etPassReg);
        btnSignUp = (Button) view.findViewById(R.id.btnSignUp);
        btnLogIn = (Button) view.findViewById(R.id.btnLogIn);
    }

    /**
     * Method for setting the instance of controller
     * @param controller instance to controller class
     */
    public void setController(LoginController controller) {
        this.controller = controller;
    }

    /**
     * This inner class handles all button presses made on the gui
     */
    private class btnClickListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            switch(view.getId()){
                case R.id.btnLogIn:
                    controller.checkLogin(etEmailReg,etPassReg);
                    break;
                case R.id.btnSignUp:
                    controller.switchFragment("Register");
                    break;
            }

        }
    }

    /**
     * Method for saving all typed information when i.ex rotating phone
     * @return arraylist containing the saved information
     */
    public ArrayList<String> getSaveInformation(){
        ArrayList<String> save = new ArrayList<>();
        save.add(etEmailReg.getText().toString());
        save.add(etPassReg.getText().toString());
        return save;
    }


}
