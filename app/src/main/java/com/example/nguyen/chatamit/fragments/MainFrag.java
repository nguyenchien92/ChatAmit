package com.example.nguyen.chatamit.fragments;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.nguyen.chatamit.R;
import com.example.nguyen.chatamit.util.NavigationTo;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;
import java.util.List;

public class MainFrag extends BaseFragment {
    private View rootView;
    private CarouselView carouselView;
    private List<Integer> mImages = new ArrayList<>();
    private Button btLogin, btRegister;


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.frag_login, container, false);

        createData();
        init();
        navigationButton();

        return rootView;
    }

    private void createData() {
        if (mImages.isEmpty()) {
            mImages.add(R.drawable.image_demo_1);
            mImages.add(R.drawable.image_demo_2);
            mImages.add(R.drawable.image_demo_3);
        }
    }

    private void init() {
        carouselView = rootView.findViewById(R.id.carouse_view);
        btLogin = rootView.findViewById(R.id.bt_login);
        btRegister = rootView.findViewById(R.id.bt_register);

        carouselView.setPageCount(mImages.size());
        carouselView.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(mImages.get(position));
            }
        });

        carouselView.setIndicatorGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);
        carouselView.setIndicatorMarginVertical(-55);
    }

    private void navigationButton() {
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToSignIn();
            }
        });

        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToSignUp();
            }
        });
    }

    private void goToSignUp() {
//        NavigationTo
//                .navigationTo(getActivity().getSupportFragmentManager()
//                        , R.id.frame_layout_main, new SignUpFragment());

        NavigationTo.navigationToVersionWithTag(getFragmentManager(), R.id.frame_layout_main,
                new SignUpFragment());

    }

    private void goToSignIn() {
//        NavigationTo
//                .navigationTo(getActivity().getSupportFragmentManager()
//                        , R.id.frame_layout_main, new SignInFragment());

        NavigationTo.navigationToVersionWithTag(getFragmentManager(), R.id.frame_layout_main,
                new SignInFragment());
    }
}
