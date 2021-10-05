package com.example.toyzproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText BusinessName, BusinessEmail, BusinessContactNo, BusinessUsername, BusinessPassword, BusinessConfirmPassword;
    Button RegistrationBtn;
    TextView AlreadyRegistered;
    FirebaseAuth fAuth;
    ProgressBar progressBar;
    FirebaseFirestore fstore;
    String businessID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Hide the toolbar
        getSupportActionBar().hide();

        BusinessName = findViewById(R.id.BusinessName);
        BusinessEmail = findViewById(R.id.email);
        BusinessContactNo = findViewById(R.id.BusinessContactNo);
        BusinessUsername = findViewById(R.id.BusinessUsername);
        BusinessPassword = findViewById(R.id.BusinessPassword);
        BusinessConfirmPassword = findViewById(R.id.BusinessConfirmPassword);
        RegistrationBtn = findViewById(R.id.RegisterBtn);
        AlreadyRegistered =findViewById(R.id.AlreadyRegistered);

        fAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
        progressBar = findViewById(R.id.progressBar);

        //if the user is already logged in
        if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),Home.class));
            finish();
        }



        //when the register btn is clicked
        RegistrationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = BusinessEmail.getText().toString().trim();
                String password = BusinessPassword.getText().toString().trim();
                String conpassword = BusinessConfirmPassword.getText().toString().trim();
                String bName = BusinessName.getText().toString();
                String bcontactNo = BusinessContactNo.getText().toString();
                String bUsername = BusinessUsername.getText().toString();

                if(TextUtils.isEmpty(bName)){
                    BusinessName.setError("Business Name is Required.");
                    return;
                }

                if(TextUtils.isEmpty(bcontactNo)){
                    BusinessContactNo.setError("Business Name is Required.");
                    return;
                }

                if (bcontactNo.length() < 10) {
                    BusinessContactNo.setError("Contact number too short. Enter 10 digits");
                    return;
                }

                if(bcontactNo.length() > 10){
                    BusinessContactNo.setError("Contact number too long. Enter 10 digits");
                    return;
                }

                if(TextUtils.isEmpty(bUsername)){
                    BusinessUsername.setError("Username is Required.");
                    return;
                }



                if(TextUtils.isEmpty(email)){
                    BusinessEmail.setError("Email is Required.");
                    return;
                }

                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    BusinessEmail.setError("Invalid Email address.");
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    BusinessPassword.setError("Password is Required.");
                    return;
                }

                if (password.length() < 6) {
                    BusinessPassword.setError("Password too short. Enter 6 characters");
                    return;
                }

                if (password.length() > 6) {
                    BusinessPassword.setError("Password is too long. Enter 6 characters");
                    return;
                }









                //set the visibility of the progress bar
                progressBar.setVisibility(view.VISIBLE);

                //register the user in firebase
                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(MainActivity.this,"Registered Successfully!",Toast.LENGTH_SHORT).show();

                            //store the data in the cloud database
                            businessID = fAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fstore.collection("businesses").document(businessID);
                            Map<String,Object> user = new HashMap<>();
                            user.put("businessName",bName);
                            user.put("username",bUsername);
                            user.put("email",email);
                            user.put("contactNo",bcontactNo);

                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Log.d("TAG","onSuccess: user profile is created for "+businessID);
                                }
                            });

                            //if the data is successfully added it will be redirected to the login page
                            startActivity(new Intent(getApplicationContext(),Login.class));
                        }else {
                            Toast.makeText(MainActivity.this,"Error! "+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(view.GONE);
                        }
                    }
                });
            }
        });


        //Already Registered button will redirect into the login page
        AlreadyRegistered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Login.class));
            }
        });
    }
}