package com.example.jihc_study.authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.jihc_study.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationPage extends AppCompatActivity {
    TextInputLayout regName, regUsername, regEmail, regPassword;
    Button regToLoginBtn, regBtn;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_page);

        regToLoginBtn = findViewById(R.id.call_login);
        regBtn = findViewById(R.id.signup_btn);
        regName = findViewById(R.id.input_fullname);
        regUsername = findViewById(R.id.input_username);
        regEmail = findViewById(R.id.input_email);
        regPassword = findViewById(R.id.input_password);

        regToLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistrationPage.this, LoginPage.class);
                startActivity(intent);
            }
        });
    }

    private Boolean validateName(){
        String val = regName.getEditText().getText().toString();

        if(val.isEmpty()){
            regName.setError("Field cannot be empty");
            return false;
        }else{
            regName.setError(null);
            regName.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateUsername(){
        String val = regUsername.getEditText().getText().toString();
        String noWhiteSpace = "(?=\\s+$)";

        if(val.isEmpty()){
            regUsername.setError("Field cannot be empty");
            return false;
        }else if(val.length()>=15){
            regUsername.setError("Username is too long");
            return false;
        }else if(val.matches(noWhiteSpace)){
            regUsername.setError("Enter username without white space");
            return false;
        }else if(val.length()<=4){
            regUsername.setError("Username is too short");
            return false;
        }
        else{
            regUsername.setError(null);
            return true;
        }
    }

    private Boolean validateEmail(){
        String val = regEmail.getEditText().getText().toString();
        String emailPattern = "^[A-Za-z0-9+_.-]+@[a-z]+\\.+[a-z]+";

        if(val.isEmpty()){
            regEmail.setError("Field cannot be empty");
            return false;
        }else if(!val.matches(emailPattern)){
            regEmail.setError("Invalid email address");
            return false;
        }
        else{
            regEmail.setError(null);
            return true;
        }
    }

    private Boolean validatePassword(){
        String val = regPassword.getEditText().getText().toString();
        String passwordVal = "^(?=.*[0-9])(?=\\S+$).{8,}$";

        if(val.isEmpty()){
            regPassword.setError("Field cannot be empty");
            return false;
        }else if(!val.matches(passwordVal)){
            regPassword.setError("Password should contain 1 digit, minimum 8 characters, no white space");
            return false;
        }
        else{
            regPassword.setError(null);
            return true;
        }
    }

    public void registerUser(View view) {

        if(!validateName() | !validateUsername() | !validateEmail() | !validatePassword()){
            return;
        }


        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("users");

        String name = regName.getEditText().getText().toString();
        String username = regUsername.getEditText().getText().toString();
        String email = regEmail.getEditText().getText().toString();
        String password = regPassword.getEditText().getText().toString();

        UserHelperClass helperClass = new UserHelperClass(name, username, email, password);

        reference.child(username).setValue(helperClass);

        Intent intent = new Intent(RegistrationPage.this, LoginPage.class);
        startActivity(intent);

    }
}