package com.cart.shoppingapp.ui.product;

import com.cart.shoppingapp.model.Product;
import com.cart.shoppingapp.testdata.ProductTestData;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.List;

import static org.mockito.Mockito.verify;

public class ProductListFragmentTest {

    // region constants ----------------------------------------------------------------------------
    private static final List<Product> PRODUCT_DETAILS = ProductTestData.getProduct();
    // endregion constants -------------------------------------------------------------------------

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @InjectMocks
    ProductListFragment SUT;

    @Mock
    ProductListView mProductListView;

    @Mock
    private ProductListViewModel mProductListViewModel;

    @Before
    public void setUp() throws Exception {
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
    public void onStart_register_listener_before_service_call() throws Exception {
        // Arrange
        // Act
        SUT.onStart();
        // Assert
        verify(mProductListView).registerListener(SUT);
        verify(mProductListViewModel).registerListener(SUT);
    }

    @Test
    public void onStop_unregister_listener_after_service_call() throws Exception {
        // Arrange
        // Act
        SUT.onStop();
        // Assert
        verify(mProductListView).unregisterListener(SUT);
        verify(mProductListViewModel).unregisterListener(SUT);
    }

    @Test
    public void onService_fail_progressIndicationHide() throws Exception {
        // Arrange
        // Act
        SUT.onFetchProductFailAndNotify();
        // Assert
        verify(mProductListView).hideProgressIndication();
    }

    @Test
    public void onService_successfulResponse_productBoundToView() throws Exception {
        // Arrange
        // Act
        SUT.onFetchProductSecessAndNotify(PRODUCT_DETAILS);
        // Assert
        verify(mProductListView).hideProgressIndication();
        verify(mProductListView).bindProductData(PRODUCT_DETAILS);
    }

    @Test
    public void onService_failResponse_errorBoundToView() throws Exception {
        // Arrange
        // Act
        SUT.onFetchProductFailAndNotify();
        // Assert
        verify(mProductListView).setServerError();
    }
}