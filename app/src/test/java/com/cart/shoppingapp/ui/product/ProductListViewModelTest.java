package com.cart.shoppingapp.ui.product;

import com.cart.shoppingapp.model.Product;
import com.cart.shoppingapp.repository.ShoppingRepository;
import com.cart.shoppingapp.testdata.ProductTestData;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;

import java.util.List;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ProductListViewModelTest {

    // region constants ----------------------------------------------------------------------------
    private static final List<Product> PRODUCT_DETAILS = ProductTestData.getProduct();
    // endregion constants -------------------------------------------------------------------------

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();
    @Mock
    ProductListViewModel.Listener mListener1;
    @Mock
    ProductListViewModel.Listener mListener2;

    @Mock
    private FetchProductListUseCase mFetchProductListUseCase;

    //@InjectMocks
    ProductListViewModel SUT;



    @Before
    public void setUp() throws Exception {
        SUT = new ProductListViewModel(mFetchProductListUseCase);
        SUT.registerListener(mListener1);
        SUT.registerListener(mListener2);
    }

    @Test
    public void fetchProductSecessAndNotify_success_listenersNotifiedWithCorrectData() throws Exception {
        // Arrange
        // Act
        SUT.onFetchProductSecessAndNotify(PRODUCT_DETAILS);
        // Assert
        verify(mListener1).onFetchProductSecessAndNotify(PRODUCT_DETAILS);
    }

    @Test
    public void FetchProductFailAndNotify_fail_listenersNotified() throws Exception {
        // Arrange
        //success();
        // Act
        SUT.onFetchProductFailAndNotify();
        // Assert
        verify(mListener1).onFetchProductFailAndNotify();
    }


}