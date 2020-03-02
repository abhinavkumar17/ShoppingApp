package com.cart.shoppingapp.db.wishlist;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.cart.shoppingapp.model.Product;

@Entity(tableName = "wishlist_products")
public class WishListProduct {


    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "wishlist_id")
    private int id;

    @ColumnInfo(name = "wishlist_name")
    private String name;

    @ColumnInfo(name = "wishlist_caterogy")
    private String caterogy;

    @ColumnInfo(name = "wishlist_price")
    private String price;

    @ColumnInfo(name = "oldPrice")
    private String oldPrice;

    @ColumnInfo(name = "wishlist_stock")
    private int stock;

    public WishListProduct(){

    }

    public WishListProduct(int id, String name, String category, String price, String old_price, int stock) {
        this.id = id;
        this.name = name;
        this.caterogy = category;
        this.price = price;
        this.oldPrice = old_price;
        this.stock = stock;
    }

    public WishListProduct(WishListProduct wishListProduct) {
        this.id = wishListProduct.id;
        this.name = wishListProduct.name;
        this.caterogy = wishListProduct.caterogy;
        this.price = wishListProduct.price;
        this.oldPrice = wishListProduct.oldPrice;
        this.stock = wishListProduct.stock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCaterogy() {
        return caterogy;
    }

    public void setCaterogy(String caterogy) {
        this.caterogy = caterogy;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(String oldPrice) {
        this.oldPrice = oldPrice;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}