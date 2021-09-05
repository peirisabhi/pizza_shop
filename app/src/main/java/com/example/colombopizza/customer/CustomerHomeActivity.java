package com.example.colombopizza.customer;

import android.os.Bundle;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.colombopizza.R;
import com.example.colombopizza.customer.ui.home.DashboardFragment;
import com.example.colombopizza.customer.ui.map.LocationsFragment;
import com.example.colombopizza.customer.ui.menu.MenuFragment;
import com.example.colombopizza.customer.ui.map.NotificationsFragment;
import com.example.colombopizza.databinding.ActivityCustomerHomeBinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;


public class CustomerHomeActivity extends AppCompatActivity {

    private ActivityCustomerHomeBinding binding;

    MeowBottomNavigation bottomNavigation;

    DashboardFragment dashboardFragment = new DashboardFragment();
    MenuFragment menuFragment = new MenuFragment();
    LocationsFragment locationsFragment = new LocationsFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityCustomerHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
//                .build();
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_customer_home);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
//        NavigationUI.setupWithNavController(binding.navView, navController);

        FragmentManager fm = getSupportFragmentManager();

        fm.beginTransaction().add(R.id.frame_layout, locationsFragment, "3").hide(locationsFragment).commit();
        fm.beginTransaction().add(R.id.frame_layout, menuFragment, "2").hide(menuFragment).commit();
        fm.beginTransaction().add(R.id.frame_layout,dashboardFragment, "1").commit();

        bottomNavigation = findViewById(R.id.bottom_navigation);

        bottomNavigation.add(new MeowBottomNavigation.Model(1,R.drawable.ic_home));
        bottomNavigation.add(new MeowBottomNavigation.Model(2,R.drawable.ic_menu));
        bottomNavigation.add(new MeowBottomNavigation.Model(3,R.drawable.ic_location));


        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                Fragment fragment = null;
                switch (item.getId()){
                    case 1:
                        fragment = dashboardFragment;
                        fm.beginTransaction().hide(menuFragment).hide(locationsFragment).show(dashboardFragment).commit();
                        break;

                    case 2:
                        fragment = menuFragment;
                        fm.beginTransaction().hide(dashboardFragment).hide(locationsFragment).show(menuFragment).commit();
                        break;

                    case 3:
                        fragment = locationsFragment;
                        fm.beginTransaction().hide(menuFragment).hide(dashboardFragment).show(locationsFragment).commit();
                        break;


                }
                System.out.println(fragment);
//                loadFragment(fragment);
            }
        });



        bottomNavigation.setCount(1,"CP");
        bottomNavigation.show(1,true);
        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
//                Toast.makeText(getApplicationContext(),"You Clicked"+item.getId(),Toast.LENGTH_SHORT).show();
            }
        });
        bottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {
//                Toast.makeText(getApplicationContext(),"You Reslected"+item.getId(),Toast.LENGTH_SHORT).show();
            }
        });
    }



    private void loadFragment(Fragment fragment) {

        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                .show(fragment)
                .commit();
    }
}