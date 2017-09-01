/*
 * Project    : RetailStoreApp
 * File       : ListMarginDecoration
 * Created on : 8/11/16 10:21 AM
 */
package com.vertaperic.store.widget;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * The item decorator for the recycler view with the {@link android.support.v7.widget.LinearLayoutManager},
 * which will add margins between the items in recycler view(only margin between two consecutive
 * will be added).
 *
 * @author Anny Patel
 */
public class ListMarginDecoration extends RecyclerView.ItemDecoration {

    /**
     * The item margin.
     */
    private int margin;

    /**
     * Constructs new ListMarginDecoration.
     *
     * @param margin The item margin.
     */
    public ListMarginDecoration(int margin) {
        this.margin = margin;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left = 0;
        outRect.right = 0;
        outRect.bottom = 0;
        outRect.top = parent.getChildAdapterPosition(view) == 0 ? 0 : this.margin;
    }
}
