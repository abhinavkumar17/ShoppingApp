package com.cart.shoppingapp.ui.baseview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.cart.shoppingapp.ui.cartdetails.CartDetailsFragmentImpl;
import com.cart.shoppingapp.ui.cartdetails.CartDetailsView;
import com.cart.shoppingapp.ui.product.ProductListView;
import com.cart.shoppingapp.ui.product.ProductListViewImpl;
import com.cart.shoppingapp.ui.productdetails.ProductDetailsFragmentImpl;
import com.cart.shoppingapp.ui.productdetails.ProductDetailsView;
import com.cart.shoppingapp.ui.wishlist.WishListFragmentImpl;
import com.cart.shoppingapp.ui.wishlist.WishListView;

import javax.inject.Inject;

import io.reactivex.annotations.Nullable;

public class ViewFactory {

    private final Context mContext;

    @Inject
    public ViewFactory(Context context) {
        mContext = context;
    }

    public <T extends ViewRoot> T newInstance(Class<T> mvcViewClass, @Nullable ViewGroup container, LayoutInflater mLayoutInflater) {

        ViewRoot viewRoot;

        if (mvcViewClass == ProductListView.class) {
            viewRoot = new ProductListViewImpl(mLayoutInflater, container);
        }else if(mvcViewClass == ProductDetailsView.class){
            viewRoot = new ProductDetailsFragmentImpl(mLayoutInflater, container);
        }else if(mvcViewClass == WishListView.class){
            viewRoot = new WishListFragmentImpl(mLayoutInflater, container);
        }else if(mvcViewClass == CartDetailsView.class){
            viewRoot = new CartDetailsFragmentImpl(mLayoutInflater, container);
        }
        else {
            throw new IllegalArgumentException("unsupported MVC view class " + mvcViewClass);
        }

        //noinspection unchecked
        return (T) viewRoot;
    }
}
