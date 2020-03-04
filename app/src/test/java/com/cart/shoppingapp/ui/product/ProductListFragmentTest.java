package com.cart.shoppingapp.ui.product;

import com.cart.shoppingapp.di.viewmodule.ViewModelProviderFactory;
import com.cart.shoppingapp.model.Product;
import com.cart.shoppingapp.repository.ShoppingRepository;
import com.cart.shoppingapp.testdata.ProductTestData;
import com.cart.shoppingapp.ui.baseview.ViewFactory;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.List;

import io.reactivex.Single;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ProductListFragmentTest {

    // region constants ----------------------------------------------------------------------------
    private static final List<Product> PRODUCT_DETAILS = ProductTestData.getProduct();
    private static final List<Product> PRODUCT_ERROR = ProductTestData.getProductError();
    // private static final MutableLiveData<List<Product>> PRODUCT_DETAILS_DATA = ProductTestData.getProductListData();
    // endregion constants -------------------------------------------------------------------------

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @InjectMocks
    ProductListFragment SUT;

    @Mock
    ViewFactory mViewFactory;

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
        // mProductListView.bindProductData(PRODUCT_DETAILS);
    }

    @Test
    public void onStart_progressIndicationShown() throws Exception {
        // Arrange

        // Act
        SUT.onStart();
        // Assert
        verify(mProductListView).showProgressIndication();
    }

    @Test
    public void onStart1_progressIndicationShown() throws Exception {
        // Arrange
        //Setting how up the mock behaves
        final Single<List<Product>> returnedData = Single.just(PRODUCT_DETAILS);
        when(mShoppingRepository.getProducts()).thenReturn(returnedData);
        // Act
        //Fire the test method
        mProductListViewModel = new ProductListViewModel(mShoppingRepository);
        mProductListViewModel.fetchData();
        //Check that our live data is updated
        // Assert.assertEquals(PRODUCT_DETAILS, mProductListViewModel.getProductList().getValue());
        // Assert
        assertNotNull(mProductListViewModel.getProductList());
    }

    @Test
    public void onStart2_progressIndicationShown() throws Exception {
        // Arrange
        //Setting how up the mock behaves
        final Single<List<Product>> returnedData = Single.just(PRODUCT_ERROR);
        when(mShoppingRepository.getProducts()).thenReturn(returnedData);
        // Act
        //Fire the test method
        mProductListViewModel = new ProductListViewModel(mShoppingRepository);
        mProductListViewModel.fetchData();
        //Check that our live data is updated
        // Assert.assertEquals(PRODUCT_DETAILS, mProductListViewModel.getProductList().getValue());
        // Assert
        assertNull(mProductListViewModel.getProductList());

    }
}