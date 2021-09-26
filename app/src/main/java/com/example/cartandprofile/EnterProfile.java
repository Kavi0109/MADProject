package com.example.cartandprofile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;

import java.util.HashMap;

public class EnterProfile extends AppCompatActivity {

    AwesomeValidation awesomeValidation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_profile);
        final EditText edit_name = findViewById(R.id.edit_name);
        final EditText edit_age = findViewById(R.id.edit_age);
        final EditText edit_bio = findViewById(R.id.edit_bio);
        final EditText edit_school = findViewById(R.id.edit_school);

        //Initializing the validations
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        //Adding Validations
        awesomeValidation.addValidation(this,R.id.edit_name, RegexTemplate.NOT_EMPTY,R.string.invalid_name);
        awesomeValidation.addValidation(this,R.id.edit_age,"[0-1]{1}[0-8]{1}$",R.string.invalid_mobile);
        awesomeValidation.addValidation(this,R.id.edit_bio,".{5,}",R.string.invalid_bio);
        awesomeValidation.addValidation(this,R.id.edit_school,".{10,}",R.string.invalid_school);
        Button btn_validate = findViewById(R.id.btn_validate);


        Button btn = findViewById(R.id.btn_submit);
        Button btn_open = findViewById(R.id.btn_open);
        Button btn_image = findViewById(R.id.button4);

        btn_image.setOnClickListener(v -> {
            Intent intent = new Intent(EnterProfile.this,CreateProfile.class);
            startActivity(intent);
        });

        btn_open.setOnClickListener(v->{
            Intent intent = new Intent(EnterProfile.this,RVActivity.class);
            startActivity(intent);
        });

        DAOUser dao = new DAOUser();
        User user_edit = (User) getIntent().getSerializableExtra("EDIT");
        if(user_edit != null){
            btn.setText("UPDATE");
            edit_name.setText(user_edit.getName());
            edit_age.setText(user_edit.getAge());
            edit_bio.setText(user_edit.getBio());
            edit_school.setText(user_edit.getSchool());
            btn_open.setVisibility(View.GONE);
        }
        else{
            btn.setText("SUBMIT");
            btn_open.setVisibility(View.VISIBLE);
        }

        btn_validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(awesomeValidation.validate()){
                    Toast.makeText(getApplicationContext(),"Form Validate Successfully..",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(),"Validation Failed.",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn.setOnClickListener(v ->
        {

            User user = new User(edit_name.getText().toString(),edit_age.getText().toString(),edit_bio.getText().toString(),edit_school.getText().toString());

            if(user_edit==null)
            {
                dao.add(user).addOnSuccessListener(suc ->
                {
                    Toast.makeText(this, "Record Is Inserted!", Toast.LENGTH_SHORT).show();
                }).addOnFailureListener(er ->
                {
                    Toast.makeText(this, "" + er.getMessage(), Toast.LENGTH_SHORT).show();

                });
            }
            else {

                HashMap<String,Object> hashMap = new HashMap<>();
                hashMap.put("name",edit_name.getText().toString());
                hashMap.put("age",edit_age.getText().toString());
                hashMap.put("bio",edit_bio.getText().toString());
                hashMap.put("school",edit_school.getText().toString());
                dao.update(user_edit.getKey(),hashMap).addOnSuccessListener(suc ->
                {
                    Toast.makeText(this,"Record Is Updated!",Toast.LENGTH_SHORT).show();
                    finish();
                }).addOnFailureListener(er->
                {
                    Toast.makeText(this,""+er.getMessage(),Toast.LENGTH_SHORT).show();

                });

            }






            //Find By Individual ID
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


    }


}