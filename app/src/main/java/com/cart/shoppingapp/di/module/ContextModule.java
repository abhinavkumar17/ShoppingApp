package com.cart.shoppingapp.di.module;

import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ContextModule {

    @Binds
    abstract Context provideContext(Application application);
}
