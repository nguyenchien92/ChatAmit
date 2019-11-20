package com.example.nguyen.chatamit.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.nguyen.chatamit.R;
import com.example.nguyen.chatamit.util.NavigationTo;

public class ContactContainerFragment extends BaseFragment {
    View rootView;
    private FrameLayout frameContainer;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.container_fragment, container, false);
        frameContainer = rootView.findViewById(R.id.frame_container);

        NavigationTo.navigationToRoot(getChildFragmentManager(),R.id.frame_container,new ContactFragment());

        return rootView;
    }
}
