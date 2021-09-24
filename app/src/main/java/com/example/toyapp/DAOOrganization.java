package com.example.toyapp;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.HashMap;

public class DAOOrganization {

    private DatabaseReference dbRef;

    public DAOOrganization() {

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        dbRef = db.getReference(Organization.class.getSimpleName());
    }

    public Task<Void> add(Organization org){
        return dbRef.push().setValue(org);

    }

    public Task<Void> update(String key, HashMap<String,Object> hashMap){
        return dbRef.child(key).updateChildren(hashMap);
    }
    public Task<Void> remove(String key){

        return dbRef.child(key).removeValue();
    }

    public Query get(String key)
    {

        if(key == null){
            return dbRef.orderByKey().limitToFirst(8);

       }
       return dbRef.orderByKey().startAfter(key).limitToFirst(8);


    }



}
