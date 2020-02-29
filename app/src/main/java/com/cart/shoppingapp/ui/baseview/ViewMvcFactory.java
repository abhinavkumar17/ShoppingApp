package com.cart.shoppingapp.ui.baseview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.cart.shoppingapp.ui.product.ProductListView;
import com.cart.shoppingapp.ui.product.ProductListViewImpl;

import javax.inject.Inject;

import io.reactivex.annotations.Nullable;

public class ViewMvcFactory {

    private final Context mLayoutInflater;

    @Inject
    public ViewMvcFactory(Context layoutInflater) {
        mLayoutInflater = layoutInflater;
    }

    public <T extends ViewMvc> T newInstance(Class<T> mvcViewClass, @Nullable ViewGroup container,LayoutInflater mLayoutInflater) {

        ViewMvc viewMvc;

        if (mvcViewClass == ProductListView.class) {
            viewMvc = new ProductListViewImpl(mLayoutInflater, container);
        }
        else {
            throw new IllegalArgumentException("unsupported MVC view class " + mvcViewClass);
        }

        //noinspection unchecked
        return (T) viewMvc;
    }
}
