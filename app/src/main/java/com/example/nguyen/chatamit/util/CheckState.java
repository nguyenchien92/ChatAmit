package com.example.nguyen.chatamit.util;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.List;

public class CheckState {
    public static Fragment getFragVisible(FragmentManager fm)
    {

        List<Fragment> mFragments = fm.getFragments();
        Fragment currentFrag = null;

        for(Fragment a : mFragments)
        {
            boolean state = a.isVisible();
            if(state)
            {
                currentFrag = a;
            }
        }

        return currentFrag;
    }
}
