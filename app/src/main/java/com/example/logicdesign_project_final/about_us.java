package com.example.logicdesign_project_final;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

public class about_us extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        TextView link = (TextView) findViewById(R.id.text22);
        link.setMovementMethod(LinkMovementMethod.getInstance());
    }
}