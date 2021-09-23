package com.example.cartandprofile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class EnterProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_profile);
        final EditText edit_name = findViewById(R.id.edit_name);
        final EditText edit_age = findViewById(R.id.edit_age);
        final EditText edit_bio = findViewById(R.id.edit_bio);
        final EditText edit_school = findViewById(R.id.edit_school);
        Button btn = findViewById(R.id.btn_submit);
        DAOUser dao = new DAOUser();

        btn.setOnClickListener(v -> {

            User user = new User(edit_name.getText().toString(),edit_age.getText().toString(),edit_bio.getText().toString(),edit_school.getText().toString());
            dao.add(user).addOnSuccessListener(suc ->{
                Toast.makeText(this,"Record Is Inserted!",Toast.LENGTH_SHORT).show();
            }).addOnFailureListener(er->{
                Toast.makeText(this,""+er.getMessage(),Toast.LENGTH_SHORT).show();

            });

            /*HashMap<String,Object> hashMap = new HashMap<>();
            hashMap.put("name",edit_name.getText().toString());
            hashMap.put("age",edit_age.getText().toString());
            dao.update("-MkFi7C-1-xVzrT3aIbv",hashMap).addOnSuccessListener(suc ->{
                Toast.makeText(this,"Record Is Updated!",Toast.LENGTH_SHORT).show();
            }).addOnFailureListener(er->{
                Toast.makeText(this,""+er.getMessage(),Toast.LENGTH_SHORT).show();

            });*/

            /*HashMap<String,Object> hashMap = new HashMap<>();
            hashMap.put("name",edit_name.getText().toString());
            hashMap.put("age",edit_age.getText().toString());*/

            /*dao.remove("-MkFi7C-1-xVzrT3aIbv").addOnSuccessListener(suc ->{
                Toast.makeText(this,"Record Is Removed!",Toast.LENGTH_SHORT).show();
            }).addOnFailureListener(er->{
                Toast.makeText(this,""+er.getMessage(),Toast.LENGTH_SHORT).show();

            });*/
        });

        Button btn_open = findViewById(R.id.btn_open);
        btn_open.setOnClickListener(v->{
            Intent intent = new Intent(EnterProfile.this,RVActivity.class);
            startActivity(intent);
        });
    }
}