package com.example.logicdesign_project_final;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.Result;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class qr_scanner extends AppCompatActivity {
    TextView txt;
    ProgressBar progressBar;

    CodeScanner codeScanner;
    CodeScannerView codeScannerView;

    FirebaseAuth mAuth;
    FirebaseDatabase  rootNode;
    DatabaseReference reference;
    FirebaseUser user;
    String userID;

    Button btn_rescan,btn_confirm;
    //Code
    String code;
    String [] split;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_scanner);

        txt = (TextView) findViewById(R.id.tv_textview);
        codeScannerView = (CodeScannerView) findViewById(R.id.scanner_view);
        codeScanner = new CodeScanner(this, codeScannerView);

        //Button
        btn_rescan = findViewById(R.id.btn_rescan);
        btn_confirm =findViewById(R.id.btn_confirm);

        progressBar = findViewById(R.id.progressBar3);
        // Firebase
        mAuth = FirebaseAuth.getInstance();
//Fire base
        rootNode = FirebaseDatabase.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        userID = user.getUid();
        reference = rootNode.getReference("Users").child(userID);

        progressBar.setVisibility(View.VISIBLE);
        txt.setVisibility(View.INVISIBLE);

        codeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull Result result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setVisibility(View.INVISIBLE);
                        txt.setVisibility(View.VISIBLE);
                        code = ProgressCode(result);

                        //txt.setText(result.getText());


                    }
                });
            }
        });

        codeScannerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                codeScanner.startPreview();
                progressBar.setVisibility(View.VISIBLE);
                txt.setVisibility(View.INVISIBLE);
            }
        });
        btn_rescan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressBar.setVisibility(View.VISIBLE);
                txt.setVisibility(View.INVISIBLE);
                codeScanner.startPreview();

                codeScanner.setDecodeCallback(new DecodeCallback() {
                    @Override
                    public void onDecoded(@NonNull Result result) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progressBar.setVisibility(View.INVISIBLE);
                                txt.setVisibility(View.VISIBLE);
                                code = ProgressCode(result);

                            }
                        });
                    }
                });

            }
        });

        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Take time on phone
                DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy hh:mm aaa");
                String date = df.format(Calendar.getInstance().getTime());
                Log.d("Time format",date);
                final String[] last_index = new String[1];
                PlacesHelper placesHelper = new PlacesHelper(split[0],split[1],date);

                reference.child("places").push().setValue(placesHelper);
                Log.d("QR",placesHelper.building + placesHelper.time+placesHelper.room);

                reference.child("places").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot ds : snapshot.getChildren()) {
                            String stt = ds.getKey();
                            Log.d("Places stt", stt);

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        requestCamera();
    }

    private void requestCamera() {
        codeScanner.startPreview();
    }

    private boolean checkFormat(String code){
        // ASCII 35 = #
        return code.indexOf("!") == 0 && 35 == code.charAt(code.length() - 1);
    }
    private String ProcessCode(String code){
        ///Slpit data
        code = code.replace("!","");
        code = code.replace("#","");
    return code;
    }

    private String ProgressCode(Result result){
        String code =result.getText();
        if(checkFormat(code))  {
            code = ProcessCode(code);
            txt.setText(code);
        }
        else{
            txt.setText("WRONG FORMAT");
        }
        split = code.split(":");
        return code;
    }

//    private boolean CheckBuilding(String building){
//        rootNode = FirebaseDatabase.getInstance();
//        reference = rootNode.getReference("Location");
//
//        reference.
//        DataSnapshot snapshot;
//
//        return snapshot.hasChild(building);
//    }

}