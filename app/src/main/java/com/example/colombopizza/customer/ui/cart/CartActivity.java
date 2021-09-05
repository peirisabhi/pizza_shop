package com.example.colombopizza.customer.ui.cart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.colombopizza.R;
import com.example.colombopizza.adapter.CartAdapter;
import com.example.colombopizza.adapter.ProductAdapter;
import com.example.colombopizza.customer.ui.checkout.CheckoutActivity;
import com.example.colombopizza.db.DB_Helper;
import com.example.colombopizza.model.Product;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    Button checkoutBtn;

    private List<Product> productList;
    private RecyclerView productRecycler;
    private RecyclerView.LayoutManager productLayoutManager;
    private DB_Helper db;
    private TextView subTotal;
    private TextView netTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);


        db = new DB_Helper(CartActivity.this);

        productRecycler = findViewById(R.id.product_recycler);
        productRecycler.setHasFixedSize(true);

        productLayoutManager = new LinearLayoutManager(CartActivity.this);
        productRecycler.setLayoutManager(productLayoutManager);

        checkoutBtn = findViewById(R.id.button8);
        subTotal = findViewById(R.id.sub_total);
        netTotal = findViewById(R.id.net_total);

        checkoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartActivity.this, CheckoutActivity.class);
                startActivity(intent);
            }
        });

        loadCart();
    }


    private void loadCart(){
        ArrayList<Product> products = db.getCartItems();

        if(products != null){
            System.out.println("products -- " + products.size());
            calculateTotal(products);
            CartAdapter adapter = new CartAdapter(products, CartActivity.this);

            adapter.setListener(new CartAdapter.Listener() {
                @Override
                public void addToCartBtnOnClick(int position) {
                    System.out.println("addToCartBtnOnClick");
                    db.addQty(products.get(position).getPid());
                    loadCart();
                }

                @Override
                public void removeQtyBtnOnClick(int position) {
                    System.out.println("removeQtyBtnOnClick");
                    db.removeQty(products.get(position).getPid());
                    loadCart();
                }

                @Override
                public void addQtyBtnOnClick(int position) {
                    System.out.println("addQtyBtnOnClick");
                    db.addQty(products.get(position).getPid());
                    loadCart();
                }
            });

            productRecycler.setAdapter(adapter);


        }
    }


    public void calculateTotal(List<Product> productList){
        System.out.println("calculateTotal -------------------");
        double subTotal = 0;
        double netTotal = 0;
        if(productList != null){
            for (Product p : productList){
                double price = p.getQty() * p.getPrice();

                subTotal += price;
            }

            netTotal = subTotal + 250;

            this.subTotal.setText(String.valueOf(subTotal));
            this.netTotal.setText(String.valueOf(netTotal));
        }
    }

}