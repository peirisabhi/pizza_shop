package com.example.colombopizza;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.colombopizza.db.DB_Helper;
import com.example.colombopizza.model.Product3;

import java.util.ArrayList;

public class Menu3 extends AppCompatActivity {
    ArrayList<Product3> products3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu3);

        ListView proList = findViewById(R.id.listProducts);
        DB_Helper db = new DB_Helper(this);

        products3 = db.viewAllProducts3();


        if (products3.size() > 0) {

            ProductAdapter3 productAdapter3 = new ProductAdapter3(this, products3);
            proList.setAdapter(productAdapter3);
        } else {
            Toast.makeText(this, "No products", Toast.LENGTH_SHORT).show();
        }

    }

    public void getProducts3(View view) {
        int index = (int) view.getTag();
        int id = products3.get(index).getPid();
        String name = products3.get(index).getName();
        Double price = products3.get(index).getPrice();
        Toast toast = Toast.makeText(this, "" + id + "\n" + name + "\n" + price, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();
    }
}