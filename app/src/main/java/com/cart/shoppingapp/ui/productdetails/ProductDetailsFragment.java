package com.cart.shoppingapp.ui.productdetails;

import android.os.Bundle;

import com.cart.shoppingapp.ui.product.ProductListFragment;

import dagger.android.support.DaggerFragment;

public class ProductDetailsFragment extends DaggerFragment {

    public static ProductListFragment newInstance() {
        ProductListFragment fragment = new ProductListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
}
