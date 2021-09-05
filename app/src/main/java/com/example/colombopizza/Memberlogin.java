package com.example.colombopizza;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.colombopizza.customer.CustomerHomeActivity;
import com.example.colombopizza.db.DB_Helper;

public class Memberlogin extends AppCompatActivity {
    EditText txtId, txtPassword;
    DB_Helper db;
    String TAG = "Test";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memberlogin);
        txtId = findViewById(R.id.txtUserMobileNo01);
        txtPassword = findViewById(R.id.txtUserPassword03);
        db = new DB_Helper(this);

//        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.newred)));
        statusbarcolor();
    }
    public void clear() {
        txtId.setText(null);
        txtPassword.setText(null);
        txtId.requestFocus();
    }

    public void clear_click(View view) {
        clear();
    }

    public void statusbarcolor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.design_default_color_error));
        }
    }
    public  void startmembersigin(View view){
        Intent intent = new Intent(Memberlogin.this,MemberSignin.class);
        startActivity(intent);
    }
    public void loginMember(View view){

        String idString = txtId.getText().toString();

        if(!idString.equals("") && idString != null ) {
            System.out.println("ss");

            int id = Integer.parseInt(idString);
            String password = txtPassword.getText().toString();

            if(!password.equals("") && password != null) {

                System.out.println("ww");

                boolean logresult = db.login(id, password);
                if (logresult) {
                    Toast.makeText(this, "Login Successfull", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Memberlogin.this, CustomerHomeActivity.class);
                    startActivity(intent);
                    clear();
                    //Method to go to next activities
                } else {
                    Toast.makeText(this, "Login Unsuccessfull", Toast.LENGTH_SHORT).show();
                    clear();
                }
            }
        }
    }


}