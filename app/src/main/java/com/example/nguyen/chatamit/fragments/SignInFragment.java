package com.example.nguyen.chatamit.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.nguyen.chatamit.R;
import com.example.nguyen.chatamit.util.NavigationTo;


public class SignInFragment extends BaseFragment {

    private View rootView;
    private TextView tvTitle, tvDescription;
    private EditText edPhoneNumber, edPass, edName;
    private ImageView ivIconNext;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.frag_register_login_layout, container, false);

        init();
        setUI();

        ivIconNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavigationTo
                        .navigationToRoot(getFragmentManager()
                                , R.id.frame_layout_main
                                , new ContentFragment());

            }
        });

        return rootView;
    }


    private void setUI() {
        tvTitle.setText("Login");
        tvDescription.setText("Please enter phone number and pass word to login");
        edPhoneNumber.setHint("Phone number");
        edPass.setHint("Pass word");
        edName.setVisibility(View.GONE);
    }

    private void init() {
        tvTitle = rootView.findViewById(R.id.tv_title);
        tvDescription = rootView.findViewById(R.id.tv_description);
        edName = rootView.findViewById(R.id.ed_des_field_name);
        edPass = rootView.findViewById(R.id.ed_des_field_pass);
        edPhoneNumber = rootView.findViewById(R.id.ed_des_field_phone_number);
        ivIconNext = rootView.findViewById(R.id.iv_icon_next);
    }

}