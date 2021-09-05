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

import com.example.colombopizza.model.Product3;

import java.util.ArrayList;

public class ProductAdapter3 extends BaseAdapter {

    Context context;
    ArrayList<Product3>products3;
    TextView lblID, lblName,lblNote,lblPrice;
    ImageView imageView;
    Button btnClick;

    public ProductAdapter3(Context context, ArrayList<Product3> products3) {
        this.context = context;
        this.products3 = products3;
    }

    @Override
    public int getCount() {
        return products3.size();
    }

    @Override
    public Object getItem(int position) {
        return products3.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.view3 ,parent,false);

        lblID = v.findViewById(R.id.lblProID4);
        lblName = v.findViewById(R.id.lblProName4);
        lblNote= v.findViewById(R.id.lblNote4);
        lblPrice = v.findViewById(R.id.lblPrice4);
        imageView= v.findViewById(R.id.proImage4);
        btnClick= v.findViewById(R.id.btnClick7);

        btnClick.setTag(position);

        Product3 product3 = products3.get(position);
        lblID.setText("" + product3.getPid());
        lblName.setText(product3.getName());
        lblNote.setText(product3.getNote());
        lblPrice.setText("" + product3.getPrice());
        Bitmap bitmap = BitmapFactory.decodeByteArray(product3.getImg(),0,product3.getImg().length);
        imageView.setImageBitmap(bitmap);
        return v;
    }
}


