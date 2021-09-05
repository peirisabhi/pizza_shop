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

import com.example.colombopizza.model.Product2;

import java.util.ArrayList;

public class ProductAdapter2 extends BaseAdapter {
        Context context;
        ArrayList<Product2>products2;
        TextView lblID, lblName,lblNote,lblPrice;
        ImageView imageView;
        Button btnClick;

public ProductAdapter2(Context context, ArrayList<Product2> products2) {
        this.context = context;
        this.products2 = products2;
        }

        @Override
        public int getCount() {
        return products2.size();
        }

        @Override
        public Object getItem(int position) {
        return products2.get(position);
        }

        @Override
        public long getItemId(int position) {
        return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.view2 ,parent,false);

        lblID = v.findViewById(R.id.lblProID3);
        lblName = v.findViewById(R.id.lblProName3);
        lblNote= v.findViewById(R.id.lblNote3);
        lblPrice = v.findViewById(R.id.lblPrice3);
        imageView= v.findViewById(R.id.proImage3);
        btnClick= v.findViewById(R.id.btnClick5);

        btnClick.setTag(position);

        Product2 product2 = products2.get(position);
        lblID.setText("" + product2.getPid());
        lblName.setText(product2.getName());
        lblNote.setText(product2.getNote());
        lblPrice.setText("" + product2.getPrice());
        Bitmap bitmap = BitmapFactory.decodeByteArray(product2.getImg(),0,product2.getImg().length);
        imageView.setImageBitmap(bitmap);
        return v;
        }
}

