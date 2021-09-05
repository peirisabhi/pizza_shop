package com.example.colombopizza.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.colombopizza.R;
import com.example.colombopizza.model.OrderItem;
import com.example.colombopizza.model.Product;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class OrderItemAdapter extends RecyclerView.Adapter<OrderItemAdapter.ViewHolder> {

    ArrayList<OrderItem> orderItemsList;
    Context context;

    public OrderItemAdapter(ArrayList<OrderItem> orderItemsList, Context context) {
        this.orderItemsList = orderItemsList;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order_product, parent, false);

        return new OrderItemAdapter.ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        CardView cardView = holder.cardView;
        TextView name = cardView.findViewById(R.id.textViewTitle);
        TextView price = cardView.findViewById(R.id.textViewPrice);
        ImageView img = cardView.findViewById(R.id.image);

        TextView qty = cardView.findViewById(R.id.qty);


        name.setText(orderItemsList.get(position).getpName());
        price.setText(String.valueOf(orderItemsList.get(position).getPrice()));


        if(orderItemsList.get(position).getImg() == null) {
            Glide.with(context).load(R.drawable.image).into(img);
        }else{
            Glide.with(context).load(orderItemsList.get(position).getImg()).into(img);
        }

        qty.setText(String.valueOf(orderItemsList.get(position).getQty()));

    }

    @Override
    public int getItemCount() {
        return orderItemsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;

        public ViewHolder(@NonNull CardView itemView) {
            super((CardView) itemView);
            this.cardView = (CardView) itemView;
        }
    }
}
