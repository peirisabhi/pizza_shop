package com.example.colombopizza.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.colombopizza.customer.ui.menu.appetizers.AppetizersFragment;
import com.example.colombopizza.customer.ui.menu.beverages.BeveragesFragment;
import com.example.colombopizza.customer.ui.menu.pizza.PizzaFragment;

import org.jetbrains.annotations.NotNull;

public class MenuTabAdapter extends FragmentPagerAdapter {

    private int numOfTabs;

    public PizzaFragment pizzaFragment;
    public AppetizersFragment appetizersFragment;
    public BeveragesFragment beveragesFragment;

    public MenuTabAdapter(@NonNull @NotNull FragmentManager fm, int numOfTabs, PizzaFragment pizzaFragment, AppetizersFragment appetizersFragment, BeveragesFragment beveragesFragment) {
        super(fm);
        this.numOfTabs = numOfTabs;
        this.pizzaFragment = pizzaFragment;
        this.appetizersFragment = appetizersFragment;
        this.beveragesFragment = beveragesFragment;
    }


    @NonNull
    @NotNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return pizzaFragment;

            case 1 :
                return appetizersFragment;

            case 2:
                return  beveragesFragment;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
