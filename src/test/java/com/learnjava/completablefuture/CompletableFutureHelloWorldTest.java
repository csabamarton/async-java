package com.learnjava.completablefuture;

import com.learnjava.service.HelloWorldService;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CompletableFutureHelloWorldTest {

    HelloWorldService helloWorldService;
    CompletableFutureHelloWorld completableFutureHW;

    @BeforeEach
    void setUp() {
        helloWorldService = new HelloWorldService();
        completableFutureHW = new CompletableFutureHelloWorld(helloWorldService);
    }

    @Test
    void helloWorldMethod_convertString_uppercase() {
        completableFutureHW.helloWorld()
                .thenAccept(s -> assertEquals(s, "HELLO WORLD"))
                .join();
    }

    @Test
    void negativetest_helloWorldMethod_convertString_uppercase() {
        completableFutureHW.helloWorld()
                .thenAccept(s -> assertNotEquals(s, "HELLO WORLD1"))
                .join();
    }

}