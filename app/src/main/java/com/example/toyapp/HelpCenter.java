package com.example.toyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class HelpCenter extends AppCompatActivity {

    AlertDialog.Builder builder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_center);

        final EditText type= findViewById(R.id.ty);
        final EditText suggestion = findViewById(R.id.suggestion);
        final EditText email = findViewById(R.id.email);
        builder = new AlertDialog.Builder(this);

        Button send = findViewById(R.id.btn_send);
        ImageView navabt = findViewById(R.id.fbarrow);
        navabt.setOnClickListener(v->{
            Intent intent =new Intent(this, Home.class);
            startActivity(intent);
        });
        DAOFeedback dfb = new DAOFeedback();

        send.setOnClickListener(v -> {

            if (TextUtils.isEmpty(type.getText().toString().trim()))
                type.setError("Please enter a type");
            else if (TextUtils.isEmpty(suggestion.getText().toString().trim()))
                suggestion.setError("Enter suggestion");
            else if (TextUtils.isEmpty(email.getText().toString().trim()))
                email.setError("please enter email");
            else {
                //Setting message manually and performing action on button click
                builder.setMessage("Do you want to send the message?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                finish();
                                startActivity(getIntent());
                                Feedback fbk = new Feedback(type.getText().toString(), suggestion.getText().toString(), email.getText().toString());
                                dfb.addfeed(fbk).addOnSuccessListener(suc -> {
                                    Toast.makeText(getApplicationContext(), "Data Saved Successfully", Toast.LENGTH_LONG).show();

                                });
                            }

                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //  Action for 'NO' Button
                                dialog.cancel();
                                finish();
                                Toast.makeText(getApplicationContext(), "Data failed Save",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
                //Creating dialog box
                AlertDialog alert = builder.create();
                //Setting the title manually
                // alert.setTitle("AlertDialogExample");
                alert.show();
            }

    });
//
//            Feedback fbk= new Feedback(type.getText().toString(),suggestion.getText().toString(),email.getText().toString());
//            dfb.addfeed(fbk).addOnSuccessListener(suc -> {
//                Toast.makeText(this, "Data Saved Successfully", Toast.LENGTH_SHORT).show();
//
//            }).addOnFailureListener(er -> {
//                Toast.makeText(this, "" + er.getMessage(), Toast.LENGTH_SHORT).show();
//
//            });
//
//        });

    }
}