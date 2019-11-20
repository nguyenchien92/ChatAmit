package com.example.nguyen.chatamit.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;

import com.example.nguyen.chatamit.R;

public class TransactionContainer extends BaseFragment {

    private View rootView;

    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.trainsaction_container_layout, container, false);

        return rootView;
    }
}
