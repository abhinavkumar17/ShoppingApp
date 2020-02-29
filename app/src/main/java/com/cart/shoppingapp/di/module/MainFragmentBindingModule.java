package com.cart.shoppingapp.di.module;

import com.cart.shoppingapp.ui.product.ProductListFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainFragmentBindingModule {

    @ContributesAndroidInjector
    abstract ProductListFragment provideShoppingListFragment();
}
