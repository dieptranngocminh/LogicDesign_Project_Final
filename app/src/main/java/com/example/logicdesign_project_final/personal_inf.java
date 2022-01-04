package com.example.logicdesign_project_final;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;

public class personal_inf extends AppCompatActivity {

    private FirebaseUser user;
    private DatabaseReference reference;

    private String userID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_inf);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase
    }
}