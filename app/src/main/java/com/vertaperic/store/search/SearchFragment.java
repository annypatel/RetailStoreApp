/*
 * Project    : RetailStoreApp
 * File       : SearchFragment
 * Created on : 11/3/2016 2:57 PM
 */
package com.vertaperic.store.search;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vertaperic.store.R;
import com.vertaperic.store.app.NavigationDrawerFragment;
import com.vertaperic.store.cart.MyCartActivity;

import javax.inject.Inject;

/**
 * The fragment for product search functionality. This fragment is only for demo purpose, no search
 * functionality is implemented.
 *
 * @author Anny Patel
 */
public class SearchFragment extends NavigationDrawerFragment implements SearchContract.View {

    /**
     * The presenter attached with this view.
     */
    @Inject
    SearchContract.Presenter presenter;

    /**
     * Constructs new SearchFragment.
     */
    public SearchFragment() {
        // fragment must have public no-argument constructor
    }

    /**
     * To create the new instance of SearchFragment.
     *
     * @return The newly created instance.
     */
    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        SearchFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false);
        binding.toolbar.inflateMenu(R.menu.menu_my_cart);
        binding.setPresenter(this.presenter);

        return binding.getRoot();
    }

    @Override
    public void showNavigationView() {
        getNavigationDrawerController().openNavigationDrawer();
    }

    @Override
    public void showMyCartScreen() {
        startActivity(new Intent(getContext(), MyCartActivity.class));
    }
}
