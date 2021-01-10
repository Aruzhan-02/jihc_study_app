package com.example.jihc_study.authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.jihc_study.R;
import com.example.jihc_study.navigation.Dashboard;
import com.example.jihc_study.navigation.ProfilePage;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginPage extends AppCompatActivity {
    Button callSignup, btnLogin;
    TextView Title, Subtitle;
    TextInputLayout Username, Password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.login_page);

        callSignup = findViewById(R.id.call_signup);
        Title = findViewById(R.id.tv_login_title);
        Subtitle = findViewById(R.id.tv_subtitle);
        Username = findViewById(R.id.login_username);
        Password = findViewById(R.id.login_password);
        btnLogin = findViewById(R.id.login_btn);

        callSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginPage.this, RegistrationPage.class);

                Pair[] pairs = new Pair[7];

                pairs[0] = new Pair<View, String>(Title,"logo_image");
                pairs[1] = new Pair<View, String>(Subtitle,"logo_text");
                pairs[2] = new Pair<View, String>(Username,"input_username");
                pairs[3] = new Pair<View, String>(Password,"input_password");
                pairs[4] = new Pair<View, String>(btnLogin,"btn_login");
                pairs[5] = new Pair<View, String>(Title,"logo_image");
                pairs[6] = new Pair<View, String>(callSignup,"btn_callsignup");

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(LoginPage.this, pairs);
                    startActivity(intent, options.toBundle());
                }

            }
        });


    }

    private Boolean validateUsername(){
        String val = Username.getEditText().getText().toString();

        if(val.isEmpty()) {
            Username.setError("Field cannot be empty");
            return false;
        }
        else{
            Username.setError(null);
            Username.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePassword(){
        String val = Password.getEditText().getText().toString();


        if(val.isEmpty()){
            Password.setError("Field cannot be empty");
            return false;
        }
        else{
            Password.setError(null);
            return true;
        }
    }

    public void loginUser(View view) {

        if(!validateUsername() | !validatePassword()){
            return;
        }else{
            isUser();
        }

    }

    private void isUser() {
        String userEnteredUsername = Username.getEditText().getText().toString().trim();
        String userEnteredPassword = Password.getEditText().getText().toString().trim();

        DatabaseReference reference  = FirebaseDatabase.getInstance().getReference("users");
        Query checkUser = reference.orderByChild("username").equalTo(userEnteredUsername);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    Username.setError(null);
                    Username.setErrorEnabled(false);

                    String passwordFromDB = dataSnapshot.child(userEnteredUsername).child("password").getValue(String.class);

                    if(passwordFromDB.equals(userEnteredPassword)){
                        Username.setError(null);
                        Username.setErrorEnabled(false);

                        String nameFromDB = dataSnapshot.child(userEnteredUsername).child("name").getValue(String.class);
                        String usernameFromDB = dataSnapshot.child(userEnteredUsername).child("username").getValue(String.class);
                        String emailFromDB = dataSnapshot.child(userEnteredUsername).child("email").getValue(String.class);

                        Intent intent = new Intent(getApplicationContext(), Dashboard.class);

                        intent.putExtra("name", nameFromDB);
                        intent.putExtra("username", usernameFromDB);
                        intent.putExtra("email", emailFromDB);
                        intent.putExtra("password", passwordFromDB);

                        startActivity(intent);

                    }else{
                        Password.setError("Wrong Password");
                        Password.requestFocus();
                    }
                }else{
                    Username.setError("There is no such user");
                    Username.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}