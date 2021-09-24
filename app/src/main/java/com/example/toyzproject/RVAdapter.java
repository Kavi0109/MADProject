package com.example.toyzproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private Context context;
    ArrayList<Toys> list = new ArrayList<>();
    public RVAdapter(Context ctx)
    {
        this.context = ctx;
    }

    public void setItems(ArrayList<Toys> toys)
    {
        list.addAll(toys);
    }





    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item,parent,false);
        return new ToysVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position)
    {
        ToysVH vh = (ToysVH) holder;
        Toys toys = list.get(position);
        vh.txt_toyid.setText(toys.getToyID());
        vh.txt_toyname.setText(toys.getToyName());
        vh.txt_toyDescription.setText(toys.getToyDescription());

    }

    @Override
    public int getItemCount()
    {
        return list.size();
    }
}
