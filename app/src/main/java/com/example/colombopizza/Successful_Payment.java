package com.example.colombopizza;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.colombopizza.customer.CustomerHomeActivity;


public class Successful_Payment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_successful__payment);

        TextView lblWelcome = findViewById(R.id.lblOrder);

        Intent intent = getIntent();
        lblWelcome.setText("Order Places Successfully");


//        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.newred)));
        statusbarcolor();
    }
    public void statusbarcolor(){
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
            getWindow().setStatusBarColor(getResources().getColor(R.color.design_default_color_error));
        }
    }

    public void Done(View view) {
        Toast.makeText(this, "...:::Thank You:::...", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Successful_Payment.this, CustomerHomeActivity.class);
        startActivity(intent);
    }
    @Override
    public void onBackPressed(){

    }

}