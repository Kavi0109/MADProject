package com.example.toyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button btn_open = findViewById(R.id.btnOpen);
        btn_open.setOnClickListener(v->
        {
            Intent intent =new Intent(this, RVActivity.class);
            startActivity(intent);
        });

    }
    public void Donate(View View) {
        String button_text;
        button_text = ((Button) View).getText().toString();
        if (button_text.equals("DONATE TOYS")) {
            Intent donate = new Intent(this, AbtDonation.class);
            startActivity(donate);
        } else if(button_text.equals("APPLY FOR TOYS")) {
            Intent apply = new Intent(this, MainActivity.class);
            startActivity(apply);

        }
    }
}