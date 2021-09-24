package com.example.toyzproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Categories extends AppCompatActivity {

    public Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        //Change the tool bar name
        getSupportActionBar().setTitle("CATEGORIES");



        //Toy Vehicle Btn
        button = (Button) findViewById(R.id.ToysVehiclesBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Categories.this,ToysVehical.class);
                startActivity(intent);
            }
        });
    }
}