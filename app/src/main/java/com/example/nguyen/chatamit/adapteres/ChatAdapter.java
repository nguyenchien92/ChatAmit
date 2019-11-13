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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nguyen.chatamit.R;
import com.example.nguyen.chatamit.models.Message;
import com.example.nguyen.chatamit.models.Sticker;

import java.util.List;
import java.util.Map;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatHolder> {
    private Context context;
    private List<Message> mMessages;
//    private Map<String, List<Message>> mapData;
//    public static final String TYPE_DATA = "type_data";


//    public ChatAdapter(Context context, Map<String, List<Message>> mapData) {
//        this.context = context;
//        this.mapData = mapData;
//    }

//    public void setData(Map<String, List<Message>> mapData) {
//        this.mapData = mapData;
//    }

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


        if (m.getContent() != null) {
            holder.setViewWithText(m);
        } else {
            holder.setViewWithImage(m);
        }


    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
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
            tvUser.setText(m.getContent());
            ivUser.setVisibility(View.GONE);
        }
    }
}
