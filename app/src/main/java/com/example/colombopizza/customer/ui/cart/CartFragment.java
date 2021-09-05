package com.example.colombopizza.customer.ui.cart;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.colombopizza.R;
import com.example.colombopizza.adapter.ProductAdapter;
import com.example.colombopizza.db.DB_Helper;
import com.example.colombopizza.model.Product;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class CartFragment extends Fragment {

    private List<Product> productList;
    private RecyclerView productRecycler;
    private RecyclerView.LayoutManager productLayoutManager;
    private DB_Helper db;

//    CartFragment

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        db = new DB_Helper(requireContext());

        productRecycler = view.findViewById(R.id.product_recycler);
        productRecycler.setHasFixedSize(true);

        productLayoutManager = new LinearLayoutManager(requireActivity());
        productRecycler.setLayoutManager(productLayoutManager);

        System.out.println("-------------- cart");

        loadCart();

    }



    private void loadCart(){
        ArrayList<Product> products = db.getCartItems();

        if(products != null){
            System.out.println("products -- " + products.size());
//            calculateTotalQty(products);
            ProductAdapter adapter = new ProductAdapter(products, requireActivity());

            adapter.setListener(new ProductAdapter.Listener() {
                @Override
                public void addToCartBtnOnClick(int position) {
                    System.out.println("addToCartBtnOnClick");
                    db.addQty(products.get(position).getPid());
                    loadCart();
                }

                @Override
                public void removeQtyBtnOnClick(int position) {
                    System.out.println("removeQtyBtnOnClick");
                    db.removeQty(products.get(position).getPid());
                    loadCart();
                }

                @Override
                public void addQtyBtnOnClick(int position) {
                    System.out.println("addQtyBtnOnClick");
                    db.addQty(products.get(position).getPid());
                    loadCart();
                }
            });

            productRecycler.setAdapter(adapter);


        }
    }
}