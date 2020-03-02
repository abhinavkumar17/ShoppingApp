package com.cart.shoppingapp.ui.wishlist;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.cart.shoppingapp.db.wishlist.WishListProduct;
import com.cart.shoppingapp.repository.ShoppingRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class WishListViewModel extends ViewModel {

    public interface Listener{
          void onEmptyListError();
          void onServerError();
    }

    private Set<WishListViewModel.Listener> mListeners = new HashSet<>();

    private static final String TAG = WishListViewModel.class.getSimpleName();

    private ShoppingRepository shoppingRepository;
    private CompositeDisposable mCompositeDisposable;

    private final MutableLiveData<List<WishListProduct>> wishListProducts = new MutableLiveData<>();

    LiveData<List<WishListProduct>> getWishListProducts() {
        return wishListProducts;
    }

    @Inject
    WishListViewModel(ShoppingRepository shoppingRepository) {
        this.shoppingRepository = shoppingRepository;
        mCompositeDisposable = new CompositeDisposable();
    }

    public void registerListener(Listener listener) {
        mListeners.add(listener);
    }

    public void unregisterListener(Listener listener) {
        mListeners.remove(listener);
    }

    public void fetchAllWishListProducts() {
        mCompositeDisposable.add(shoppingRepository.getAllWishListProducts().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<WishListProduct>>() {
                    @Override
                    public void onSuccess(List<WishListProduct> products) {
                        if (products.size() == 0) {
                               onEmptyListError();
                        }else {
                            wishListProducts.setValue(products);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        onServerError();
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

    void deleteProduct(WishListProduct wishListProduct) {
        shoppingRepository.deleteProduct(wishListProduct);
        fetchAllWishListProducts();
    }

    void moveProducttoCart(WishListProduct wishListProduct) {
        shoppingRepository.addItemCart(wishListProduct.getId());
        shoppingRepository.deleteProduct(wishListProduct);
        fetchAllWishListProducts();
    }

    private int getProductCount(){
        return wishListProducts.getValue() != null ? wishListProducts.getValue().size() : 0;
    }

    private float getNetAmount(){
        if (wishListProducts.getValue() == null )
            return 0;
        float totalAmount = 0;
        for (WishListProduct trolleyProduct : wishListProducts.getValue()){
            totalAmount += Float.parseFloat(trolleyProduct.getPrice());
        }
        return totalAmount;
    }
}
