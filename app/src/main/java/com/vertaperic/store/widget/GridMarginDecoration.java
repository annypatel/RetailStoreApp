/*
 * Project    : RetailStoreApp
 * File       : GridMarginDecoration
 * Created on : 8/11/16 10:18 AM
 */
package com.vertaperic.store.widget;

import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * The item decorator for the recycler view with the {@link android.support.v7.widget.GridLayoutManager},
 * which will add margins between the items in recycler view(only margin between two consecutive
 * will be added).
 *
 * @author Anny Patel
 */
public class GridMarginDecoration extends RecyclerView.ItemDecoration {

    /**
     * The item margin.
     */
    private final int margin;

    /**
     * Constructs new GridMarginDecoration.
     *
     * @param margin The item margin.
     */
    public GridMarginDecoration(int margin) {
        this.margin = margin;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        GridLayoutManager.LayoutParams layoutParams = (GridLayoutManager.LayoutParams) view.getLayoutParams();
        int position = layoutParams.getViewLayoutPosition();
        if (position == RecyclerView.NO_POSITION) {
            outRect.set(0, 0, 0, 0);
            return;
        }

        // add edge margin only if item edge is not the grid edge
        int itemSpanIndex = layoutParams.getSpanIndex();
        // is left grid edge?
        outRect.left = itemSpanIndex == 0 ? 0 : margin;
        // is top grid edge?
        outRect.top = itemSpanIndex == position ? 0 : margin;
        outRect.right = 0;
        outRect.bottom = 0;
    }
}
