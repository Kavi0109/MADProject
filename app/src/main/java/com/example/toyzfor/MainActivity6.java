package com.example.toyzfor;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class MainActivity6 extends AppCompatActivity {
	
	private static final int PICK_IMAGE_REQUEST = 1;
	private Button mButtonChooseImage;
	private Button mButtonUpload;
	private EditText mEditTextFileName;
	private ImageView mImageView;
	private Uri mImageUri;
	
	private StorageReference mStorageRef;
	private DatabaseReference mDatabaseRef;
	private StorageTask mUploadTask;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main6);
		
		mStorageRef = FirebaseStorage.getInstance().getReference("uploads");
		mDatabaseRef = FirebaseDatabase.getInstance().getReference("uploads");
		
		mButtonChooseImage = findViewById(R.id.button9);
		mButtonUpload = findViewById(R.id.button13);
		mEditTextFileName = findViewById(R.id.toyname);
		mImageView = findViewById(R.id.image_view);
		
		mButtonChooseImage.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				openFileChooser();
			}
		});
		mButtonUpload.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (mUploadTask != null && mUploadTask.isInProgress()) {
					Toast.makeText(MainActivity6.this, "Upload in progress", Toast.LENGTH_SHORT).show();
				} else {
					uploadFile();
				}
			}
		});
		
	}
	
	private void openFileChooser() {
		Intent intent = new Intent();
		intent.setType("image/*");
		intent.setAction(Intent.ACTION_GET_CONTENT);
		startActivityForResult(intent, PICK_IMAGE_REQUEST);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
				    && data != null && data.getData() != null) {
			mImageUri = data.getData();
			Picasso.get().load(mImageUri).into(mImageView);
		}
	}
	
	private void uploadFile() {
		if (mImageUri != null) {
			StorageReference fileReference = mStorageRef.child(System.currentTimeMillis()
					                                                   + "." + getFileExtension(mImageUri));
			mUploadTask = fileReference.putFile(mImageUri)
					              .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
						              @Override
						              public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
							              Handler handler = new Handler();
							              handler.postDelayed(new Runnable() {
								              @Override
								              public void run() {
								              }
							              }, 500);
							              Toast.makeText(MainActivity6.this, "Upload successful", Toast.LENGTH_LONG).show();
							              Upload upload = new Upload(mEditTextFileName.getText().toString().trim(),
									              taskSnapshot.getMetadata().getReference().getDownloadUrl().toString());
							              String uploadId = mDatabaseRef.push().getKey();
							              mDatabaseRef.child(uploadId).setValue(upload);
						              }
					              })
					              .addOnFailureListener(new OnFailureListener() {
						              @Override
						              public void onFailure(@NonNull Exception e) {
							              Toast.makeText(MainActivity6.this, e.getMessage(), Toast.LENGTH_SHORT).show();
						              }
					              })
					              .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
						              @Override
						              public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
							              double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
						              }
					              });
		} else {
			Toast.makeText(this, "No file selected", Toast.LENGTH_SHORT).show();
		}
	}
	
	private String getFileExtension(Uri uri) {
		ContentResolver cR = getContentResolver();
		MimeTypeMap mime = MimeTypeMap.getSingleton();
		return mime.getExtensionFromMimeType(cR.getType(uri));
	}
	
}