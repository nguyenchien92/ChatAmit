package com.example.nguyen.chatamit.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.nguyen.chatamit.R;


public class SignUpFragment extends BaseFragment {

    private View rootView;
    private TextView tvTitle, tvDescription;
    private EditText edPhoneNumber, edPass, edName;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.frag_register_login_layout, container, false);

        init();
        setUI();

        return rootView;
    }

    private void setUI() {
        tvTitle.setText("Register");
        tvDescription.setText("Please complete the information and account");
        edPhoneNumber.setHint("Phone number");
        edPass.setHint("Pass word");
        edName.setHint("First and last name");
    }

    private void init() {
        tvTitle = rootView.findViewById(R.id.tv_title);
        tvDescription = rootView.findViewById(R.id.tv_description);
        edName = rootView.findViewById(R.id.ed_des_field_name);
        edPass = rootView.findViewById(R.id.ed_des_field_pass);
        edPhoneNumber = rootView.findViewById(R.id.ed_des_field_phone_number);
    }
}
