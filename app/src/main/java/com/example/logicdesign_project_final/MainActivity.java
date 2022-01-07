package com.example.logicdesign_project_final;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private Button logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button qrscannerbtn = (Button)findViewById(R.id.qrscanner);
        Button routebtn = (Button)findViewById(R.id.route);
        Button personalinfbtn = (Button)findViewById(R.id.personalinf);
        Button settingbtn = (Button)findViewById(R.id.setting);
        Button usermanualbtn = (Button)findViewById(R.id.usermanual);
        Button aboutusbtn = (Button)findViewById(R.id.aboutus);

        qrscannerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int1=new Intent(MainActivity.this, qr_scanner.class);
                startActivity(int1);
            }
        });

        routebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int2=new Intent(MainActivity.this, route_history.class);
                startActivity(int2);
            }
        });

        personalinfbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int3=new Intent(MainActivity.this, personal_inf.class);
                startActivity(int3);
            }
        });

        settingbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int4=new Intent(MainActivity.this, Setting.class);
                startActivity(int4);
            }
        });

        usermanualbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int5=new Intent(MainActivity.this, Usermanual.class);
                startActivity(int5);
            }
        });

        aboutusbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int6=new Intent(MainActivity.this, about_us.class);
                startActivity(int6);
            }
        });
        logout = (Button) findViewById(R.id.Logout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(MainActivity.this, Login.class));
            }
        });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1: {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(MainActivity.this, "Permission denied to read your External storage", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
}