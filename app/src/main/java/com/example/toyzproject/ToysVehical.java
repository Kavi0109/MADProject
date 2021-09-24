package com.example.toyzproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ToysVehical extends AppCompatActivity {

    public Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toys_vehical);

        //Change the tool bar name
        getSupportActionBar().setTitle("TOY VEHICLE");

        button = (Button) findViewById(R.id.addNewItemsBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ToysVehical.this,ToysAddForm.class);
                startActivity(intent);
            }
        });


    }
}