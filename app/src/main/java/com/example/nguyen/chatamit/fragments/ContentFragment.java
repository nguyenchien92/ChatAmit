package com.example.nguyen.chatamit.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.nguyen.chatamit.R;
import com.example.nguyen.chatamit.util.NavigationTo;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class ContentFragment extends BaseFragment {

    private View rootView;
    private BottomNavigationView bottom_navigation_bar;
    public FrameLayout frameDisplayContent;
    private Dialog myDialog;
    private TextView tvYes, tvNo;


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
                        loadFragment(new MoreContainerFragment());
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

        myDialog = new Dialog(getContext());

        loadHomeFrag(getFragmentManager(), new MessContainerFragment());
    }

    private void loadHomeFrag(FragmentManager fm, Fragment fragment) {
        NavigationTo.navigationToVersionWithTag(fm, R.id.frame_display_content, fragment);
    }

    @Override
    public boolean onBackPressed() {
        FragmentManager fm = getFragmentManager();

        Fragment frag = fm.findFragmentById(R.id.frame_display_content);

        FragmentManager childMn = frag.getChildFragmentManager();

        int count = childMn.getBackStackEntryCount();

        if (count > 0) {

            Fragment childFrag = childMn.getFragments().get(0);

            if (childFrag instanceof ChatScreenFrag) {
                View view = childFrag.getView();

                RecyclerView rvStick = view.findViewById(R.id.rv_sticker_screen);


                if(rvStick.getVisibility() == View.VISIBLE)
                {
                    rvStick.setVisibility(View.GONE);
                    return true;
                }

            }
                childMn.popBackStack();
                bottom_navigation_bar.setVisibility(View.VISIBLE);

                return true;
        } else {
            if (frag instanceof MessContainerFragment) {
                showPopUp();
                return true;
            } else {
                bottom_navigation_bar.setSelectedItemId(R.id.action_message);

                return true;
            }
        }

    }

    private void showPopUp() {
        myDialog.setContentView(R.layout.custom_popup);
        tvYes = myDialog.findViewById(R.id.tv_yes);
        tvNo = myDialog.findViewById(R.id.tv_no);

        myDialog.show();

        tvNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                myDialog.dismiss();
            }
        });

        tvYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getActivity().finish();
            }
        });
    }
}
//
//        if (count > 0) {
//
//                if (current instanceof ChatScreenFrag) {
//                View view = current.getView().findViewById(R.id.include_bottom);
//                RecyclerView rvStickerView = view.findViewById(R.id.rv_sticker_screen);
//
//                if (rvStickerView.getVisibility() == View.VISIBLE) {
//                rvStickerView.setVisibility(View.GONE);
//                childFm.popBackStack(current.getTag(), 0);
//                } else {
//                childBackFragment(childFm, bottom);
//                }
//                } else {
//
//                childBackFragment(childFm, bottom);
//                }
//
//                } else {
//                if (!(current instanceof MessageFragment)) {
//                bottom.setSelectedItemId(R.id.action_message);
//                } else {
//                showPopUp();
//                }
//                }
//                }
//
//
////This method is temporary... no idea instead :((
//private void childBackFragment(FragmentManager fm, BottomNavigationView bottomNavigationView) {
//        fm.popBackStack(fm.getFragments().get(0).getTag(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
//        bottomNavigationView.setVisibility(View.VISIBLE);
//        }