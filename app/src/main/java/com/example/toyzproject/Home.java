package com.example.toyzproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;

import com.google.android.material.tabs.TabLayout;

public class Home extends AppCompatActivity {
    public Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Change the tool bar name
        getSupportActionBar().setTitle("HOME");



        //NewArrivals Btn





        //Category Btn
        button = (Button) findViewById(R.id.CategoryBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this,Categories.class);
                startActivity(intent);
            }
        });

        //Newsletter Btn
        button = (Button) findViewById(R.id.NewsletterBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this,newsletter.class);
                startActivity(intent);
            }
        });

        //statistics
        button = (Button) findViewById(R.id.StaticsBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this,Statistics.class);
                startActivity(intent);
            }
        });




    }
}