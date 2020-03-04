package com.cart.shoppingapp.ui.wishlist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.cart.shoppingapp.db.wishlist.WishListProduct;
import com.cart.shoppingapp.di.viewmodule.ViewModelProviderFactory;
import com.cart.shoppingapp.model.Product;
import com.cart.shoppingapp.ui.baseview.ViewFactory;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class WishListFragment extends DaggerFragment implements WishListView.Listener,WishListViewModel.Listener {

    private static final String PRODUCT_EXTRA = "product";

    @Inject
    ViewModelProviderFactory mViewModelProviderFactory;

    @Inject
    ViewFactory mViewFactory;

    private WishListView mWishListView;
    private WishListViewModel mWishListViewModel;

    public static WishListFragment newInstance(Product product) {
        WishListFragment fragment = new WishListFragment();
        if (product != null) {
            Bundle bundle = new Bundle();
            bundle.putParcelable(PRODUCT_EXTRA, product);
            fragment.setArguments(bundle);
        }
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mWishListView = mViewFactory.newInstance(WishListView.class, container, inflater);
        mWishListViewModel = ViewModelProviders.of(this, mViewModelProviderFactory).get(WishListViewModel.class);
        return mWishListView.getRootView();
    }

    @Override
    public void onStart() {
        super.onStart();
        mWishListView.registerListener(this);
        mWishListViewModel.registerListener(this);
        mWishListViewModel.fetchAllWishListProducts();
        mWishListViewModel.getWishListProducts().observe(this, new Observer<List<WishListProduct>>() {
            @Override
            public void onChanged(List<WishListProduct> wishListProducts) {
                mWishListView.setWishListProducts(wishListProducts);
            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();
        mWishListView.unregisterListener(this);
        mWishListViewModel.unregisterListener(this);
    }

    @Override
    public void onWishListDelete(WishListProduct product) {
        mWishListViewModel.deleteProduct(product);
    }

    @Override
    public void onWishListMove(WishListProduct product) {
       mWishListViewModel.moveProducttoCart(product);
    }

    @Override
    public void onEmptyListError() {
        mWishListView.setEmptError();
    }

    @Override
    public void onServerError() {
        mWishListView.serServerError();
    }
}
