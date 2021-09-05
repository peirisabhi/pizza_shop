package com.example.colombopizza;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.colombopizza.db.DB_Helper;

public class MemberSignin extends AppCompatActivity {


    EditText txtId, txtName, txtEmail, txtLocation, txtPassword;
    DB_Helper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        txtId = findViewById(R.id.txtUserID);
        txtName = findViewById(R.id.txtUserName1);
        txtEmail = findViewById(R.id.txtEmail);
        txtLocation = findViewById(R.id.txtLocation);
        txtPassword = findViewById(R.id.txtPassword2);

        db = new DB_Helper(this);

//        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.newred)));
        statusbarcolor();
    }
    public void statusbarcolor(){
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
            getWindow().setStatusBarColor(getResources().getColor(R.color.design_default_color_error));
        }
    }


    public void insert_member(View view) {
        Member m = new Member();

        String idString = txtId.getText().toString();

        String name = txtName.getText().toString();
        String email = txtEmail.getText().toString();
        String location = txtLocation.getText().toString();
        String pass = txtPassword.getText().toString();

        if(idString.equals("") || idString == null) {
            Toast.makeText(this, "Id is Empty", Toast.LENGTH_SHORT).show();
        }else if(name.equals("") || name == null) {
            Toast.makeText(this, "Name is Empty", Toast.LENGTH_SHORT).show();
        }else if(email.equals("") || email == null) {
            Toast.makeText(this, "Email is Empty", Toast.LENGTH_SHORT).show();
        }else if(pass.equals("") || pass == null) {
            Toast.makeText(this, "Password is Empty", Toast.LENGTH_SHORT).show();
        }else if(location.equals("") || location == null) {
            Toast.makeText(this, "Location is Empty", Toast.LENGTH_SHORT).show();
        }else {

            int id = Integer.parseInt(idString);

            m.setId(id);
            m.setName(name);
            m.setEmail(email);
            m.setLocation(location);
            m.setPass(pass);

            try {
                db.createMember(m);
                Toast.makeText(this, "User Record Inserted", Toast.LENGTH_SHORT).show();
                clear();
            /*if (db.createUser(user) > 0) {
                Toast.makeText(this, "User Record Inserted",Toast.LENGTH_SHORT).show();
                 }
           */
            } catch (Exception e) {
                Toast.makeText(this, "Cannot insert User", Toast.LENGTH_SHORT).show();
                e.getStackTrace();
            }
        }
    }

    public void clear() {
        txtId.setText(null);
        txtName.setText(null);
        txtEmail.setText(null);
        txtLocation.setText(null);
        txtPassword.setText(null);
        txtId.requestFocus();
    }

    public void clear_click(View view) {
        clear();
    }


}