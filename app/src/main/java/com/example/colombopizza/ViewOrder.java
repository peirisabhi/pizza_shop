package com.example.colombopizza;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;

public class ViewOrder extends AppCompatActivity {
    ImageSlider imageSlider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_order);

        imageSlider = findViewById(R.id.image_slider);

        ArrayList<SlideModel> images = new ArrayList<>();
        images.add(new SlideModel(R.drawable.food1, null));
        images.add(new SlideModel(R.drawable.food2, null));
        images.add(new SlideModel(R.drawable.food3, null));
        images.add(new SlideModel(R.drawable.food4, null));
        images.add(new SlideModel(R.drawable.food5, null));
        images.add(new SlideModel(R.drawable.food6, null));
        images.add(new SlideModel(R.drawable.food7, null));
        images.add(new SlideModel(R.drawable.food8, null));
        images.add(new SlideModel(R.drawable.food10, null));
        images.add(new SlideModel(R.drawable.food11, null));
        images.add(new SlideModel(R.drawable.food12, null));
        images.add(new SlideModel(R.drawable.food13, null));
        images.add(new SlideModel(R.drawable.food14, null));
        images.add(new SlideModel(R.drawable.food15, null));
        images.add(new SlideModel(R.drawable.food16, null));
        images.add(new SlideModel(R.drawable.food17, null));
        images.add(new SlideModel(R.drawable.food18, null));
        images.add(new SlideModel(R.drawable.food19, null));
        images.add(new SlideModel(R.drawable.food20, null));
        images.add(new SlideModel(R.drawable.food21, null));
        images.add(new SlideModel(R.drawable.food22, null));
        images.add(new SlideModel(R.drawable.food23, null));
        images.add(new SlideModel(R.drawable.food24, null));
        images.add(new SlideModel(R.drawable.food25, null));
        images.add(new SlideModel(R.drawable.food26, null));
        images.add(new SlideModel(R.drawable.food27, null));
        images.add(new SlideModel(R.drawable.food28, null));
        images.add(new SlideModel(R.drawable.food29, null));
        images.add(new SlideModel(R.drawable.food30, null));
        images.add(new SlideModel(R.drawable.food31, null));
        images.add(new SlideModel(R.drawable.food33, null));
        images.add(new SlideModel(R.drawable.food34, null));

        imageSlider.setImageList(images);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.newred)));
        statusbarcolor();
    }

    public void statusbarcolor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.design_default_color_error));
        }
    }

    public void DoneMenu(View view) {
        ProgressDialog progressDialog = new ProgressDialog(ViewOrder.this);
        progressDialog.setTitle("Order");
        progressDialog.setMessage("Making......");
        progressDialog.show();

        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(ViewOrder.this, Successful_Payment.class);
                startActivity(intent);
                progressDialog.dismiss();
            }

        }, 5000);
    }
}

