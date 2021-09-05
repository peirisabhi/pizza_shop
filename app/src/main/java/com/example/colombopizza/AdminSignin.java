package com.example.colombopizza;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.colombopizza.db.DB_Demo1;

public class AdminSignin extends AppCompatActivity {

    EditText txtId, txtName, txtEmail, txtPassword;
    DB_Demo1 db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_signin);
        txtId = findViewById(R.id.txtAdminID2);
        txtName = findViewById(R.id.txtAdminName2);
        txtEmail = findViewById(R.id.txtAdminEmail2);
        txtPassword = findViewById(R.id.txtAdminPassword2);

        db = new DB_Demo1(this);

//    getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.newred)));
    statusbarcolor();
}
    public void statusbarcolor(){
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
            getWindow().setStatusBarColor(getResources().getColor(R.color.design_default_color_error));
        }
    }

    public void insert_admin(View view) {
        Admin a = new Admin();

        int id = Integer.parseInt(txtId.getText().toString());
        String name = txtName.getText().toString();
        String email = txtEmail.getText().toString();
        String pass = txtPassword.getText().toString();

        a.setId(id);
        a.setName(name);
        a.setEmail(email);
        a.setPass(pass);

        try {
            db.createAdmin(a);
            Toast.makeText(this, "Admin Record Inserted", Toast.LENGTH_SHORT).show();
            clear();

        } catch (Exception e) {
            Toast.makeText(this, "Cannot insert Admin Record", Toast.LENGTH_SHORT).show();
            e.getStackTrace();
        }
    }

    public void clear() {
        txtId.setText(null);
        txtName.setText(null);
        txtEmail.setText(null);
        txtPassword.setText(null);
        txtId.requestFocus();
    }

    public void clear_click(View view) {
        clear();
    }

}