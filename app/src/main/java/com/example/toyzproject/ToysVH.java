package com.example.toyzproject;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ToysVH extends RecyclerView.ViewHolder
{

    public TextView txt_toyid,txt_toyname,txt_toyDescription,txt_toycolour,txt_toyBrand,txt_toyModel,txt_toyPrice,txt_toyDate, txt_option;
    public ToysVH(@NonNull View itemView)
    {
        super(itemView);
        txt_toyid = itemView.findViewById(R.id.txt_toyid);
        txt_toyname = itemView.findViewById(R.id.txt_toyname);
        txt_toyDescription = itemView.findViewById(R.id.txt_toyDescription);
        txt_toycolour = itemView.findViewById(R.id.txt_toycolour);
        txt_toyBrand = itemView.findViewById(R.id.txt_toyBrand);
        txt_toyModel = itemView.findViewById(R.id.txt_toyModel);
        txt_toyPrice = itemView.findViewById(R.id.txt_toyPrice);
        txt_toyDate = itemView.findViewById(R.id.txt_toyDate);
        txt_option = itemView.findViewById(R.id.txt_option);
    }
}
