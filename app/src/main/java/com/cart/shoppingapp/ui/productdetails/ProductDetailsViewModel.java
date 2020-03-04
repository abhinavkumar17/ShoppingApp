package com.cart.shoppingapp.ui.productdetails;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.cart.shoppingapp.db.wishlist.WishListProduct;
import com.cart.shoppingapp.model.CartDetails;
import com.cart.shoppingapp.model.Product;
import com.cart.shoppingapp.repository.ShoppingRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailsViewModel extends ViewModel {

    public interface Listener {
        void onEmptyListError();

        void onServerError();
    }

    private Set<ProductDetailsViewModel.Listener> mListeners = new HashSet<>();
    private static final String TAG = ProductDetailsViewModel.class.getSimpleName();

    private ShoppingRepository mShoppingRepository;

    private CompositeDisposable mCompositeDisposable;

    private Context mContext;

    private final MutableLiveData<List<WishListProduct>> productList = new MutableLiveData<>();
    private final MutableLiveData<List<CartDetails>> cartList = new MutableLiveData<>();


    @Inject
    ProductDetailsViewModel(ShoppingRepository shoppingRepository, Context context) {
        mShoppingRepository = shoppingRepository;
        mContext = context;
        mCompositeDisposable = new CompositeDisposable();
    }

    public Context getContext() {
        return mContext;
    }

    LiveData<List<WishListProduct>> getProductList() {
        return productList;
    }

    public LiveData<List<CartDetails>> getAllCartList() {
        return cartList;
    }

    public void registerListener(Listener listener) {
        mListeners.add(listener);
    }

    public void unregisterListener(Listener listener) {
        mListeners.remove(listener);
    }


    void addItemToWishlist(Product product) {
        mShoppingRepository.insertIntoTrolley(product);
        fetchData();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
            mCompositeDisposable = null;
        }
    }

    private void fetchData() {
        mCompositeDisposable.add(mShoppingRepository.getAllWishListProducts().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableSingleObserver<List<WishListProduct>>() {
                    @Override
                    public void onSuccess(List<WishListProduct> products) {
                        Log.d(TAG, "products" + products);
                        productList.setValue(products);
                        if (products.size() == 0) {
                            onEmptyListError();
                        } else {
                            productList.setValue(products);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "Error" + e);
                    }
                }));
    }

    private void onServerError() {
        for (Listener listener : mListeners) {
            listener.onServerError();
        }
    }

    private void onEmptyListError() {
        for (Listener listener : mListeners) {
            listener.onEmptyListError();
        }

    }

    public void addItemtoCart(int id) {
        mShoppingRepository.addItemCart(id).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(getContext(), response.message(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void fetchCartDetails() {
        mCompositeDisposable.add(mShoppingRepository.getCartProducts().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableSingleObserver<List<CartDetails>>() {
                    @Override
                    public void onSuccess(List<CartDetails> products) {
                        if (products.size() == 0) {
                            onEmptyListError();
                        } else {
                            cartList.setValue(products);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        onServerError();
                    }
                }));
    }

    public void deleteCartItem(int productId) {
        mShoppingRepository.deletCartItem(productId).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(getContext(), response.message(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
