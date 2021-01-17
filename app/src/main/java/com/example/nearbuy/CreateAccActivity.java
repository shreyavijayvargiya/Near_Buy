package com.example.nearbuy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;


public class CreateAccActivity extends AppCompatActivity {
    private EditText editTextName;
    private EditText editTextMail;
    private EditText editTextPhone;
    private EditText editTextAddress;
    Button createbtn = findViewById(R.id.create);

    private DatabaseReference mUserDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_acc);

        FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();
        FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();

        editTextName = findViewById(R.id.name);
        editTextMail = findViewById(R.id.mail);
        editTextPhone = findViewById(R.id.phone);
        editTextAddress = findViewById(R.id.address);

        mUserDatabaseReference = mFirebaseDatabase.getReference().child("users");

        createbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextName.getText().toString().trim();
                String mail = editTextMail.getText().toString().trim();
                String phone = editTextPhone.getText().toString().trim();
                String address = editTextAddress.getText().toString().trim();

                User user = new User(name, mail, phone, address);
                mUserDatabaseReference.push().setValue(user);
            }
        });
    }

    private boolean validateInputs(String name, String mail, String phone, String address) {
        if (name.isEmpty()) {
            editTextName.setError("Name required");
            editTextName.requestFocus();
            return true;
        }

        if (mail.isEmpty()) {
            editTextMail.setError("Mail ID required");
            editTextMail.requestFocus();
            return true;
        }

        if (phone.isEmpty()) {
            editTextPhone.setError("Phone required");
            editTextPhone.requestFocus();
            return true;
        }

        if (address.isEmpty()) {
            editTextAddress.setError("Address required");
            editTextAddress.requestFocus();
            return true;
        }


        return false;
    }



        //if (!validateInputs(name, mail, phone, address)) {

            // CollectionReference dbProducts = db.collection("User");

            // User user = new User(
            //       name,
            //     mail,
            //   phone,
            // address
            //);
            // dbProducts.add(user)
            // .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            // @Override
            //public void onSuccess(DocumentReference documentReference) {
            // Toast.makeText(CreateAccActivity.this, "Product Added", Toast.LENGTH_LONG).show();
            //}
            //})
            //.addOnFailureListener(new OnFailureListener() {
            //   @Override
            // public void onFailure(@NonNull Exception e) {
            //Toast.makeText(CreateAccActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
            // }
            // });

        }