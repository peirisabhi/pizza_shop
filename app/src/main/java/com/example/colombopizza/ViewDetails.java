package com.example.colombopizza;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.colombopizza.ui.main.ui.orders.OrdersActivity;

public class ViewDetails extends AppCompatActivity {

    Button viewAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_details);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.newred)));
        statusbarcolor();

        viewAll = findViewById(R.id.button5);

        viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("viewAll-----");
                Intent intent = new Intent(ViewDetails.this, OrdersActivity.class);
                startActivity(intent);
            }
        });
    }
    public void statusbarcolor(){
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
            getWindow().setStatusBarColor(getResources().getColor(R.color.design_default_color_error));
        }
    }
}