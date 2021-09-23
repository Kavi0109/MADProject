package com.example.cartandprofile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    ArrayList<User> list = new ArrayList<>();

    public RVAdapter(Context ctx){
        this.context = ctx;
    }

    public void setItems(ArrayList<User> user){
        list.addAll(user);
    }



    @Override
    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item,parent,false);
        return new UserVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position){
        UserVH vh = (UserVH) holder;
        User user = list.get(position);
        vh.txt_name.setText(user.getName());
        vh.txt_age.setText(user.getAge());
        vh.txt_bio.setText(user.getBio());
        vh.txt_school.setText(user.getSchool());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
