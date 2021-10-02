package com.example.cartandprofile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class Cart extends AppCompatActivity {

    private TextView show_cart;
    private Button button1,button2,button7;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        reference = FirebaseDatabase.getInstance().getReference("CartAndProfile");

        show_cart = findViewById(R.id.show_cart);
        button1 = findViewById(R.id.btton1);
        button2 = findViewById(R.id.btton2);
        button7 = findViewById(R.id.button7);


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ID_cart = reference.push().getKey();
                HashMap<String, String> parameters = new HashMap<>();
                parameters.put("Product Name","SpiderMan");
                parameters.put("Price", "USD20");
                reference.child(ID_cart).setValue(parameters);

            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ID_cart = reference.push().getKey();
                HashMap<String, String> parameters = new HashMap<>();
                parameters.put("Product Name","BarbieSet");
                parameters.put("Price", "USD150");
                reference.child(ID_cart).setValue(parameters);

            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), OrderConfirm.class));
            }
        });



        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                show_cart.setText(""+snapshot.getChildrenCount());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        show_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ShowCart.class));
            }
        });

    }
}