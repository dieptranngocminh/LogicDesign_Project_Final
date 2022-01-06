package com.example.logicdesign_project_final;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.Result;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class qr_scanner extends AppCompatActivity {
    TextView txt;
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

        // Firebase
        mAuth = FirebaseAuth.getInstance();



        codeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull Result result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        code = result.getText();
                        if(checkFormat(code))  {txt.setText(code);}
                        else{
                            txt.setText("WRONG FORMAT");
                        }

                        //txt.setText(result.getText());


                    }
                });
            }
        });

        btn_rescan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                codeScanner.setDecodeCallback(new DecodeCallback() {
                    @Override
                    public void onDecoded(@NonNull Result result) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                code = result.getText();
                                if(checkFormat(code))  {txt.setText(code);}
                                else{
                                    txt.setText("WRONG FORMAT");
                                }

                                //txt.setText(result.getText());


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
                DateFormat df = new SimpleDateFormat("h:mm a");
                String date = df.format(Calendar.getInstance().getTime());

                //Fire base
                user = FirebaseAuth.getInstance().getCurrentUser();
                reference = FirebaseDatabase.getInstance().getReference("Users");
                userID = user.getUid();

//                reference.child("places").limitToLast(1)
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
        if (code.indexOf("!") == 0 && 35 == code.charAt(code.length()-1) ){
            // ASCII 35 = #
            return true;
        }
        return false;
    }
    private String ProcessCode(String code){
        ///Slpit data
        code.replace("!","");
        code.replace("#","");
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