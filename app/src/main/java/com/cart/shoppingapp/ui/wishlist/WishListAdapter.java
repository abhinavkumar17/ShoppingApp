package com.cart.shoppingapp.ui.wishlist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cart.shoppingapp.R;
import com.cart.shoppingapp.db.wishlist.WishListProduct;

import java.util.ArrayList;
import java.util.List;

public class WishListAdapter extends RecyclerView.Adapter<WishListAdapter.WishListViewHolder> {

    public interface ProductSelectionListener {
        void onWishListDelete(WishListProduct product);

        void onWishListMove(WishListProduct product);
    }

    private ProductSelectionListener mProductSelectionListener;
    private List<WishListProduct> data = new ArrayList<>();

    public WishListAdapter(ProductSelectionListener mProductSelectionListener) {
        this.mProductSelectionListener = mProductSelectionListener;
    }

    @NonNull
    @Override
    public WishListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_wish_list_adapter, parent, false);
        return new WishListAdapter.WishListViewHolder(view, mProductSelectionListener);
    }


    @Override
    public void onBindViewHolder(@NonNull WishListViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    public void setProductData(List<WishListProduct> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static final class WishListViewHolder extends RecyclerView.ViewHolder {

        TextView wishListcategory;
        TextView wishListPrice;
        TextView wishListName;
        TextView wishListOldPrice;
        TextView wishListdPrice;
        TextView wishListStock;
        ImageView wishlistDelete;
        Button wishlistCart;

        private WishListProduct product;

        WishListViewHolder(@NonNull View itemView, final ProductSelectionListener productSelectionListener) {
            super(itemView);
            //ButterKnife.bind(this, itemView);
            wishListcategory = itemView.findViewById(R.id.wishlist_category);
            wishListPrice = itemView.findViewById(R.id.wishlist_product_price);
            wishListName = itemView.findViewById(R.id.wishlist_product_name);
            wishListOldPrice = itemView.findViewById(R.id.wishlist_product_old_price);
            wishListdPrice = itemView.findViewById(R.id.wishlist_product_discounted_price);
            wishListStock = itemView.findViewById(R.id.wishlist_product_stock);
            wishlistDelete = itemView.findViewById(R.id.wish_list_item_delete);
            wishlistCart = itemView.findViewById(R.id.wishlist_to_cart);

            wishlistDelete.setOnClickListener(v -> {
                if (this.product != null && productSelectionListener != null) {
                    productSelectionListener.onWishListDelete(product);
                }
            });

            wishlistCart.setOnClickListener(v -> {
                if (this.product != null && productSelectionListener != null) {
                    productSelectionListener.onWishListMove(product);
                }
            });

        }

        void bind(WishListProduct product) {
            this.product = product;
            wishListcategory.setText(product.getCaterogy());
            wishListName.setText(product.getName());
            wishListPrice.setText(product.getPrice());
            wishListOldPrice.setText(product.getPrice());
            wishListStock.setText(String.valueOf(product.getStock()));
        }
    }
}
