package com.example.logicdesign_project_final;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Changeprofile extends AppCompatActivity {

    TextView fullname;
    TextView dob;
    TextView studentid;
    TextView email;
    TextView phonenumber;
    TextView id;
    TextView password;

    String NAME, DOB, STUDENTID, EMAIL, PHONENO, ID, PASSWORD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changeprofile);

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

        fullname.setText(NAME);
        dob.setText(DOB);
        studentid.setText(STUDENTID);
        email.setText(EMAIL);
        phonenumber.setText(PHONENO);
        id.setText(ID);
        password.setText(PASSWORD);
    }

    public void update(View view){
        if(isNameChanged() || isDobChanged());
    }

    private boolean isDobChanged() {
        return true;
    }

    private boolean isNameChanged() {
        return true;
    }
}