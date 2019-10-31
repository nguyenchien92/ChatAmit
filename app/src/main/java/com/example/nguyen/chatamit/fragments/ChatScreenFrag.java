package com.example.nguyen.chatamit.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nguyen.chatamit.MainActivity;
import com.example.nguyen.chatamit.R;
import com.example.nguyen.chatamit.adapteres.StickerAdapter;
import com.example.nguyen.chatamit.models.Sticker;
import com.example.nguyen.chatamit.util.NavigationTo;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ChatScreenFrag extends Fragment {

    private View rootView, viewBottom;
    private ImageView ivBack, ivSticker, ivSend;
    private RecyclerView rvSticker,rvDisplayContentChat;
    private List<Sticker> mStickers = new ArrayList<>();
    private StickerAdapter adapter;
    private boolean isState = true;
    private EditText edSend;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.frag_chat_screen_layout, container, false);

        init();
        setEvent();

        return rootView;
    }

    private void setEvent() {
        ivBack.setOnClickListener(backToParent);

        ivSticker.setOnClickListener(setRvSticker);

        ivSend.setOnClickListener(setSend);

        edSend.setOnClickListener(setEdSend);
    }

    View.OnClickListener setEdSend = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            rvSticker.setVisibility(View.GONE);
        }
    };


    View.OnClickListener backToParent = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            NavigationTo.backTo(getFragmentManager());
            getActivity().findViewById(R.id.bottom_navigation_bar).setVisibility(View.VISIBLE);
        }
    };

    View.OnClickListener setRvSticker = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (isState) {
                showRvSticker();
                isState = false;
            } else {
                rvSticker.setVisibility(View.GONE);
                isState = true;
            }
        }
    };

    private void showRvSticker() {
        rvSticker.setVisibility(View.VISIBLE);
        if (mStickers != null) {
            adapter = new StickerAdapter(getContext(), mStickers);
            GridLayoutManager grid = new GridLayoutManager(getActivity(), 9);
            rvSticker.setAdapter(adapter);
            rvSticker.setLayoutManager(grid);

            adapter.setClickListener(new StickerAdapter.ClickListener() {
                @Override
                public void clickItem(int position) {
//                    Toast.makeText(getContext(), position+"", Toast.LENGTH_SHORT).show();

                    setContent(position);

                }
            });
        }
    }

    private void setContent(int position) {

        Sticker image = mStickers.get(position);




    }

    View.OnClickListener setSend = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //set event send mess;
        }
    };

    private void init() {
        ivBack = rootView.findViewById(R.id.iv_back_screen_chat);
        viewBottom = rootView.findViewById(R.id.include_bottom);

        ivSticker = viewBottom.findViewById(R.id.iv_sticker_screen);
        ivSend = viewBottom.findViewById(R.id.iv_send_chat_screen);
        rvSticker = viewBottom.findViewById(R.id.rv_sticker_screen);
        edSend = viewBottom.findViewById(R.id.ed_send_screen);
        rvDisplayContentChat = rootView.findViewById(R.id.display_content_chat);

        if (getActivity() instanceof MainActivity) {
            BottomNavigationView bottom = getActivity().findViewById(R.id.bottom_navigation_bar);
            bottom.setVisibility(View.GONE);
        }


        rvSticker.setVisibility(View.GONE);

        createDataSticker();
    }

    private void createDataSticker() {
        try {
            List<String> images = Arrays.asList(getContext()
                    .getResources().getAssets().list("StickerDemo"));

            for (String i : images) {
                Sticker sticker = new Sticker(i);
                mStickers.addAll(Collections.singleton(sticker));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
