package com.example.colombopizza.ui.main.ui.orders;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.colombopizza.R;
import com.example.colombopizza.adapter.OrderAdapter;
import com.example.colombopizza.adapter.ProductAdapter;
import com.example.colombopizza.db.DB_Helper;
import com.example.colombopizza.model.Order;
import com.example.colombopizza.model.Product;
import com.example.colombopizza.ui.main.ui.orderDetails.OrderDetailsActivity;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class OrdersActivity extends AppCompatActivity {

    private List<Product> orderList;
    private RecyclerView orderRecycler;
    private RecyclerView.LayoutManager orderLayoutManager;
    private DB_Helper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        db = new DB_Helper(this);

        orderRecycler = findViewById(R.id.order_recycler);
        orderRecycler.setHasFixedSize(true);

        orderLayoutManager = new LinearLayoutManager(this);
        orderRecycler.setLayoutManager(orderLayoutManager);

        System.out.println("-------------- pizzaaaaaa");

        Intent intent = getIntent();

        if(intent.getStringExtra("mobile") != null){
            String mobile = intent.getStringExtra("mobile");
            loadOrders(db.getOrderByMobile(mobile));

        }else if(intent.getStringExtra("name") != null){
            String name = intent.getStringExtra("name");
            loadOrders(db.getOrderByName(name));

        }else {

            loadOrders(db.getAllOrders());
        }
    }


    private void loadOrders(ArrayList<Order> orders){

        if(orders != null){
            System.out.println("orders -- " + orders.size());

            OrderAdapter adapter = new OrderAdapter(orders, this);

            adapter.setListener(new OrderAdapter.Listener() {
                @Override
                public void onClick(int position) {
                    System.out.println(position);

                    Intent intent = new Intent(OrdersActivity.this, OrderDetailsActivity.class);
                    intent.putExtra("orderId", orders.get(position).getOID());

                    startActivity(intent);
                }
            });

            orderRecycler.setAdapter(adapter);


        }
    }

}