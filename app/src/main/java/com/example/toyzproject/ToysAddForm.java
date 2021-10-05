package com.example.toyzproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class ToysAddForm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toys_add_form);

        //Change the tool bar name
        getSupportActionBar().hide();

        final EditText edit_ToyID = findViewById(R.id.edit_ToyID);
        final EditText edit_ToyName = findViewById(R.id.edit_ToyName);
        final EditText edit_ToyDescription = findViewById(R.id.edit_ToyDescription);
        final EditText edit_ToyColour = findViewById(R.id.edit_ToyColour);
        final EditText edit_ToyBrand = findViewById(R.id.edit_ToyBrand);
        final EditText edit_ToyModel = findViewById(R.id.edit_ToyModel);
        final EditText edit_Price = findViewById(R.id.edit_Price);
        final EditText edit_ToyDate = findViewById(R.id.edit_ToyDate);

        Button btn = findViewById(R.id.btn_submit);
        Button btn_open = findViewById(R.id.btn_open);
        btn_open.setOnClickListener(v->
        {
            Intent intent =new Intent(ToysAddForm.this,RVActivity.class);
            startActivity(intent);
        });


        DAOToys dao = new DAOToys();

        Toys toys_edit = (Toys)getIntent().getSerializableExtra("EDIT");
        if(toys_edit != null)
        {
            btn.setText("UPDATE");
            edit_ToyID.setText(toys_edit.getToyID());
            edit_ToyName.setText(toys_edit.getToyName());
            edit_ToyDescription.setText(toys_edit.getToyDescription());
            edit_ToyColour.setText(toys_edit.getToyColor());
            edit_ToyBrand.setText(toys_edit.getToyBrand());
            edit_ToyModel.setText(toys_edit.getToyModel());
            edit_Price.setText(toys_edit.getToyPrice());
            edit_ToyDate.setText(toys_edit.getToyDate());
            btn_open.setVisibility(View.GONE);
        }
        else {
            btn.setText("SUBMIT");
            btn_open.setVisibility(View.VISIBLE);
        }








        btn.setOnClickListener(v->
        {

            //validating the form
            if(TextUtils.isEmpty(edit_ToyName.getText().toString().trim()))
                edit_ToyName.setError("Please enter the toy name");
            else if(TextUtils.isEmpty(edit_ToyDescription.getText().toString().trim()))
                edit_ToyDescription.setError("Please enter the toy description");
            else if(TextUtils.isEmpty(edit_ToyColour.getText().toString().trim()))
                edit_ToyColour.setError("Please enter the toy colour");
            else if(TextUtils.isEmpty(edit_ToyBrand.getText().toString().trim()))
                edit_ToyBrand.setError("Please enter the brand");
            else if(TextUtils.isEmpty(edit_ToyModel.getText().toString().trim()))
                edit_ToyModel.setError("Please enter the model");
            else if(TextUtils.isEmpty(edit_Price.getText().toString().trim()))
                edit_Price.setError("Please enter the price");
            else if(TextUtils.isEmpty(edit_ToyDate.getText().toString().trim()))
                edit_ToyDate.setError("Please enter the manufacture date");

            else {

                //Insert
                Toys toys = new Toys(edit_ToyID.getText().toString(),
                        edit_ToyName.getText().toString(),
                        edit_ToyDescription.getText().toString(),
                        edit_ToyColour.getText().toString(),
                        edit_ToyBrand.getText().toString(),
                        edit_ToyModel.getText().toString(),
                        edit_Price.getText().toString(),
                        edit_ToyDate.getText().toString());


                if (toys_edit == null) {

                    dao.add(toys).addOnSuccessListener(suc ->
                    {
                        Toast.makeText(this, "Record is Inserted!", Toast.LENGTH_SHORT).show();

                    }).addOnFailureListener(er ->
                    {
                        Toast.makeText(this, "" + er.getMessage(), Toast.LENGTH_SHORT).show();
                    });
                } else {

                    //Update
                    HashMap<String, Object> hashMap = new HashMap<>();
                    hashMap.put("ToyID", edit_ToyID.getText().toString());
                    hashMap.put("ToyName", edit_ToyName.getText().toString());
                    hashMap.put("ToyDescription", edit_ToyDescription.getText().toString());
                    hashMap.put("ToyColour", edit_ToyColour.getText().toString());
                    hashMap.put("ToyBrand", edit_ToyBrand.getText().toString());
                    hashMap.put("ToyModel", edit_ToyModel.getText().toString());
                    hashMap.put("ToyPrice", edit_Price.getText().toString());
                    hashMap.put("ToyDate", edit_ToyDate.getText().toString());


                    dao.update(toys_edit.getKey(), hashMap).addOnSuccessListener(suc ->
                    {
                        Toast.makeText(this, "Record is Updated!", Toast.LENGTH_SHORT).show();
                        finish();

                    }).addOnFailureListener(er ->
                    {
                        Toast.makeText(this, "" + er.getMessage(), Toast.LENGTH_SHORT).show();
                    });


                }

            }

        });







    }
}