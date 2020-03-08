package com.cart.shoppingapp.ui.product;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.cart.shoppingapp.model.CartDetails;
import com.cart.shoppingapp.model.Product;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class ProductListViewModel extends ViewModel implements FetchProductListUseCase.Listener {


    public interface Listener {

        void onFetchProductSecessAndNotify(List<Product> products);

        void onFetchProductFailAndNotify();
    }

    private FetchProductListUseCase mFetchProductListUseCase;

    private MutableLiveData<List<CartDetails>> cartList = new MutableLiveData<>();

    private Set<Listener> mListeners = new HashSet<>();

    @Inject
    ProductListViewModel(FetchProductListUseCase fetchProductListUseCase) {
        mFetchProductListUseCase = fetchProductListUseCase;
    }

    public void registerViewModel() {
        mFetchProductListUseCase.registerListener(this);
    }


    @Override
    protected void onCleared() {
        super.onCleared();
        mFetchProductListUseCase.unregisterListener(this);
    }

    public void fetchProductList() {
       mFetchProductListUseCase.fetchProductList();
    }

    public void registerListener(Listener listener) {
        mListeners.add(listener);
    }

    public void unregisterListener(Listener listener) {
        mListeners.remove(listener);
    }

    @Override
    public void onFetchProductSecessAndNotify(List<Product> products) {
        for (Listener listener : mListeners) {
            listener.onFetchProductSecessAndNotify(products);
        }
    }

    @Override
    public void onFetchProductFailAndNotify() {
        for (Listener listener : mListeners) {
            listener.onFetchProductFailAndNotify();
        }
    }
}
