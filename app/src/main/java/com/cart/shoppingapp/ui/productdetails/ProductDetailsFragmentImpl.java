package com.cart.shoppingapp.ui.productdetails;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import com.cart.shoppingapp.R;
import com.cart.shoppingapp.model.Product;
import com.cart.shoppingapp.ui.baseview.BaseViewMvc;



public class ProductDetailsFragmentImpl extends BaseViewMvc<ProductDetailsView.Listener> implements ProductDetailsView {

    private final TextView mCategory;
    private final TextView mPrice;
    private final TextView mProductName;
    private final TextView mOldPrice;
    private final TextView mDiscountedPrice;
    private final TextView mStock;
    private final Button addWishListButton;
    private final Button moveWishlistButton;
    private final Button moveCarttButton;
    private final Button addcartButton;
    private Product mProduct;

    public ProductDetailsFragmentImpl(LayoutInflater inflater, ViewGroup container) {
        setRootView(inflater.inflate(R.layout.fragment_product_details, container, false));
        mCategory = findViewById(R.id.shoppingd_category);
        mPrice = findViewById(R.id.shoppingd_product_price);
        mProductName = findViewById(R.id.shoppingd_product_name);
        mOldPrice = findViewById(R.id.shoppingd_product_old_price);
        mDiscountedPrice = findViewById(R.id.shoppingd_product_discounted_price);
        mStock = findViewById(R.id.shoppingd_product_stock);
        addWishListButton = findViewById(R.id.add_remove_from_wish_Button);
        moveWishlistButton = findViewById(R.id.wish_button);
        moveCarttButton= findViewById(R.id.cart_button);
        addcartButton = findViewById(R.id.add_remove_from_cart_Button);

        moveWishlistButton.setOnClickListener(trolleyButtonView -> {
            for(Listener listener:getListeners()){
                listener.navigateWishList();
            }
        });

        addWishListButton.setOnClickListener(addTrolleyButtonView -> {
            for(Listener listener:getListeners()){
                listener.insertWishList(mProduct);
            }
        });

        moveCarttButton.setOnClickListener(cartButtonView -> {
            for(Listener listener:getListeners()){
                listener.navigateCartList();
            }
        });

        addcartButton.setOnClickListener(cartAddView -> {
            for(Listener listener:getListeners()){
                listener.insertCartList(mProduct);
            }
        });
    }

    @Override
    public void setProductDetailData(Product mProduct) {
        this.mProduct = mProduct;
        mCategory.setText((mProduct.getCaterogy()));
        mPrice.setText((mProduct.getPrice()));
        mProductName.setText((mProduct.getName()));
        mOldPrice.setText((mProduct.getOldPrice()));
        mStock.setText((Integer.toString(mProduct.getStock())));
    }
}
