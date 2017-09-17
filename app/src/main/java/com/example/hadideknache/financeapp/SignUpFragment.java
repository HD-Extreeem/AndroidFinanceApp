package com.example.hadideknache.financeapp;


import android.os.Bundle;
import android.app.Fragment;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends Fragment {
    LoginController controller;
    EditText etName,etEmail,etPass,etSurname;
    Button btnReg,btnLogAlready;
    int i=0;
    boolean isEmailReg=false;
    boolean register = true;
    public SignUpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        compInit(view);
        regButton();
        return view;
    }
    private void regButton() {
        btnClickListener listener = new btnClickListener();
        btnLogAlready.setOnClickListener(listener);
        btnReg.setOnClickListener(listener);
    }

    private void compInit(View view) {
        etName = (EditText) view.findViewById(R.id.etName);
        etSurname = (EditText) view.findViewById(R.id.etSurname);
        etEmail = (EditText) view.findViewById(R.id.etEmail);
        etPass = (EditText) view.findViewById(R.id.etPass);
        btnReg = (Button) view.findViewById(R.id.btnRegister);
        btnLogAlready = (Button) view.findViewById(R.id.btnLoginAlready);
    }

    public void setController(LoginController controller) {
        this.controller = controller;
    }
    private class btnClickListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            switch(view.getId()){
                case R.id.btnLoginAlready:
                    controller.switchFragment("Login");
                    break;
                case R.id.btnRegister:
                    checkInput();
                    if(register){
                        User user = new User(etName.getText().toString(),etSurname.getText().toString(),etEmail.getText().toString().toLowerCase(),etPass.getText().toString());
                        controller.addUser(user);
                        controller.switchFragment("Login");
                    }
                    break;
            }
        }
    }
    private void checkInput() {
        Boolean isUser = controller.checkUser(etEmail.getText().toString().toLowerCase());

        if (!checkName(etName.getText().toString())){
            etName.setError("Enter your name!");
            register=false;
        }
        if(!(checkPass(etPass.getText().toString()))){
            etPass.setError("Password need to be atleast 6 chars!");
            register=false;
        }
        if (!(checkEmail(etEmail.getText().toString()))){
            etEmail.setError("Enter a valid email!");
            register=false;
        }
        else{
            if (isUser){
                etEmail.setError("This Email is already registred!");
                register=false;
            }
            else{
                register=true;
                controller.success();
            }

        }
    }
    private boolean checkPass(CharSequence pass){
        if (pass.length()<=5){
            return false;
        }
        else{
            return true;
        }
    }

    private Boolean checkEmail(CharSequence mail){
        return Patterns.EMAIL_ADDRESS.matcher(mail).matches();
    }

    private Boolean checkName(CharSequence name){
        if (name.length()<=1){
            return false;
        }
        else{
            return true;
        }
    }
}
