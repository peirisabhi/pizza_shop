package com.example.colombopizza;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.example.colombopizza.ui.main.ui.pizza.ManagePizzaItems;

public class three extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.newred)));
        statusbarcolor();
    }
    public void statusbarcolor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.design_default_color_error));
        }
    }
    public void Pizza(View view){
        Intent intent = new Intent(three.this,ManageItems.class);
        startActivity(intent);
    }
    public void beverages(View view){
        Intent intent = new Intent(three.this, ManagePizzaItems.class);
        startActivity(intent);
    }
    public void Appetizers(View view){
        Intent intent = new Intent(three.this,ManageItemsDetails3.class);
        startActivity(intent);
    }
}