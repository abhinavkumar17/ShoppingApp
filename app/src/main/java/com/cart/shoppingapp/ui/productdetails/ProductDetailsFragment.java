package com.cart.shoppingapp.ui.productdetails;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.cart.shoppingapp.R;
import com.cart.shoppingapp.di.viewmodule.ViewModelProviderFactory;
import com.cart.shoppingapp.model.Product;
import com.cart.shoppingapp.ui.baseview.ViewMvcFactory;
import com.cart.shoppingapp.ui.cartdetails.CartDetailsFragment;
import com.cart.shoppingapp.ui.wishlist.WishListFragment;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class ProductDetailsFragment extends DaggerFragment implements ProductDetailsView.Listener {

    private static final String PRODUCT_EXTRA = "product";
    private Product mProduct;
    private ProductDetailsView mProductDetailsView;
    private ProductDetailsViewModel mProductDetailsViewModel;

    @Inject
    ViewMvcFactory mViewMvcFactory;

    @Inject
    ViewModelProviderFactory mViewModelProviderFactory;

    public static ProductDetailsFragment newInstance(Product product) {
        ProductDetailsFragment fragment = new ProductDetailsFragment();
        if (product != null) {
            Bundle bundle = new Bundle();
            bundle.putParcelable(PRODUCT_EXTRA, product);
            fragment.setArguments(bundle);
        }
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mProduct = getArguments() != null && getArguments().containsKey(PRODUCT_EXTRA) ? getArguments().getParcelable(PRODUCT_EXTRA) : null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mProductDetailsView = mViewMvcFactory.newInstance(ProductDetailsView.class,container,inflater);
        mProductDetailsViewModel = ViewModelProviders.of(this, mViewModelProviderFactory).get(ProductDetailsViewModel.class);
        return mProductDetailsView.getRootView();
    }

    @Override
    public void onStart() {
        super.onStart();
        mProductDetailsView.registerListener(this);
        mProductDetailsView.setProductDetailData(mProduct);
    }

    @Override
    public void onStop() {
        super.onStop();
        mProductDetailsView.unregisterListener(this);
    }

    @Override
    public void navigateWishList() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.screenContainer, WishListFragment.newInstance(mProduct));
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void insertWishList(Product product) {
        mProductDetailsViewModel.addItemToWishlist(product);
    }

    @Override
    public void navigateCartList() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.screenContainer, CartDetailsFragment.newInstance(mProduct));
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void insertCartList(Product product) {
        if (product.getStock() > 0) {
            mProductDetailsViewModel.addItemtoCart(product.getId());
        }else {
            Toast.makeText(getContext(), getContext().getResources().getString((R.string.outofstock)), Toast.LENGTH_SHORT).show();
        }
    }
}
