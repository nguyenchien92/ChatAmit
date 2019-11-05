package com.example.nguyen.chatamit.adapteres;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
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

    public List<Message> getmMessages() {
        return mMessages;
    }

    public void setmMessages(List<Message> m) {
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

        holder.setView(position);
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

        public void setView(int position) {
            Message message = mMessages.get(position);

            Sticker mSticker = message.getmSticker();
            String resIdSticker = mSticker.getImage();

            if (!resIdSticker.isEmpty()) {
                ivUser.setImageResource(Integer.parseInt(resIdSticker));
            }
        }
    }
}
