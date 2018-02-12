/*
 * Project    : RetailStoreApp
 * File       : AboutFragment
 * Created on : 11/3/2016 2:55 PM
 */
package com.vertaperic.store.about;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vertaperic.store.R;
import com.vertaperic.store.cart.MyCartActivity;
import com.vertaperic.store.mvp.BaseFragment;

/**
 * The fragment for displaying the information about the app and the company.
 *
 * @author Anny Patel
 */
public class AboutFragment extends BaseFragment<AboutContract.Presenter>
        implements AboutContract.View {

    /**
     * Constructs new AboutFragment.
     */
    public AboutFragment() {
        // fragment must have public no-argument constructor
    }

    /**
     * To create the new instance of AboutFragment.
     *
     * @return The newly created instance.
     */
    public static AboutFragment newInstance() {
        return new AboutFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        AboutFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_about, container, false);
        binding.toolbar.inflateMenu(R.menu.menu_my_cart);
        binding.setPresenter(presenter());

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
