package com.cart.shoppingapp.di.module;

import com.cart.shoppingapp.ui.cartdetails.CartDetailsFragment;
import com.cart.shoppingapp.ui.product.ProductListFragment;
import com.cart.shoppingapp.ui.productdetails.ProductDetailsFragment;
import com.cart.shoppingapp.ui.wishlist.WishListFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainFragmentBindingModule {

    @ContributesAndroidInjector
    abstract ProductListFragment provideProductListFragment();

    @ContributesAndroidInjector
    abstract ProductDetailsFragment provideProductDetailsFragment();

    @ContributesAndroidInjector
    abstract WishListFragment provideWishListFragment();

    @ContributesAndroidInjector
    abstract CartDetailsFragment provideCartDetailsFragment();
}
