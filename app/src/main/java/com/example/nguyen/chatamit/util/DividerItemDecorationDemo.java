package com.example.nguyen.chatamit.util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nguyen.chatamit.R;


public class DividerItemDecorationDemo extends RecyclerView.ItemDecoration {
    //snippet below code description custom divider item decoration

    private Drawable divider;
    private int idP1, idP0;

    //used for custom fragment Contact....
    //R.id.idP1
    //R.id.idP0
    public DividerItemDecorationDemo(Context context, int resId, int idP1, int idP0) {
        divider = ContextCompat.getDrawable(context, resId);
        this.idP1 = idP1;
        this.idP0 = idP0;
    }

    public DividerItemDecorationDemo(Context context, int resId) {
        divider = ContextCompat.getDrawable(context, resId);
    }


    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {

        int left = parent.getWidth() - (parent.findViewById(idP1).getWidth() +
                parent.findViewById(idP0).getWidth() / 2);

        int right = parent.getWidth() - parent.getPaddingRight();

        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);

            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            int top = child.getBottom() + params.bottomMargin;
            int bottom = top + divider.getIntrinsicHeight();

            divider.setBounds(left, top, right, bottom);
            divider.draw(c);
        }
    }

}
