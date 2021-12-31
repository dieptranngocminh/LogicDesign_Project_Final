package com.example.logicdesign_project_final;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class Register<ProgessBar> extends AppCompatActivity {
    EditText mFullName, mEmail, mPassWord, mPhone, mDob,mStudentID,mID;
    Button mRegisterBtn;
   // TextView mLoginBtn;
    FirebaseAuth fAuth;
    ProgessBar progessBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mFullName = findViewById(R.id.Fullname);
        mEmail = findViewById(R.id.Email);
        mPassWord = findViewById(R.id.password);
        mPhone = findViewById(R.id.Phonenumber);
        mRegisterBtn = findViewById(R.id.Register);
        //mLoginBtn = findViewById(R.id.createText);

        fAuth = FirebaseAuth.getInstance();
        //progessBar = findViewById(R.id.progressBar);
    }
    //Thay doi
}