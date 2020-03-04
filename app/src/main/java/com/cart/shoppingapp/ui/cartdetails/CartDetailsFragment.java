package com.cart.shoppingapp.ui.cartdetails;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.cart.shoppingapp.di.viewmodule.ViewModelProviderFactory;
import com.cart.shoppingapp.model.CartDetails;
import com.cart.shoppingapp.model.Product;
import com.cart.shoppingapp.ui.baseview.ViewFactory;
import com.cart.shoppingapp.ui.productdetails.ProductDetailsViewModel;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class CartDetailsFragment extends DaggerFragment implements CartDetailsView.Listener, ProductDetailsViewModel.Listener {

    @Inject
    ViewModelProviderFactory mViewModelProviderFactory;

    @Inject
    ViewFactory mViewFactory;

    private ProductDetailsViewModel mProductDetailsViewModel;
    private CartDetailsView mCartDetailsView;

    public static CartDetailsFragment newInstance(Product product) {
        CartDetailsFragment fragment = new CartDetailsFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mCartDetailsView = mViewFactory.newInstance(CartDetailsView.class, container, inflater);
        mProductDetailsViewModel = ViewModelProviders.of(this, mViewModelProviderFactory).get(ProductDetailsViewModel.class);
        return mCartDetailsView.getRootView();
    }


    @Override
    public void onStart() {
        super.onStart();
        mCartDetailsView.registerListener(this);
        mProductDetailsViewModel.registerListener(this);
        mProductDetailsViewModel.fetchCartDetails();
        mProductDetailsViewModel.getAllCartList().observe(this, new Observer<List<CartDetails>>() {
            @Override
            public void onChanged(List<CartDetails> cartDetails) {
                mCartDetailsView.setCartData(cartDetails);
            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();
        mCartDetailsView.unregisterListener(this);
        mProductDetailsViewModel.unregisterListener(this);
    }

    @Override
    public void onCartItemDelete(CartDetails cartDetails) {
        mProductDetailsViewModel.deleteCartItem(cartDetails.getProductId());
    }

    @Override
    public void onEmptyListError() {
        mCartDetailsView.setEmptError();
    }

    @Override
    public void onServerError() {
        mCartDetailsView.serServerError();
    }
}
