package com.example.logicdesign_project_final;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Changeprofile extends AppCompatActivity {

    TextInputLayout fullname;
    TextInputLayout dob;
    TextInputLayout studentid;
    TextInputLayout email;
    TextInputLayout phonenumber;
    TextInputLayout id;
    TextInputLayout password;

    String NAME, DOB, STUDENTID, EMAIL, PHONENO, ID, PASSWORD;

    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changeprofile);

        reference = FirebaseDatabase.getInstance().getReference("Users");

        fullname = findViewById(R.id.Fullname);
        dob = findViewById(R.id.Dob);
        studentid = findViewById(R.id.StudentID);
        email = findViewById(R.id.Email);
        phonenumber = findViewById(R.id.Phonenumber);
        id = findViewById(R.id.ID);
        password = findViewById(R.id.Password);

        showAlluserdata();
    }

    private void showAlluserdata(){

        Intent intent = getIntent();
        NAME = intent.getStringExtra("fullName");
        DOB = intent.getStringExtra("doB");
        STUDENTID = intent.getStringExtra("studentID");
        EMAIL = intent.getStringExtra("email");
        PHONENO = intent.getStringExtra("phone");
        ID = intent.getStringExtra("id");
        PASSWORD = intent.getStringExtra("password");

        fullname.getEditText().getText(NAME);
        dob.setText(DOB);
        studentid.setText(STUDENTID);
        email.setText(EMAIL);
        phonenumber.setText(PHONENO);
        id.setText(ID);
        password.setText(PASSWORD);
    }

    public void update(View view){
        if(isNameChanged() || isDobChanged()){
            Toast.makeText(this,"Data has been updated", Toast.LENGTH_LONG).show();
        }
    }

    private boolean isDobChanged() {
        if(!DOB.equals(dob.getEditText().getText().toString())){
            reference.child(NAME).child("doB").setValue(fullname.getEditText().getText().toString());
            return true;
        }
        else{
            return false;
        }
    }

    private boolean isNameChanged() {
        if(!NAME.equals(fullname.getEditText().getText().toString())){
            reference.child(NAME).child("fullName").setValue(fullname.getEditText().getText().toString());
            return true;
        }
        else{
            return false;
        }
    }
}