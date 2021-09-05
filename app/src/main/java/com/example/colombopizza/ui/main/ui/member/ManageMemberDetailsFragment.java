package com.example.colombopizza.ui.main.ui.member;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.colombopizza.db.DB_Helper;
import com.example.colombopizza.Member;
import com.example.colombopizza.R;
import com.example.colombopizza.databinding.FragmentManageMemberDetailsBinding;

import org.jetbrains.annotations.NotNull;


public class ManageMemberDetailsFragment extends Fragment {

    private ManageMemberDetailsViewModel manageMemberDetailsViewModel;
    private FragmentManageMemberDetailsBinding binding;

    EditText txtId, txtName, txtEmail, txtLocation ,txtPassword;
    DB_Helper db;

    Button btnCView, btnCFind, btnCClear, btnCModify, btnCErase;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        manageMemberDetailsViewModel =
                new ViewModelProvider(this).get(ManageMemberDetailsViewModel.class);

        binding = FragmentManageMemberDetailsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        final TextView textView = binding.textSlideshow;
//        manageMemberDetailsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
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

        txtId = view.findViewById(R.id.txtUserID001);
        txtName = view.findViewById(R.id.txtUserName001);
        txtEmail = view.findViewById(R.id.txtUserEmail001);
        txtLocation = view.findViewById(R.id.txtUserLocation001);
        txtPassword = view.findViewById(R.id.txtUserPassword001);

        db = new DB_Helper(requireContext());

        btnCView = view.findViewById(R.id.btnCView);
        btnCFind = view.findViewById(R.id.btnCFind);
        btnCClear = view.findViewById(R.id.btnCClear);
        btnCModify = view.findViewById(R.id.btnCModify);
        btnCErase = view.findViewById(R.id.btnCErase);

        btnCView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewdetails(v);
            }
        });

        btnCFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                find_memebr(v);
            }
        });


        btnCClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_click(v);
            }
        });

        btnCModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update(v);
            }
        });

        btnCErase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete(v);
            }
        });


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void clear() {
        txtId.setText(null);
        txtName.setText(null);
        txtEmail.setText(null);
        txtLocation.setText(null);
        txtPassword.setText(null);
        txtId.requestFocus();
    }

    public void clear_click(View view) {
        clear();
    }

    public void find_memebr(View view) {
        Member member = new Member();
        int id = Integer.parseInt(txtId.getText().toString());
        member.setId(id);
        member = db.findMember(member);
        try {
            if (member != null) {
                txtName.setText(member.getName());
                txtEmail.setText(member.getEmail());
                txtLocation.setText(member.getLocation());
                txtPassword.setText(member.getPassword());
            } else {
                Toast.makeText(requireContext(), "User Record not Found!", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(requireContext(), "Cannot Find User", Toast.LENGTH_SHORT).show();
            e.getStackTrace();
        }
    }

    public void update(View view) {
        Member member = new Member();
        member.setId(Integer.parseInt(txtId.getText().toString()));
        member.setName(txtName.getText().toString());
        member.setEmail(txtEmail.getText().toString());
        member.setLocation(txtLocation.getText().toString());
        member.setPass(txtPassword.getText().toString());

        db.update_member(member);
        Toast.makeText(requireContext(), "User Record Updated!", Toast.LENGTH_SHORT).show();
        clear();

    }

    public void delete(View view) {
        Member member = new Member();
        member.setId(Integer.parseInt(txtId.getText().toString()));

        db.delete_member(member);
        Toast.makeText(requireContext(), "User Record Deleted!", Toast.LENGTH_SHORT).show();
        clear();
    }

    public void viewdetails(View view) {
        Cursor res = db.viewdata();
        if(res.getCount()==0){
            Toast.makeText(requireContext(),"No Entry Exists",Toast.LENGTH_SHORT).show();
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()){

            buffer.append("MOBILE NO :"+res.getInt(0)+"\n");
            buffer.append("NAME           :"+res.getString(1)+"\n");
            buffer.append("EMAIL           :"+res.getString(2)+"\n");
            buffer.append("LOCATION    :"+res.getString(3)+"\n");
            buffer.append("PASSOWRD  :"+res.getString(4)+"\n\n");

        }
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());

        builder.setCancelable(true);
        builder.setTitle("Customer Details");
        builder.setMessage(buffer.toString());
        builder.show();
    }
}