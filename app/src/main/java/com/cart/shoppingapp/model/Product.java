package com.cart.shoppingapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Ignore;

import io.reactivex.annotations.NonNull;

public class Product implements Parcelable {


    @NonNull
    private int id;
    @NonNull
    private String name;
    @NonNull
    private String caterogy;
    @NonNull
    private String price;
    @NonNull
    private String oldPrice;
    @NonNull
    private int stock;

    public Product(int id, String name, String caterogy, String price, String oldPrice, int stock) {
        this.id = id;
        this.name = name;
        this.caterogy = caterogy;
        this.price = price;
        this.oldPrice = oldPrice;
        this.stock = stock;
    }

    @Ignore
    public Product(Product product) {
        this.id = product.id;
        this.name = product.name;
        this.caterogy = product.caterogy;
        this.price = product.price;
        this.oldPrice = product.oldPrice;
        this.stock = product.stock;
    }


    protected Product(Parcel in) {
        id = in.readInt();
        name = in.readString();
        caterogy = in.readString();
        price = in.readString();
        oldPrice = in.readString();
        stock = in.readInt();
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



    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(caterogy);
        parcel.writeString(price);
        parcel.writeString(oldPrice);
        parcel.writeInt(stock);
    }
}
