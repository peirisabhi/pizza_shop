package com.example.colombopizza;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
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
import com.example.colombopizza.model.Product;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ManageItems extends AppCompatActivity {
    EditText txtID, txtName, txtNote, txtPrice;
    ImageView image;
    byte[] imageByte;
    DB_Helper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_items);

        txtID = findViewById(R.id.txtPID);
        txtName = findViewById(R.id.txtPname);
        txtNote = findViewById(R.id.txtNote);
        txtPrice = findViewById(R.id.txtPrice);
        image = findViewById(R.id.imgProduct);
        db = new DB_Helper(this);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.newred)));
        statusbarcolor();
    }
    public void statusbarcolor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.design_default_color_error));
        }
    }
    public void clear() {
        txtID.setText(null);
        txtName.setText(null);
        txtNote.setText(null);
        txtPrice.setText(null);
        image.setImageBitmap(null);
        txtID.requestFocus();
    }

    public void clear_click(View view) {
        clear();
    }


    public void get_image(View view) {
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
    public void insert_product(View view) {
        Product product = new Product();

        product.setPid(Integer.parseInt(txtID.getText().toString()));
        product.setName(txtName.getText().toString());
        product.setNote(txtNote.getText().toString());
        product.setPrice(Double.parseDouble(txtPrice.getText().toString()));
        product.setImg(imageByte);

        if (db.insertProduct(product) > 0) {
            Toast.makeText(this, "New Product Interested!", Toast.LENGTH_SHORT).show();
            clear();
        }
    }

    public void viewAllProducts(View view) {
        Intent intent = new Intent(ManageItems.this, PizzaMenu.class);
        startActivity(intent);
    }

    public void find_product(View view){
        Product product = new Product();
        int id = Integer.parseInt(txtID.getText().toString());
        product.setPid(id);
        product = db.findProduct(product);

        try {

            if (product!= null) {

                txtName.setText(product.getName());
                txtNote.setText(product.getNote());
                txtPrice.setText("" + product.getPrice());
                Bitmap bitmap = BitmapFactory.decodeByteArray(product.getImg(),0,product.getImg().length);
                image.setImageBitmap(bitmap);

            } else {
                Toast.makeText(this, "Product Records Not Found", Toast.LENGTH_SHORT).show();

            }
        }catch (Exception e){
            e.getStackTrace();
            Toast.makeText(this, "can not Find the product", Toast.LENGTH_SHORT).show();
        }

    }

        public void update(View view){
            Product product = new Product();

            product.setPid(Integer.parseInt(txtID.getText().toString()));
            product.setName(txtName.getText().toString());
            product.setNote(txtNote.getText().toString());
            product.setPrice(Double.parseDouble(txtPrice.getText().toString()));



            product.setImg(imageByte);



            db.update_product(product);
            Toast.makeText(this,"Product Record Updated", Toast.LENGTH_SHORT).show();
            clear();




        }

    public void deleteProduct(View view){
        Product product = new Product();
        product.setPid(Integer.parseInt(txtID.getText().toString()));

        if(db.delete_product(product)>0){
            Toast.makeText(this,"product Record Deleted", Toast.LENGTH_SHORT).show();
            clear();
        }

    }
}