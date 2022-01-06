package com.example.logicdesign_project_final;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.Result;


public class qr_scanner extends AppCompatActivity {
    TextView txt;
    CodeScanner codeScanner;
    CodeScannerView codeScannerView;
    FirebaseAuth mAuth;
    FirebaseDatabase  rootNode;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_scanner);

        txt = (TextView) findViewById(R.id.tv_textview);
        codeScannerView = (CodeScannerView) findViewById(R.id.scanner_view);
        codeScanner = new CodeScanner(this, codeScannerView);

        // Firebase
        mAuth = FirebaseAuth.getInstance();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        FirebaseUser user = mAuth.getCurrentUser();

        codeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull Result result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String code = result.getText();
                        if(checkFormat(code))  {txt.setText(code);}
                        else{
                            txt.setText("WRONG FORMAT");
                        }



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
        if (code.indexOf("!") == 0 && code.lastIndexOf("#") == code.charAt(code.length()-1) ){
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

}