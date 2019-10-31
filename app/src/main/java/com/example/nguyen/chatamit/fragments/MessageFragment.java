package com.example.nguyen.chatamit.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import com.example.nguyen.chatamit.R;
import com.example.nguyen.chatamit.adapteres.CustomAdapter;
import com.example.nguyen.chatamit.models.User;
import com.example.nguyen.chatamit.util.NavigationTo;
import com.example.nguyen.chatamit.viewmodels.MessViewModel;

import java.util.List;

public class MessageFragment extends Fragment {

    private CustomAdapter adapter;
    private View rootView;
    private TextView tvTitle;
    private RecyclerView rvContainer;
    private PopupMenu popupMenu;
    private ImageView ivPopupMenu;
    private FrameLayout frameLayoutMain;
    private SwipeRefreshLayout myReload;
    private MessViewModel messViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.frag_mess_contact_layout, container, false);

//        createData();
        init();
        setClickListenerImage();
        setConfigView();
        // set event load more data of message fragment...
        setReload();

        return rootView;
    }

    private void setReload() {
        myReload.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                FragmentManager manager = getFragmentManager();

                Fragment currentFragment = manager.getFragments().get(0);

                if ((currentFragment.getTag()).equals(MessageFragment.class.getSimpleName()))
                {

                    //performing work update data...?
                    updateData();
                    myReload.setRefreshing(false);
                }
            }
        });
    }

    private void updateData() {
        messViewModel.updateUser(new User("Chien_281992", "20/08/2019", "Updated"
                , R.drawable.ic_user_group_gray, 1));
        Toast.makeText(getActivity(), "Updated", Toast.LENGTH_SHORT).show();
    }


    private void setConfigView() {

        adapter = new CustomAdapter(getActivity(),
                messViewModel.getDataUser().getValue(),getFragmentManager());

        adapter.setClickItemListener(new CustomAdapter.ClickItemListener() {
            @Override
            public void clickItem(int position) {
                NavigationTo.navigationToVersionWithTag(getFragmentManager(),
                        R.id.frame_container, new ChatScreenFrag());
            }
        });


        LinearLayoutManager linear = new LinearLayoutManager(getActivity());
        linear.setReverseLayout(false);

        rvContainer.setAdapter(adapter);
        rvContainer.setLayoutManager(linear);
        rvContainer.addItemDecoration(new DividerItemDecoration(getActivity(), OrientationHelper.VERTICAL));

        tvTitle.setText("Messages");
        tvTitle.setTextColor(Color.BLACK);
        tvTitle.setTextSize(20);
    }

    private void init() {
        rvContainer = rootView.findViewById(R.id.rv_container_mess_contact);
        tvTitle = rootView.findViewById(R.id.tv_title_tab_frag);
        frameLayoutMain = rootView.findViewById(R.id.frame_layout_main);


        ivPopupMenu = rootView.findViewById(R.id.iv_icon_pop_menu);
        ivPopupMenu.setImageResource(R.drawable.ic_edit);

        //find reload id;

        myReload = rootView.findViewById(R.id.swipe_reload);

        //event live data
        performLiveData();
    }


    private void setClickListenerImage() {
        ivPopupMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopUp();
            }
        });
    }


    private void showPopUp() {
        popupMenu = new PopupMenu(getActivity(), ivPopupMenu);
        MenuInflater inflater = popupMenu.getMenuInflater();
        inflater.inflate(R.menu.menu_bar, popupMenu.getMenu());

        int size = popupMenu.getMenu().size();

        removeItem(size, R.id.action_create_group);

        popupMenu.show();


        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.action_create_group:
                        NavigationTo.navigationToVersionWithTag(getFragmentManager(),
                                R.id.frame_container, new CreateNameGroupFrag());

                        return true;
                }

                return false;
            }
        });


        popupMenu.setOnDismissListener(new PopupMenu.OnDismissListener() {
            @Override
            public void onDismiss(PopupMenu popupMenu) {
                popupMenu.dismiss();
            }
        });
    }


    private void removeItem(int sizeMenu, int idItem) {

        for (int i = 0; i < sizeMenu; i++) {

            MenuItem item = popupMenu.getMenu().getItem(i);
            MenuItem createGroup = popupMenu.getMenu().findItem(idItem);

            if (!createGroup.getTitle().toString().equals(item.getTitle().toString())) {
                item.setVisible(false);
            }
        }
    }


    public void performLiveData()
    {
        messViewModel = ViewModelProviders.of(this).get(MessViewModel.class);
        messViewModel.init();

        messViewModel.getDataUser().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                adapter.notifyDataSetChanged();
            }
        });
    }
}
