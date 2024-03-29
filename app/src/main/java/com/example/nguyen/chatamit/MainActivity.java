package com.example.nguyen.chatamit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nguyen.chatamit.fragments.BaseFragment;
import com.example.nguyen.chatamit.fragments.ChatScreenFrag;
import com.example.nguyen.chatamit.fragments.MainFrag;
import com.example.nguyen.chatamit.fragments.MessageFragment;
import com.example.nguyen.chatamit.fragments.SignUpFragment;
import com.example.nguyen.chatamit.util.NavigationTo;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    private FrameLayout frameLayout;
    private Dialog myDialog;
    private TextView tvYes, tvNo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        navigationTo();

    }

    private void navigationTo() {
        NavigationTo.navigationToRoot(getSupportFragmentManager(), R.id.frame_layout_main,
                new MainFrag());
    }

    private void init() {
        frameLayout = findViewById(R.id.frame_layout_main);
        myDialog = new Dialog(this);
    }


    @Override
    public void onBackPressed() {

        FragmentManager fm = getSupportFragmentManager();

        List<Fragment> current = fm.getFragments();

        boolean state = false;

        for (Fragment f : current) {
            if (f != null && f instanceof BaseFragment) {
                state = ((BaseFragment) f).onBackPressed();
                if (state) {
                    break;
                }
            }
        }
        if (!state) {
            super.onBackPressed();
        }


    }


    //This method is temporary... no idea instead :((
    private void childBackFragment(FragmentManager fm, BottomNavigationView bottomNavigationView) {
        fm.popBackStack(fm.getFragments().get(0).getTag(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
        bottomNavigationView.setVisibility(View.VISIBLE);
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

                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (myDialog != null && myDialog.isShowing()) {
            myDialog.dismiss();
        }
    }

}
