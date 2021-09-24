package com.example.toyapp;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DAOFeedback {

    private DatabaseReference dbref;

    public DAOFeedback() {
        FirebaseDatabase dbi = FirebaseDatabase.getInstance();
        dbref = dbi.getReference(Feedback.class.getSimpleName());
    }

    public Task<Void> addfeed(Feedback fb){
        return dbref.push().setValue(fb);

    }
}
