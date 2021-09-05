package com.example.colombopizza.ui.main.ui.details;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.colombopizza.R;
import com.example.colombopizza.ViewDetails;
import com.example.colombopizza.ui.main.ui.orders.OrdersActivity;

import org.jetbrains.annotations.NotNull;


public class ViewDetailsFragment extends Fragment {

    Button viewAll;
    Button viewDetails;
    TextView mobile;
    TextView name;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewAll = view.findViewById(R.id.button5);
        viewDetails = view.findViewById(R.id.button6);
        mobile = view.findViewById(R.id.txtMNumber);
        name = view.findViewById(R.id.txtUName);

        viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("viewAll-----");
                Intent intent = new Intent(requireActivity(), OrdersActivity.class);
                startActivity(intent);
            }
        });


        viewDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mobileVal = mobile.getText().toString();
                String nameVal = name.getText().toString();

                if((mobileVal.equals("") || mobileVal == null) && (nameVal.equals("") || nameVal == null)){
                    Toast.makeText(requireContext(), "Required Field Is Empty", Toast.LENGTH_SHORT).show();
                }else{
                   if(!mobileVal.equals("") && mobileVal != null){
                       Intent intent = new Intent(requireActivity(), OrdersActivity.class);
                       intent.putExtra("mobile", mobileVal);
                       startActivity(intent);
                   }else if(!nameVal.equals("") && nameVal != null){
                       Intent intent = new Intent(requireActivity(), OrdersActivity.class);
                       intent.putExtra("name", nameVal);
                       startActivity(intent);
                    }
                }

            }
        });
    }
}