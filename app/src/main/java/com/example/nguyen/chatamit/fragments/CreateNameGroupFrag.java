package com.example.nguyen.chatamit.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.nguyen.chatamit.R;
import com.example.nguyen.chatamit.util.NavigationTo;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CreateNameGroupFrag extends Fragment {
    private View rootView;
    private ImageView ivBack, ivSuccess;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.frag_create_name_group_chat_layout, container, false);

        init();
        setEventListener();

        return rootView;
    }

    private void setEventListener() {

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavigationTo.backTo(getFragmentManager());
                getActivity().findViewById(R.id.bottom_navigation_bar).setVisibility(View.VISIBLE);
            }
        });

        ivSuccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // No event set yet....

                redirectionFragment();


//                NavigationTo.navigationToVersionWithTag(getFragmentManager(),
//                        R.id.frame_container,new CreateChatGroupFrag());
            }
        });
    }

    private void redirectionFragment() {
        FragmentManager mn = getFragmentManager();

        Fragment currentMess = mn.findFragmentByTag(MessageFragment.class.getSimpleName());
        Fragment currentContact = mn.findFragmentByTag(ContactFragment.class.getSimpleName());

        if (currentMess instanceof MessageFragment) {
            NavigationTo.navigationToVersionWithTag(getFragmentManager(),
                    R.id.frame_container, new CreateChatGroupFrag());

        } else if (currentContact instanceof ContactFragment) {

            Toast.makeText(getContext(), "No set event yet....", Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(getContext(), "The thread activity incorrect", Toast.LENGTH_SHORT).show();
        }

    }

    private void init() {
        ivBack = rootView.findViewById(R.id.iv_back);
        ivSuccess = rootView.findViewById(R.id.iv_success);

        BottomNavigationView bottomNavigationView = getActivity()
                .findViewById(R.id.bottom_navigation_bar);
        bottomNavigationView.setVisibility(View.GONE);
    }
}
