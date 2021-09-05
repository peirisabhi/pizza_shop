package com.example.colombopizza;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AlertDialog;

import android.database.Cursor;
import android.widget.EditText;
import android.widget.Toast;

import com.example.colombopizza.db.DB_Demo1;

public class ManageAdminDetails extends AppCompatActivity {

    EditText txtId, txtName, txtEmail, txtPassword;
    DB_Demo1 db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_admin_details);

        txtId = findViewById(R.id.txtAdminID);
        txtName = findViewById(R.id.txtAdminName);
        txtEmail = findViewById(R.id.txtAdminEmail);
        txtPassword = findViewById(R.id.txtAdminPassword);

        db = new DB_Demo1(this);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.newred)));
        statusbarcolor();
    }
    public void statusbarcolor(){
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
            getWindow().setStatusBarColor(getResources().getColor(R.color.design_default_color_error));
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

    public void findAdmin(View view) {
        Admin admin = new Admin();
        int id = Integer.parseInt(txtId.getText().toString());
        admin.setId(id);
        admin = db.findAdmin(admin);
        try {
            if (admin!= null) {
                txtName.setText(admin.getName());
                txtEmail.setText(admin.getEmail());
                txtPassword.setText(admin.getPassword());
            } else {
                Toast.makeText(this, "User Record not Found!", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Cannot Find User", Toast.LENGTH_SHORT).show();
            e.getStackTrace();
        }
    }

    public void update(View view) {
        Admin admin = new Admin();
        admin.setId(Integer.parseInt(txtId.getText().toString()));
        admin.setName(txtName.getText().toString());
        admin.setEmail(txtEmail.getText().toString());
        admin.setPass(txtPassword.getText().toString());

        db.update_admin(admin);
        Toast.makeText(this, "User Record Updated!", Toast.LENGTH_SHORT).show();
        clear();

    }

    public void delete(View view) {
        Admin admin = new Admin();
        admin.setId(Integer.parseInt(txtId.getText().toString()));

        db.delete_admin(admin);
        Toast.makeText(this, "User Record Deleted!", Toast.LENGTH_SHORT).show();
        clear();
    }


    public void viewdetails(View view) {
        Cursor res = db.viewdata();
        if(res.getCount()==0){
            Toast.makeText(ManageAdminDetails.this,"No Entry Exists",Toast.LENGTH_SHORT).show();
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()){

            buffer.append("ADMIN ID      :"+res.getInt(0)+"\n");
            buffer.append("NAME             :"+res.getString(1)+"\n");
            buffer.append("EMAIL            :"+res.getString(2)+"\n");
            buffer.append("PASSWORD   :"+res.getString(3)+"\n\n");

        }
        AlertDialog.Builder builder = new AlertDialog.Builder(ManageAdminDetails.this);

        builder.setCancelable(true);
        builder.setTitle("Administrations  Details");
        builder.setMessage(buffer.toString());
        builder.show();
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
}


