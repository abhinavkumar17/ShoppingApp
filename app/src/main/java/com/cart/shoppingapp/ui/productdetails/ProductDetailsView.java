package com.cart.shoppingapp.ui.productdetails;

import com.cart.shoppingapp.ui.baseview.ObservableViewMvc;


public interface ProductDetailsView extends ObservableViewMvc<ProductDetailsView.Listener> {
    public interface Listener{

    }

    void showProgressIndication();
    void hideProgressIndication();
}
