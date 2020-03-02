package com.cart.shoppingapp.db.wishlist;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;

import com.cart.shoppingapp.testdata.LiveDataTestUtil;
import com.cart.shoppingapp.testdata.ProductDatabaseTest;
import com.cart.shoppingapp.testdata.ProductTestData;

import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class WishListDaoTest extends ProductDatabaseTest {

    @Rule
    public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();

    @Test
    public void insertReadDelete() throws Exception {
        WishListProduct mWishListProduct = new WishListProduct(ProductTestData.TEST_PRODUCT_1);

        // insert
        getWishDao().insert(mWishListProduct); // wait until inserted

        // read
        LiveDataTestUtil<List<WishListProduct>> liveDataTestUtil = new LiveDataTestUtil<>();
        List<WishListProduct> insertedNotes = null;//liveDataTestUtil.getValue(getWishDao().getAllTrolleyProducts());

        assertNotNull(insertedNotes);

        assertEquals(mWishListProduct.getCaterogy(), insertedNotes.get(0).getCaterogy());
        assertEquals(mWishListProduct.getName(), insertedNotes.get(0).getName());
        assertEquals(mWishListProduct.getPrice(), insertedNotes.get(0).getPrice());
        assertEquals(mWishListProduct.getStock(), insertedNotes.get(0).getStock());
        assertEquals(mWishListProduct.getOldPrice(), insertedNotes.get(0).getOldPrice());

        mWishListProduct.setId(insertedNotes.get(0).getId());
        assertEquals(mWishListProduct, insertedNotes.get(0));

        // delete
        getWishDao().remove(mWishListProduct);

        // confirm the database is empty
        //insertedNotes = liveDataTestUtil.getValue(getWishDao().getAllTrolleyProducts());
        assertEquals(0, insertedNotes.size());

    }

}