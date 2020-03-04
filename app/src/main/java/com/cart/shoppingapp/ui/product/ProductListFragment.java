package com.cart.shoppingapp.ui.product;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.cart.shoppingapp.R;
import com.cart.shoppingapp.di.viewmodule.ViewModelProviderFactory;
import com.cart.shoppingapp.model.Product;
import com.cart.shoppingapp.ui.baseview.ViewFactory;
import com.cart.shoppingapp.ui.productdetails.ProductDetailsFragment;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class ProductListFragment extends DaggerFragment implements ProductListView.Listener {


    @Inject
    ViewFactory mViewFactory;

    @Inject
    ViewModelProviderFactory mViewModelProviderFactory;

    private ProductListView mProductListView;

    private ProductListViewModel mProductListViewModel;

    private List<Product> mProcucts;

    public static ProductListFragment newInstance() {
        ProductListFragment fragment = new ProductListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mProductListView = mViewFactory.newInstance(ProductListView.class,container,inflater);
        mProductListViewModel = ViewModelProviders.of(this, mViewModelProviderFactory).get(ProductListViewModel.class);
        return mProductListView.getRootView();
    }

    @Override
    public void onStart() {
        super.onStart();
        mProductListView.registerListener(this);
        mProductListView.showProgressIndication();
    }

    @Override
    public void onResume() {
        super.onResume();
        getProductListData();
    }

    private void getProductListData() {
        mProductListViewModel.fetchData();
            mProductListViewModel.getProductList().observe(this, new Observer<List<Product>>() {
                @Override
                public void onChanged(List<Product> products) {
                    mProcucts =products;
                    mProductListView.hideProgressIndication();
                    mProductListView.bindProductData(products);
                }
            });
    }

    @Override
    public void onStop() {
        super.onStop();
        mProductListView.unregisterListener(this);
    }

    @Override
    public void onProductItemClick(Product product) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.screenContainer, ProductDetailsFragment.newInstance(product));
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
