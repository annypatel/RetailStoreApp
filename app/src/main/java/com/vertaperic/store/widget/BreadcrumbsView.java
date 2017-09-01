/*
 * Project    : RetailStoreApp
 * File       : BreadcrumbsView
 * Created on : 11/5/2016 11:51 AM
 */
package com.vertaperic.store.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.vertaperic.store.R;

import java.util.ArrayList;
import java.util.List;

/**
 * The custom view for Breadcrumb UI pattern. Just add this layout to the layout xml and add
 * breadcrumb sections using the {@link #add(BreadcrumbSection)} method.
 *
 * @author Anny Patel
 */
public class BreadcrumbsView extends HorizontalScrollView implements View.OnClickListener {

    /**
     * The single child of HorizontalScrollView.
     */
    private LinearLayout container;
    /**
     * The currently selected section view.
     */
    private BreadcrumbSectionView currentSectionView;
    /**
     * The breadcrumb click listener.
     */
    private OnBreadcrumbClickListener breadcrumbClickListener;
    /**
     * The list of sections added in this view.
     */
    private List<BreadcrumbSection> sections;


    public BreadcrumbsView(Context context) {
        this(context, null);
    }

    public BreadcrumbsView(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.breadcrumbsViewStyle);
    }

    public BreadcrumbsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr, 0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public BreadcrumbsView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs, defStyleAttr, defStyleRes);
    }

    /**
     * Internal method to initialize this view.
     */
    private void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        // configure this view
        setClipToPadding(false);
        setOverScrollMode(OVER_SCROLL_NEVER);
        setHorizontalScrollBarEnabled(false);
        setVerticalScrollBarEnabled(false);

        // add container to this view
        this.container = new LinearLayout(getContext());
        this.container.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        this.container.setVerticalGravity(Gravity.CENTER_VERTICAL);
        this.container.setOrientation(LinearLayout.HORIZONTAL);
        addView(this.container);

        // initialize the section list
        this.sections = new ArrayList<>();
    }

    /**
     * To add the breadcrumb section.
     *
     * @param section The section to add.
     */
    public void add(BreadcrumbSection section) {
        // add section view to this view
        BreadcrumbSectionView sectionView = addInternal(section);

        // add section to list
        this.sections.add(section);

        // set added section view as current
        setCurrentSectionView(sectionView);
        // scroll to it
        scrollTo(sectionView, true);
    }

    /**
     * To add the breadcrumb section.
     *
     * @param section The section to add.
     * @return The view that was added.
     */
    private BreadcrumbSectionView addInternal(BreadcrumbSection section) {
        BreadcrumbSectionView sectionView = new BreadcrumbSectionView(getContext());
        sectionView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
        sectionView.setGravity(Gravity.CENTER_VERTICAL);
        sectionView.setOnClickListener(this);
        sectionView.setBreadcrumbSection(section);
        this.container.addView(sectionView);
        return sectionView;
    }

    /**
     * To remove the one section from the top.
     */
    public void remove() {
        // remove view from top
        this.container.removeViewAt(this.container.getChildCount() - 1);
        // remove section from top of the list
        this.sections.remove(this.sections.size() - 1);

        // top section is removed now, set current section again
        if (this.container.getChildCount() > 0) {
            View view = this.container.getChildAt(this.container.getChildCount() - 1);

            // set top section view as current
            setCurrentSectionView((BreadcrumbSectionView) view);
            // scroll to it
            scrollTo((BreadcrumbSectionView) view, true);
        }
    }

    /**
     * To remove all section views up to given view.
     *
     * @param sectionView The section view.
     */
    public void removeUpTo(BreadcrumbSectionView sectionView) {
        // get the index of clicked view
        int index = this.container.indexOfChild(sectionView);
        if (index < 0) {
            return;
        }

        // remove all the view on right side of given view
        this.container.removeViews(index + 1, container.getChildCount() - index - 1);
        // remove all section on right side of given view
        this.sections = this.sections.subList(0, index + 1);

        // top section is removed now, set current section again
        setCurrentSectionView(sectionView);
        // scroll to the clicked view
        scrollTo(sectionView, true);
    }

    /**
     * To check if view contains the section.
     *
     * @param section The breadcrumb section.
     * @return true if section is already added to view otherwise false.
     */
    public boolean contains(BreadcrumbSection section) {
        return this.sections.contains(section);
    }

    /**
     * To get the number of sections added in this view.
     *
     * @return The number of sections.
     */
    public int getBreadcrumbSectionCount() {
        return this.sections.size();
    }

    /**
     * To set the currently selected section view.
     *
     * @param sectionView The section view.
     */
    private void setCurrentSectionView(BreadcrumbSectionView sectionView) {
        if (this.currentSectionView != null) {
            this.currentSectionView.setChecked(false);
            this.currentSectionView.setClickable(true);
        }
        this.currentSectionView = sectionView;
        this.currentSectionView.setClickable(false);
        this.currentSectionView.setChecked(true);
    }

    /**
     * To scroll to specified section view.
     *
     * @param sectionView The current section view.
     * @param smooth      true for smooth scrolling false otherwise.
     */
    private void scrollTo(final BreadcrumbSectionView sectionView, final boolean smooth) {
        post(new Runnable() {

            @Override
            public void run() {

                int padding = getWidth() - sectionView.getWidth() - (sectionView.getPaddingRight() * 2);
                setPadding(getPaddingLeft(), getPaddingTop(), padding, getPaddingBottom());
                if (smooth)
                    smoothScrollTo(sectionView.getRight(), 0);
                else
                    scrollTo(sectionView.getRight(), 0);
            }
        });
    }

    @Override
    public void onClick(View v) {
        // dispatch the click event
        if (this.breadcrumbClickListener != null) {
            this.breadcrumbClickListener.onBreadcrumbClick((BreadcrumbSectionView) v);
        }
    }

    /**
     * To set the breadcrumb click listener.
     *
     * @param breadcrumbClickListener The listener.
     */
    public void setOnBreadcrumbClickListener(OnBreadcrumbClickListener breadcrumbClickListener) {
        this.breadcrumbClickListener = breadcrumbClickListener;
    }


    @Override
    protected Parcelable onSaveInstanceState() {
        // save the state
        Parcelable superState = super.onSaveInstanceState();
        SavedState ss = new SavedState(superState);
        ss.sections = this.sections;
        return ss;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        // restore state
        SavedState ss = (SavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());
        this.sections = ss.sections;

        // restore view
        BreadcrumbSectionView sectionView = null;
        for (BreadcrumbSection section : this.sections) {
            sectionView = addInternal(section);
            setCurrentSectionView(sectionView);
        }
        // scroll to last view
        if (sectionView != null) {
            scrollTo(sectionView, false);
        }
    }

    /**
     * The breadcrumb click listener.
     */
    public interface OnBreadcrumbClickListener {

        /**
         * Called when section is clicked.
         *
         * @param sectionView The view that was clicked.
         */
        void onBreadcrumbClick(BreadcrumbSectionView sectionView);
    }

    /**
     * The state to save across the activity lifecycle.
     */
    static class SavedState extends BaseSavedState {

        /**
         * The breadcrumb sections to save.
         */
        private List<BreadcrumbSection> sections;

        SavedState(Parcelable superState) {
            super(superState);
        }

        SavedState(Parcel source) {
            super(source);
            this.sections = new ArrayList<>();
            source.readTypedList(sections, BreadcrumbSection.CREATOR);
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            super.writeToParcel(dest, flags);
            dest.writeTypedList(this.sections);
        }

        /**
         * The creator for this parcelable.
         */
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() {

            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }

            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };
    }
}
