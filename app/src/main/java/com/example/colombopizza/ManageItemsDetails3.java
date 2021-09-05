package com.example.colombopizza;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.colombopizza.db.DB_Helper;
import com.example.colombopizza.model.Product3;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ManageItemsDetails3 extends AppCompatActivity {
    EditText txtID, txtName, txtNote, txtPrice;
    ImageView image;
    byte[] imageByte;
    DB_Helper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_items_details3);
        txtID = findViewById(R.id.txtPID4);
        txtName = findViewById(R.id.txtPname4);
        txtNote = findViewById(R.id.txtNote4);
        txtPrice = findViewById(R.id.txtPrice4);
        image = findViewById(R.id.imgProduct4);
        db = new DB_Helper(this);


//        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.newred)));
        statusbarcolor();
    }

    public void statusbarcolor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.design_default_color_error));
        }
    }

    public void clear3() {
        txtID.setText(null);
        txtName.setText(null);
        txtNote.setText(null);
        txtPrice.setText(null);
        image.setImageBitmap(null);
        txtID.requestFocus();
    }

    public void clear_click3(View view) {
        clear3();
    }


    public void get_image3(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 0);
        intent.putExtra("aspectY", 0);
        intent.putExtra("outputX", 160);
        intent.putExtra("outputY", 150);
        intent.putExtra("return_data", true);
        startActivityForResult(Intent.createChooser(intent, "SELECT PRODUCT IMAGE"), 11);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 11) {
            if (data != null) {
                Uri uri = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                    ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 0, arrayOutputStream);
                    imageByte = arrayOutputStream.toByteArray();
                    image.setImageBitmap(bitmap);
                } catch (IOException e) {
                    Log.e("Error", "MSG" + e.getMessage());
                }
            }
        }
    }
    public void insert_product3(View view) {
        Product3 product3 = new Product3();

        product3.setPid(Integer.parseInt(txtID.getText().toString()));
        product3.setName(txtName.getText().toString());
        product3.setNote(txtNote.getText().toString());
        product3.setPrice(Double.parseDouble(txtPrice.getText().toString()));
        product3.setImg(imageByte);

        if (db.insertProduct3(product3) > 0) {
            Toast.makeText(this, "New Product Interested!", Toast.LENGTH_SHORT).show();
            clear3();
        }
    }

    public void viewAllProducts3(View view) {
        Intent intent = new Intent(ManageItemsDetails3.this,Menu3.class);
        startActivity(intent);
    }

    public void find_product3(View view){
        Product3 product3 = new Product3();
        int id = Integer.parseInt(txtID.getText().toString());
        product3.setPid(id);
        product3 = db.findProduct3(product3);

        try {

            if (product3!= null) {

                txtName.setText(product3.getName());
                txtNote.setText(product3.getNote());
                txtPrice.setText("" + product3.getPrice());
                Bitmap bitmap = BitmapFactory.decodeByteArray(product3.getImg(),0,product3.getImg().length);
                image.setImageBitmap(bitmap);

            } else {
                Toast.makeText(this, "Product Records Not Found", Toast.LENGTH_SHORT).show();

            }
        }catch (Exception e){
            e.getStackTrace();
            Toast.makeText(this, "can not Find the product", Toast.LENGTH_SHORT).show();
        }

    }
    public void update3(View view){
        Product3 product3 = new Product3();

        product3.setPid(Integer.parseInt(txtID.getText().toString()));
        product3.setName(txtName.getText().toString());
        product3.setNote(txtNote.getText().toString());
        product3.setPrice(Double.parseDouble(txtPrice.getText().toString()));



        product3.setImg(imageByte);



        db.update_product3(product3);
        Toast.makeText(this,"Product Record Updated", Toast.LENGTH_SHORT).show();
        clear3();




    }
    public void deleteProduct3(View view){
        Product3 product3 = new Product3();
        product3.setPid(Integer.parseInt(txtID.getText().toString()));


        if(db.delete_product3(product3)>0){
            Toast.makeText(this,"product Record Deleted", Toast.LENGTH_SHORT).show();
            clear3();
        }

    }
}
