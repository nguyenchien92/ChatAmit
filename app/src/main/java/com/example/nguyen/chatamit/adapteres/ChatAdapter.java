package com.example.nguyen.chatamit.adapteres;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nguyen.chatamit.R;
import com.example.nguyen.chatamit.models.Message;
import com.example.nguyen.chatamit.models.Sticker;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatHolder> {
    private Context context;
    private List<Message> mMessages;

    public ChatAdapter(Context context, List<Message> mMessages) {
        this.context = context;
        this.mMessages = mMessages;
    }

    public List<Message> getData() {
        return mMessages;
    }

    public void setData(List<Message> m) {
        this.mMessages = m;
    }

    @NonNull
    @Override
    public ChatHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_chat_screen_layout, parent, false);

        return new ChatHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatHolder holder, int position) {

        Message m = mMessages.get(position);


        // not bind data....


    }

    @Override
    public int getItemCount() {
        return mMessages.size();
    }

    public class ChatHolder extends RecyclerView.ViewHolder {

        public TextView tvOtherUser, tvUser;
        public ImageView ivAnotherUser, ivUser;

        public ChatHolder(@NonNull View itemView) {
            super(itemView);


            tvOtherUser = itemView.findViewById(R.id.tv_other_user);
            tvUser = itemView.findViewById(R.id.tv_user);

            ivAnotherUser = itemView.findViewById(R.id.iv_another_user);
            ivUser = itemView.findViewById(R.id.iv_user);
        }

        public void setViewWithImage(Message message) {

            Sticker mSticker = message.getmSticker();
            String resIdSticker = mSticker.getImage();

            ivUser.setImageResource(Integer.parseInt(resIdSticker));
            tvUser.setVisibility(View.GONE);
        }

        public void setViewWithText(Message m) {
            ivUser.setVisibility(View.GONE);
            tvUser.setText(m.getContent());
        }
    }
}
