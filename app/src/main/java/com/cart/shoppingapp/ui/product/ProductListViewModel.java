package com.cart.shoppingapp.ui.product;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.cart.shoppingapp.model.CartDetails;
import com.cart.shoppingapp.model.Product;
import com.cart.shoppingapp.repository.ShoppingRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductListViewModel extends ViewModel {
    private ShoppingRepository mShoppingRepository;

    private CompositeDisposable mCompositeDisposable;

    private MutableLiveData<List<Product>> productList = new MutableLiveData<>();

    private MutableLiveData<List<CartDetails>> cartList = new MutableLiveData<>();

    @Inject
    ProductListViewModel(ShoppingRepository shoppingRepository) {
        mShoppingRepository = shoppingRepository;
        mCompositeDisposable = new CompositeDisposable();
    }

    public LiveData<List<Product>> getProductList() {
        return productList;
    }

    public void setProductList(MutableLiveData<List<Product>> productList) {
        this.productList = productList;
    }

    public LiveData<List<CartDetails>> getAllCartList() {
        return cartList;
    }

    public void fetchData() {
        mCompositeDisposable.add(mShoppingRepository.getProducts().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableSingleObserver<List<Product>>() {
                    @Override
                    public void onSuccess(List<Product> products) {
                        productList.setValue(products);
                        Log.d("", "");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("", "");
                    }
                }));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
            mCompositeDisposable = null;
        }
    }

    public void fetchCartDetails() {
        mCompositeDisposable.add(mShoppingRepository.getCartProducts().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableSingleObserver<List<CartDetails>>() {
                    @Override
                    public void onSuccess(List<CartDetails> products) {
                        cartList.setValue(products);
                        Log.d("", "");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("", "");
                    }
                }));
    }

    public void deleteCartItem(int productId) {
        mShoppingRepository.deletCartItem(productId).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d("", "");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("", "");
            }
        });
    }
}
