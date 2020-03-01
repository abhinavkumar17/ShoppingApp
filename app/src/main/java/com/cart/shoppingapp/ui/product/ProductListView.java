package com.cart.shoppingapp.ui.product;

import com.cart.shoppingapp.model.Product;
import com.cart.shoppingapp.ui.baseview.ObservableViewMvc;

import java.util.List;

public interface ProductListView extends ObservableViewMvc<ProductListView.Listener> {
    interface Listener {
        void onProductItemClick(Product product);
    }

    void showProgressIndication();
    void hideProgressIndication();
    void bindProductData(List<Product> products);
}
