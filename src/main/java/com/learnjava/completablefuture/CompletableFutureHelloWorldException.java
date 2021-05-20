package com.learnjava.completablefuture;

import com.learnjava.service.HelloWorldService;

import java.util.concurrent.CompletableFuture;

import static com.learnjava.util.CommonUtil.*;
import static com.learnjava.util.LoggerUtil.log;

public class CompletableFutureHelloWorldException {

    HelloWorldService hws;

    public CompletableFutureHelloWorldException(HelloWorldService hws) {
        this.hws = hws;
    }

    public String helloworld_combine_3_asyn_calls_handle() {
        startTimer();

        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> hws.hello());
        CompletableFuture<String> world = CompletableFuture.supplyAsync(() -> hws.world());
        CompletableFuture<String> hiCompletableFuture = CompletableFuture.supplyAsync(() -> {
            delay(1000);
            return " Hi CompletableFuture!";
        });

        String helloworld = hello
                .handle((res, ex) -> {
                    log("Exception is: " + ex.getMessage());
                    return "";
                })
                .thenCombine(world, (h, w) -> h + w)
                .thenCombine(hiCompletableFuture, (previous, current)->previous+current)
                .thenApply(String::toUpperCase)
                .join();

        timeTaken();

        return helloworld;
    }
}
