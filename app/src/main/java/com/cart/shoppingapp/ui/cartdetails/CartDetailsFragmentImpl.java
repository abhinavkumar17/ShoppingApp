package com.cart.shoppingapp.ui.cartdetails;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.cart.shoppingapp.R;
import com.cart.shoppingapp.model.CartDetails;
import com.cart.shoppingapp.ui.baseview.BaseViewMvc;

import java.util.List;

public class CartDetailsFragmentImpl extends BaseViewMvc<CartDetailsView.Listener> implements CartDetailsView,
        CartListAdapter.CartSelectionListener {

    private static final int NO_OF_COLUMNS = 1;
    private final RecyclerView mRecyclerView;
    private final ProgressBar mProgressBar;
    private final TextView mEmplyListErrorView;
    private final CartListAdapter mCartListAdapter;

    public CartDetailsFragmentImpl(LayoutInflater inflater, ViewGroup container) {
        setRootView(inflater.inflate(R.layout.fragment_cart_list, container, false));

        mRecyclerView = findViewById(R.id.cart_list_recycler_view);
        mCartListAdapter = new CartListAdapter(this);
        mRecyclerView.setAdapter(mCartListAdapter);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(NO_OF_COLUMNS, StaggeredGridLayoutManager.VERTICAL));

        mProgressBar = findViewById(R.id.loading_progress_view);
        mEmplyListErrorView = findViewById(R.id.generic_error_text_view);
    }

    @Override
    public void onCartItemRemove(CartDetails product) {
        for(Listener listener:getListeners()){
            listener.onCartItemDelete(product);
        }
    }

    @Override
    public void serServerError() {
        mRecyclerView.setVisibility(View.GONE);
        mEmplyListErrorView.setVisibility(View.VISIBLE);
        mEmplyListErrorView.setText(getContext().getResources().getString(R.string.generic_error_statement));
    }

    @Override
    public void setEmptError() {
        mRecyclerView.setVisibility(View.GONE);
        mEmplyListErrorView.setVisibility(View.VISIBLE);
        mEmplyListErrorView.setText(getContext().getResources().getString(R.string.no_items_error_statement));
    }

    @Override
    public void setCartData(List<CartDetails> cartDetails) {
        mCartListAdapter.setProductData(cartDetails);
    }
}
