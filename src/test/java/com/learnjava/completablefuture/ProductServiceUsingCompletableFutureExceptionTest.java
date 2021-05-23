package com.learnjava.completablefuture;

import com.learnjava.domain.Product;
import com.learnjava.service.InventoryService;
import com.learnjava.service.ProductInfoService;
import com.learnjava.service.ReviewService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceUsingCompletableFutureExceptionTest {

    @Mock
    private ProductInfoService productInfoServiceMock;

    @Mock
    private ReviewService reviewServiceMock;

    @Mock
    private InventoryService inventoryServiceMock;

    @InjectMocks
    private ProductServiceUsingCompletableFuture productServiceUsingCompletableFuture;


    @Test
    void retrieveProductDetailsWithInventory() {
        String productId = "ABC123";
        when(productInfoServiceMock.retrieveProductInfo(productId)).thenCallRealMethod();
        when(inventoryServiceMock.addInventory(any())).thenCallRealMethod();
        when(reviewServiceMock.retrieveReviews(productId)).thenThrow(new RuntimeException("Runtime Exception for Review Service"));

        Product product = this.productServiceUsingCompletableFuture.retrieveProductDetailsWithInventory(productId);
        assertNotNull(product);
        assertTrue(product.getProductInfo().getProductOptions().size() > 0);
        assertNotNull(product.getReview());
        assertEquals(0, product.getReview().getNoOfReviews());
        product.getProductInfo().getProductOptions().forEach(productOption -> {
            assertNotNull(productOption.getInventory());
            assertTrue(productOption.getInventory().getCount() > 0);
        });
    }

    @Test
    void retrieveProductDetailsWithInventory_productInfoServiceError() {
        String productId = "ABC123";
        when(productInfoServiceMock.retrieveProductInfo(productId)).thenThrow(new RuntimeException("Runtime Exception for ProductInfo Service"));
        when(reviewServiceMock.retrieveReviews(productId)).thenCallRealMethod();

        Assertions.assertThrows(RuntimeException.class,
                ()-> this.productServiceUsingCompletableFuture.retrieveProductDetailsWithInventory(productId));

    }
}