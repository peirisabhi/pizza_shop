package com.example.colombopizza.customer.ui.checkout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.colombopizza.R;
import com.example.colombopizza.Successful_Payment;
import com.example.colombopizza.customer.ui.cart.CartActivity;
import com.example.colombopizza.db.DB_Helper;
import com.example.colombopizza.model.Order;
import com.example.colombopizza.model.Product;

import java.util.List;

public class CheckoutActivity extends AppCompatActivity {

    private DB_Helper db;
    private TextView subTotal;
    private TextView netTotal;

    private EditText name;
    private EditText nic;
    private EditText email;
    private EditText address;
    private EditText mobile;

    List<Product> productList;
    double subTotalVal = 0;
    double netTotalVal = 0;
    Button placeOrderBtn;

    public static final String NOTIFICATION_CHANNEL_ID = "10001" ;
    private final static String default_notification_channel_id = "default";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        db = new DB_Helper(CheckoutActivity.this);

        name = findViewById(R.id.full_name);
        nic = findViewById(R.id.nic);
        email = findViewById(R.id.email);
        address = findViewById(R.id.address);
        mobile = findViewById(R.id.mobile);

        placeOrderBtn = findViewById(R.id.button8);
        subTotal = findViewById(R.id.sub_total);
        netTotal = findViewById(R.id.net_total);

        productList = db.getCartItems();

        calculateTotal(productList);


        placeOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveOrder();
            }
        });
    }


    public void saveOrder(){
        String nameVal = name.getText().toString();
        String nicVal = nic.getText().toString();
        String emailVal = email.getText().toString();
        String addressVal = address.getText().toString();
        String mobileVal = mobile.getText().toString();

        if(nameVal == null || nameVal.equals("")){
            Toast.makeText(this, "Please Enter Your Name", Toast.LENGTH_SHORT).show();
        }else if(nicVal == null || nicVal.equals("")){
            Toast.makeText(this, "Please Enter Your NIC", Toast.LENGTH_SHORT).show();
        }else if(mobileVal == null || mobileVal.equals("")){
            Toast.makeText(this, "Please Enter Your Mobile", Toast.LENGTH_SHORT).show();
        }else if(addressVal == null || addressVal.equals("")){
            Toast.makeText(this, "Please Enter Your Address", Toast.LENGTH_SHORT).show();
        }else{
            Order order = new Order(nameVal, mobileVal, nicVal, emailVal, addressVal, subTotalVal, netTotalVal, 0, 0);

            long orderId = db.createOrder(order, productList);

            if(orderId != 0){
                showNotification(nameVal);
                Intent intent = new Intent(CheckoutActivity.this, Successful_Payment.class);
                startActivity(intent);
            }else{
                Toast.makeText(this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
            }

        }

    }

    public void calculateTotal(List<Product> productList){
        System.out.println("calculateTotal -------------------");

        if(productList != null){
            for (Product p : productList){
                double price = p.getQty() * p.getPrice();

                subTotalVal += price;
            }

            netTotalVal = subTotalVal + 250;

            this.subTotal.setText(String.valueOf(subTotalVal));
            this.netTotal.setText(String.valueOf(netTotalVal));
        }
    }


    void showNotification(String customeName){
        NotificationManager notificationManager;
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE );
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext(), default_notification_channel_id ) ;
        mBuilder.setContentTitle("New Order Added");
        mBuilder.setContentText("Customer Name : " + customeName);
        mBuilder.setSmallIcon(R.mipmap.ic_launcher) ;
        mBuilder.setAutoCancel( true ) ;
        if (android.os.Build.VERSION. SDK_INT >= android.os.Build.VERSION_CODES. O ) {
            int importance = NotificationManager. IMPORTANCE_HIGH ;
            NotificationChannel notificationChannel = new NotificationChannel( NOTIFICATION_CHANNEL_ID , "NOTIFICATION_CHANNEL_NAME" , importance) ;
            mBuilder.setChannelId( NOTIFICATION_CHANNEL_ID ) ;
            assert notificationManager != null;
            notificationManager.createNotificationChannel(notificationChannel) ;
        }
        assert notificationManager != null;
        notificationManager.notify(( int ) System. currentTimeMillis () , mBuilder.build()) ;
    }
}