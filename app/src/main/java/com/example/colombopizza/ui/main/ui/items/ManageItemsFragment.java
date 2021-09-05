package com.example.colombopizza.ui.main.ui.items;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.colombopizza.ui.main.ui.appetizers.ManageAppertizersActivity;
import com.example.colombopizza.ui.main.ui.appetizers.ManageAppetizersFragment;
import com.example.colombopizza.ui.main.ui.beverages.ManageBeveragesActivity;
import com.example.colombopizza.ui.main.ui.pizza.ManagePizzaItems;
import com.example.colombopizza.ManageItemsDetails3;
import com.example.colombopizza.R;

import org.jetbrains.annotations.NotNull;


public class ManageItemsFragment extends Fragment {

    Button pizzaBtn;
    Button beverageBtn;
    Button appetizersBtn;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_manage_items, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        pizzaBtn = view.findViewById(R.id.btnPizza);
        beverageBtn = view.findViewById(R.id.btnBeverage);
        appetizersBtn = view.findViewById(R.id.btnAppetizers);

        pizzaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireContext(), ManagePizzaItems.class);
                startActivity(intent);
            }
        });

        beverageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireContext(), ManageBeveragesActivity.class);
                startActivity(intent);
            }
        });


        appetizersBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireContext(), ManageAppertizersActivity.class);
                startActivity(intent);
            }
        });
    }

//        public void Pizza(View view){
//        Intent intent = new Intent(requireContext(), ManageItems.class);
//        startActivity(intent);
//    }
//    public void beverages(View view){
//        Intent intent = new Intent(requireContext(), ManageItemDetails2.class);
//        startActivity(intent);
//    }
//    public void Appetizers(View view){
//        Intent intent = new Intent(requireContext(), ManageItemsDetails3.class);
//        startActivity(intent);
//    }



}