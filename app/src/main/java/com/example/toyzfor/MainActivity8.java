package com.example.toyzfor;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity8 extends AppCompatActivity {
	
	private FirebaseAuth auth;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main8);
		
		auth = FirebaseAuth.getInstance();
		
		Intent intent = getIntent();
		
		String position = intent.getStringExtra("position");
		
		FirebaseDatabase database = FirebaseDatabase.getInstance();
		DatabaseReference ref = database.getReference("uploads");
		
		ref.addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot dataSnapshot) {
				String image = dataSnapshot.child("uploads").child(position).child("imageUrl").getValue().toString();
				String name = dataSnapshot.child("uploads").child(position).child("name").getValue().toString();
				System.out.println(image);
				System.out.println(name);
			}
			
			@Override
			public void onCancelled(DatabaseError databaseError) {
				System.out.println("The read failed: " + databaseError.getCode());
			}
		});
		
	}
}