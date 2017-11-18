package com.example.hadideknache.financeapp.Fragments;


import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hadideknache.financeapp.LoginActivity;
import com.example.hadideknache.financeapp.LoginController;
import com.example.hadideknache.financeapp.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {
    LoginController controller;
    int i=0,j=0;
    int id;
    boolean isEmail=false;
    private EditText etEmailReg,etPassReg;
    private Button btnSignUp,btnLogIn;



    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        setRetainInstance(true);
        compInit(view);
        regButton();

        if (savedInstanceState!=null){
            ((LoginActivity)getActivity()).getController().setSaveInformation();
        }

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        //outState.putString("email",etEmailReg.getText().toString());
        //outState.putString("pass",etPassReg.getText().toString());
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState!=null) {
            etEmailReg.setText(savedInstanceState.getString("email"));
            etPassReg.setText(savedInstanceState.getString("pass"));
        }
    }

    private void regButton() {
        btnClickListener listener = new btnClickListener();
        btnLogIn.setOnClickListener(listener);
        btnSignUp.setOnClickListener(listener);
    }

    private void compInit(View view) {
        etEmailReg = (EditText) view.findViewById(R.id.etEmailReg);
        etPassReg = (EditText) view.findViewById(R.id.etPassReg);
        btnSignUp = (Button) view.findViewById(R.id.btnSignUp);
        btnLogIn = (Button) view.findViewById(R.id.btnLogIn);
    }

    public void setController(LoginController controller) {
        this.controller = controller;
    }

    public void setInformation(String email, String pass) {
        etEmailReg.setText(email);
        etPassReg.setText(pass);
    }


    private class btnClickListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            switch(view.getId()){
                case R.id.btnLogIn:
                    //controller.checkAlreadyExist(etEmailReg.getText().toString());
                    controller.checkLogin(etEmailReg,etPassReg);
                    break;
                case R.id.btnSignUp:
                    controller.switchFragment("Register");
                    break;
            }

        }
    }
    public ArrayList<String> getSaveInformation(){
        ArrayList<String> save = new ArrayList<>();
        save.add(etEmailReg.getText().toString());
        save.add(etPassReg.getText().toString());
        return save;
    }


}
