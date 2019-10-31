package com.example.nguyen.chatamit.fragments;

import android.app.Dialog;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.nguyen.chatamit.R;
import com.example.nguyen.chatamit.adapteres.CustomAdapter;
import com.example.nguyen.chatamit.models.User;
import com.example.nguyen.chatamit.util.DividerItemDecorationDemo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.example.nguyen.chatamit.adapteres.CustomAdapter.TYPE_MORE;

public class MoreFragment extends Fragment {

    private View rootView;
    private RecyclerView rvContentInfo, rvContentInfo2, rvContentInfo3;
    private TextView tvNameUser, tvTitleMore;
    private List<User> inforList = new ArrayList<>();
    private CustomAdapter adapter;
    private List<User> mInfor1 = new ArrayList<>();
    private List<User> mInfor2 = new ArrayList<>();
    private List<User> mInfor3 = new ArrayList<>();
    private Dialog myDialog;
    private TextView tvYes, tvNo;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.frag_more_layout, container, false);

        init();
        createData();
        fillData();
        setDisableScrollRv();
        setTvTitle();

        return rootView;
    }

    private void setDisableScrollRv() {
        rvContentInfo.setNestedScrollingEnabled(false);
        rvContentInfo2.setNestedScrollingEnabled(false);
        rvContentInfo3.setNestedScrollingEnabled(false);
    }


    // confusing data...of method fillData()
    private void fillData() {

        for (int i = 0; i < inforList.size(); i++) {

            User user = inforList.get(i);

            if (i < 3) {
                mInfor1.add(user);
            } else if (i >= 3 && i < 9) {
                mInfor2.add(user);
            } else {
                mInfor3.add(user);
            }
        }

        setViewConfig(rvContentInfo, mInfor1);
        setViewConfig(rvContentInfo2, mInfor2);
        setViewConfig(rvContentInfo3, mInfor3);
    }

    //This method set view for each recycle view
    private void setViewConfig(RecyclerView rv, final List<User> listUser) {
        adapter = new CustomAdapter(getActivity(), listUser,getFragmentManager());
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter.setClickItemListener(new CustomAdapter.ClickItemListener() {
            @Override
            public void clickItem(int position) {
                 User user = listUser.get(position);
                 if(user.getImageUser() == R.drawable.ic_logout)
                 {
                     showPopUp();
                 }
            }
        });
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

    private void createData() {
        if (inforList.isEmpty()) {
            inforList.add(new User(R.drawable.ic_lock, "Privacy", TYPE_MORE));
            inforList.add(new User(R.drawable.ic_security, "Account and security", TYPE_MORE));
            inforList.add(new User(R.drawable.ic_cloud_backup, "Backup and remote messages", TYPE_MORE));

            inforList.add(new User(R.drawable.ic_notification, "Notification", TYPE_MORE));
            inforList.add(new User(R.drawable.ic_mess, "Message", TYPE_MORE));
            inforList.add(new User(R.drawable.ic_clock, "Diary and moment", TYPE_MORE));

            inforList.add(new User(R.drawable.ic_contact, "Contact", TYPE_MORE));
            inforList.add(new User(R.drawable.ic_language, "Language and font", TYPE_MORE));
            inforList.add(new User(R.drawable.ic_infomation, "Information about ChatANE", TYPE_MORE));

            inforList.add(new User(R.drawable.ic_logout, "Logout", TYPE_MORE));

        }
    }

    private void init() {
        rvContentInfo = rootView.findViewById(R.id.rv_content_info);
        rvContentInfo2 = rootView.findViewById(R.id.rv_content_info_2);
        rvContentInfo3 = rootView.findViewById(R.id.rv_content_info_3);


        tvNameUser = rootView.findViewById(R.id.tv_name_user);
        tvTitleMore = rootView.findViewById(R.id.tv_title_more);

        DividerItemDecorationDemo dividerItemDecoration = new DividerItemDecorationDemo(getContext()
                , R.drawable.divider, R.id.linear_text, R.id.iv_icon_user_more);
        rvContentInfo.addItemDecoration(dividerItemDecoration);
        rvContentInfo2.addItemDecoration(dividerItemDecoration);
        rvContentInfo3.addItemDecoration(dividerItemDecoration);
        myDialog = new Dialog(getActivity());
    }

    private void setTvTitle() {
        tvTitleMore.setText("More");
        tvTitleMore.setTextColor(Color.BLACK);
        tvTitleMore.setTextSize(20);

        tvNameUser.setTextColor(Color.BLACK);
    }

}
