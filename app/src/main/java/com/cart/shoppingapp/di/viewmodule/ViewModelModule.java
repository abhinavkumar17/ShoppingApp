package com.cart.shoppingapp.di.viewmodule;

import androidx.lifecycle.ViewModel;

import com.cart.shoppingapp.ui.product.ProductListViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ProductListViewModel.class)
    abstract ViewModel bindShoppingListViewModel(ProductListViewModel productListViewModel);
}
