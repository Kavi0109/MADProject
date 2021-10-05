package com.example.toyzproject;

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
        vh.txt_toycolour.setText(toys.getToyColor());
        vh.txt_toyBrand.setText(toys.getToyBrand());
        vh.txt_toyModel.setText(toys.getToyModel());
        vh.txt_toyPrice.setText(toys.getToyPrice());
        vh.txt_toyDate.setText(toys.getToyDate());

        vh.txt_option.setOnClickListener(v->
        {
            PopupMenu popupMenu =new PopupMenu(context,vh.txt_option);
            popupMenu.inflate(R.menu.option_menu);
            popupMenu.setOnMenuItemClickListener(item->
            {
                switch(item.getItemId())
                {
                    case R.id.menu_edit:
                        Intent intent =new Intent(context,ToysAddForm.class);
                        intent.putExtra("EDIT", toys);
                        context.startActivity(intent);
                        break;

                    case R.id.menu_remove:
                        //Delete
                        DAOToys dao = new DAOToys();
                        dao.remove(toys.getKey()).addOnSuccessListener(suc->
                        {
                            Toast.makeText(context, "Record is Removed!", Toast.LENGTH_SHORT).show();
                            notifyItemRemoved(position);

                        }).addOnFailureListener(er->
                        {
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
