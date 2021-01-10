package com.example.jihc_study.navigation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.jihc_study.R;
import com.google.android.material.textfield.TextInputLayout;

public class ProfilePage extends AppCompatActivity {
    TextInputLayout fullName, userName, email, password;
    TextView fullNameLabel, userNameLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_page);

        fullName = findViewById(R.id.name_profile);
        userName = findViewById(R.id.username_profile);
        email = findViewById(R.id.email_profile);
        password = findViewById(R.id.password_profile);
        fullNameLabel = findViewById(R.id.fullname_field);
        userNameLabel = findViewById(R.id.username_field);

        showAllUserData();

    }

    private void showAllUserData() {
        Intent intent = getIntent();

        String user_name = intent.getStringExtra("name");
        String user_username = intent.getStringExtra("username");
        String user_mail = intent.getStringExtra("email");
        String user_password = intent.getStringExtra("password");

        fullNameLabel.setText(user_name);
        userNameLabel.setText(user_username);
        fullName.getEditText().setText(user_name);
        userName.getEditText().setText(user_username);
        email.getEditText().setText(user_mail);
        password.getEditText().setText(user_password);
    }
}