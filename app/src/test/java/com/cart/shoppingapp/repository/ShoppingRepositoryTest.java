package com.cart.shoppingapp.repository;

import com.cart.shoppingapp.db.wishlist.WishListDao;
import com.cart.shoppingapp.db.wishlist.WishListProduct;
import com.cart.shoppingapp.model.Product;

import org.junit.Before;
import org.junit.Test;

import io.reactivex.Single;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;


public class ShoppingRepositoryTest {
    //private static final Note NOTE1 = new Note(TestUtil.TEST_NOTE_1);

    // system under test
    private ShoppingRepository shoppingRepository;

    private WishListDao wishListDao;

    ShoppingService mShoppingService;

    @Before
    public void initEach(){
        wishListDao = mock(WishListDao.class);
        mShoppingService = mock(ShoppingService.class);
        shoppingRepository = new ShoppingRepository(mShoppingService,wishListDao);
    }

}