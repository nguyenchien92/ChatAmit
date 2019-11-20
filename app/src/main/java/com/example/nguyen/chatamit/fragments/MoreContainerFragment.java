package com.example.nguyen.chatamit.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.nguyen.chatamit.R;
import com.example.nguyen.chatamit.util.NavigationTo;

public class MoreContainerFragment extends BaseFragment {
    View rootView;
    FrameLayout frameContainer;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.container_fragment, container, false);

        frameContainer = rootView.findViewById(R.id.frame_container);

        NavigationTo.navigationToRoot(getChildFragmentManager(),R.id.frame_container,new MoreFragment());

        return rootView;
    }
}
