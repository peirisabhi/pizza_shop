package com.example.colombopizza;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.colombopizza.model.Product;

import java.util.ArrayList;

public class Fragment1 extends Fragment {
    private String TAG = "Test";
    private View view;
    Context context;
    ArrayList<Product> products;
    TextView lblID, lblName, lblNote, lblPrice;
    ImageView imageView;
    Button btnClick;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment1_layout, container, false);
               /*

                  lblID = View.findViewById(R.id.lblProID);
                  lblName = view.findViewById(R.id.lblProName);
                  lblNote= view.findViewById(R.id.lblNote);
                  lblPrice = view.findViewById(R.id.lblPrice);
                  imageView= view.findViewById(R.id.proImage);
                  btnClick= view.findViewById(R.id.btnClick);



            }));
            Users_recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    ((StorageActivity) Objects.requireNonNull(getActivity())).controlFABVisibility(dy);
                }
            });
            LoadAllUsers();
            return view;
        }




    */
    }
}