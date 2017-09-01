/*
 * Project    : RetailStoreApp
 * File       : BreadcrumbSectionView
 * Created on : 11/5/2016 12:03 PM
 */
package com.vertaperic.store.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatCheckedTextView;
import android.util.AttributeSet;

import com.vertaperic.store.R;

/**
 * The single breadcrumb section view. This view is configured using the {@link BreadcrumbSection}
 * by calling {@link #setBreadcrumbSection(BreadcrumbSection)}.
 *
 * @author Anny Patel
 */
public class BreadcrumbSectionView extends AppCompatCheckedTextView {

    /**
     * The separator drawable for this view.
     */
    private Drawable separator;
    /**
     * The breadcrumb section associated with this view.
     */
    private BreadcrumbSection breadcrumbSection;


    public BreadcrumbSectionView(Context context) {
        this(context, null);
    }

    public BreadcrumbSectionView(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.breadcrumbsSectionViewStyle);
    }

    public BreadcrumbSectionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr, 0);
    }

    /**
     * Internal method to get style attributes for view.
     */
    private void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.BreadcrumbSectionView, defStyleAttr, defStyleRes);
        this.separator = attributes.getDrawable(R.styleable.BreadcrumbSectionView_separator);
        attributes.recycle();
    }

    /**
     * To get the breadcrumb section associated with this view.
     *
     * @return The breadcrumb section.
     */
    public BreadcrumbSection getBreadcrumbSection() {
        return breadcrumbSection;
    }

    /**
     * To set the breadcrumb section.
     *
     * @param breadcrumbSection The breadcrumb section.
     */
    public void setBreadcrumbSection(@NonNull BreadcrumbSection breadcrumbSection) {
        this.breadcrumbSection = breadcrumbSection;

        setText(this.breadcrumbSection.getTitle());
        if (this.breadcrumbSection.isSeparatorVisible()) {
            setCompoundDrawablesWithIntrinsicBounds(separator, null, null, null);
        } else {
            setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        }
    }
}
