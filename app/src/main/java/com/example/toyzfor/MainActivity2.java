package com.example.toyzfor;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

public class MainActivity2 extends AppCompatActivity {
	
	private EditText name, p_number, email, password;
	private Button sign_in, sign_up;
	
	private FirebaseAuth auth;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main2);
		
		auth = FirebaseAuth.getInstance();
		
		name = findViewById(R.id.textView3);
		p_number = findViewById(R.id.textView4);
		email = findViewById(R.id.textView5);
		password = findViewById(R.id.textView6);
		
		sign_in = findViewById(R.id.button3);
		sign_up = findViewById(R.id.button4);
		
		sign_up.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				
				String mail = email.getText().toString();
				String pass = password.getText().toString();
				
				auth.createUserWithEmailAndPassword(mail, pass).addOnCompleteListener(MainActivity2.this, new OnCompleteListener<AuthResult>() {
					@Override
					public void onComplete(@NonNull Task<AuthResult> task) {
						if (task.isSuccessful()) {
							// Sign in success, update UI with the signed-in user's information
							Log.d("LOG", "createUserWithEmail:success");
							startActivity(new Intent(MainActivity2.this, MainActivity5.class));
							finish();
						} else {
							// If sign in fails, display a message to the user.
							Log.w("LOG", "createUserWithEmail:failure", task.getException());
							Toast.makeText(MainActivity2.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
						}
					}
				});
			}
		});
		
		sign_in.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				startActivity(new Intent(MainActivity2.this, MainActivity.class));
				finish();
			}
		});
		
	}
}