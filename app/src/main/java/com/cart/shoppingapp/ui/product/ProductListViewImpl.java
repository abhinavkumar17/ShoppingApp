package com.cart.shoppingapp.ui.product;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.cart.shoppingapp.R;
import com.cart.shoppingapp.model.Product;
import com.cart.shoppingapp.ui.baseview.BaseView;

import java.util.List;

public class ProductListViewImpl extends BaseView<ProductListView.Listener>
        implements ProductListView, ProductListAdapter.ProductSelectionListener {

    private static final int NO_OF_COLUMNS = 2;
    private final RecyclerView mRecyclerView;
    private final ProgressBar mProgressBar;
    private final TextView  mEmplyListErrorView;
    private final ProductListAdapter mProductListAdapter;

    public ProductListViewImpl(LayoutInflater inflater, ViewGroup container){
       setRootView(inflater.inflate(R.layout.fragment_product_list, container, false));

        mRecyclerView = findViewById(R.id.shopping_list_recycler_view);
        mProductListAdapter = new ProductListAdapter(this);
        mRecyclerView.setAdapter(mProductListAdapter);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(NO_OF_COLUMNS, StaggeredGridLayoutManager.VERTICAL));

        mProgressBar = findViewById(R.id.loading_progress_view);
        mEmplyListErrorView = findViewById(R.id.generic_error_text_view);
    }

    @Override
    public void showProgressIndication() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressIndication() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void bindProductData(List<Product> products) {
        mRecyclerView.setVisibility(View.VISIBLE);
        mProductListAdapter.setProductData(products);
    }

    @Override
    public void setServerError() {
        mRecyclerView.setVisibility(View.GONE);
        mEmplyListErrorView.setVisibility(View.VISIBLE);
        mEmplyListErrorView.setText(getContext().getResources().getString(R.string.generic_error_statement));
    }

    @Override
    public void onProductClick(Product product) {
       for(Listener listener:getListeners()){
           listener.onProductItemClick(product);
       }
    }
}
