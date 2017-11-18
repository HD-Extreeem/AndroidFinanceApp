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
 * A simple {@link Fragment} subclass.
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
    /*public static SettingsFragment newInstance(String str) {
        SettingsFragment setFrag = new SettingsFragment();
        Bundle args = new Bundle();
        args.putString("setFrag", str);
        setFrag.setArguments(args);
        return setFrag;
    }*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }
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

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //outState.putString("name",name.);
    }



    private void regButton() {
        ButtonClickListener listener = new ButtonClickListener();
        btnConfirm.setOnClickListener(listener);
    }

    public void init(View view) {
        name = (EditText) view.findViewById(R.id.name);
        surName = (EditText) view.findViewById(R.id.surname);
        email = (EditText) view.findViewById(R.id.email);
        password = (EditText) view.findViewById(R.id.password);
        newPassword = (EditText) view.findViewById(R.id.newPassword);
        btnConfirm = (Button) view.findViewById(R.id.btnConf);
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    /*public void fillInformation(String names, String surnames, String emails) {
        name.setText(names);
        surName.setText(surnames);
        email.setText(emails);
    }*/

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
