package com.example.toyzproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Email extends AppCompatActivity {

    EditText etTo,etSuject,etMessage;
    Button btSend, btBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);

        //Change the tool bar name
        getSupportActionBar().setTitle("EMAIL");

        etTo = findViewById(R.id.et_to);
        etSuject =findViewById(R.id.et_subject);
        etMessage = findViewById(R.id.et_message);
        btSend = findViewById(R.id.bt_send);
        btBack = findViewById(R.id.EmailBackBtn);

        btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("mailto:" + etTo.getText().toString()));
                intent.putExtra(Intent.EXTRA_SUBJECT,etSuject.getText().toString());
                intent.putExtra(Intent.EXTRA_TEXT,etMessage.getText().toString());
                startActivity(intent);
            }
        });

        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Email.this,newsletter.class);
                startActivity(intent);
            }
        });
    }

    public void openDialog() {
        PopUpDialog popUpDialog = new PopUpDialog();
        popUpDialog.show(getSupportFragmentManager(),"popup dialog");
    }
}