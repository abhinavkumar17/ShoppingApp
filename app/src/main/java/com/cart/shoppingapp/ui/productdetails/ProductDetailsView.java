package com.cart.shoppingapp.ui.productdetails;

import com.cart.shoppingapp.model.Product;
import com.cart.shoppingapp.ui.baseview.ObservableView;


public interface ProductDetailsView extends ObservableView<ProductDetailsView.Listener> {

    public interface Listener {
        void navigateWishList();

        void insertWishList(Product product);

        void navigateCartList();

        void insertCartList(Product product);
    }

    void setProductDetailData(Product mProduct);

}
