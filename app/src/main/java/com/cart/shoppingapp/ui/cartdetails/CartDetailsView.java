package com.cart.shoppingapp.ui.cartdetails;

import com.cart.shoppingapp.model.CartDetails;
import com.cart.shoppingapp.ui.baseview.ObservableView;

import java.util.List;

public interface CartDetailsView  extends ObservableView<CartDetailsView.Listener> {

    public interface Listener{

        void onCartItemDelete(CartDetails cartDetails);
    }

    void serServerError();

    void setEmptError();

    void setCartData(List<CartDetails> cartDetails);
}
