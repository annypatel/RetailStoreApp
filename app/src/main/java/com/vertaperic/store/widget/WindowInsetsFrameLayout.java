/*
 * Project    : RetailStoreApp
 * File       : WindowInsetsFrameLayout
 * Created on : 11/2/2016 5:05 PM
 */
package com.vertaperic.store.widget;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * A FrameLayout subclass that dispatches WindowInsets to its children instead of adjusting its
 * padding. Useful for fragment containers.
 *
 * @author Anny Patel
 */
public class WindowInsetsFrameLayout extends FrameLayout {

    public WindowInsetsFrameLayout(Context context) {
        this(context, null);
    }

    public WindowInsetsFrameLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WindowInsetsFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        applyOnApplyWindowInsetsListener();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public WindowInsetsFrameLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        applyOnApplyWindowInsetsListener();
    }

    /**
     * To apply window inset listener.
     */
    private void applyOnApplyWindowInsetsListener() {
        ViewCompat.setOnApplyWindowInsetsListener(this, (v, insets) -> {

            boolean consumed = false;
            for (int i = 0, count = getChildCount(); i < count; i++) {
                ViewCompat.dispatchApplyWindowInsets(getChildAt(i), insets);
                if (insets.isConsumed()) {
                    consumed = true;
                }
            }
            return consumed ? insets.consumeSystemWindowInsets() : insets;
        });
    }
}
