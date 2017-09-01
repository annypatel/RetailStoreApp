/*
 * Project    : RetailStoreApp
 * File       : ImageViewDataBinding
 * Created on : 8/11/16 11:18 AM
 */
package com.vertaperic.store.binding;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Binding adapter for the ImageView source image loading.
 *
 * @author Anny Patel
 */
public class ImageViewDataBinding {

    /**
     * Set the <code>uri</code> as the image view background.
     *
     * @param view The image view to which image will be set.
     * @param uri  The image uri.
     */
    @BindingAdapter({"android:src", "placeHolder"})
    public static void setImageUri(ImageView view, String uri, Drawable placeHolder) {
        Picasso.with(view.getContext())
                .load(uri)
                .placeholder(placeHolder)
                .into(view);
    }
}
