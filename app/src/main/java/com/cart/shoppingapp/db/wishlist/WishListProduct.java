package com.cart.shoppingapp.db.wishlist;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

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
    private String stock;


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

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }
}