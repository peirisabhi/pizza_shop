package com.example.colombopizza.model;

public class Order {
    int OID;
    String name;
    String mobile;
    String nic;
    String email;
    String address;
    double subTotal;
    double netTotal;
    double lat;
    double lan;

    public Order() {
    }

    public Order(int OID, String name, String mobile, String nic, String email, String address, double subTotal, double netTotal, double lat, double lan) {
        this.OID = OID;
        this.name = name;
        this.mobile = mobile;
        this.nic = nic;
        this.email = email;
        this.address = address;
        this.subTotal = subTotal;
        this.netTotal = netTotal;
        this.lat = lat;
        this.lan = lan;
    }

    public Order(String name, String mobile, String nic, String email, String address, double subTotal, double netTotal, double lat, double lan) {
        this.name = name;
        this.mobile = mobile;
        this.nic = nic;
        this.email = email;
        this.address = address;
        this.subTotal = subTotal;
        this.netTotal = netTotal;
        this.lat = lat;
        this.lan = lan;
    }

    public int getOID() {
        return OID;
    }

    public void setOID(int OID) {
        this.OID = OID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public double getNetTotal() {
        return netTotal;
    }

    public void setNetTotal(double netTotal) {
        this.netTotal = netTotal;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLan() {
        return lan;
    }

    public void setLan(double lan) {
        this.lan = lan;
    }
}
