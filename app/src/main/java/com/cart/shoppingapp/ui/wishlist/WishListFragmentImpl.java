package com.cart.shoppingapp.ui.wishlist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.cart.shoppingapp.R;
import com.cart.shoppingapp.db.wishlist.WishListProduct;
import com.cart.shoppingapp.model.CartDetails;
import com.cart.shoppingapp.ui.baseview.BaseViewMvc;
import com.cart.shoppingapp.ui.cartdetails.CartListAdapter;

import java.util.List;

public class WishListFragmentImpl extends BaseViewMvc<WishListView.Listener> implements WishListView,
        WishListAdapter.ProductSelectionListener {

    private static final int NO_OF_COLUMNS = 1;
    private final RecyclerView mRecyclerView;
    private final ProgressBar mProgressBar;
    private final TextView mEmplyListErrorView;
    private final WishListAdapter mWishListAdapter;

    public WishListFragmentImpl(LayoutInflater inflater, ViewGroup container) {
        setRootView(inflater.inflate(R.layout.fragment_product_list, container, false));
        mRecyclerView = findViewById(R.id.shopping_list_recycler_view);
        mWishListAdapter = new WishListAdapter(this);
        mRecyclerView.setAdapter(mWishListAdapter);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(NO_OF_COLUMNS, StaggeredGridLayoutManager.VERTICAL));

        mProgressBar = findViewById(R.id.loading_progress_view);
        mEmplyListErrorView = findViewById(R.id.generic_error_text_view);
    }

    @Override
    public void onWishListDelete(WishListProduct product) {
        for (Listener listener : getListeners()) {
            listener.onWishListDelete(product);
        }
    }

    @Override
    public void onWishListMove(WishListProduct product) {
        for (Listener listener : getListeners()) {
            listener.onWishListMove(product);
        }
    }

    @Override
    public void setWishListProducts(List<WishListProduct> wishListProducts) {
        mWishListAdapter.setProductData(wishListProducts);
    }

    @Override
    public void setEmptError() {
        mRecyclerView.setVisibility(View.GONE);
        mEmplyListErrorView.setVisibility(View.VISIBLE);
        mEmplyListErrorView.setText(getContext().getResources().getString(R.string.no_items_error_statement));
    }

    @Override
    public void serServerError() {
        mRecyclerView.setVisibility(View.GONE);
        mEmplyListErrorView.setVisibility(View.VISIBLE);
        mEmplyListErrorView.setText(getContext().getResources().getString(R.string.generic_error_statement));
    }
}
