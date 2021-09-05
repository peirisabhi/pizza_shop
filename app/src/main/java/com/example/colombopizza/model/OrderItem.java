package com.example.colombopizza.model;

import java.sql.Blob;

public class OrderItem {
    int OITID;
    int pid;
    String pName;
    String note;
    double price;
    byte[] img;
    String category;
    int qty;
    double total;
    int oId;

    public OrderItem() {
    }

    public OrderItem(int OITID, int pid, String pName, String note, double price, byte[] img, String category, int qty, double total, int oId) {
        this.OITID = OITID;
        this.pid = pid;
        this.pName = pName;
        this.note = note;
        this.price = price;
        this.img = img;
        this.category = category;
        this.qty = qty;
        this.total = total;
        this.oId = oId;
    }


    public int getOITID() {
        return OITID;
    }

    public void setOITID(int OITID) {
        this.OITID = OITID;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getoId() {
        return oId;
    }

    public void setoId(int oId) {
        this.oId = oId;
    }
}
