package com.cart.shoppingapp.di.viewmodule;

import androidx.lifecycle.ViewModel;

import com.cart.shoppingapp.ui.product.ProductListViewModel;
import com.cart.shoppingapp.ui.productdetails.ProductDetailsViewModel;
import com.cart.shoppingapp.ui.wishlist.WishListViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ProductListViewModel.class)
    abstract ViewModel bindShoppingListViewModel(ProductListViewModel productListViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(WishListViewModel.class)
    abstract ViewModel bindWishListViewModel(WishListViewModel wishListViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ProductDetailsViewModel.class)
    abstract ViewModel bindProductDetailsViewModel(ProductDetailsViewModel productDetailsViewModel);
}
