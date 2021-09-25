package com.example.toyzproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class newsletter extends AppCompatActivity {

    Button EmailBtn,SMSBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsletter);

        //Change the tool bar name
        getSupportActionBar().setTitle("NEWSLETTER");

        EmailBtn = findViewById(R.id.emailBtn);

        // Email Btn
        EmailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(newsletter.this,Email.class);
                startActivity(intent);
            }
        });
    }
}