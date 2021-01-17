package com.example.nearbuy;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class ProfileFragment extends Fragment {
TextView name, phone, mail, address;
private FirebaseDatabase database;
private DatabaseReference userRef;
private static final String USERS= "users";

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_profile, container, false);

        Button button , button1;
        FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();

        name= view.findViewById(R.id.Name);
        phone= view.findViewById(R.id.phoneNo);
        mail= view.findViewById(R.id.eMail);
       address= view.findViewById(R.id.Address);

       database= FirebaseDatabase.getInstance();
       userRef= database.getReference(USERS);
       mFirebaseAuth.getCurrentUser();
       userRef.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               for(DataSnapshot ds: dataSnapshot.getChildren()){
                  name.setText(ds.child("name").getValue(String.class));
                   phone.setText(ds.child("phone").getValue(String.class));
                   mail.setText(ds.child("mail").getValue(String.class));
                   address.setText(ds.child("address").getValue(String.class));

               };
           }

           @Override
           public void onCancelled(@NonNull DatabaseError error) {

           }
       });


         button = (Button) view.findViewById(R.id.acc);
         button.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent = new Intent(getActivity(), CreateAccActivity.class);
                 startActivity(intent);
             }
         });
         button1=(Button)view.findViewById(R.id.logout);
         button1.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 //mFirebaseAuth.signOut();
                 Toast.makeText(getActivity(),"signed out",Toast.LENGTH_SHORT).show();
                 //getActivity().finish();
                 System.exit(0);
             }
         });

        return view ;


    }
}