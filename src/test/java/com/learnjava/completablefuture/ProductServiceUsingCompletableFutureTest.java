package com.learnjava.completablefuture;

import com.learnjava.domain.Product;
import com.learnjava.service.InventoryService;
import com.learnjava.service.ProductInfoService;
import com.learnjava.service.ReviewService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;

import static com.learnjava.util.CommonUtil.startTimer;
import static com.learnjava.util.CommonUtil.timeTaken;
import static org.junit.jupiter.api.Assertions.*;

class ProductServiceUsingCompletableFutureTest {

    ProductInfoService productInfoService;
    ReviewService reviewService;
    ProductServiceUsingCompletableFuture productService;
    InventoryService inventoryService;

    @BeforeEach
    void init() {
        productInfoService = new ProductInfoService();
        reviewService = new ReviewService();
        inventoryService = new InventoryService();
        productService = new ProductServiceUsingCompletableFuture(productInfoService, reviewService, inventoryService);
    }

    @Test
    void retrieveProductDetails() {
        String productId = "ABC123";
        Product product = this.productService.retrieveProductDetails(productId);
        assertNotNull(product);
    }

    @Test
    void retrieveAsyncProductDetailsTest() {

        startTimer();

        String productId = "ABC123";
        CompletableFuture<Product> cfProduct = this.productService.retrieveAsyncProductDetails(productId);
        cfProduct
                .thenAccept(product -> {
                    assertNotNull(product);
                    assertTrue(product.getProductInfo().getProductOptions().size()>0);
                    assertNotNull(product.getReview());
                })
                .join();

        timeTaken();
    }

    @Test
    void retrieveProductDetailsWithInventory() {
        String productId = "ABC123";
        Product product = this.productService.retrieveProductDetailsWithInventory(productId);
        assertNotNull(product);
        assertTrue(product.getProductInfo().getProductOptions().size()>0);
        assertNotNull(product.getReview());
        product.getProductInfo().getProductOptions().forEach(productOption -> {
            assertNotNull(productOption.getInventory());
            assertTrue(productOption.getInventory().getCount()>0);
        });
    }
}