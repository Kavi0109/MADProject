package com.example.cartandprofile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ShowCart extends AppCompatActivity {

    private RecyclerView rec;
    private List<ShowCart_Model> showCart_modelList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_cart);
        rec = findViewById(R.id.rec);
        rec.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        DatabaseReference show_info = FirebaseDatabase.getInstance().getReference("CartAndProfile");

        show_info.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    showCart_modelList.clear();
                    for(DataSnapshot snapshot1 : snapshot.getChildren()){
                        ShowCart_Model showCart_model = snapshot1.getValue(ShowCart_Model.class);
                        showCart_modelList.add(showCart_model);
                    }
                    rec.setAdapter(new ShowCart_Adapter(getApplicationContext(),showCart_modelList));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}