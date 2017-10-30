/*
 * Project    : RetailStoreApp
 * File       : HomeFragment
 * Created on : 11/1/2016 8:10 PM
 */
package com.vertaperic.store.home;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vertaperic.store.R;
import com.vertaperic.store.browse.BrowseActivity;
import com.vertaperic.store.cart.MyCartActivity;
import com.vertaperic.store.mvp.BaseFragment;

import dagger.android.support.AndroidSupportInjection;

/**
 * The home fragment that will be displayed to user when the app is launched and HomeActivity is
 * displayed.
 * <p/>
 * The UI of this fragment displays the extra two card(Gift Cards and Wish List) for demo purpose.
 *
 * @author Anny Patel
 */
public class HomeFragment extends BaseFragment<HomeContract.Presenter>
        implements HomeContract.View {

    /**
     * The binding instance for this fragment.
     */
    private HomeFragmentBinding binding;

    /**
     * Constructs new HomeFragment.
     */
    public HomeFragment() {
        // fragment must have public no-argument constructor
    }

    /**
     * To create the new instance of HomeFragment.
     *
     * @return The newly created instance.
     */
    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        this.binding.toolbar.inflateMenu(R.menu.menu_my_cart);
        this.binding.setPresenter(presenter());

        return this.binding.getRoot();
    }

    @Override
    public void showBrowseScreen() {
        startActivity(new Intent(getContext(), BrowseActivity.class));
    }

    @Override
    public void showGiftCardsScreen() {
        Snackbar.make(this.binding.coordinatorLayout, R.string.home_coming_soon, Snackbar.LENGTH_SHORT)
                .show();
    }

    @Override
    public void showWishListsScreen() {
        Snackbar.make(this.binding.coordinatorLayout, R.string.home_coming_soon, Snackbar.LENGTH_SHORT)
                .show();
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
