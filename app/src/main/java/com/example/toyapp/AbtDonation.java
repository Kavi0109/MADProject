package com.example.toyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class AbtDonation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abt_donation);
        Button btn_send = findViewById(R.id.btn_send);
        ImageView navabt = findViewById(R.id.fbarrow);
        navabt.setOnClickListener(v->{
            Intent intent =new Intent(this, Home.class);
            startActivity(intent);
        });


    }

    public void Help(View View) {
        String button_text;
        button_text = ((Button) View).getText().toString();
        if (button_text.equals("DONATE YOUR TOYS DIRECTLY")) {
            Intent ntoy = new Intent(this, MainActivity2.class);
            startActivity(ntoy);
        }
        else if (button_text.equals("MAKE AN INQUIRY")) {
            Intent send2 = new Intent(this, sendEmail.class);
            startActivity(send2);
        }

    }
}