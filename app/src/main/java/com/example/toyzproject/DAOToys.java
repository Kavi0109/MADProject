package com.example.toyzproject;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.HashMap;

public class DAOToys
{

    private DatabaseReference databaseReference;
    public DAOToys()
    {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(Toys.class.getSimpleName());
    }

    //Insert
    public Task<Void> add(Toys toys)
    {

        return databaseReference.push().setValue(toys);
    }

    //Update
    public  Task<Void> update(String key, HashMap<String,Object> hashMap)
    {
        return databaseReference.child(key).updateChildren(hashMap);

    }


    //Delete
    public Task<Void> remove(String key)
    {

        return databaseReference.child(key).removeValue();
    }


    public Query get(String key)
    {
        if(key == null)
        {
           return databaseReference.orderByKey().limitToFirst(8);
        }
        return databaseReference.orderByKey().startAfter(key).limitToFirst(8);
    }



}
