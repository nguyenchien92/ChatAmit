package com.example.nguyen.chatamit.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;


import com.example.nguyen.chatamit.R;
import com.example.nguyen.chatamit.util.NavigationTo;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class ContentFragment extends Fragment {

    private View rootView;
    private BottomNavigationView bottom_navigation_bar;
    public FrameLayout frameDisplayContent;


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.frag_content, container, false);

        init();
        navigationItems();

        return rootView;
    }

    private void navigationItems() {

        bottom_navigation_bar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                int itemId = menuItem.getItemId();

                switch (itemId) {
                    case R.id.action_message:
                        loadFragment(new MessContainerFragment());
                        return true;
                    case R.id.action_contact:
                        loadFragment(new ContactContainerFragment());
                        return true;
                    case R.id.action_more:
                        loadFragment(new MoreFragment());
                        return true;
                }

                return false;
            }
        });

    }



    private void loadFragment(Fragment fragment) {
        NavigationTo.navigationToRoot(getFragmentManager(),
                R.id.frame_display_content, fragment);
    }

    private void init() {
        frameDisplayContent = rootView.findViewById(R.id.frame_display_content);
        bottom_navigation_bar = rootView.findViewById(R.id.bottom_navigation_bar);

        Menu menu = bottom_navigation_bar.getMenu();
        menu.findItem(R.id.action_create_group).setVisible(false);
        menu.findItem(R.id.action_new_group_conversation).setVisible(false);

        loadHomeFrag(getFragmentManager(),new MessContainerFragment());
    }

    private void loadHomeFrag(FragmentManager fm,Fragment fragment)
    {
        NavigationTo.navigationToRoot(fm,R.id.frame_display_content,fragment);
    }


}
