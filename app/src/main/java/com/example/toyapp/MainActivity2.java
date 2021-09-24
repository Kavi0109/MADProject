package com.example.toyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ImageView navabt = findViewById(R.id.arrow2);
        navabt.setOnClickListener(v->{
            Intent intent =new Intent(this, AbtDonation.class);
            startActivity(intent);
        });
    }
}