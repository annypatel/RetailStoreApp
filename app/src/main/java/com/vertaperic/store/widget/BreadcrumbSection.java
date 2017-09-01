/*
 * Project    : RetailStoreApp
 * File       : BreadcrumbSection
 * Created on : 11/5/2016 12:08 PM
 */
package com.vertaperic.store.widget;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * The simple plain old java object for the breadcrumb section. Use this class to configure the
 * {@link BreadcrumbSectionView}.
 *
 * @author Anny Patel
 */
public class BreadcrumbSection implements Parcelable {

    /**
     * Creator for parcelable implementation.
     */
    public static final Creator<BreadcrumbSection> CREATOR = new Creator<BreadcrumbSection>() {
        @Override
        public BreadcrumbSection createFromParcel(Parcel in) {
            return new BreadcrumbSection(in);
        }

        @Override
        public BreadcrumbSection[] newArray(int size) {
            return new BreadcrumbSection[size];
        }
    };

    /**
     * The title to display on section.
     */
    private String title;
    /**
     * The flag for visibility of separator.
     */
    private boolean showSeparator;
    /**
     * The tag for this section.
     */
    private String tag;

    /**
     * Constructs new BreadcrumbSection.
     *
     * @param title The title to display on section.
     */
    public BreadcrumbSection(String title) {
        this.title = title;
    }

    /**
     * Constructs new BreadcrumbSection.
     *
     * @param in Parcel to restore the state from.
     */
    private BreadcrumbSection(Parcel in) {
        this.title = in.readString();
        this.showSeparator = in.readByte() != 0;
        this.tag = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeByte((byte) (this.showSeparator ? 1 : 0));
        dest.writeString(this.tag);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * @return The section title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title The section title.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return true if separator is visible otherwise false.
     */
    public boolean isSeparatorVisible() {
        return showSeparator;
    }

    /**
     * @param showSeparator true if separator is visible otherwise false.
     */
    public void setSeparatorVisibility(boolean showSeparator) {
        this.showSeparator = showSeparator;
    }

    /**
     * @return The tag for this section.
     */
    public String getTag() {
        return tag;
    }

    /**
     * @param tag The tag for this section.
     */
    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        BreadcrumbSection section = (BreadcrumbSection) o;
        if (showSeparator != section.showSeparator)
            return false;

        if (title != null ? !title.equals(section.title) : section.title != null)
            return false;

        return tag != null ? tag.equals(section.tag) : section.tag == null;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (showSeparator ? 1 : 0);
        result = 31 * result + (tag != null ? tag.hashCode() : 0);
        return result;
    }
}
