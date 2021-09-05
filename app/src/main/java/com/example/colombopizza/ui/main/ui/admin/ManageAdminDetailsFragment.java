package com.example.colombopizza.ui.main.ui.admin;

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

import com.example.colombopizza.Admin;
import com.example.colombopizza.db.DB_Demo1;
import com.example.colombopizza.R;
import com.example.colombopizza.databinding.FragmentManageAdminDetailsBinding;
import com.google.android.play.core.splitinstall.f;

import org.jetbrains.annotations.NotNull;


public class ManageAdminDetailsFragment extends Fragment {

    private ManageAdminDetailsViewModel manageAdminDetailsViewModel;
    private FragmentManageAdminDetailsBinding binding;

    EditText txtId, txtName, txtEmail, txtPassword;
    DB_Demo1 db;

    Button btnASignup, btnAFind, clear_click, btnAView, btnADelate, btnAUpdate;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        manageAdminDetailsViewModel =
                new ViewModelProvider(this).get(ManageAdminDetailsViewModel.class);

        binding = FragmentManageAdminDetailsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        final TextView textView = binding.textGallery;
//        manageAdminDetailsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });



        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }



    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        txtId = view.findViewById(R.id.txtAdminID);
        txtName = view.findViewById(R.id.txtAdminName);
        txtEmail = view.findViewById(R.id.txtAdminEmail);
        txtPassword = view.findViewById(R.id.txtAdminPassword);

        db = new DB_Demo1(requireContext());

        btnASignup = view.findViewById(R.id.btnASignup);
        btnAFind = view.findViewById(R.id.btnAFind);
        clear_click = view.findViewById(R.id.btnAClear);
        btnAView = view.findViewById(R.id.btnAView);
        btnADelate = view.findViewById(R.id.btnADelate);
        btnAUpdate = view.findViewById(R.id.btnAUpdate);

        btnASignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert_admin();
            }
        });


        btnAFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findAdmin(v);
            }
        });

        clear_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_click(v);
            }
        });

        btnAView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewdetails(v);
            }
        });

        btnADelate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete(v);
            }
        });

        btnAUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update(v);
            }
        });

    }

    public void clear() {
        txtId.setText(null);
        txtName.setText(null);
        txtEmail.setText(null);
        txtPassword.setText(null);
        txtId.requestFocus();
    }

    public void clear_click(View view) {
        clear();
    }

    public void findAdmin(View view) {
        Admin admin = new Admin();
        int id = Integer.parseInt(txtId.getText().toString());
        admin.setId(id);
        admin = db.findAdmin(admin);
        try {
            if (admin!= null) {
                txtName.setText(admin.getName());
                txtEmail.setText(admin.getEmail());
                txtPassword.setText(admin.getPassword());
            } else {
                Toast.makeText(requireContext(), "User Record not Found!", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(requireContext(), "Cannot Find User", Toast.LENGTH_SHORT).show();
            e.getStackTrace();
        }
    }

    public void update(View view) {
        Admin admin = new Admin();
        admin.setId(Integer.parseInt(txtId.getText().toString()));
        admin.setName(txtName.getText().toString());
        admin.setEmail(txtEmail.getText().toString());
        admin.setPass(txtPassword.getText().toString());

        db.update_admin(admin);
        Toast.makeText(requireContext(), "User Record Updated!", Toast.LENGTH_SHORT).show();
        clear();

    }

    public void delete(View view) {
        Admin admin = new Admin();
        admin.setId(Integer.parseInt(txtId.getText().toString()));

        db.delete_admin(admin);
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

            buffer.append("ADMIN ID      :"+res.getInt(0)+"\n");
            buffer.append("NAME             :"+res.getString(1)+"\n");
            buffer.append("EMAIL            :"+res.getString(2)+"\n");
            buffer.append("PASSWORD   :"+res.getString(3)+"\n\n");

        }
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());

        builder.setCancelable(true);
        builder.setTitle("Administrations  Details");
        builder.setMessage(buffer.toString());
        builder.show();
    }
    public void insert_admin() {

        String idString = txtId.getText().toString();
        String name = txtName.getText().toString();
        String email = txtEmail.getText().toString();
        String pass = txtPassword.getText().toString();

        if(idString.equals("") || idString == null) {
            Toast.makeText(requireActivity(), "Id is Empty", Toast.LENGTH_SHORT).show();
        }else if(name.equals("") || name == null) {
            Toast.makeText(requireActivity(), "Name is Empty", Toast.LENGTH_SHORT).show();
        }else if(email.equals("") || email == null) {
            Toast.makeText(requireActivity(), "Email is Empty", Toast.LENGTH_SHORT).show();
        }else if(pass.equals("") || pass == null) {
            Toast.makeText(requireActivity(), "Pass is Empty", Toast.LENGTH_SHORT).show();
        }else {

            Admin a = new Admin();

            int id = Integer.parseInt(idString);


            a.setId(id);
            a.setName(name);
            a.setEmail(email);
            a.setPass(pass);

            try {
                db.createAdmin(a);
                Toast.makeText(requireContext(), "Admin Record Inserted", Toast.LENGTH_SHORT).show();
                clear();

            } catch (Exception e) {
                Toast.makeText(requireContext(), "Cannot insert Admin Record", Toast.LENGTH_SHORT).show();
                e.getStackTrace();
            }
        }
    }
}