package com.example.toyapp;

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

public class RVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context context;
    ArrayList<Organization> list = new ArrayList<>();
    public RVAdapter(Context ctx)
    {
        this.context = ctx;
    }
    public void setItems(ArrayList<Organization> org)
    {
        list.addAll(org);
    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item,parent,false);
        return new OrganizationVH(view);

    }

    @Override
//    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
//
//
//        Organization o = null;
//        this.onBindViewHolder(holder,position,o);
//
//    }


    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

       OrganizationVH vh= (OrganizationVH) holder;
       //Organization org = o==null? list.get(position):o;
       Organization org = list.get(position);
       vh.txt_name.setText(org.getOrgName());
       vh.txt_address.setText(org.getOrgAddress());
       vh.txt_email.setText(org.getOrgEmail());
 //      vh.txt_Phone.setText(org.getOrgPhone());
       vh.txt_DonType.setText(org.getDonationType());
       vh.txt_DonGender.setText(org.getDonationGender());
       vh.txt_DonQuantity.setText(org.getQuantity());
       vh.txt_Age.setText(org.getAgeGroup());
       vh.txt_Period.setText(org.getDonationPeriod());

       vh.txt_option.setOnClickListener(v->{

           PopupMenu popupMenu =new PopupMenu(context,vh.txt_option);
           popupMenu.inflate(R.menu.option_menue);
           popupMenu.setOnMenuItemClickListener(item->
           {
               switch (item.getItemId()) {
                   case R.id.menu_edit:
                       Intent intent = new Intent(context, MainActivity.class);
                       intent.putExtra("EDIT",org);
                       context.startActivity(intent);
                       break;
                   case R.id.menu_remove:
                       DAOOrganization dao=new DAOOrganization();
                       dao.remove(org.getKey()).addOnSuccessListener(suc->
                       {

                           Toast.makeText(context, "Record is removed", Toast.LENGTH_SHORT).show();
                           Intent intent2 = new Intent(context, RVActivity.class);
                           context.startActivity(intent2);
                           notifyItemRemoved(position);

                          // list.remove(org);
                       }).addOnFailureListener(er->
                       {
                           Toast.makeText(context, ""+er.getMessage(), Toast.LENGTH_SHORT).show();
                       });

                       break;
                   case R.id.menu_calculate:
                       Intent intent4 = new Intent(context, DonCalculation.class);
                       context.startActivity(intent4);

                       break;

               }

               return false;

            });

           popupMenu.show();
       });
    }



    @Override
    public int getItemCount() {
        //send array list
        return list.size();
    }
}

