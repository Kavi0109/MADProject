package com.example.cartandprofile;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    Button button1;
    Button button2;
    Button button5;
    Button button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1 = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        button5 = (Button) findViewById(R.id.button5);
        button3 = (Button) findViewById(R.id.button3);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewAtivity();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(),Cart.class));
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),EnterProfile.class));
            }
        });

        button3.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                startActivity(new Intent(getApplicationContext(),SendEmail.class));
            }

        });

        }



    public void openNewAtivity() {
        Intent intent = new Intent(this,CreateProfile.class);
        startActivity(intent);
    }





}