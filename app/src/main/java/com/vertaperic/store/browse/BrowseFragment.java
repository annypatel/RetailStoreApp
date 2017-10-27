/*
 * Project    : RetailStoreApp
 * File       : BrowseFragment
 * Created on : 11/3/2016 4:14 PM
 */
package com.vertaperic.store.browse;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vertaperic.android.support.BreadcrumbTransactionInfo;
import com.vertaperic.android.support.BreadcrumbsController;
import com.vertaperic.android.support.FragmentAnimation;
import com.vertaperic.android.support.FragmentController;
import com.vertaperic.android.support.SupportFactory;
import com.vertaperic.store.R;
import com.vertaperic.store.app.NavigationDrawerFragment;
import com.vertaperic.store.cart.MyCartActivity;
import com.vertaperic.store.category.CategoriesFragment;

import javax.inject.Inject;

/**
 * The fragment for category wise product browsing functionality.
 *
 * @author Anny Patel
 */
public class BrowseFragment extends NavigationDrawerFragment implements BrowseContract.View {

    /**
     * The presenter attached with this view.
     */
    @Inject
    BrowseContract.Presenter presenter;
    /**
     * The breadcrumb view controller.
     */
    private BreadcrumbsController breadcrumbsController;

    /**
     * Constructs new BrowseFragment.
     */
    public BrowseFragment() {
        // fragment must have public no-argument constructor
    }

    /**
     * To create the new instance of BrowseFragment.
     *
     * @return The newly created instance.
     */
    public static BrowseFragment newInstance() {
        return new BrowseFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        BrowseFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_browse, container, false);
        binding.toolbar.inflateMenu(R.menu.menu_my_cart);
        binding.setPresenter(this.presenter);

        // apply fragment animation
        FragmentController fragmentController = getChildFragmentController();
        FragmentAnimation animation = new FragmentAnimation(
                R.anim.anim_fragment_open_enter,
                R.anim.anim_fragment_open_exit,
                R.anim.anim_fragment_close_enter,
                R.anim.anim_fragment_close_exit
        );
        fragmentController.getBackStackManager().setAnimation(animation);

        // initialize breadcrumbs controller
        this.breadcrumbsController = SupportFactory.breadcrumbsController(fragmentController, binding.breadcrumbsView);

        // add main category fragment only if this fragment is not restored
        if (savedInstanceState == null) {
            addMainCategoriesFragment();
        }

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

    /**
     * Adds main {@link CategoriesFragment} to child container.
     */
    private void addMainCategoriesFragment() {
        CategoriesFragment fragment = CategoriesFragment.newInstance();
        this.breadcrumbsController.add(
                new BreadcrumbTransactionInfo(getString(R.string.category_all_categories), fragment, R.id.childContainer)
                        .showSeparator(false)
                        .animate(false)
                        .addToBackStack(true)
        );
    }

    /**
     * To get the breadcrumbs controller.
     *
     * @return The breadcrumbs controller attached with this fragment.
     */
    public BreadcrumbsController getBreadcrumbsController() {
        return this.breadcrumbsController;
    }

    @Override
    public boolean onBackPressed() {
        // dispatch back press to breadcrumb controller
        return this.breadcrumbsController.handleOnBackPressed() || super.onBackPressed();
    }

    @Override
    protected boolean isBackPressSupportEnabled() {
        // return true to enable back press event callback
        return true;
    }
}
