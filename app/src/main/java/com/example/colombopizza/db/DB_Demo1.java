package com.example.colombopizza.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.colombopizza.Admin;

import java.util.ArrayList;

public class DB_Demo1 extends SQLiteOpenHelper {
    String TAG = "Test";
    public DB_Demo1(@Nullable Context context) {

        super(context, "ColomboPizzaAdmin", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE tblAdmin(ID INTEGER PRIMARY KEY, NAME VARCHAR(20), EMAIL VARCHAR(20), PASS VARCHAR(20))";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = "DROP TABLE IF EXISTS tblAdmin";
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
    }

    public void createAdmin(Admin admin) {
        SQLiteDatabase db = getWritableDatabase();
        Log.d(TAG, "createAdmin: "+ admin.getId() + ",'" + admin.getName()+ "','" + admin.getEmail() + "','" + admin.getPassword());
        String sql = "INSERT INTO tblAdmin VALUES(" + admin.getId() + ",'" + admin.getName() + "','" + admin.getEmail() + "','" + admin.getPassword() + "')";
        db.execSQL(sql);

    }
    public Admin findAdmin(Admin a) {
        String sql = "SELECT * FROM tblAdmin WHERE ID=" + a.getId();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        int count = cursor.getCount();
        Admin admin = new Admin();

        if (count != 0) {
            if (cursor.moveToFirst()) {
                admin.setId(cursor.getInt(cursor.getColumnIndex("ID")));
                admin.setName(cursor.getString(cursor.getColumnIndex("NAME")));
                admin.setEmail(cursor.getString(cursor.getColumnIndex("EMAIL")));
                admin.setPass(cursor.getString(cursor.getColumnIndex("PASS")));
            } else {
                admin = null;
            }
        }
        return admin;
    }

    public void update_admin(Admin a) {
        String sql = "UPDATE tblAdmin SET NAME ='" + a.getName() + "',EMAIL ='" + a.getEmail() + "',PASS='" + a.getPassword() + "'" + "WHERE ID =" + a.getId();
        SQLiteDatabase db = getReadableDatabase();
        db.execSQL(sql);


    }

    public void delete_admin(Admin a) {
        String sql = "DELETE FROM tblAdmin WHERE ID=" + a.getId();
        SQLiteDatabase db = getReadableDatabase();
        db.execSQL(sql);
    }

    public Cursor viewdata() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from tblAdmin", null);
        return cursor;
    }
    public boolean login(int id,String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(true,"tblAdmin",new String[]{"PASS"},"ID = ?",new String[]{String.valueOf(id)},null,null,null,"1");
        String passowrd = null;
        boolean result = false;
        while (cursor.moveToNext()) {
            passowrd = cursor.getString(0);
        }
        cursor.close();
        if(passowrd != null && passowrd.equals(password)){
            result = true;
        }
        return result;
    }

}



