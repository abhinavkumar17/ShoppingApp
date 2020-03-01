package com.cart.shoppingapp.ui.cartdetails;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cart.shoppingapp.R;
import com.cart.shoppingapp.db.wishlist.WishListProduct;
import com.cart.shoppingapp.model.CartDetails;
import com.cart.shoppingapp.model.Product;
import com.cart.shoppingapp.ui.wishlist.WishListAdapter;

import java.util.ArrayList;
import java.util.List;

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.CartViewHolder>  {

    public interface CartSelectionListener {

        void onCartItemRemove(CartDetails product);
    }

    private CartSelectionListener mCartSelectionListener;

    private List<CartDetails> data = new ArrayList<>();

    public CartListAdapter(CartSelectionListener mCartSelectionListener) {
        this.mCartSelectionListener = mCartSelectionListener;
    }

    public void setProductData(List<CartDetails> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_cart_adapter, parent, false);
        return new CartListAdapter.CartViewHolder(view,mCartSelectionListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static final class CartViewHolder extends RecyclerView.ViewHolder {
        TextView cardId;
        TextView cartProductId;
        ImageView itemDelete;

        private CartDetails product;

        CartViewHolder(@NonNull View itemView,CartSelectionListener cartSelectionListener) {
            super(itemView);

            cardId = itemView.findViewById(R.id.cart_id);
            cartProductId = itemView.findViewById(R.id.cart_productid);
            itemDelete = itemView.findViewById(R.id.item_delete);

            itemDelete.setOnClickListener(v -> {
                if (this.product != null && cartSelectionListener != null) {
                    cartSelectionListener.onCartItemRemove(product);
                }
            });

        }

        public void bind(CartDetails cartDetails) {
            product = cartDetails;
            cardId.setText(Integer.toString(cartDetails.getId()));
            cartProductId.setText(Integer.toString(cartDetails.getProductId()));

        }
    }
}
