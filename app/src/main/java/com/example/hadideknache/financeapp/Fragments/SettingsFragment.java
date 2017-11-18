package com.example.hadideknache.financeapp.Fragments;


import android.os.Bundle;
//import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.hadideknache.financeapp.Controller;
import com.example.hadideknache.financeapp.R;


/**
 * This class handles the settingsFragment
 * Let user change information as name,surname,password
 * Created by Hadi Deknache on 2017-09-17.
 */
public class SettingsFragment extends Fragment {
    private Controller controller;
    private EditText name;
    private EditText surName;
    private EditText password;
    private EditText newPassword;
    private EditText email;
    private Button btnConfirm;

    public SettingsFragment() {
        // Required empty public constructor
    }

    /**
     * Overriden method which uses the view to register components
     * Initiates the components and prepares the components
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setRetainInstance(true);
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        init(view);
        regButton();
        controller.fillInformation(name,surName,email);
        return view;
    }

    /**
     * Method for registering the buttons whne pressed
     */
    private void regButton() {
        ButtonClickListener listener = new ButtonClickListener();
        btnConfirm.setOnClickListener(listener);
    }

    /**
     * Method for registering the components upon creating the view and inflating it
     * @param view the view which contains the components for gui
     */
    public void init(View view) {
        name = (EditText) view.findViewById(R.id.name);
        surName = (EditText) view.findViewById(R.id.surname);
        email = (EditText) view.findViewById(R.id.email);
        password = (EditText) view.findViewById(R.id.password);
        newPassword = (EditText) view.findViewById(R.id.newPassword);
        btnConfirm = (Button) view.findViewById(R.id.btnConf);
    }

    /**
     * Method for setting the instance to the controller
     * @param controller instance for the controller class
     */
    public void setController(Controller controller) {
        this.controller = controller;
    }

    /**
     * This inner class is used for listenening to button press occuring in the gui of this fragment
     * handles the actionspress whn clicking on updateinformation
     */
    private class ButtonClickListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btnConf:
                    Boolean isUpdate = controller.UpdateUserSettings(name.getText().toString(),surName.getText().toString(),password.getText().toString(),newPassword.getText().toString());
                    if(isUpdate){
                        controller.chartInfo("Information updated!");
                        password.getText().clear();
                        newPassword.getText().clear();
                        controller.setHeaderInfo();

                    }
                    else{
                        controller.chartInfo("Enter correct Password!");
                    }
            }
        }
    }
}
