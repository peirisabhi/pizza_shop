package com.example.colombopizza;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

public class Administration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administration);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.newred)));
        statusbarcolor();
    }
    public void statusbarcolor(){
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
            getWindow().setStatusBarColor(getResources().getColor(R.color.design_default_color_error));
        }
    }
    public void startmanageadmindetails(View view){
        Intent intent = new Intent(Administration.this, ManageAdminDetails.class);
        startActivity(intent);
    }
    public void startmanagememberdetails(View view){
        Intent intent = new Intent(Administration.this, ManageMemberDetails.class);
        startActivity(intent);
    }
    public void startmanageItemdetails(View view){
        Intent intent = new Intent(Administration.this,three.class);
        startActivity(intent);
    }

    public void startmanageviewdetails(View view){
        Intent intent = new Intent(Administration.this, ViewDetails.class);
        startActivity(intent);
    }
    public void startsuccessfulpayment(View view){
        Intent intent = new Intent(Administration.this,Successful_Payment.class);
        startActivity(intent);
    }
}