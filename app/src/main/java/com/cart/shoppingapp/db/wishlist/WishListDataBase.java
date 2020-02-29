package com.cart.shoppingapp.db.wishlist;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {WishListProduct.class}, version = 1, exportSchema = false)
public abstract class WishListDataBase extends RoomDatabase {

    public static final String DATABASE_NAME = "shopping.db";

    public abstract WishListDao getWishListDao();
}
