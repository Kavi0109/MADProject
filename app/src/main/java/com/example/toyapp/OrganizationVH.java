package com.example.toyapp;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class OrganizationVH extends RecyclerView.ViewHolder{

    public TextView txt_name, txt_address, txt_email,txt_option, txt_DonType,txt_DonGender,txt_DonQuantity,txt_Age,txt_Period;

    public OrganizationVH(@NonNull View itemView) {
        super(itemView);
        txt_name=itemView.findViewById(R.id.txt_name);
        txt_address= itemView.findViewById(R.id.txt_address);
        txt_email= itemView.findViewById(R.id.txt_email);
//        txt_Phone = itemView.findViewById(R.id.txt_phone);
        txt_DonType= itemView.findViewById(R.id.txt_dontype);
        txt_DonGender= itemView.findViewById(R.id.txt_dongender);
       txt_DonQuantity= itemView.findViewById(R.id.txt_quantity);
        txt_Age= itemView.findViewById(R.id.txt_age);
        txt_Period= itemView.findViewById(R.id.txt_period);
        txt_option = itemView.findViewById(R.id.txt_option);
    }
}
