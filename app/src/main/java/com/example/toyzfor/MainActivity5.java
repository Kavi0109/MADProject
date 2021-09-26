package com.example.toyzfor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity5 extends AppCompatActivity implements ItemClickListener {
	
	private FirebaseAuth auth;
	DatabaseReference database;
	
	private RecyclerView mRecyclerView;
	private MyAdapter mAdapter;
	private List<Upload> uploads;
	
	Button swap, add, user;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main5);
		
		auth = FirebaseAuth.getInstance();
		database = FirebaseDatabase.getInstance().getReference("uploads");
		uploads = new ArrayList<>();
		
		mRecyclerView = (RecyclerView) findViewById(R.id.toyList);
		mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
		mRecyclerView.setItemAnimator(new DefaultItemAnimator());
		
		swap = findViewById(R.id.button11);
		add = findViewById(R.id.button10);
		user = findViewById(R.id.button8);
		
		database.addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(@NonNull DataSnapshot snapshot) {
				
				for (DataSnapshot postSnapshot : snapshot.getChildren()) {
					Upload upload = postSnapshot.getValue(Upload.class);
					uploads.add(upload);
				}
				mAdapter = new MyAdapter(uploads, R.layout.item, MainActivity5.this);
				mRecyclerView.setAdapter(mAdapter);
				mAdapter.setClickListener(MainActivity5.this);
				mAdapter.notifyDataSetChanged();
				
			}
			
			@Override
			public void onCancelled(@NonNull DatabaseError error) {
			
			}
		});
		
		add.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				startActivity(new Intent(MainActivity5.this, MainActivity6.class));
			}
		});
		
		swap.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				startActivity(new Intent(MainActivity5.this, MainActivity7.class));
			}
		});
		
		user.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				startActivity(new Intent(MainActivity5.this, MainActivity3.class));
			}
		});
	}
	
	@Override
	public void onClick(View view, int position) {
		Intent i = new Intent(MainActivity5.this, MainActivity8.class);
		i.putExtra("position", position + "");
		startActivity(i);
	}
	
	@Override
	public void onPointerCaptureChanged(boolean hasCapture) {
	}
}