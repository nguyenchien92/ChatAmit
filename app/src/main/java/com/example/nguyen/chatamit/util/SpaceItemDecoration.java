package com.example.nguyen.chatamit.util;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SpaceItemDecoration extends RecyclerView.ItemDecoration  {
    private final int horizontalDecoration;
    private final int verticalDecoration;


    public SpaceItemDecoration(int horizontalDecoration, int verticalDecoration) {
        this.horizontalDecoration = horizontalDecoration;
        this.verticalDecoration = verticalDecoration;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        outRect.left = horizontalDecoration;
        outRect.top = verticalDecoration;

//        parent.getDecoratedBoundsWithMargins(view,outRect);
    }


}
