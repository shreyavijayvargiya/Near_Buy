package com.example.nearbuy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class CreateAccActivity extends AppCompatActivity {
    private EditText editTextName;
    private EditText editTextMail;
    private EditText editTextPhone;
    private EditText editTextAddress;
    private Button createbtn;
    private DatabaseReference mUserDatabaseReference;
    private FirebaseDatabase database;
    private  FirebaseAuth mAuth;
    private static final String USER= "user";
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_acc);

        database= FirebaseDatabase.getInstance();
        mUserDatabaseReference = database.getReference("USER");
        mAuth= FirebaseAuth.getInstance();
        editTextName = findViewById(R.id.name);
        editTextMail = findViewById(R.id.mail);
        editTextPhone = findViewById(R.id.phone);
        editTextAddress = findViewById(R.id.address);

        createbtn = (Button) findViewById(R.id.create);

        createbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextName.getText().toString().trim();
                String mail = editTextMail.getText().toString().trim();
                String phone = editTextPhone.getText().toString().trim();
                String address = editTextAddress.getText().toString().trim();
                validateInputs(name, mail, phone, address);
                user = new User(name, mail, phone, address);
                mUserDatabaseReference.push().setValue(user);
                registerUser(name, mail);
            }
        });

    }

    public void registerUser(String name, String mail){
        mAuth.createUserWithEmailAndPassword(name, mail)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            Toast.makeText(CreateAccActivity.this, "Account Created!",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }

    public void updateUI(FirebaseUser currentUser){
        String keyId= mUserDatabaseReference.push().getKey();
        mUserDatabaseReference.child(keyId).setValue(user);


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
        }

      return false;
    }


}