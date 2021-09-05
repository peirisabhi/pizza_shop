package com.example.colombopizza;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class AdminConfirm extends AppCompatActivity {
    EditText txtACName, txtACPasscode;
    TextView attempts;

    int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_confirm);
        txtACName = findViewById(R.id.txtACName);
        txtACPasscode = findViewById(R.id.txtACPasscode);
        attempts = findViewById(R.id.lblAttempts);

        txtACName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        txtACPasscode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.newred)));
        statusbarcolor();
    }
    public void statusbarcolor(){
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
            getWindow().setStatusBarColor(getResources().getColor(R.color.design_default_color_error));
        }
    }


    public void Login(View view) {
        ProgressDialog progressDialog = new ProgressDialog(AdminConfirm.this);
        progressDialog.setTitle("Login");
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!txtACName.getText().toString().isEmpty() && !txtACPasscode.getText().toString().isEmpty()) {
                    String user = txtACName.getText().toString();
                    String pass = txtACPasscode.getText().toString();
                    if (user.equalsIgnoreCase("ColomboPizza") && pass.equals("54321")) {
                        Intent intent = new Intent(AdminConfirm.this,Adminlogin.class);
                        intent.putExtra("USER", user.toUpperCase());
                        startActivity(intent);
                    } else {
                        count++;
                        attempts.setText("Please Try Again. >> Attempts :  " + count);
                        // Toast.makeText(this, "Invaild Authentication!", Toast.LENGTH_SHORT).show();
                    }
                }
                progressDialog.dismiss();
            }
        }, 2000);

    }}
