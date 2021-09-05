package com.example.colombopizza;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.colombopizza.db.DB_Helper;

public class ManageMemberDetails extends AppCompatActivity {


    EditText txtId, txtName, txtEmail, txtLocation ,txtPassword;
    DB_Helper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_member_details);

        txtId = findViewById(R.id.txtUserID001);
        txtName = findViewById(R.id.txtUserName001);
        txtEmail = findViewById(R.id.txtUserEmail001);
        txtLocation = findViewById(R.id.txtUserLocation001);
        txtPassword = findViewById(R.id.txtUserPassword001);

        db = new DB_Helper(this);

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
        txtLocation.setText(null);
        txtPassword.setText(null);
        txtId.requestFocus();
    }

    public void clear_click(View view) {
        clear();
    }

    public void find_memebr(View view) {
        Member member = new Member();
        int id = Integer.parseInt(txtId.getText().toString());
        member.setId(id);
        member = db.findMember(member);
        try {
            if (member != null) {
                txtName.setText(member.getName());
                txtEmail.setText(member.getEmail());
                txtLocation.setText(member.getLocation());
                txtPassword.setText(member.getPassword());
            } else {
                Toast.makeText(this, "User Record not Found!", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Cannot Find User", Toast.LENGTH_SHORT).show();
            e.getStackTrace();
        }
    }

    public void update(View view) {
        Member member = new Member();
        member.setId(Integer.parseInt(txtId.getText().toString()));
        member.setName(txtName.getText().toString());
        member.setEmail(txtEmail.getText().toString());
        member.setLocation(txtLocation.getText().toString());
        member.setPass(txtPassword.getText().toString());

        db.update_member(member);
        Toast.makeText(this, "User Record Updated!", Toast.LENGTH_SHORT).show();
        clear();

    }

    public void delete(View view) {
        Member member = new Member();
        member.setId(Integer.parseInt(txtId.getText().toString()));

        db.delete_member(member);
        Toast.makeText(this, "User Record Deleted!", Toast.LENGTH_SHORT).show();
        clear();
    }

    public void viewdetails(View view) {
        Cursor res = db.viewdata();
        if(res.getCount()==0){
            Toast.makeText(ManageMemberDetails.this,"No Entry Exists",Toast.LENGTH_SHORT).show();
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()){

            buffer.append("MOBILE NO :"+res.getInt(0)+"\n");
            buffer.append("NAME           :"+res.getString(1)+"\n");
            buffer.append("EMAIL           :"+res.getString(2)+"\n");
            buffer.append("LOCATION    :"+res.getString(3)+"\n");
            buffer.append("PASSOWRD  :"+res.getString(4)+"\n\n");

        }
        AlertDialog.Builder builder = new AlertDialog.Builder(ManageMemberDetails.this);

        builder.setCancelable(true);
        builder.setTitle("Customer Details");
        builder.setMessage(buffer.toString());
        builder.show();
    }
}



