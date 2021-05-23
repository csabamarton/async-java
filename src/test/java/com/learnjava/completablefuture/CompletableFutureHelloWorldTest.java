package com.learnjava.completablefuture;

import com.learnjava.service.HelloWorldService;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static com.learnjava.util.CommonUtil.startTimer;
import static com.learnjava.util.CommonUtil.timeTaken;
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

    @Test
    void helloWorld_withSize() throws ExecutionException, InterruptedException {
       completableFutureHW.helloWorld_withSize("Alma")
       .thenAccept(s -> assertEquals("4 - Alma", s))
       .join();
    }

    @Test
    void helloworld_muiltiple_asyn_calls() {
        String helloworld = completableFutureHW.helloworld_muiltiple_asyn_calls();

        assertEquals("HELLO WORLD!", helloworld);
    }

    @Test
    void helloworld_combine_3_asyn_calls() {
        String helloworld = completableFutureHW.helloworld_combine_3_asyn_calls();

        assertEquals("HELLO WORLD! HI COMPLETABLEFUTURE!", helloworld);
    }

    @Test
    void helloworld_combine_3_asyn_calls_async() {
        String helloworld = completableFutureHW.helloworld_combine_3_asyn_calls_async();

        assertEquals("HELLO WORLD! HI COMPLETABLEFUTURE!", helloworld);
    }

    @Test
    void helloWorld_4_async_calls() {
        String helloworld = completableFutureHW.helloWorld_4_async_calls();

        assertEquals("HELLO WORLD! HI COMPLETABLEFUTURE! BYE!", helloworld);
    }

    @Test
    void helloWorld_compose_async_calls() {
        startTimer();

        CompletableFuture<String> completableFuture = completableFutureHW.helloWorld_thenCompose();
        completableFuture.thenAccept(
                s -> assertEquals("HELLO WORLD!", s)
        )
        .join();

        timeTaken();
    }

    @Test
    void helloworld_combine_3_asyn_calls_custom_threadpool() {
        String helloWorld = completableFutureHW.helloworld_combine_3_asyn_calls_custom_threadpool();

        assertEquals("HELLO WORLD! HI COMPLETABLEFUTURE!", helloWorld);
    }
}