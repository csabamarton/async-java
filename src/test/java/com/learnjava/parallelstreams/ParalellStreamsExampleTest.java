package com.learnjava.parallelstreams;

import com.learnjava.util.DataSet;
import org.junit.After;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Stream;

import static com.learnjava.util.CommonUtil.*;
import static org.junit.jupiter.api.Assertions.*;

class ParalellStreamsExampleTest {

    ParalellStreamsExample paralellStreamsExample;
    List<String> names;

    @BeforeEach
    void setUp() {
        stopWatchReset();
        names = DataSet.namesList();
        paralellStreamsExample = new ParalellStreamsExample();
    }

    @ParameterizedTest
    @ValueSource(booleans = {false, true})
    void transformNames(boolean isParallel) {
        startTimer();
        List<String> resultList = paralellStreamsExample.transformNames(names, isParallel);
        timeTaken();

        assertNotNull(resultList);
        assertEquals(resultList.size(), names.size());
        resultList.forEach(item -> assertTrue(item.contains(" - ")));
    }

    @ParameterizedTest
    @ValueSource(booleans = {false, true})
    void setStreamProcess(boolean isParallel) {
        Stream<String> nameStream = names.stream();

        paralellStreamsExample.setStreamProcess(nameStream, isParallel);
        assertEquals(nameStream.isParallel(), isParallel);
    }
}