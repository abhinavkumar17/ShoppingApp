package com.cart.shoppingapp.ui.product;

import com.cart.shoppingapp.model.CartDetails;
import com.cart.shoppingapp.model.Product;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ShoppingService {

    @Headers({"x-api-key: dd8aa8aa73-00d4-42f1-ab81-f21f1bf1b8f8"})
    @GET("cloths/products")
    Single<List<Product>> getProductsList();

    @Headers({"x-api-key: dd8aa8aa73-00d4-42f1-ab81-f21f1bf1b8f8"})
    @GET("cloths/cart")
    Single<List<CartDetails>> getCartsList();

    @Headers({"x-api-key: dd8aa8aa73-00d4-42f1-ab81-f21f1bf1b8f8"})
    @DELETE("cloths/cart")
    Call<Void> deleteItem(@Query("id") int id );

    @Headers({"x-api-key: dd8aa8aa73-00d4-42f1-ab81-f21f1bf1b8f8"})
    @POST("cloths/cart")
    Call<Void> addCartItem(@Query("productId") int productId );
}
