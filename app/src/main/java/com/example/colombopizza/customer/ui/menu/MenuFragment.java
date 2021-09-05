package com.example.colombopizza.customer.ui.menu;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.example.colombopizza.R;
import com.example.colombopizza.adapter.MenuTabAdapter;
import com.example.colombopizza.customer.ui.cart.CartActivity;
import com.example.colombopizza.customer.ui.menu.appetizers.AppetizersFragment;
import com.example.colombopizza.customer.ui.menu.beverages.BeveragesFragment;
import com.example.colombopizza.customer.ui.menu.pizza.PizzaFragment;
import com.example.colombopizza.databinding.FragmentMenuBinding;
import com.google.android.material.tabs.TabLayout;

import org.jetbrains.annotations.NotNull;


public class MenuFragment extends Fragment {

    private MenuViewModel menuViewModel;
    private FragmentMenuBinding binding;

    TabLayout tabLayout;
    ViewPager viewPager;

    private ConstraintLayout constraintLayout2;
    private TextView itemCount;

    public PizzaFragment pizzaFragment;
    public AppetizersFragment appetizersFragment;
    public BeveragesFragment beveragesFragment;



    private Button cartBtn;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        menuViewModel =
                new ViewModelProvider(this).get(MenuViewModel.class);

        binding = FragmentMenuBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
//
//        final TextView textView = binding.textHome;
//        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });



        return root;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tabLayout = view.findViewById(R.id.tabLayout);
        viewPager = view.findViewById(R.id.viewPager);
        constraintLayout2 = view.findViewById(R.id.constraintLayout2);
        itemCount = view.findViewById(R.id.item_count);
        cartBtn  = view.findViewById(R.id.view_cart);

        pizzaFragment = new PizzaFragment(this);
        appetizersFragment = new AppetizersFragment(this);
        beveragesFragment = new BeveragesFragment(this);

        MenuTabAdapter tabAdapter = new MenuTabAdapter(requireActivity().getSupportFragmentManager(), tabLayout.getTabCount(), pizzaFragment, appetizersFragment, beveragesFragment);

        viewPager.setAdapter(tabAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                TabLayout.Tab tab1 = tabLayout.getTabAt(position);
                tab1.select();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireActivity(), CartActivity.class);
                startActivity(intent);

            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void onChangeQty(int qty){
        System.out.println("onChangeQty--");
        if(qty > 0){
            constraintLayout2.setVisibility(View.VISIBLE);
            itemCount.setText(String.valueOf(qty));
        }else{
            constraintLayout2.setVisibility(View.GONE);

        }
    }
}