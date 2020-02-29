package com.cart.shoppingapp.di.component;

import android.app.Application;

import androidx.fragment.app.FragmentActivity;

import com.cart.shoppingapp.ShoppingApplication;
import com.cart.shoppingapp.di.module.ActivityBuildersModule;
import com.cart.shoppingapp.di.module.AppModule;
import com.cart.shoppingapp.di.module.ContextModule;
import com.cart.shoppingapp.di.module.WishListDatabaseModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {AndroidSupportInjectionModule.class, WishListDatabaseModule.class,
        AppModule.class, ContextModule.class, ActivityBuildersModule.class})
public interface ApplicationComponent extends AndroidInjector<ShoppingApplication> {

    void inject(ShoppingApplication application);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        ApplicationComponent build();
    }
}
