package com.example.logicdesign_project_final;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {
    EditText mFullName, mEmail, mPassWord, mPhone, mDob,mStudentID,mID;
    Button mRegisterBtn;
   // TextView mLoginBtn;
    FirebaseAuth fAuth;
    ProgressBar progessBar;

    FirebaseDatabase rootNode;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mFullName = findViewById(R.id.Fullname);
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
                String fullname = mFullName.getText().toString().trim();
                String DoB = mDob.getText().toString().trim();
                String phone = mPhone.getText().toString().trim();
                String studentID = mStudentID.getText().toString().trim();
                String ID = mID.getText().toString().trim();

                if(fullname.isEmpty() ){
                    mFullName.setError("Full name is Required.");
                    return;
                }
                if(DoB.isEmpty()){
                    mDob.setError("Day of Birth is Required.");
                    return;
                }
                if(studentID.isEmpty() ){
                    mStudentID.setError("Student ID is Required.");
                    return;
                }
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

                if(ID.isEmpty() ){
                    mID.setError("Your ID is Required.");
                    return;
                }


                if(phone.isEmpty() ){
                    mPhone.setError("Phone number is Required.");
                    return;
                }


                progessBar.setVisibility(View.VISIBLE);


                // resgiter the user in Firebase

                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser user = fAuth.getCurrentUser();
                            Toast.makeText(Register.this, "User Created.",Toast.LENGTH_SHORT).show();
                            // Write USER to the database

                            //Get Reference
                            rootNode = FirebaseDatabase.getInstance();
                            reference = rootNode.getReference("1952168");

                            UserHelper userHelper= new UserHelper(fullname,DoB,studentID,email,password,phone,ID);

                            reference.setValue(userHelper);
                            Log.d("Regiter database"," Success Add New");

                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }else{
                            Toast.makeText(Register.this, "Error !" + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
//
            }
        });
    }
    //Thay doi
}