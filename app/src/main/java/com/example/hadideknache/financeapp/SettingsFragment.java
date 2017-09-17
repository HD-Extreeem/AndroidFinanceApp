package com.example.hadideknache.financeapp;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {
    Controller controller;
    EditText name;
    EditText surName;
    EditText password;
    EditText newPassword;
    EditText email;
    Button btnConfirm;

    public SettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        init(view);
        regButton();
        controller.fillInformation(name,surName,email);
        return view;
    }

    private void regButton() {
        ButtonClickListener listener = new ButtonClickListener();
        btnConfirm.setOnClickListener(listener);
    }

    private void init(View view) {
        name= (EditText) view.findViewById(R.id.name);
        surName = (EditText) view.findViewById(R.id.surname);
        email = (EditText) view.findViewById(R.id.email);
        password = (EditText) view.findViewById(R.id.password);
        newPassword = (EditText) view.findViewById(R.id.newPassword);
        btnConfirm = (Button) view.findViewById(R.id.btnConf);

    }

    public void setController(Controller controller) {
        this.controller = controller;
    }
    private class ButtonClickListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btnConf:
                    if(controller.UpdateUserSettings(name.getText().toString(),surName.getText().toString(),password.getText().toString(),newPassword.getText().toString())){
                        controller.chartInfo("Information updated!");
                        password.getText().clear();
                        newPassword.getText().clear();
                    }
                    else{
                        controller.chartInfo("Enter correct Password!");
                    }
            }
        }
    }
}
