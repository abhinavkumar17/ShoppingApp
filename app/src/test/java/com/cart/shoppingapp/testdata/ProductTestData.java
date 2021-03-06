package com.cart.shoppingapp.testdata;

import androidx.lifecycle.MutableLiveData;

import com.cart.shoppingapp.db.wishlist.WishListProduct;
import com.cart.shoppingapp.model.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductTestData {

    public static final WishListProduct TEST_PRODUCT_1 = new WishListProduct(1, "name1", "category1", "price1", "old_price", 1);

    public static List<Product> getProduct() {
        List<Product> list = new ArrayList<Product>();
        list.add(new Product(1, "name1", "category1", "price1", "old_price", 1));
        list.add(new Product(2, "name2", "category2", "price2", "old_price2", 2));
        return list;
    }
}
