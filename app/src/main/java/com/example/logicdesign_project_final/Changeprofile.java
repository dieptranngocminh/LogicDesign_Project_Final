package com.example.logicdesign_project_final;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Changeprofile extends AppCompatActivity {

    EditText fullname,dob,studentid,email,phonenumber,id,password;

    String NAME, DOB, STUDENTID, EMAIL, PHONENO, ID, PASSWORD;

    DatabaseReference reference;
    FirebaseAuth mAuth;
    FirebaseUser user;
    Button updatebtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changeprofile);

        mAuth= FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");

        fullname = findViewById(R.id.Fullname_prof);
        dob = findViewById(R.id.Dob_prof);
        studentid = findViewById(R.id.StudentID_prof);
        //email = findViewById(R.id.Email_prof);
        phonenumber = findViewById(R.id.Phonenumber_prof);
        id = findViewById(R.id.ID_prof);
        //password = findViewById(R.id.password_prof);

        updatebtn = findViewById(R.id.updatebtn);

        reference.child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserHelper userProfile = snapshot.getValue(UserHelper.class);

                if(userProfile != null){
                     NAME = userProfile.fullname;
                     DOB = userProfile.DoB;
                     STUDENTID = userProfile.studentID;
                     EMAIL = userProfile.email;
                     PHONENO = userProfile.phone;
                     ID = userProfile.ID;
                     //PASSWORD = userProfile.password;

                     fullname.setText(NAME);
                     dob.setText(DOB);
                    studentid.setText(STUDENTID);
                    //email.setText(EMAIL);
                    phonenumber.setText(PHONENO);
                    id.setText(ID);
                    //password.setText(PASSWORD);

//                    FullnameTextview.setText(Fullname);
//                    DobTextview.setText(Dob);
//                    StudentIDTextview.setText(StudentID);
//                    EmailTextview.setText(Email);
//                    PhonenumberTextview.setText(Phonenumber);
//                    IDTextview.setText(ID);
//                    PasswordTextview.setText(Password);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Changeprofile.this,"Something wrong happened!", Toast.LENGTH_LONG).show();
            }
        });
        //showAlluserdata();
        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update(view);
            }
        });

    }

//    private void showAlluserdata(){
//
//        Intent intent = getIntent();
//        NAME = intent.getStringExtra("fullName");
//        DOB = intent.getStringExtra("doB");
//        STUDENTID = intent.getStringExtra("studentID");
//        EMAIL = intent.getStringExtra("email");
//        PHONENO = intent.getStringExtra("phone");
//        ID = intent.getStringExtra("id");
//        PASSWORD = intent.getStringExtra("password");

//        fullname.getEditText().setText(NAME);
//        dob.getEditText().setText(DOB);
//        studentid.getEditText().setText(STUDENTID);
//        email.getEditText().setText(EMAIL);
//        phonenumber.getEditText().setText(PHONENO);
//        id.getEditText().setText(ID);
//        password.getEditText().setText(PASSWORD);
//    }

    public void update(View view){
        if(isNameChanged() || isDobchanged() || isStudentidChanged() || isPhonenumberChanged() || isIDChanged()){
            Toast.makeText(this,"Data has been updated", Toast.LENGTH_LONG).show();
        }
        else Toast.makeText(this,"Data is same and can not be updated", Toast.LENGTH_LONG).show();
    }

    private boolean isIDChanged() {
//        if(id.getText().toString() != "") {
            if (!ID.equals(id.getText().toString())) {
                reference.child(user.getUid()).child("id").setValue(id.getText().toString());
                ID = id.getText().toString();
                return true;
            } else {
                return false;
            }
//        }
//        return false;
    }

    private boolean isPhonenumberChanged() {
        if(!PHONENO.equals(phonenumber.getText().toString())){
            reference.child(user.getUid()).child("phone").setValue(phonenumber.getText().toString());
            PHONENO = phonenumber.getText().toString();
            return true;
        }
        else{
            return false;
        }
    }

//    private boolean isEmailChanged() {
//        if(!EMAIL.equals(email.getText().toString())){
//            reference.child(user.getUid()).child("email").setValue(email.getText().toString());
//            EMAIL = email.getText().toString();
//            return true;
//        }
//        else{
//            return false;
//        }
//    }

    private boolean isStudentidChanged() {
        if(!STUDENTID.equals(studentid.getText().toString())){
            reference.child(user.getUid()).child("studentID").setValue(studentid.getText().toString());
            STUDENTID = studentid.getText().toString();
            return true;
        }
        else{
            return false;
        }
    }

    private boolean isDobchanged() {
        if(!DOB.equals(dob.getText().toString())){
            reference.child(user.getUid()).child("doB").setValue(dob.getText().toString());
            DOB = dob.getText().toString();
            return true;
        }
        else{
            return false;
        }
    }

//    private boolean isPasswordChanged() {
//        if(!PASSWORD.equals(password.getText().toString())){
//            reference.child(user.getUid()).child("password").setValue(password.getText().toString());
//            PASSWORD = password.getText().toString();
//            return true;
//        }
//        else{
//            return false;
//        }
//    }

    private boolean isNameChanged() {
        if(!NAME.equals(fullname.getText().toString())){
            reference.child(user.getUid()).child("fullName").setValue(fullname.getText().toString());
            NAME = fullname.getText().toString();
            return true;
        }
        else{
            return false;
        }
    }
}