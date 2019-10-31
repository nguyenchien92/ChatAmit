package com.example.nguyen.chatamit.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.nguyen.chatamit.R;
import com.example.nguyen.chatamit.adapteres.CustomAdapter;
import com.example.nguyen.chatamit.models.User;
import com.example.nguyen.chatamit.util.NavigationTo;
import com.example.nguyen.chatamit.viewmodels.ContactViewModel;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ContactFragment extends Fragment {

    private View rootView;
    private CustomAdapter adapter;
    private ImageView ivPopupMenu;
    private TextView tvTitle;
    private List<User> userList = new ArrayList<>();
    private RecyclerView rvContainer;
    private SwipeRefreshLayout myReload;
    private ContactViewModel contactViewModel;


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.frag_mess_contact_layout, container, false);

//        createData();
        init();
        setImageClickListener();
        setConfigView();

        // set event load more data of contact fragment...
        setReload();

        return rootView;
    }

    private void setReload() {
        myReload.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                FragmentManager manager = getFragmentManager();
                Fragment current = manager.getFragments().get(0);

                if ((current.getTag()).equals(ContactFragment.class.getSimpleName())) {
                    updateData();
                    myReload.setRefreshing(false);
                }
            }
        });
    }

    private void updateData() {
        Toast.makeText(getActivity(), ContactFragment.class.getSimpleName(), Toast.LENGTH_SHORT).show();
    }


    private void setConfigView() {
        adapter = new CustomAdapter(getActivity(),
                contactViewModel.getDataUser().getValue(),getFragmentManager());

        adapter.setClickItemListener(new CustomAdapter.ClickItemListener() {
            @Override
            public void clickItem(int position) {
                NavigationTo.navigationToVersionWithTag(getFragmentManager(),
                        R.id.frame_container, new ChatScreenFrag());
            }
        });

        rvContainer.setAdapter(adapter);
        rvContainer.setLayoutManager(new LinearLayoutManager(getActivity()));

        tvTitle.setText("Contacts");
        tvTitle.setTextColor(Color.BLACK);
        tvTitle.setTextSize(20);
    }

    private void init() {
        rvContainer = rootView.findViewById(R.id.rv_container_mess_contact);
        tvTitle = rootView.findViewById(R.id.tv_title_tab_frag);

        ivPopupMenu = rootView.findViewById(R.id.iv_icon_pop_menu);
        ivPopupMenu.setImageResource(R.drawable.ic_user_group);

        //find reload id;
        myReload = rootView.findViewById(R.id.swipe_reload);

        performLiveData();
    }

    private void performLiveData() {
        contactViewModel = ViewModelProviders.of(this).get(ContactViewModel.class);
        contactViewModel.init();

        contactViewModel.getDataUser().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                adapter.notifyDataSetChanged();
                saveDataUser(users);
            }
        });

    }

    private void saveDataUser(List<User> mUser) {
        try {
            File file = new File(getContext().
                    getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), "myData.txt");

            ObjectOutputStream outputStream = new ObjectOutputStream(
                    new FileOutputStream(file));

            for (User u : mUser) {
                outputStream.writeObject(u);
//                Log.d("Result",u.getName());
            }

            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    private void createData() {
//        if (userList.isEmpty()) {
//            userList.add(new User("Chien", 0));
//            userList.add(new User("Hoa", 0));
//            userList.add(new User("Ly", 0));
//            userList.add(new User("Minh", 0));
//            userList.add(new User("Hien", 0));
//            userList.add(new User("Chau", 0));
//        }
//    }

    private void setImageClickListener() {
        ivPopupMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavigationTo.navigationToVersionWithTag(getFragmentManager(),
                        R.id.frame_container, new CreateNameGroupFrag());
            }
        });
    }

}
