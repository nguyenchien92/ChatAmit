package com.example.nguyen.chatamit.util;

import android.app.Activity;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.nguyen.chatamit.R;

import static androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE;

public class NavigationTo {


//    public static void navigationTo(FragmentManager fm, int frameLayout, Fragment fragment) {
//        FragmentTransaction transaction = fm.beginTransaction();
//        transaction.replace(frameLayout, fragment);
//        transaction.addToBackStack(null);
//        transaction.commit();
//
//    }

    public static void navigationToRoot(FragmentManager fm, int frameLayout, Fragment fragment) {
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(frameLayout, fragment, fragment.getClass().getSimpleName());
        transaction.commit();

    }

    // The confusing at direction within use this method
    // parameter 1 : R.id.xxxx---frameLayout
    // parameter 3: A.Class.getSimpleName() --- tagName
    public static void navigationToVersionWithTag(final FragmentManager fm, int frameLayout,
                                                  Fragment fragment) {

        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(frameLayout, fragment, fragment.getClass().getSimpleName());
        transaction.addToBackStack(fragment.getClass().getSimpleName());
        transaction.commit();

    }

    public static void backTo(FragmentManager fm) {
        fm.popBackStack();
    }
}
