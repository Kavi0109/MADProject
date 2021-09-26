package com.example.toyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class sendEmail extends AppCompatActivity {

    EditText etTo,etSubject,etMessage;
    Button btnSend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_email);

        etTo=findViewById(R.id.et_to);
        etSubject=findViewById(R.id.et_subject);
        etMessage=findViewById(R.id.et_message);
        btnSend=findViewById(R.id.btnsend);

        ImageView navabt = findViewById(R.id.hlarrow);
        navabt.setOnClickListener(v->{
            Intent intent =new Intent(this, AbtDonation.class);
            startActivity(intent);
        });

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_VIEW
                , Uri.parse("mailto:"+ etTo.getText().toString()));
                intent.putExtra(Intent.EXTRA_SUBJECT,etSubject.getText().toString());
                intent.putExtra(Intent.EXTRA_TEXT,etMessage.getText().toString());
                startActivity(intent);

            }
        });

    }
}