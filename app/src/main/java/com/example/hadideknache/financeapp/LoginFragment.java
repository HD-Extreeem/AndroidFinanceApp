package com.example.hadideknache.financeapp;


import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        compInit(view);
        regButton();

        return view;
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


}
