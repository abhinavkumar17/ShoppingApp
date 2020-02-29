package com.cart.shoppingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.cart.shoppingapp.ui.product.ProductListFragment;

import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.screenContainer, ProductListFragment.newInstance()).commit();
        }
    }
}
