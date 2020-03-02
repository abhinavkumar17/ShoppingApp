package com.cart.shoppingapp.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProductTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void isNotesEqual_identicalProperties_returnTrue() throws Exception {

        // Arrange
        Product product1 = new Product(1,"name1","category1","price1","old_prrce1",1);
        Product product2 = new Product(1,"name2","category2","price2","old_prrce2",2);

        // Act

        // Assert
        assertNotEquals(product1, product2);
    }
}