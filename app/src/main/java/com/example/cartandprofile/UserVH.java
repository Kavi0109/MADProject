package com.example.cartandprofile;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class UserVH extends RecyclerView.ViewHolder {

    public TextView txt_name,txt_age,txt_bio,txt_school,txt_option;

    public UserVH(@NonNull View itemView){
        super(itemView);
        txt_name = itemView.findViewById(R.id.txt_name);
        txt_age = itemView.findViewById(R.id.txt_age);
        txt_bio = itemView.findViewById(R.id.txt_bio);
        txt_school = itemView.findViewById(R.id.txt_school);
        txt_option = itemView.findViewById(R.id.txt_option);

    }
}
