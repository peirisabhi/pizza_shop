package com.example.colombopizza;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.colombopizza.db.DB_Helper;
import com.example.colombopizza.model.Product;

import java.util.ArrayList;

public class PizzaMenu extends AppCompatActivity {
    ArrayList<Product> products;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza_menu);

        ListView proList = findViewById(R.id.listProducts);
        DB_Helper db = new DB_Helper(this);

        products = db.viewAllProducts();


        if (products.size() > 0) {

            ProductAdapter productAdapter = new ProductAdapter(this, products);
            proList.setAdapter(productAdapter);
        } else {
            Toast.makeText(this, "No products", Toast.LENGTH_SHORT).show();
        }

    }

    public void getProducts(View view) {
        int index = (int) view.getTag();
        int id = products.get(index).getPid();
        String name = products.get(index).getName();
        Double price = products.get(index).getPrice();
        Toast toast = Toast.makeText(this, "" + id + "\n" + name + "\n" + price,Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();
    }


}
