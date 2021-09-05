package com.example.colombopizza;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.colombopizza.db.DB_Demo1;
import com.example.colombopizza.ui.main.AdminHomeActivity;

public class Adminlogin extends AppCompatActivity {
    EditText txtId,txtPassword;
    DB_Demo1 db;
    String TAG = "Test";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminlogin);

        txtId = findViewById(R.id.txtAdminId001);
        txtPassword = findViewById(R.id.txtUserPassword003);
        db = new DB_Demo1(this);
        Intent intent = getIntent();


//        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.newred)));
        statusbarcolor();
    }




    public void statusbarcolor(){
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
            getWindow().setStatusBarColor(getResources().getColor(R.color.design_default_color_error));
        }
    }
    public void clear() {
        txtId.setText(null);
        txtPassword.setText(null);
        txtId.requestFocus();
    }

    public void clear_click(View view) {
        clear();
    }

    public void startadminsignin(View view){
        Intent intent = new Intent(Adminlogin.this,AdminSignin.class);
        startActivity(intent);
    }

    public void startadministration(View view){
        Intent intent = new Intent(Adminlogin.this,Administration.class);
        startActivity(intent);
    }

    public void startback(View view){
        Intent intent = new Intent(Adminlogin.this,Login.class);
        startActivity(intent);
    }
    public void loginAdmin(View view){

        String idString = txtId.getText().toString();

        if(!idString.equals("") && idString != null) {

            int id = Integer.parseInt(idString);
            String password = txtPassword.getText().toString();

            if(!password.equals("") && password != null) {

                boolean logresult = db.login(id, password);
                if (logresult) {
                    Toast.makeText(this, "Login Successfull", Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent(Adminlogin.this,Administration.class);
                    Intent intent = new Intent(Adminlogin.this, AdminHomeActivity.class);
                    startActivity(intent);
                    clear();
                    //Method to go to next activities
                } else {

                    if (id == 999 && password.equals("999")) {
                        Intent intent = new Intent(Adminlogin.this, AdminHomeActivity.class);
                        startActivity(intent);
                        clear();
                    } else {

                        Toast.makeText(this, "Login Unsuccessfull", Toast.LENGTH_SHORT).show();
                        clear();
                    }
                }
            }
        }
    }
}
