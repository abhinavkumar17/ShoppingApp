package com.cart.shoppingapp.db.wishlist;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface WishListDao {

    @Query("SELECT * FROM wishlist_products")
    Single<List<WishListProduct>> getAllTrolleyProducts();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(WishListProduct wishListProduct);

    @Delete
    void remove(WishListProduct wishListProduct);
}
