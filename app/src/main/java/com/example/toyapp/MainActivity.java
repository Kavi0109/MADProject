package com.example.toyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final EditText txtName=findViewById(R.id.org_name);
        final EditText txtAddress= findViewById(R.id.org_Address);
        final EditText txtEmail= findViewById(R.id.org_Email);
      //  final EditText txtPhone = findViewById(R.id.org_Phone);
        final EditText txtDonType= findViewById(R.id.donType);
        final EditText txtDonGender= findViewById(R.id.donGender);
        final EditText txtDonQuantity= findViewById(R.id.donQuantity);
        final EditText txtAge= findViewById(R.id.ageGroup);
        final EditText txtPeriod= findViewById(R.id.donPeriod);
        CheckBox chk = findViewById(R.id.checkBox1);
        String emailpattern="[a-zA-Z0-9._-]+@[a-z]+\\.[a-z]+";


        Button btnSubmit= findViewById(R.id.btn_Submit);
//        Button btn_open = findViewById(R.id.btnOpen);
//        btn_open.setOnClickListener(v->
//        {
//            Intent intent =new Intent(MainActivity.this, RVActivity.class);
//            startActivity(intent);
//       });

        ImageView nav = findViewById(R.id.farrow);
        nav.setOnClickListener(v->{
            Intent intent =new Intent(this, Home.class);
            startActivity(intent);
        });

       DAOOrganization dao = new DAOOrganization();
       Organization org_edit = (Organization) getIntent().getSerializableExtra("EDIT");
       if(org_edit != null){
           btnSubmit.setText("UPDATE");
           txtName.setText(org_edit.getOrgName());
           txtAddress.setText(org_edit.getOrgAddress());
           txtEmail.setText(org_edit.getOrgEmail());
//           txtPhone.setText(org_edit.getOrgPhone());
           txtDonType.setText(org_edit.getDonationType());
          txtDonGender.setText(org_edit.getDonationGender());
           txtDonQuantity.setText(org_edit.getQuantity());
           txtAge.setText(org_edit.getAgeGroup());
           txtPeriod.setText(org_edit.getDonationPeriod());

       //    btn_open.setVisibility(View.GONE);
           chk.setChecked(true);
       }
       else
       {
           btnSubmit.setText("SUBMIT");
     //      btn_open.setVisibility(View.VISIBLE);
       }



        btnSubmit.setOnClickListener(v->{

            if (TextUtils.isEmpty(txtName.getText().toString().trim()))
                txtName.setError("Please enter Name");
                //Toast.makeText(this, "Please enter Name", Toast.LENGTH_SHORT).show();
            else if (TextUtils.isEmpty(txtAddress.getText().toString().trim()))
                Toast.makeText(this, "Please enter an Address", Toast.LENGTH_SHORT).show();
            else if (TextUtils.isEmpty(txtEmail.getText().toString().trim()))
                Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();
            else if (TextUtils.isEmpty(txtDonType.getText().toString().trim()))
                Toast.makeText(this, "Please enter donationTYpe", Toast.LENGTH_SHORT).show();
            else if (TextUtils.isEmpty(txtDonGender.getText().toString().trim()))
                Toast.makeText(this, "Please enter gender", Toast.LENGTH_SHORT).show();
            else if (TextUtils.isEmpty(txtDonQuantity.getText().toString().trim()))
                Toast.makeText(this, "Please enter quantity", Toast.LENGTH_SHORT).show();
            else if (TextUtils.isEmpty(txtAge.getText().toString().trim()))
                Toast.makeText(this, "Please enter age", Toast.LENGTH_SHORT).show();
            else if (TextUtils.isEmpty(txtPeriod.getText().toString().trim()))
                Toast.makeText(this, "Please enter period", Toast.LENGTH_SHORT).show();
            else if (!chk.isChecked())
                Toast.makeText(this, "Please Accept privacy policy", Toast.LENGTH_SHORT).show();
            else if (!txtEmail.getText().toString().trim().matches(emailpattern))
                txtEmail.setError("Enter valid email");
            else if (txtAddress.getText().toString().trim().length()>20)
                txtAddress.setError("address must be max 20 characters");
            else {

                Organization org = new Organization(txtName.getText().toString(), txtAddress.getText().toString(), txtEmail.getText().toString(), txtDonType.getText().toString(), txtDonGender.getText().toString(), txtDonQuantity.getText().toString(), txtAge.getText().toString(), txtPeriod.getText().toString());
                if (org_edit == null) {
                    dao.add(org).addOnSuccessListener(suc -> {

                        Toast.makeText(this, "Data Saved Successfully", Toast.LENGTH_SHORT).show();
                        txtName.setText("");
                        txtAddress.setText("");
                        txtEmail.setText("");
                        txtDonType.setText("");
                        txtDonGender.setText("");
                        txtDonQuantity.setText("");
                        txtAge.setText("");
                        txtPeriod.setText("");
                        chk.setChecked(false);

                    }).addOnFailureListener(er -> {
                        Toast.makeText(this, "" + er.getMessage(), Toast.LENGTH_SHORT).show();

                    });
                } else {

                    HashMap<String, Object> hashMap = new HashMap<>();
                    hashMap.put("orgName", txtName.getText().toString());
                    hashMap.put("orgAddress", txtAddress.getText().toString());
                    hashMap.put("orgEmail", txtEmail.getText().toString());
                    hashMap.put("donationType", txtDonType.getText().toString());
                    hashMap.put("donationGender", txtDonGender.getText().toString());
                    hashMap.put("quantity", txtDonQuantity.getText().toString());
                    hashMap.put("ageGroup", txtAge.getText().toString());
                    hashMap.put("donationPeriod", txtPeriod.getText().toString());

                    dao.update(org_edit.getKey(), hashMap).addOnSuccessListener(suc -> {
                        Intent intent2 = new Intent(this, RVActivity.class);
                        this.startActivity(intent2);
                        Toast.makeText(this, "Data updated Successfully", Toast.LENGTH_SHORT).show();
                        finish();


                    }).addOnFailureListener(er -> {
                        Toast.makeText(this, "" + er.getMessage(), Toast.LENGTH_SHORT).show();

                    });

                }
            }


        });

    }


}