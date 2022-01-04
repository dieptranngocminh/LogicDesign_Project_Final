package com.example.logicdesign_project_final;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class personal_inf extends AppCompatActivity {

    private FirebaseUser user;
    private DatabaseReference reference;

    private String userID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_inf);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();

        final TextView FullnameTextview = (TextView)findViewById(R.id.Fullname);
        final TextView DobTextview = (TextView)findViewById(R.id.Dob);
        final TextView StudentIDTextview = (TextView)findViewById(R.id.StudentID);
        final TextView EmailTextview = (TextView)findViewById(R.id.Email);
        final TextView PhonenameTextview = (TextView)findViewById(R.id.Phonenumber);
        final TextView IDTextview = (TextView)findViewById(R.id.ID);
        final TextView PasswordTextview = (TextView)findViewById(R.id.Password);

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}