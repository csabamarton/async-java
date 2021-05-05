package com.learnjava.completablefuture;

import com.learnjava.service.HelloWorldService;

import java.util.concurrent.CompletableFuture;

import static com.learnjava.util.CommonUtil.delay;
import static com.learnjava.util.LoggerUtil.log;

public class CompletableFutureHelloWorld {

    HelloWorldService helloWorldService;

    public CompletableFutureHelloWorld(HelloWorldService helloWorldService) {
        this.helloWorldService = helloWorldService;
    }

    public CompletableFuture<String> helloWorld() {
        return CompletableFuture.supplyAsync(helloWorldService::helloWorld)
                .thenApply(String::toUpperCase);
    }

    public CompletableFuture<String> helloWorld_withSize(String input) {
        return CompletableFuture.supplyAsync(()-> input)
                .thenApply((s) -> s.length() + " - " + s);
    }

    public static void main(String[] args) {
        CompletableFutureHelloWorld completableFutureHelloWorld = new CompletableFutureHelloWorld(new HelloWorldService());

        CompletableFuture<String> helloWorldFuture = completableFutureHelloWorld.helloWorld();
        helloWorldFuture.thenAccept((result)-> log("Result is " + result))
                .join();
        log("Done");
        delay(2000);
    }
}
