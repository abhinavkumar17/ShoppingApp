package com.cart.shoppingapp;

import android.app.Activity;

import com.cart.shoppingapp.di.component.ApplicationComponent;
import com.cart.shoppingapp.di.component.DaggerApplicationComponent;


import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

public class ShoppingApplication extends DaggerApplication {

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        ApplicationComponent component = DaggerApplicationComponent.builder().application(this).build();
        component.inject(this);

        return component;
    }
}
