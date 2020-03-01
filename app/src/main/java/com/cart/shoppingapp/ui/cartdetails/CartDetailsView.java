package com.cart.shoppingapp.ui.cartdetails;

import com.cart.shoppingapp.model.CartDetails;
import com.cart.shoppingapp.ui.baseview.ObservableViewMvc;

import java.util.List;

public interface CartDetailsView  extends ObservableViewMvc<CartDetailsView.Listener> {

    public interface Listener{

        void onCartItemDelete(CartDetails cartDetails);
    }

    void serServerError();

    void setEmptError();

    void setCartData(List<CartDetails> cartDetails);
}
