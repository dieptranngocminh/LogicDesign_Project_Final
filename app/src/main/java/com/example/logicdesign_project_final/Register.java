package com.example.logicdesign_project_final;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {
    EditText mFullName, mEmail, mPassWord, mPhone, mDob,mStudentID,mID;
    Button mRegisterBtn;
   // TextView mLoginBtn;
    FirebaseAuth fAuth;
    ProgressBar progessBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mFullName=findViewById(R.id.Fullname);
        mEmail=findViewById(R.id.Email);
        mPassWord=findViewById(R.id.password);
        mPhone=findViewById(R.id.Phonenumber);
        mRegisterBtn=findViewById(R.id.Register);
        mDob=findViewById(R.id.Dob);
        mStudentID=findViewById(R.id.StudentID);
        mID=findViewById(R.id.ID);
        //mLoginBtn=findViewById(R.id.createText);

        fAuth=FirebaseAuth.getInstance();
        progessBar=findViewById(R.id.progressBar2);


        if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }

        // Write a message to the database



        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mEmail.getText().toString().trim();
                String password = mPassWord.getText().toString().trim();

                if(TextUtils.isEmpty((email))){
                    mEmail.setError("Email is Required.");
                    return;
                }
                if(TextUtils.isEmpty((password))){
                    mPassWord.setError("Passwrod is Required.");
                    return;
                }
                if(password.length() < 6){
                    mPassWord.setError("Passwrod Must be >= Character.");
                    return;
                }

                progessBar.setVisibility(View.VISIBLE);


                // resgiter the user in Firebase

                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            Toast.makeText(Register.this, "User Created.",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }else{
                            Toast.makeText(Register.this, "Error !" + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
//                // Write a message to the database
                FirebaseDatabase database = FirebaseDatabase.getInstance("https://adt-qrscan-default-rtdb.firebaseio.com/");
                DatabaseReference myRef = database.getReference("users");

                myRef.setValue("Hello, World!");
            }
        });
    }
    //Thay doi
}