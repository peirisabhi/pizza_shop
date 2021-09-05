package com.example.colombopizza;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.colombopizza.db.DB_Helper;
import com.example.colombopizza.model.Product2;

import java.util.ArrayList;

public class Menu2 extends AppCompatActivity {
    ArrayList<Product2> products2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu2);

        ListView proList = findViewById(R.id.listProducts);
        DB_Helper db = new DB_Helper(this);

        products2 = db.viewAllProducts2();


        if (products2.size() > 0) {

            ProductAdapter2 productAdapter2 = new ProductAdapter2(this, products2);
            proList.setAdapter(productAdapter2);
        } else {
            Toast.makeText(this, "No products", Toast.LENGTH_SHORT).show();
        }

    }

    public void getProducts2(View view) {
        int index = (int) view.getTag();
        int id = products2.get(index).getPid();
        String name = products2.get(index).getName();
        Double price = products2.get(index).getPrice();
        Toast toast = Toast.makeText(this, "" + id + "\n" + name + "\n" + price,Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();
    }

}