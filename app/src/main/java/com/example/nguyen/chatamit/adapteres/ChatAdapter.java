package com.example.nguyen.chatamit.adapteres;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nguyen.chatamit.R;
import com.example.nguyen.chatamit.models.Message;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatHolder>{
    private Context context;
    private List<Message> mMessages;

    @NonNull
    @Override
    public ChatHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_chat_screen_layout, parent, false);

        return new ChatHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatHolder holder, int position) {
        Message mess = mMessages.get(position);

        //chua hoan thien viec thiet lap.....

        holder.tvUser.setText(mess.getContent());
        holder.tvOtherUser.setText("Not event....");
    }

    @Override
    public int getItemCount() {
        return mMessages.size();
    }

    public class ChatHolder extends RecyclerView.ViewHolder
    {

        public TextView tvOtherUser,tvUser;

        public ChatHolder(@NonNull View itemView) {
            super(itemView);


            tvOtherUser = itemView.findViewById(R.id.tv_other_user);
            tvUser = itemView.findViewById(R.id.tv_user);
        }
    }
}
