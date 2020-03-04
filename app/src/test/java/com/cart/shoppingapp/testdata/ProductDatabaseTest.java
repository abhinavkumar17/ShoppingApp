package com.cart.shoppingapp.testdata;

import androidx.room.Room;

import com.cart.shoppingapp.db.wishlist.WishListDao;
import com.cart.shoppingapp.db.wishlist.WishListDataBase;

import org.junit.After;
import org.junit.Before;

public abstract class ProductDatabaseTest {

    // system under test
    private WishListDataBase wishListDataBase;


    public WishListDao getWishDao(){
        return wishListDataBase.getWishListDao();
    }

    @Before
    public void init(){
       /* wishListDataBase = Room.inMemoryDatabaseBuilder(
                getApplicationContext(),
                WishListDataBase.class
        ).build();*/
    }

    @After
    public void finish(){
        wishListDataBase.close();
    }
}
