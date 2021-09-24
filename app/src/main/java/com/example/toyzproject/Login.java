package com.example.toyzproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    EditText LoginEmail,LoginPassword;
    Button LoginBtn;
    TextView CreateAcc;
    ProgressBar LoginprogressBar;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Hide the toolbar
        getSupportActionBar().hide();

        LoginEmail = findViewById(R.id.LoginEmail);
        LoginPassword =findViewById(R.id.LoginPassword);
        LoginBtn = findViewById(R.id.LoginBtn);
        CreateAcc = findViewById(R.id.CreateAcc);
        LoginprogressBar = findViewById(R.id.LoginprogressBar);
        fAuth = FirebaseAuth.getInstance();


        //Login Button
        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    String loginemail = LoginEmail.getText().toString().trim();
                    String loginpassword = LoginPassword.getText().toString().trim();

                    if(TextUtils.isEmpty(loginemail)){
                        LoginEmail.setError("Email is Required.");
                        return;
                    }

                    if(TextUtils.isEmpty(loginpassword)){
                        LoginPassword.setError("Password is Required.");
                        return;
                    }

                    if (loginpassword.length() < 6) {
                        LoginPassword.setError("Password must be less than 6 characters");
                        return;
                    }

                    //set the visibility of the progress bar
                    LoginprogressBar.setVisibility(view.VISIBLE);


                    //Authenticate the user
                    fAuth.signInWithEmailAndPassword(loginemail,loginpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(Login.this,"Logged in Successfully!",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),Home.class));
                            }else {
                                Toast.makeText(Login.this,"Error!",Toast.LENGTH_SHORT).show();
                                LoginprogressBar.setVisibility(view.GONE);
                            }
                        }
                    });
            }
        });


        //when clicked create account button
        CreateAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });





    }
}