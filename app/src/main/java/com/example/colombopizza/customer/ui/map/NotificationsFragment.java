package com.example.colombopizza.customer.ui.map;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.colombopizza.databinding.FragmentNotificationsBinding;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;
    private FragmentNotificationsBinding binding;

    private static final int LOCATIO_PERMISSION = 100;
    FusedLocationProviderClient fusedLocationProviderClient;
    public GoogleMap currentGoogleMap;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        notificationsViewModel =
//                new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        final TextView textView = binding.textNotifications;
//        notificationsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return root;
    }


    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        @Override
        public void onMapReady(GoogleMap googleMap) {
            currentGoogleMap = googleMap;

            LatLng colombo = new LatLng(6.8999861, 79.85181);

            LatLng negombo = new LatLng(7.2099003, 79.849636);

            LatLng Kandy = new LatLng(7.2936, 80.6385);

            LatLng anuradhapuraya = new LatLng(8.3243, 80.4040);

            LatLng kurunegala = new LatLng(7.4733, 80.3529);

            LatLng nuwaraeliya = new LatLng(6.9765, 80.7640);

            LatLng Galle = new LatLng(6.0357, 80.2100);

            LatLng mathara = new LatLng(5.9482, 80.5352);

            currentGoogleMap.addMarker(new MarkerOptions().position(colombo).title("Colombo-03"));

            currentGoogleMap.addMarker(new MarkerOptions().position(negombo).title("Negombo").alpha(0.4f));

            currentGoogleMap.addMarker(new MarkerOptions().position(Kandy).title("kandy").alpha(0.4f));

            currentGoogleMap.addMarker(new MarkerOptions().position(anuradhapuraya).title("Anuradhapuraya").alpha(0.4f));

            currentGoogleMap.addMarker(new MarkerOptions().position(kurunegala).title("kurunegala").alpha(0.4f));

            currentGoogleMap.addMarker(new MarkerOptions().position(nuwaraeliya).title("Nuwara-Eliya").alpha(0.4f));

            currentGoogleMap.addMarker(new MarkerOptions().position(Galle).title("Galle").alpha(0.4f));

            currentGoogleMap.addMarker(new MarkerOptions().position(mathara).title("Mathara").alpha(0.4f));

            currentGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(colombo,8.812345f));
        }
    };


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}