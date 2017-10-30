/*
 * Project    : RetailStoreApp
 * File       : ProductDetailsFragment
 * Created on : 8/11/16 7:19 PM
 */
package com.vertaperic.store.product.detail;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vertaperic.store.R;
import com.vertaperic.store.cart.CartItem;
import com.vertaperic.store.cart.MyCartActivity;
import com.vertaperic.store.mvp.BaseFragment;
import com.vertaperic.store.product.Product;

/**
 * ProductDetailsFragment will displays the product details for the supplied product. Pass product
 * to this fragment using the {@link #EXTRA_PRODUCT} key(mandatory). Use {@link #EXTRA_CART_ITEM} to
 * pass cart item to this fragment, which is optional. If cart item is passed that means product is
 * already added in the cart.
 * <p>
 * This fragment also allows user to add product to the cart.
 * <p>
 * The UI of this fragment displays the hardcoded product overview, product description and star
 * rating for demo purpose.
 *
 * @author Anny Patel
 */
public class ProductDetailsFragment extends BaseFragment<ProductDetailsContract.Presenter>
        implements ProductDetailsContract.View {

    /**
     * The extra name for product to pass to ProductDetailsFragment. To display product details its
     * mandatory to pass {@link Product} with this key.
     */
    private static final String EXTRA_PRODUCT = "com.vertaperic.store.Product";

    /**
     * The extra name for cart item to pass to ProductDetailsFragment. Cart item is optional,
     * if passed that means product is already added in the cart otherwise not.
     */
    private static final String EXTRA_CART_ITEM = "com.vertaperic.store.CartItem";

    /**
     * The binding instance for this fragment.
     */
    private ProductDetailsFragmentBinding binding;

    /**
     * Constructs new ProductDetailsFragment.
     */
    public ProductDetailsFragment() {
        // fragment must have public no-argument constructor
    }

    /**
     * To create the new instance of ProductDetailsFragment with product and cart item.
     *
     * @param product  The product to display.
     * @param cartItem The cart item for this product, can be null.
     * @return The newly created instance.
     */
    public static ProductDetailsFragment newInstance(@NonNull Product product, @Nullable CartItem cartItem) {
        Bundle arguments = new Bundle();
        arguments.putSerializable(EXTRA_PRODUCT, product);
        arguments.putSerializable(EXTRA_CART_ITEM, cartItem);

        ProductDetailsFragment fragment = new ProductDetailsFragment();
        fragment.setArguments(arguments);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.binding = DataBindingUtil.inflate(inflater, R.layout.fragment_product_details, container, false);
        this.binding.setPresenter(presenter());

        // get product and cart item from arguments
        Product product = getProductFromArguments();
        CartItem cartItem = getCartItemFromArguments();

        // when user navigates from My Cart screen we don't need to show the My Cart action on
        // toolbar. Don't inflate menu if Cart Item is provided from My Cart screen.
        if (cartItem == null) {
            this.binding.toolbar.inflateMenu(R.menu.menu_my_cart);
        }

        // get product details
        presenter().loadProductDetails(product, cartItem);

        return this.binding.getRoot();
    }

    @Override
    public void setLoadingIndicator(boolean active) {
        int visibility = active ? View.VISIBLE : View.GONE;
        this.binding.progressBar.setVisibility(visibility);
    }

    @Override
    public void showProductDetails(@NonNull Product product, @Nullable CartItem cartItem) {
        // bind product and cart item to view
        this.binding.setProduct(product);
        this.binding.setCartItem(cartItem);
    }

    @Override
    public void showProductAddedToCart(@NonNull Product product, @NonNull CartItem cartItem) {
        Snackbar.make(this.binding.coordinatorLayout, R.string.product_detail_added_to_cart, Snackbar.LENGTH_SHORT)
                .show();

        // bind cart item to view
        this.binding.setCartItem(cartItem);
    }

    @Override
    public void showAddToCartFailure(@NonNull Product product) {
        Snackbar.make(this.binding.coordinatorLayout, R.string.product_detail_unable_to_add_to_cart, Snackbar.LENGTH_SHORT)
                .show();
    }

    @Override
    public void closeDetailsScreen() {
        ActivityCompat.finishAfterTransition(getActivity());
    }

    @Override
    public void showMyCartScreen() {
        startActivity(new Intent(getContext(), MyCartActivity.class));
    }

    /**
     * To get the product from the argument bundle.
     *
     * @return The product.
     */
    @NonNull
    private Product getProductFromArguments() {
        Bundle arguments = getArguments();
        if (arguments == null) {
            throw new IllegalArgumentException("argument bundle is null");
        }

        Product product = (Product) arguments.getSerializable(EXTRA_PRODUCT);
        if (product == null) {
            throw new IllegalArgumentException("product is null");
        }
        return product;
    }

    /**
     * To get the cart item from the argument bundle.
     *
     * @return The cart item.
     */
    @Nullable
    private CartItem getCartItemFromArguments() {
        Bundle arguments = getArguments();

        CartItem cartItem = null;
        if (arguments != null) {
            cartItem = (CartItem) arguments.getSerializable(EXTRA_CART_ITEM);
        }
        return cartItem;
    }
}
