package com.example.colombopizza.model;

import java.util.Arrays;

public class Product {
    private int pid;
    private String name;
    private String note;
    private double price;
    byte[] img;
    private String category;
    private int qty;

    public Product() {
    }

    public Product(int pid, String name, String note, double price, byte[] img, String category, int qty) {
        this.pid = pid;
        this.name = name;
        this.note = note;
        this.price = price;
        this.img = img;
        this.category = category;
        this.qty = qty;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "Product{" +
                "pid=" + pid +
                ", name='" + name + '\'' +
                ", note='" + note + '\'' +
                ", price=" + price +
                ", img=" + Arrays.toString(img) +
                ", category='" + category + '\'' +
                ", qty=" + qty +
                '}';
    }
}
