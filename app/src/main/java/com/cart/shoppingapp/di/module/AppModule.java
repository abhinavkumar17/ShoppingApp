package com.cart.shoppingapp.di.module;

import androidx.fragment.app.FragmentActivity;

import com.cart.shoppingapp.di.viewmodule.ViewModelModule;
import com.cart.shoppingapp.repository.ShoppingRepository;
import com.cart.shoppingapp.repository.ShoppingService;
import com.cart.shoppingapp.ui.product.FetchProductListUseCase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = ViewModelModule.class)
public class AppModule {

    private static final String BASE_URL = "https://2klqdzs603.execute-api.eu-west-2.amazonaws.com/";

    private static FragmentActivity mActivity;

    @Singleton
    @Provides
    static Retrofit provideRetrofit() {
        return new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    static ShoppingService provideRetrofitService(Retrofit retrofit) {
        return retrofit.create(ShoppingService.class);
    }

    @Singleton
    @Provides
    static FetchProductListUseCase provideFetchProductListUseCase(ShoppingRepository shoppingRepository) {
        return new FetchProductListUseCase(shoppingRepository);
    }
}
