package com.example.colombopizza;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.colombopizza.model.Product;

import java.util.ArrayList;

public class ProductAdapter extends BaseAdapter {

    Context context;
    ArrayList<Product>products;
    TextView lblID, lblName,lblNote,lblPrice;
    ImageView imageView;
    Button btnClick;

    public ProductAdapter(Context context, ArrayList<Product> products) {
        this.context = context;
        this.products = products;
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int position) {
        return products.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.product_view ,parent,false);

        lblID = v.findViewById(R.id.lblProID);
        lblName = v.findViewById(R.id.lblProName);
        lblNote= v.findViewById(R.id.lblNote);
        lblPrice = v.findViewById(R.id.lblPrice);
        imageView= v.findViewById(R.id.proImage);
        btnClick= v.findViewById(R.id.btnClick);

        btnClick.setTag(position);

        Product product = products.get(position);
        lblID.setText("" + product.getPid());
        lblName.setText(product.getName());
        lblNote.setText(product.getNote());
        lblPrice.setText("" + product.getPrice());
        Bitmap bitmap = BitmapFactory.decodeByteArray(product.getImg(),0,product.getImg().length);
        imageView.setImageBitmap(bitmap);
        return v;
    }
}

