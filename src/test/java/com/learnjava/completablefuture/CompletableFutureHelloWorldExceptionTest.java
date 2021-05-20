package com.learnjava.completablefuture;

import com.learnjava.service.HelloWorldService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CompletableFutureHelloWorldExceptionTest {

    @Mock
    HelloWorldService helloWorldService;

    @InjectMocks
    CompletableFutureHelloWorldException cfHelloWorldException;

    @Test
    void helloworld_combine_3_asyn_calls_handle() {
        when(helloWorldService.hello()).thenThrow(new RuntimeException("Exception Occured"));
        when(helloWorldService.world()).thenCallRealMethod();

        String result = cfHelloWorldException.helloworld_combine_3_asyn_calls_handle();

        assertEquals(" WORLD! HI COMPLETABLEFUTURE!", result);

    }

    @Test
    void helloworld_combine_3_asyn_calls_exceptionally() {
        when(helloWorldService.hello()).thenCallRealMethod();
        when(helloWorldService.world()).thenCallRealMethod();

        String result = cfHelloWorldException.helloworld_combine_3_asyn_calls_exceptionally();

        assertEquals("HELLO WORLD! HI COMPLETABLEFUTURE!", result);

    }
}