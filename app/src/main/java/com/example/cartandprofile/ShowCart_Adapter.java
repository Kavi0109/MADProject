package com.example.cartandprofile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ShowCart_Adapter extends RecyclerView.Adapter<ShowCart_Adapter.ViewHolder> {

    private Context context;
    private List<ShowCart_Model> showCart_modelList;

    public ShowCart_Adapter(Context context, List<ShowCart_Model> showCart_models) {
        this.context = context;
        this.showCart_modelList = showCart_models;
    }

    @NonNull
    @Override
    public ShowCart_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_order,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ShowCart_Model showCart_model = showCart_modelList.get(position);
        holder.product_name.setText(showCart_model.getProduct_name());
        holder.price.setText(showCart_model.getPrice());
    }

    @Override
    public int getItemCount() {
        return showCart_modelList.size();
    }

    public static class ViewHolder extends  RecyclerView.ViewHolder{

        private TextView product_name;
        private TextView price;

        public ViewHolder(View itemView){
            super(itemView);
            product_name = itemView.findViewById(R.id.product_name);
            price = itemView.findViewById(R.id.price);
        }
    }
}
