package com.example.hadideknache.financeapp.Fragments;

import android.os.Bundle;
import android.app.Fragment;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.hadideknache.financeapp.LoginController;
import com.example.hadideknache.financeapp.R;
import com.example.hadideknache.financeapp.User;

import java.util.ArrayList;

/**
 * This fragment handles the signup of the app
 * Created by Hadi Deknache on 2017-09-13.
 */
public class SignUpFragment extends Fragment {
    private LoginController controller;
    private EditText etName,etEmail,etPass,etSurname;
    private Button btnReg,btnLogAlready;
    boolean register = true;


    public SignUpFragment() {
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
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        setRetainInstance(true);
        compInit(view);
        regButton();

        return view;
    }
    /**
     * Method for registering the actions caused when clicking on the buttons
     */
    private void regButton() {
        btnClickListener listener = new btnClickListener();
        btnLogAlready.setOnClickListener(listener);
        btnReg.setOnClickListener(listener);
    }
    /**
     * This method initiates the components of this fragment
     * @param view viewReference that includes all components
     */
    private void compInit(View view) {
        etName = (EditText) view.findViewById(R.id.etName);
        etSurname = (EditText) view.findViewById(R.id.etSurname);
        etEmail = (EditText) view.findViewById(R.id.etEmail);
        etPass = (EditText) view.findViewById(R.id.etPass);
        btnReg = (Button) view.findViewById(R.id.btnRegister);
        btnLogAlready = (Button) view.findViewById(R.id.btnLoginAlready);
    }

    /**
     * Method for setting the instance of controller
     * @param controller instance to controller class
     */
    public void setController(LoginController controller) {
        this.controller = controller;
    }


    public ArrayList<String> getSaveInformation() {
        ArrayList<String> save = new ArrayList<>();
        save.add(etEmail.getText().toString());
        save.add(etName.getText().toString());
        save.add(etPass.getText().toString());
        save.add(etSurname.getText().toString());
        return save;
    }

    /**
     * This inner class handles all button presses made on the gui
     */
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

    /**
     * Method which checks if everything was typed correctly upon registering
     */
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

    /**
     * Method for assuring that password is greater then 5 chars
     * @param pass
     * @return
     */
    private boolean checkPass(CharSequence pass){
        if (pass.length()<=5){
            return false;
        }
        else{
            return true;
        }
    }

    /**
     * Method for checking if mail is valid
     * @param mail mail that is going to register
     * @return if mail is valid or not
     */
    private Boolean checkEmail(CharSequence mail){
        return Patterns.EMAIL_ADDRESS.matcher(mail).matches();
    }

    /**
     * Method for checking if name is typed and greater then 1 char
     * @param name the name that is going to be registered
     * @return whether a name is typed
     */
    private Boolean checkName(CharSequence name){
        if (name.length()<=1){
            return false;
        }
        else{
            return true;
        }
    }

}
