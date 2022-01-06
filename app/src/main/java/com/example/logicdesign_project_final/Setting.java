package com.example.logicdesign_project_final;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Setting extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        Button changeprfbtn = (Button)findViewById(R.id.changeprofile);
        Button deleteaccbtn = (Button)findViewById(R.id.deleteaccount);

        changeprfbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int1=new Intent(Setting.this, Changeprofile.class);
                startActivity(int1);
            }
        });

        deleteaccbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int2=new Intent(Setting.this, Deleteaccount.class);
                startActivity(int2);
            }
        });
    }
}