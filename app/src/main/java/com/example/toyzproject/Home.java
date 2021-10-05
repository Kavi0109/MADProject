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
import com.google.firebase.auth.FirebaseAuth;

public class Home extends AppCompatActivity {
    public Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Change the tool bar name
        getSupportActionBar().setTitle("HOME");



        //NewArrivals Btn
        button = (Button) findViewById(R.id.NewArrivalsBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this,NewArrivals.class);
                startActivity(intent);
            }
        });




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

        //statistics Btn
        button = (Button) findViewById(R.id.StaticsBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this,Statistics.class);
                startActivity(intent);
            }
        });

    }

    //Logout Btn
    public void logout(View view){
        FirebaseAuth.getInstance().signOut();//logout the user that is currently logged in
        startActivity(new Intent(getApplicationContext(),Login.class));
        finish();
    }

}