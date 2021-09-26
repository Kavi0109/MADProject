package com.example.toyzfor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
	
	private EditText username, password;
	private Button sign_in, sing_up;
	
	private FirebaseAuth auth;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		auth = FirebaseAuth.getInstance();
		
		username = findViewById(R.id.textView);
		password = findViewById(R.id.textView2);
		
		sign_in = findViewById(R.id.button);
		sing_up = findViewById(R.id.button2);
		
		sing_up.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				startActivity(new Intent(MainActivity.this, MainActivity2.class));
				finish();
			}
		});
		
		sign_in.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				String email = username.getText().toString();
				String pasword = password.getText().toString();
				
				auth.signInWithEmailAndPassword(email, pasword).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
					@Override
					public void onComplete(@NonNull Task<AuthResult> task) {
						if (task.isSuccessful()) {
							startActivity(new Intent(MainActivity.this, MainActivity5.class));
							finish();
						} else {
							Toast.makeText(MainActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
						}
					}
				});
				
			}
		});
		
	}
}