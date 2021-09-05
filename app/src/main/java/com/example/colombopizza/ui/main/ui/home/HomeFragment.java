package com.example.colombopizza.ui.main.ui.home;

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

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.colombopizza.R;
import com.example.colombopizza.databinding.FragmentHomeBinding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;

    ImageSlider imageSlider;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

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

        imageSlider = view.findViewById(R.id.image_slider);

        ArrayList<SlideModel> images = new ArrayList<>();
        images.add(new SlideModel(R.drawable.food1, null));
        images.add(new SlideModel(R.drawable.food2, null));
        images.add(new SlideModel(R.drawable.food3, null));
        images.add(new SlideModel(R.drawable.food4, null));
        images.add(new SlideModel(R.drawable.food5, null));
        images.add(new SlideModel(R.drawable.food6, null));
        images.add(new SlideModel(R.drawable.food7, null));
        images.add(new SlideModel(R.drawable.food8, null));
        images.add(new SlideModel(R.drawable.food10, null));
        images.add(new SlideModel(R.drawable.food11, null));
        images.add(new SlideModel(R.drawable.food12, null));
        images.add(new SlideModel(R.drawable.food13, null));
        images.add(new SlideModel(R.drawable.food14, null));
        images.add(new SlideModel(R.drawable.food15, null));
        images.add(new SlideModel(R.drawable.food16, null));
        images.add(new SlideModel(R.drawable.food17, null));
        images.add(new SlideModel(R.drawable.food18, null));
        images.add(new SlideModel(R.drawable.food19, null));
        images.add(new SlideModel(R.drawable.food20, null));
        images.add(new SlideModel(R.drawable.food21, null));
        images.add(new SlideModel(R.drawable.food22, null));
        images.add(new SlideModel(R.drawable.food23, null));
        images.add(new SlideModel(R.drawable.food24, null));
        images.add(new SlideModel(R.drawable.food25, null));
        images.add(new SlideModel(R.drawable.food26, null));
        images.add(new SlideModel(R.drawable.food27, null));
        images.add(new SlideModel(R.drawable.food28, null));
        images.add(new SlideModel(R.drawable.food29, null));
        images.add(new SlideModel(R.drawable.food30, null));
        images.add(new SlideModel(R.drawable.food31, null));
        images.add(new SlideModel(R.drawable.food33, null));
        images.add(new SlideModel(R.drawable.food34, null));

        imageSlider.setImageList(images);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}