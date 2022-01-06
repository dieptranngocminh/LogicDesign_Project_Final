package com.example.logicdesign_project_final;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

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
        final TextView PhonenumberTextview = (TextView)findViewById(R.id.Phonenumber);
        final TextView IDTextview = (TextView)findViewById(R.id.ID);
        final TextView PasswordTextview = (TextView)findViewById(R.id.Password);

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserHelper userProfile = snapshot.getValue(UserHelper.class);

                if(userProfile != null){
                    String Fullname = userProfile.fullname;
                    String Dob = userProfile.DoB;
                    String StudentID = userProfile.studentID;
                    String Email = userProfile.email;
                    String Phonenumber = userProfile.phone;
                    String ID = userProfile.ID;
                    String Password = userProfile.password;

                    FullnameTextview.setText(Fullname);
                    DobTextview.setText(Dob);
                    StudentIDTextview.setText(StudentID);
                    EmailTextview.setText(Email);
                    PhonenumberTextview.setText(Phonenumber);
                    IDTextview.setText(ID);
                    PasswordTextview.setText(Password);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(personal_inf.this,"Something wrong happened!", Toast.LENGTH_LONG).show();
            }
        });
    }
}