package com.example.cartandprofile;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
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
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item,parent,false);
        return new UserVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position)
    {
        UserVH vh = (UserVH) holder;
        User user = list.get(position);
        vh.txt_name.setText(user.getName());
        vh.txt_age.setText(user.getAge());
        vh.txt_bio.setText(user.getBio());
        vh.txt_school.setText(user.getSchool());

        vh.txt_option.setOnClickListener(v ->
        {
            PopupMenu popupMenu = new PopupMenu(context,vh.txt_option);
            popupMenu.inflate(R.menu.options_menu);
            popupMenu.setOnMenuItemClickListener(item ->
            {
                switch (item.getItemId())
                {
                    case R.id.menu_edit:
                        Intent intent = new Intent(context,EnterProfile.class);
                        intent.putExtra("EDIT",user);
                        context.startActivity(intent);
                        break;
                    case R.id.menu_remove:
                        DAOUser dao = new DAOUser();
                        dao.remove(user.getKey()).addOnSuccessListener(suc->{
                            Toast.makeText(context,"Record is removed",Toast.LENGTH_SHORT).show();
                            notifyItemRemoved(position);
                        }).addOnFailureListener(er->{
                            Toast.makeText(context,""+er.getMessage(),Toast.LENGTH_SHORT).show();
                        });

                        break;
                }
                return false;
            });
            popupMenu.show();
        });

    }

    @Override
    public int getItemCount()
    {
        return list.size();
    }
}
