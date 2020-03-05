package com.cart.shoppingapp.ui.product;

import android.util.Log;

import com.cart.shoppingapp.model.Product;
import com.cart.shoppingapp.repository.ShoppingRepository;
import com.cart.shoppingapp.ui.baseview.BaseObservable;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class FetchProductListUseCase extends BaseObservable<FetchProductListUseCase.Listener> {

  public interface Listener{

      void onFetchProductSecessAndNotify(List<Product> products);

      void onFetchProductFailAndNotify();
  }

  ShoppingRepository mShoppingRepository;

  public FetchProductListUseCase(ShoppingRepository mShoppingRepository) {
        this.mShoppingRepository = mShoppingRepository;
    }


    public DisposableSingleObserver<List<Product>> fetchProductList() {
        return mShoppingRepository.getProducts().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableSingleObserver<List<Product>>() {
                    @Override
                    public void onSuccess(List<Product> products) {
                        for (Listener listener:getListeners()){
                            listener.onFetchProductSecessAndNotify(products);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("", "");
                        for (Listener listener:getListeners()){
                            listener.onFetchProductFailAndNotify();
                        }
                    }
                });
    }
}
