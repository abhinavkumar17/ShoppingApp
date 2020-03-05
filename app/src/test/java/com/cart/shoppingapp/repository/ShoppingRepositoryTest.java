package com.cart.shoppingapp.repository;

import com.cart.shoppingapp.db.wishlist.WishListDao;
import com.cart.shoppingapp.model.Product;
import com.cart.shoppingapp.testdata.ProductTestData;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import io.reactivex.Single;

import static com.cart.shoppingapp.testdata.ProductTestData.TEST_PRODUCT_1;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


public class ShoppingRepositoryTest {
    // region constants ----------------------------------------------------------------------------
    private static final List<Product> PRODUCT_DETAILS = ProductTestData.getProduct();
    // endregion constants -------------------------------------------------------------------------

    // system under test
    private ShoppingRepository SUT;

    private WishListDao wishListDao;

    ShoppingService mShoppingService;

    @Before
    public void initEach() {
        wishListDao = mock(WishListDao.class);
        mShoppingService = mock(ShoppingService.class);
        SUT = new ShoppingRepository(mShoppingService, wishListDao);
    }

    @Test
    public void test_data()throws Exception{

        // Arrange
        final Long insertedRow = 1L;
        final Single<Long> returnedData = Single.just(insertedRow);
        SUT.insertIntoTrolley(PRODUCT_DETAILS.get(0));

        assertEquals(SUT.getprd(),TEST_PRODUCT_1);
    }

}