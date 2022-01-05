package com.example.logicdesign_project_final;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

public class Login extends AppCompatActivity implements View.OnClickListener {
    //private button
    private TextView register;
    private EditText editTextEmail, editTextPassword;
    private Button signIn;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        register= (TextView) findViewById(R.id.create_new_account);
        register.setOnClickListener(this);

        signIn =  (Button) findViewById(R.id.button_Login);
        signIn.setOnClickListener(this);

        editTextEmail=(EditText) findViewById(R.id.Username);
        editTextPassword=(EditText) findViewById(R.id.Password);

        mAuth =FirebaseAuth.getInstance();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.create_new_account:
                startActivity(new Intent(this, Register.class));
                break;

            case R.id.button_Login:
                userLogin();
                break;
        }
    }
    private void userLogin(){

        String email = editTextEmail.getText().toString().trim();
        String password= editTextPassword.getText().toString().trim();

        if(email.isEmpty()){
            editTextEmail.setError("Email isrequired!");
            editTextEmail.requestFocus();
            return;
        }
        if(password.length()<6){
            editTextPassword.setError("Min pass is 6 characters");
            editTextPassword.requestFocus();
            return;
        }
        //progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this,new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    FirebaseUser  user = FirebaseAuth.getInstance().getCurrentUser();
                    if(user.isEmailVerified()) {
                        Log.d("Login", "signInWithEmail:success");
                        //redirect to Main Activity
                        startActivity(new Intent(Login.this, MainActivity.class));
                    }
                    else {
                        user.sendEmailVerification();
                        Toast.makeText(Login.this, "Check your email to verify !", Toast.LENGTH_SHORT).show();
                    }

                }
                else {
                    Toast.makeText(Login.this, "Failed to login! Please check input", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}