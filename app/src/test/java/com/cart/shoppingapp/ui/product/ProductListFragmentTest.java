package com.cart.shoppingapp.ui.product;

import android.app.Instrumentation;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.cart.shoppingapp.BuildConfig;
import com.cart.shoppingapp.ShoppingApplication;
import com.cart.shoppingapp.di.viewmodule.ViewModelProviderFactory;
import com.cart.shoppingapp.model.Product;
import com.cart.shoppingapp.repository.ShoppingRepository;
import com.cart.shoppingapp.testdata.ProductTestData;
import com.cart.shoppingapp.ui.baseview.ViewMvcFactory;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;

import java.util.List;

import javax.inject.Inject;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

public class ProductListFragmentTest {

    // region constants ----------------------------------------------------------------------------
    private static final List<Product> PRODUCT_DETAILS = ProductTestData.getProduct();
    private static final MutableLiveData<List<Product>> PRODUCT_DETAILS_DATA = ProductTestData.getProductListData();
    // endregion constants -------------------------------------------------------------------------

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @InjectMocks
    ProductListFragment SUT;

    @Mock
    ViewMvcFactory mViewMvcFactory;

    @Mock
    ViewModelProviderFactory mViewModelProviderFactory;

    @Mock
    ProductListView mProductListView;

    @Mock
    private ProductListViewModel mProductListViewModel;

    @Mock
    ShoppingRepository mShoppingRepository;

    @Before
    public void setUp() throws Exception {
        mProductListView.bindProductData(PRODUCT_DETAILS);
    }

    @Test
    public void onStart_progressIndicationShown() throws Exception {
        // Arrange
        // Act
        SUT.onStart();
        // Assert
        verify(mProductListView).showProgressIndication();
    }
}