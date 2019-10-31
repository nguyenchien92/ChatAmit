package com.example.nguyen.chatamit.adapteres;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nguyen.chatamit.R;
import com.example.nguyen.chatamit.models.Sticker;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class StickerAdapter extends RecyclerView.Adapter<StickerAdapter.StickerHolder> {

    private Context context;
    private List<Sticker> mStickers;
    private ClickListener mListener;

    public StickerAdapter(Context context, List<Sticker> mStickers) {
        this.context = context;
        this.mStickers = mStickers;
    }

    public void setClickListener(ClickListener mListener) {
        this.mListener = mListener;
    }

    public StickerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_sticker_layout, parent, false);

        return new StickerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StickerHolder holder, final int position) {
        Sticker sticker = mStickers.get(position);

        holder.setViewSticker(sticker, context);
        holder.ivItemSticker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.clickItem(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mStickers.size();
    }

    public class StickerHolder extends RecyclerView.ViewHolder {
        ImageView ivItemSticker;

        public StickerHolder(View itemView) {
            super(itemView);

            ivItemSticker = itemView.findViewById(R.id.iv_item_sticker);
        }

        public void setViewSticker(Sticker sticker, Context context) {
            try {
                InputStream ims = context.getAssets().open("StickerDemo/" + sticker.getImage());
                Drawable drawable = Drawable.createFromStream(ims, null);

                ivItemSticker.setImageDrawable(drawable);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public interface ClickListener {
        public void clickItem(int position);
    }
}
