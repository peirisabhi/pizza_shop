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
import com.example.colombopizza.model.Product;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    ArrayList<Product> productList;
    Context context;
    private CartAdapter.Listener listener;

    public interface Listener {
        void addToCartBtnOnClick(int position);
        void removeQtyBtnOnClick(int position);
        void addQtyBtnOnClick(int position);
    }

    public CartAdapter(ArrayList<Product> productList, Context context) {
        this.productList = productList;
        this.context = context;

        System.out.println("ProductAdapter" + productList.size());
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart_product, parent, false);

        return new ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        CardView cardView = holder.cardView;
        TextView name = cardView.findViewById(R.id.textViewTitle);
        TextView price = cardView.findViewById(R.id.textViewPrice);
        ImageView img = cardView.findViewById(R.id.image);

        Button addQty = cardView.findViewById(R.id.plus);
        Button removeQty = cardView.findViewById(R.id.minus);
        Button qtyVal = cardView.findViewById(R.id.qty_val);

        LinearLayout qtyLayout = cardView.findViewById(R.id.qty_layout);

        name.setText(productList.get(position).getName());
        price.setText(String.valueOf(productList.get(position).getPrice()));

        System.out.println("qty ----------- "+productList.get(position).getQty());

        if(productList.get(position).getImg() == null) {
            Glide.with(context).load(R.drawable.image).into(img);
        }else{
            Glide.with(context).load(productList.get(position).getImg()).into(img);
        }

//        if(productList.get(position).getQty() > 0){
//            addToCart.setVisibility(View.GONE);
//            qtyLayout.setVisibility(View.VISIBLE);
            qtyVal.setText(String.valueOf(productList.get(position).getQty()));
//        }else{
//            addToCart.setVisibility(View.VISIBLE);
//            qtyLayout.setVisibility(View.GONE);
//        }



        addQty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null){
                    listener.addQtyBtnOnClick(position);
                }
            }
        });


        removeQty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null){
                    listener.removeQtyBtnOnClick(position);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;

        public ViewHolder(@NonNull CardView itemView) {
            super((CardView) itemView);
            this.cardView = (CardView) itemView;
        }
    }
}
