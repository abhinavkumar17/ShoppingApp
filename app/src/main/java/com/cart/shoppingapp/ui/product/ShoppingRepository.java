package com.cart.shoppingapp.ui.product;

import com.cart.shoppingapp.db.wishlist.WishListDao;
import com.cart.shoppingapp.db.wishlist.WishListProduct;
import com.cart.shoppingapp.model.CartDetails;
import com.cart.shoppingapp.model.Product;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import retrofit2.Call;

public class ShoppingRepository {

    private final ShoppingService mShoppingService;
    private final WishListDao wishListDao;

    @Inject
    public ShoppingRepository(ShoppingService mShoppingService, WishListDao mWishListDao) {
        this.mShoppingService = mShoppingService;
        this.wishListDao = mWishListDao;
    }

    public Single<List<Product>> getProducts() {
        return mShoppingService.getProductsList();
    }

    public Single<List<CartDetails>> getCartProducts() {
        return mShoppingService.getCartsList();
    }

    public Call<Void> deletCartItem(int id) {
        return mShoppingService.deleteItem(id);
    }

    public Call<Void> addItemCart(int id) {
        return mShoppingService.addCartItem(id);
    }

    public Single<List<WishListProduct>> getAllWishListProducts() {
        return wishListDao.getAllTrolleyProducts();
    }

    public void insertIntoTrolley(Product product) {
        WishListProduct wishListProduct = new WishListProduct();
        wishListProduct.setName(product.getName());
        wishListProduct.setPrice(product.getPrice());
        wishListProduct.setCaterogy(product.getCaterogy());
        wishListProduct.setStock(Integer.toString(product.getStock()));
        wishListProduct.setOldPrice(product.getOldPrice());
        wishListDao.insert(wishListProduct);
    }

    public void deleteProduct(WishListProduct wishListProduct){
        wishListDao.remove(wishListProduct);
    }
}
