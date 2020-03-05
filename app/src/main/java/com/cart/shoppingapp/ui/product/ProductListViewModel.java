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

    private CompositeDisposable mCompositeDisposable;

    private MutableLiveData<List<CartDetails>> cartList = new MutableLiveData<>();

    private Set<Listener> mListeners = new HashSet<>();

    @Inject
    ProductListViewModel(FetchProductListUseCase fetchProductListUseCase) {
        mFetchProductListUseCase = fetchProductListUseCase;
        mCompositeDisposable = new CompositeDisposable();
        mFetchProductListUseCase.registerListener(this);
    }


    @Override
    protected void onCleared() {
        super.onCleared();
        mFetchProductListUseCase.unregisterListener(this);
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
            mCompositeDisposable = null;
        }
    }

    public void fetchProductList() {
        mCompositeDisposable.add(mFetchProductListUseCase.fetchProductList());
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
