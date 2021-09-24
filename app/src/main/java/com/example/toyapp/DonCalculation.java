package com.example.toyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DonCalculation extends AppCompatActivity {

    Button btn_calculate;
    TextView tv_ans;
    EditText et_n1;
    EditText et_n2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_don_calculation);

        et_n1= findViewById(R.id.qReq);
        et_n2=findViewById(R.id.qRec);
        btn_calculate=findViewById(R.id.calc);
        tv_ans=findViewById(R.id.tv_answer);
        ImageView navcal = findViewById(R.id.calarrow);
        navcal.setOnClickListener(v->{
            Intent intent =new Intent(this, RVActivity.class);
            startActivity(intent);
        });
    }

    public void Calcu(View View) {
        Calculations cal = new Calculations();
        String num1 = et_n1.getText().toString();
        String num2 = et_n2.getText().toString();

        if(TextUtils.isEmpty(num1)){
            Toast.makeText(this,"Enter received value", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(num2)){
            Toast.makeText(this,"Enter required value", Toast.LENGTH_SHORT).show();
        }
        else {
            Float temp = Float.parseFloat(num1);
            Float temp2 = Float.parseFloat(num2);
            String temp3;

            temp3 = cal.calculatepercentageDonation(temp,temp2);

            //Toast.makeText(this,"select a radio button", Toast.LENGTH_SHORT).show();
            tv_ans.setText(new Float(temp3).toString()+"%");
        }

    }
}