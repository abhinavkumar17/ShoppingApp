package com.cart.shoppingapp.ui.wishlist;

import com.cart.shoppingapp.db.wishlist.WishListProduct;
import com.cart.shoppingapp.ui.baseview.ObservableViewMvc;
import com.cart.shoppingapp.ui.productdetails.ProductDetailsView;

import java.util.List;

public interface WishListView extends ObservableViewMvc<WishListView.Listener> {

    public interface Listener{

        void onWishListDelete(WishListProduct product);

        void onWishListMove(WishListProduct product);
    }

    void setWishListProducts(List<WishListProduct> wishListProducts);

    void setEmptError();

    void serServerError();

}
