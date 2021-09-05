package com.example.colombopizza.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.colombopizza.Member;
import com.example.colombopizza.model.Order;
import com.example.colombopizza.model.OrderItem;
import com.example.colombopizza.model.Product;
import com.example.colombopizza.model.Product2;
import com.example.colombopizza.model.Product3;

import java.util.ArrayList;
import java.util.List;

public class DB_Helper extends SQLiteOpenHelper {
    String TAG = "Test";

    public DB_Helper(@Nullable Context context) {

        super(context, "ColomboPizza21", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE tblMember(ID INTEGER PRIMARY KEY, NAME VARCHAR(20), EMAIL VARCHAR(20), LOCATION VARCHAR(20), PASS VARCHAR(20))";
        db.execSQL(sql);
        sql = "create table tblProduct (PID integer primary key, PNAME varchar(20), NOTE varchar(100), PRICE double, IMG blog, CATEGORY varchar(30), QTY integer)";
        db.execSQL(sql);

        sql = "create table tblOrder (OID integer primary key AUTOINCREMENT, NAME varchar(50), MOBILE varchar(15), NIC varchar(15), EMAIL varchar(15), ADDRESS varchar(15), SUBTOTAL double, NETTOTAL double, LAT double, LAN double)";
        db.execSQL(sql);

        sql = "create table tblOrderItems (OITID integer primary key AUTOINCREMENT, PID integer, PNAME varchar(20), NOTE varchar(100), PRICE double, IMG blog NOT NULL, CATEGORY varchar(30), QTY integer, TOTAL double, OID integer)";
        db.execSQL(sql);
//        sql = "create table tblProduct2 (PID integer primary key, PNAME varchar(20), NOTE varchar(30), PRICE double, IMG blog)";
//        db.execSQL(sql);
//        sql = "create table tblProduct3 (PID integer primary key, PNAME varchar(20), NOTE varchar(30), PRICE double, IMG blog)";
//        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = "DROP TABLE IF EXISTS tblMember";
        sqLiteDatabase.execSQL(sql);
        sql = "drop table if exists tblProduct";
        sqLiteDatabase.execSQL(sql);
//        sql = "drop table if exists tblProduct2";
//        sqLiteDatabase.execSQL(sql);
//        sql = "drop table if exists tblProduct3";
//        sqLiteDatabase.execSQL(sql);

    }

    public void createMember(Member member) {
        SQLiteDatabase db = getWritableDatabase();
        Log.d(TAG, "createMember: " + member.getId() + ",'" + member.getName() + "','" + member.getEmail() + "','" + member.getLocation() + "','" + member.getPassword());
        String sql = "INSERT INTO tblMember VALUES(" + member.getId() + ",'" + member.getName() + "','" + member.getEmail() + "','" + member.getLocation() + "','" + member.getPassword() + "')";
        db.execSQL(sql);

    }

    public Member findMember(Member m) {
        String sql = "SELECT * FROM tblMember WHERE ID=" + m.getId();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        int count = cursor.getCount();
        Member member = new Member();

        if (count != 0) {
            if (cursor.moveToFirst()) {
                member.setId(cursor.getInt(cursor.getColumnIndex("ID")));
                member.setName(cursor.getString(cursor.getColumnIndex("NAME")));
                member.setEmail(cursor.getString(cursor.getColumnIndex("EMAIL")));
                member.setLocation(cursor.getString(cursor.getColumnIndex("LOCATION")));
                member.setPass(cursor.getString(cursor.getColumnIndex("PASS")));
            } else {
                member = null;
            }
        }
        return member;
    }

    public void update_member(Member m) {
        String sql = "UPDATE tblMember SET NAME ='" + m.getName() + "',EMAIL ='" + m.getEmail() + "',LOCATION='" + m.getLocation() + "',PASS='" + m.getPassword() + "'" + "WHERE ID =" + m.getId();
        SQLiteDatabase db = getReadableDatabase();
        db.execSQL(sql);

    }

    public void delete_member(Member m) {
        String sql = "DELETE FROM tblMember WHERE ID=" + m.getId();
        SQLiteDatabase db = getReadableDatabase();
        db.execSQL(sql);
    }

    public Cursor viewdata() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from tblMember", null);
        return cursor;
    }

    public boolean login(int id, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(true, "tblMember", new String[]{"PASS"}, "ID = ?", new String[]{String.valueOf(id)}, null, null, null, "1");
        String passowrd = null;
        boolean result = false;
        while (cursor.moveToNext()) {
            passowrd = cursor.getString(0);
        }
        cursor.close();
        if (passowrd != null && passowrd.equals(password)) {
            result = true;
        }
        return result;
    }

    public long createOrder(Order order, List<Product> orderItems) {
        SQLiteDatabase db = getWritableDatabase();

        long orderId = 0;

        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("NAME", order.getName());
            contentValues.put("MOBILE", order.getMobile());
            contentValues.put("NIC", order.getNic());
            contentValues.put("EMAIL", order.getEmail());
            contentValues.put("ADDRESS", order.getAddress());
            contentValues.put("SUBTOTAL", order.getSubTotal());
            contentValues.put("NETTOTAL", order.getNetTotal());
            contentValues.put("LAT", order.getLat());
            contentValues.put("LAN", order.getLan());

            orderId = db.insert("tblOrder", null, contentValues);

            System.out.println("tblOrder -- " + orderId);

            for (Product p : orderItems) {

                double total = p.getQty() * p.getPrice();

                ContentValues cv = new ContentValues();
                cv.put("PID", p.getPid());
                cv.put("PNAME", p.getName());
                cv.put("NOTE", p.getNote());
                cv.put("PRICE", p.getPrice());
                cv.put("IMG", p.getImg());
                cv.put("CATEGORY", p.getCategory());
                cv.put("QTY", p.getQty());
                cv.put("TOTAL", total);
                cv.put("OID", orderId);

                db.insert("tblOrderItems", null, cv);
            }


            for (Product p : orderItems) {

                String sql = "update tblProduct set QTY = '0'" +
                        "where PID = " + p.getPid();
                db.execSQL(sql);

            }


        } catch (Exception e) {
            e.printStackTrace();
        }


        return orderId;
    }


    public long insertProduct(Product p) {
        SQLiteDatabase db = getWritableDatabase();

        System.out.println("p.getCategory() --" + p.getCategory());

        ContentValues contentValues = new ContentValues();
        contentValues.put("PID", p.getPid());
        contentValues.put("PNAME", p.getName());
        contentValues.put("NOTE", p.getNote());
        contentValues.put("PRICE", p.getPrice());
        contentValues.put("IMG", p.getImg());
        contentValues.put("CATEGORY", p.getCategory());
        contentValues.put("QTY", 0);

        return db.insert("tblProduct", null, contentValues);
    }

    public ArrayList<Product> viewAllProducts() {
        SQLiteDatabase db = getReadableDatabase();
        String sql = "select * from tblProduct";
        Cursor cursor = db.rawQuery(sql, null);
        ArrayList<Product> products = new ArrayList<Product>();
        int count = cursor.getCount();

        if (count != 0) {
            while (cursor.moveToNext()) {
                Product p = new Product();
                p.setPid(cursor.getInt(0));
                p.setName(cursor.getString(1));
                p.setNote(cursor.getString(2));
                p.setPrice(cursor.getDouble(3));
                p.setImg(cursor.getBlob(4));
                p.setCategory(cursor.getString(5));
                p.setQty(cursor.getInt(6));
                products.add(p);
            }
        } else {
            products = null;
        }
        return products;
    }

    public ArrayList<Product> getProductByCategory(String category) {
        SQLiteDatabase db = getReadableDatabase();
        String sql = "select * from tblProduct WHERE CATEGORY = '" + category + "'";
        Cursor cursor = db.rawQuery(sql, null);
        ArrayList<Product> products = new ArrayList<Product>();
        int count = cursor.getCount();

        if (count != 0) {
            while (cursor.moveToNext()) {
                Product p = new Product();
                p.setPid(cursor.getInt(0));
                p.setName(cursor.getString(1));
                p.setNote(cursor.getString(2));
                p.setPrice(cursor.getDouble(3));
                p.setImg(cursor.getBlob(4));
                p.setCategory(cursor.getString(5));
                p.setQty(cursor.getInt(6));
                products.add(p);
            }
        } else {
            products = null;
        }
        return products;
    }


    public ArrayList<Order> getAllOrders() {
        SQLiteDatabase db = getReadableDatabase();
        String sql = "select * from tblOrder ";
        Cursor cursor = db.rawQuery(sql, null);
        ArrayList<Order> orders = new ArrayList<>();
        int count = cursor.getCount();

        if (count != 0) {
            while (cursor.moveToNext()) {
                Order o = new Order();

                o.setOID(cursor.getInt(0));
                o.setName(cursor.getString(1));
                o.setMobile(cursor.getString(2));
                o.setNic(cursor.getString(3));
                o.setEmail(cursor.getString(4));
                o.setAddress(cursor.getString(5));
                o.setSubTotal(cursor.getDouble(6));
                o.setNetTotal(cursor.getDouble(7));
                o.setLat(cursor.getDouble(8));
                o.setLan(cursor.getDouble(9));

                orders.add(o);
            }
        } else {
            orders = null;
        }
        return orders;
    }

    public ArrayList<Order> getOrderById(int id) {
        SQLiteDatabase db = getReadableDatabase();
        String sql = "select * from tblOrder WHERE OID = '" + id + "'";
        Cursor cursor = db.rawQuery(sql, null);
        ArrayList<Order> orders = new ArrayList<>();
        int count = cursor.getCount();

        if (count != 0) {
            while (cursor.moveToNext()) {
                Order o = new Order();

                o.setOID(cursor.getInt(0));
                o.setName(cursor.getString(1));
                o.setMobile(cursor.getString(2));
                o.setNic(cursor.getString(3));
                o.setEmail(cursor.getString(4));
                o.setAddress(cursor.getString(5));
                o.setSubTotal(cursor.getDouble(6));
                o.setNetTotal(cursor.getDouble(7));
                o.setLat(cursor.getDouble(8));
                o.setLan(cursor.getDouble(9));

                orders.add(o);
            }
        } else {
            orders = null;
        }
        return orders;
    }

    public ArrayList<Order> getOrderByName(String name) {
        SQLiteDatabase db = getReadableDatabase();
        String sql = "select * from tblOrder WHERE NAME = '" + name + "'";
        Cursor cursor = db.rawQuery(sql, null);
        ArrayList<Order> orders = new ArrayList<>();
        int count = cursor.getCount();

        if (count != 0) {
            while (cursor.moveToNext()) {
                Order o = new Order();

                o.setOID(cursor.getInt(0));
                o.setName(cursor.getString(1));
                o.setMobile(cursor.getString(2));
                o.setNic(cursor.getString(3));
                o.setEmail(cursor.getString(4));
                o.setAddress(cursor.getString(5));
                o.setSubTotal(cursor.getDouble(6));
                o.setNetTotal(cursor.getDouble(7));
                o.setLat(cursor.getDouble(8));
                o.setLan(cursor.getDouble(9));

                orders.add(o);
            }
        } else {
            orders = null;
        }
        return orders;
    }


    public ArrayList<Order> getOrderByMobile(String mobile) {
        SQLiteDatabase db = getReadableDatabase();
        String sql = "select * from tblOrder WHERE MOBILE = '" + mobile + "'";
        Cursor cursor = db.rawQuery(sql, null);
        ArrayList<Order> orders = new ArrayList<>();
        int count = cursor.getCount();

        if (count != 0) {
            while (cursor.moveToNext()) {
                Order o = new Order();

                o.setOID(cursor.getInt(0));
                o.setName(cursor.getString(1));
                o.setMobile(cursor.getString(2));
                o.setNic(cursor.getString(3));
                o.setEmail(cursor.getString(4));
                o.setAddress(cursor.getString(5));
                o.setSubTotal(cursor.getDouble(6));
                o.setNetTotal(cursor.getDouble(7));
                o.setLat(cursor.getDouble(8));
                o.setLan(cursor.getDouble(9));

                orders.add(o);
            }
        } else {
            orders = null;
        }
        return orders;
    }

    public ArrayList<OrderItem> getOrderItemsByOrderId(int id) {
        SQLiteDatabase db = getReadableDatabase();
        String sql = "select * from tblOrderItems WHERE OID = '" + id + "'";
        Cursor cursor = db.rawQuery(sql, null);
        ArrayList<OrderItem> orderItems = new ArrayList<>();
        int count = cursor.getCount();

        if (count != 0) {
            while (cursor.moveToNext()) {
                OrderItem oi = new OrderItem();

                oi.setOITID(cursor.getInt(0));
                oi.setPid(cursor.getInt(1));
                oi.setpName(cursor.getString(2));
                oi.setNote(cursor.getString(3));
                oi.setPrice(cursor.getDouble(4));
                oi.setImg(cursor.getBlob(5));
                oi.setCategory(cursor.getString(6));
                oi.setQty(cursor.getInt(7));
                oi.setTotal(cursor.getDouble(8));
                oi.setoId(cursor.getInt(9));

                orderItems.add(oi);
            }
        } else {
            orderItems = null;
        }
        return orderItems;
    }

    public ArrayList<Product> getCartItems() {
        SQLiteDatabase db = getReadableDatabase();
        String sql = "select * from tblProduct WHERE QTY > '0' ";
        Cursor cursor = db.rawQuery(sql, null);
        ArrayList<Product> products = new ArrayList<Product>();
        int count = cursor.getCount();

        if (count != 0) {
            while (cursor.moveToNext()) {
                Product p = new Product();
                p.setPid(cursor.getInt(0));
                p.setName(cursor.getString(1));
                p.setNote(cursor.getString(2));
                p.setPrice(cursor.getDouble(3));
                p.setImg(cursor.getBlob(4));
                p.setCategory(cursor.getString(5));
                p.setQty(cursor.getInt(6));
                products.add(p);
            }
        } else {
            products = null;
        }
        return products;
    }


    public Product findProduct(Product product) {
        String sql = "select * from tblProduct where PID = " + product.getPid();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        int count = cursor.getCount();
        Product pdct = new Product();
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            pdct.setPid(cursor.getInt(cursor.getColumnIndex("PID")));
            pdct.setName(cursor.getString(cursor.getColumnIndex("PNAME")));
            pdct.setNote(cursor.getString(cursor.getColumnIndex("NOTE")));
            pdct.setPrice(cursor.getDouble(cursor.getColumnIndex("PRICE")));
            pdct.setImg(cursor.getBlob(cursor.getColumnIndex("IMG")));
        } else {
            pdct = null;
        }
        return pdct;
    }

    public void update_product(Product product) {
        SQLiteDatabase db = getWritableDatabase();

        String sql = "update tblProduct set PNAME = '" + product.getName() + "', NOTE = '" + product.getNote() + "', PRICE =" + product.getPrice() + ", IMG ='" + product.getImg() + "'" +
                "where PID = " + product.getPid();
        db.execSQL(sql);
    }

    public int delete_product(Product product) {
        SQLiteDatabase db = getWritableDatabase();

        return db.delete("tblProduct", "PID=" + product.getPid(), null);
    }


//
//    public long insertProduct2(Product2 p2) {
//        SQLiteDatabase db = getWritableDatabase();
//
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("PID", p2.getPid());
//        contentValues.put("PNAME", p2.getName());
//        contentValues.put("NOTE", p2.getNote());
//        contentValues.put("PRICE", p2.getPrice());
//        contentValues.put("IMG", p2.getImg());
//
//        return db.insert("tblProduct2", null, contentValues);
//    }

    public ArrayList<Product2> viewAllProducts2() {
        SQLiteDatabase db = getReadableDatabase();
        String sql = "select * from tblProduct2";
        Cursor cursor = db.rawQuery(sql, null);
        ArrayList<Product2> products2 = new ArrayList<Product2>();
        int count = cursor.getCount();

        if (count != 0) {
            while (cursor.moveToNext()) {
                Product2 p2 = new Product2();
                p2.setPid(cursor.getInt(0));
                p2.setName(cursor.getString(1));
                p2.setNote(cursor.getString(2));
                p2.setPrice(cursor.getDouble(3));
                p2.setImg(cursor.getBlob(4));
                products2.add(p2);
            }
        } else {
            products2 = null;
        }
        return products2;
    }


//    public Product2 findProduct2(Product2 product2) {
//        String sql = "select * from tblProduct2 where PID = " + product2.getPid();
//        SQLiteDatabase db = getReadableDatabase();
//        Cursor cursor = db.rawQuery(sql, null);
//        int count = cursor.getCount();
//        Product2 pdct2 = new Product2();
//        if (cursor.getCount() > 0) {
//            cursor.moveToFirst();
//            pdct2.setPid(cursor.getInt(cursor.getColumnIndex("PID")));
//            pdct2.setName(cursor.getString(cursor.getColumnIndex("PNAME")));
//            pdct2.setNote(cursor.getString(cursor.getColumnIndex("NOTE")));
//            pdct2.setPrice(cursor.getDouble(cursor.getColumnIndex("PRICE")));
//            pdct2.setImg(cursor.getBlob(cursor.getColumnIndex("IMG")));
//        } else {
//            pdct2 = null;
//        }
//        return pdct2;
//    }


    public void addQty(int productID) {
        System.out.println("addQty -- " + productID);
        String sql = "select * from tblProduct where PID = " + productID;

        System.out.println(sql);
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        int count = cursor.getCount();
        System.out.println("count -- " + count);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            int qty = (cursor.getInt(cursor.getColumnIndex("QTY"))) + 1;
            System.out.println("qty0--- " + qty);
            SQLiteDatabase db1 = getWritableDatabase();

            String sql1 = "update tblProduct set QTY = '" + qty + "' " +
                    "where PID = " + productID;
            db1.execSQL(sql1);
        }
    }

    public void removeQty(int productID) {
        String sql = "select * from tblProduct where PID = " + productID;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        int count = cursor.getCount();
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            int qty = cursor.getInt(cursor.getColumnIndex("QTY"));
            if (qty > 0) {
                SQLiteDatabase db1 = getWritableDatabase();
                qty--;
                String sql1 = "update tblProduct set QTY = '" + qty + "' " +
                        "where PID = " + productID;
                db1.execSQL(sql1);
            }
        }
    }


//    public void update_product2(Product2 product2) {
//        SQLiteDatabase db = getWritableDatabase();
//
//        String sql = "update tblProduct2 set PNAME = '" + product2.getName() + "', NOTE = '" + product2.getNote() + "', PRICE =" + product2.getPrice() + ", IMG ='" + product2.getImg() + "'" +
//                "where PID = " + product2.getPid();
//        db.execSQL(sql);
//    }
//
//    public int delete_product2(Product2 product2) {
//        SQLiteDatabase db = getWritableDatabase();
//
//        return db.delete("tblProduct2", "PID=" + product2.getPid(), null);
//    }


    public long insertProduct3(Product3 p3) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("PID", p3.getPid());
        contentValues.put("PNAME", p3.getName());
        contentValues.put("NOTE", p3.getNote());
        contentValues.put("PRICE", p3.getPrice());
        contentValues.put("IMG", p3.getImg());

        return db.insert("tblProduct3", null, contentValues);
    }

    public ArrayList<Product3> viewAllProducts3() {
        SQLiteDatabase db = getReadableDatabase();
        String sql = "select * from tblProduct3";
        Cursor cursor = db.rawQuery(sql, null);
        ArrayList<Product3> products3 = new ArrayList<Product3>();
        int count = cursor.getCount();

        if (count != 0) {
            while (cursor.moveToNext()) {
                Product3 p3 = new Product3();
                p3.setPid(cursor.getInt(0));
                p3.setName(cursor.getString(1));
                p3.setNote(cursor.getString(2));
                p3.setPrice(cursor.getDouble(3));
                p3.setImg(cursor.getBlob(4));
                products3.add(p3);
            }
        } else {
            products3 = null;
        }
        return products3;
    }

    public Product3 findProduct3(Product3 product3) {
        String sql = "select * from tblProduct3 where PID = " + product3.getPid();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        int count = cursor.getCount();
        Product3 pdct3 = new Product3();
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            pdct3.setPid(cursor.getInt(cursor.getColumnIndex("PID")));
            pdct3.setName(cursor.getString(cursor.getColumnIndex("PNAME")));
            pdct3.setNote(cursor.getString(cursor.getColumnIndex("NOTE")));
            pdct3.setPrice(cursor.getDouble(cursor.getColumnIndex("PRICE")));
            pdct3.setImg(cursor.getBlob(cursor.getColumnIndex("IMG")));
        } else {
            pdct3 = null;
        }
        return pdct3;
    }

    public void update_product3(Product3 product3) {
        SQLiteDatabase db = getWritableDatabase();

        String sql = "update tblProduct3 set PNAME = '" + product3.getName() + "', NOTE = '" + product3.getNote() + "', PRICE =" + product3.getPrice() + ", IMG ='" + product3.getImg() + "'" +
                "where PID = " + product3.getPid();
        db.execSQL(sql);
    }

    public int delete_product3(Product3 product3) {
        SQLiteDatabase db = getWritableDatabase();

        return db.delete("tblProduct3", "PID=" + product3.getPid(), null);
    }


}


