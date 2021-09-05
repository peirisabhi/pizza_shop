package com.example.colombopizza.ui.main.ui.orderDetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.colombopizza.R;
import com.example.colombopizza.adapter.OrderAdapter;
import com.example.colombopizza.adapter.OrderItemAdapter;
import com.example.colombopizza.adapter.ProductAdapter;
import com.example.colombopizza.db.DB_Helper;
import com.example.colombopizza.model.Order;
import com.example.colombopizza.model.OrderItem;
import com.example.colombopizza.model.Product;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailsActivity extends AppCompatActivity {

    private List<Product> orderItemList;
    private RecyclerView orderItemRecycler;
    private RecyclerView.LayoutManager orderItemLayoutManager;
    private DB_Helper db;

    int orderId = 0;

    TextView name;
    TextView email;
    TextView nic;
    TextView mobile;
    TextView address;
    TextView subTotal;
    TextView netTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        nic = findViewById(R.id.nic);
        mobile = findViewById(R.id.mobile);
        address = findViewById(R.id.address);
        subTotal = findViewById(R.id.sub_total);
        netTotal = findViewById(R.id.net_total);

        db = new DB_Helper(this);

        orderItemRecycler = findViewById(R.id.order_item_recycler);
        orderItemRecycler.setHasFixedSize(true);

        orderItemLayoutManager = new LinearLayoutManager(this);
        orderItemRecycler.setLayoutManager(orderItemLayoutManager);

        Intent intent = getIntent();
        orderId = intent.getIntExtra("orderId", 0);

        loadDetails();
    }


    private void loadDetails(){
        ArrayList<Order> orders = db.getOrderById(orderId);

        if(orders != null){

            if(orders.iterator().hasNext()){
                Order order = orders.iterator().next();

                name.setText(order.getName());
                email.setText(order.getEmail());
                nic.setText(order.getNic());
                mobile.setText(order.getMobile());
                address.setText(order.getAddress());
                subTotal.setText(String.valueOf(order.getSubTotal()));
                netTotal.setText(String.valueOf(order.getNetTotal()));


                ArrayList<OrderItem> orderItems = db.getOrderItemsByOrderId(orderId);

                if(orderItems != null){
                    System.out.println("orderItems -- " + orderItems.size());

                    OrderItemAdapter adapter = new OrderItemAdapter(orderItems, this);


                    orderItemRecycler.setAdapter(adapter);
                }

            }




        }
    }

}