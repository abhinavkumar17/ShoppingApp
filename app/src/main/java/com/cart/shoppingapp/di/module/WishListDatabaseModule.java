package com.cart.shoppingapp.di.module;

import android.content.Context;

import androidx.room.Room;

import com.cart.shoppingapp.db.wishlist.WishListDao;
import com.cart.shoppingapp.db.wishlist.WishListDataBase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class WishListDatabaseModule {

    @Singleton
    @Provides
    WishListDataBase getCartDataBase(Context context){
        return Room.databaseBuilder(context,
                WishListDataBase.class, WishListDataBase.DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }

    @Singleton
    @Provides
    WishListDao provideCartDao(WishListDataBase cardDatabase) {
        return cardDatabase.getWishListDao();
    }
}
