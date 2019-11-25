package com.example.nguyen.chatamit.fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.PointerIcon;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Adapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.nguyen.chatamit.R;
import com.example.nguyen.chatamit.adapteres.CustomAdapter;
import com.example.nguyen.chatamit.models.User;
import com.example.nguyen.chatamit.util.NavigationTo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CreateChatGroupFrag extends BaseFragment {

    private View rootView,rootChild;
    private RelativeLayout mSearchView, mEditSearchView;
    private EditText edSearch;
    private TextView tvClose;
    private ImageView ivIconSearch, ivBackScreen;
    private RecyclerView rvSuggestUser, rvListContact;
    private CustomAdapter adapter;
    private List<User> mUsers = new ArrayList<>();
    private boolean state = true;
    private LinearLayoutManager manager = new LinearLayoutManager(getContext());
    private RadioButton radioButton;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.frag_create_group_chat_layout, container, false);

        getData();
        init();
        configUI();

        setAdapter();

        edSearch.requestFocus();
        return rootView;
    }

    private void setAdapter() {

        adapter = new CustomAdapter(getContext(), mUsers, getFragmentManager());

        rvListContact.setAdapter(adapter);
        rvListContact.setLayoutManager(manager);

        state = setClickEventItem(adapter);

        if(state)
        {
            //het y tuong....
        }

    }

    private boolean setClickEventItem(final CustomAdapter adapter) {
        adapter.setClickItemListener(new CustomAdapter.ClickItemListener() {
            @Override
            public void clickItem(int position) {
                Toast.makeText(getContext(), adapter.getUserList().get(position).getName(), Toast.LENGTH_SHORT).show();

                User user = adapter.getUserList().get(position);

                //how's get view item of rvListContact....?
                View rootChild = rvListContact.getChildAt(position);

                radioButton = rootChild.findViewById(R.id.radio_button);
                radioButton.setChecked(true);

            }
        });

        return true;
    }

    private void getData() {

        File mFile = new File(getContext()
                .getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), "myData.txt");

        if (mFile.exists()) {
            try {

                FileInputStream fileOutputStream = new FileInputStream(mFile.getAbsolutePath());

                ObjectInputStream outputStream = new ObjectInputStream(fileOutputStream);

                while (state) {
                    User user = (User) outputStream.readObject();

                    if (user != null)
                        mUsers.add(user);
                    else
                        state = false;
                }

                outputStream.close();
                fileOutputStream.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(getContext(), "file is not exist", Toast.LENGTH_SHORT).show();
        }

    }

    private void configUI() {
        mSearchView.setOnClickListener(setEvent);
        tvClose.setOnClickListener(setEvent);
        ivBackScreen.setOnClickListener(setEvent);
    }


    private View.OnClickListener setEvent = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int id = view.getId();
            switch (id) {
                case R.id.relative_search_view:
                    mSearchView.setVisibility(View.GONE);
                    mEditSearchView.setVisibility(View.VISIBLE);

                    break;

                case R.id.tv_close:
                    mSearchView.setVisibility(View.VISIBLE);
                    mEditSearchView.setVisibility(View.GONE);

                    break;

                case R.id.iv_back_screen:
                    NavigationTo.backTo(getFragmentManager());

                    break;
            }

        }
    };

    private void init() {
        mSearchView = rootView.findViewById(R.id.relative_search_view);
        mEditSearchView = rootView.findViewById(R.id.relative_search_view_edit);
        edSearch = rootView.findViewById(R.id.edit_search);
        tvClose = rootView.findViewById(R.id.tv_close);

        ivBackScreen = rootView.findViewById(R.id.iv_back_screen);
        rvSuggestUser = rootView.findViewById(R.id.rv_suggest_user);
        rvListContact = rootView.findViewById(R.id.rv_display_list_people);

        mSearchView.setVisibility(View.GONE);
        rvSuggestUser.setVisibility(View.GONE);
    }

}