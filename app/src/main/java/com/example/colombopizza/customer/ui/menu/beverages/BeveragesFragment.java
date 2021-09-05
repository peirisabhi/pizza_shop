package com.example.colombopizza.customer.ui.menu.beverages;

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
import com.example.colombopizza.customer.ui.menu.MenuFragment;
import com.example.colombopizza.db.DB_Helper;
import com.example.colombopizza.model.Product;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class BeveragesFragment extends Fragment {

    private List<Product> productList;
    private RecyclerView productRecycler;
    private RecyclerView.LayoutManager productLayoutManager;
    private DB_Helper db;
    private MenuFragment menuFragment;

    public BeveragesFragment() {
        // Required empty public constructor
    }

    public BeveragesFragment(MenuFragment menuFragment) {
        this.menuFragment = menuFragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_beverages, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        db = new DB_Helper(requireContext());

        productRecycler = view.findViewById(R.id.product_recycler);
        productRecycler.setHasFixedSize(true);

        productLayoutManager = new LinearLayoutManager(requireActivity());
        productRecycler.setLayoutManager(productLayoutManager);

        System.out.println("-------------- pizzaaaaaa");

        loadProducts();

    }



    @Override
    public void onViewStateRestored(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        System.out.println("onViewStateRestored");
    }

    @Override
    public void onResume() {
        super.onResume();
        System.out.println("onResume");
        loadProducts();
    }

    @Override
    public void onStart() {
        super.onStart();
        System.out.println("onStart");
        loadProducts();
    }


    private void loadProducts(){
        ArrayList<Product> products = db.getProductByCategory("Beverages");

        if(products != null){
            System.out.println("products -- " + products.size());
            calculateTotalQty();
            ProductAdapter adapter = new ProductAdapter(products, requireActivity());

            adapter.setListener(new ProductAdapter.Listener() {
                @Override
                public void addToCartBtnOnClick(int position) {
                    System.out.println("addToCartBtnOnClick");
                    db.addQty(products.get(position).getPid());
                    loadProducts();
                }

                @Override
                public void removeQtyBtnOnClick(int position) {
                    System.out.println("removeQtyBtnOnClick");
                    db.removeQty(products.get(position).getPid());
                    loadProducts();
                }

                @Override
                public void addQtyBtnOnClick(int position) {
                    System.out.println("addQtyBtnOnClick");
                    db.addQty(products.get(position).getPid());
                    loadProducts();
                }
            });

            productRecycler.setAdapter(adapter);


        }
    }


    public void calculateTotalQty(){
        System.out.println("calculateTotalQty -------------------");

        ArrayList<Product> products = db.viewAllProducts();

        int qty = 0;
        if(products != null){
            for (Product p : products){
                qty += p.getQty();
            }

            this.menuFragment.onChangeQty(qty);
        }
    }
}