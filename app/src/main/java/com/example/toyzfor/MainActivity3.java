package com.example.toyzfor;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class MainActivity3 extends AppCompatActivity {
	
	private EditText name, phone, email, password;
	private Button edit, ok;
	
	private FirebaseAuth auth;
	
	private DatabaseReference databaseReference;
	private FirebaseAuth firebaseAuth;
	private FirebaseDatabase firebaseDatabase;
	private FirebaseStorage firebaseStorage;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main3);
		
		firebaseAuth = FirebaseAuth.getInstance();
		databaseReference = FirebaseDatabase.getInstance().getReference();
		
		firebaseDatabase = FirebaseDatabase.getInstance();
		firebaseStorage = FirebaseStorage.getInstance();
		
		DatabaseReference databaseReference = firebaseDatabase.getReference(firebaseAuth.getUid());
		StorageReference storageReference = firebaseStorage.getReference();
		
		name = findViewById(R.id.textView7);
		phone = findViewById(R.id.editTextPhone);
		email = findViewById(R.id.editTextTextEmailAddress);
		password = findViewById(R.id.editTextTextPassword);
		
		name.setEnabled(false);
		phone.setEnabled(false);
		email.setEnabled(false);
		password.setEnabled(false);
		
		edit = findViewById(R.id.button6);
		ok = findViewById(R.id.button5);
		
		edit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				name.setEnabled(true);
				phone.setEnabled(true);
				email.setEnabled(true);
				password.setEnabled(true);
			}
		});
		
		ok.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				String n = name.getText().toString();
				String p = phone.getText().toString();
				String e = email.getText().toString();
				String pa = password.getText().toString();
				
				UserInfo ui = new UserInfo(n, p, e, pa);
				FirebaseUser user = firebaseAuth.getCurrentUser();
				databaseReference.child(user.getUid()).setValue(ui);
				
				user.updateEmail(e)
						.addOnCompleteListener(new OnCompleteListener<Void>() {
							@Override
							public void onComplete(@NonNull Task<Void> task) {
								if (task.isSuccessful()) {
									Log.d("LOG", "User email address updated.");
								}
							}
						});
				
				AuthCredential credential = EmailAuthProvider.getCredential(e, pa);
				user.reauthenticate(credential)
						.addOnCompleteListener(new OnCompleteListener<Void>() {
							@Override
							public void onComplete(@NonNull Task<Void> task) {
								Log.d("LOG", "User re-authenticated.");
							}
						});
				System.out.println("Successfull");
			}
		});
		
	}
}