package com.learnjava.parallelstreams;

import com.learnjava.util.DataSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.learnjava.util.CommonUtil.startTimer;
import static com.learnjava.util.CommonUtil.timeTaken;
import static org.junit.jupiter.api.Assertions.*;

class ParalellStreamsExampleTest {

    ParalellStreamsExample paralellStreamsExample;
    List<String> names;

    @BeforeEach
    void setUp() {
        names = DataSet.namesList();
        paralellStreamsExample = new ParalellStreamsExample();
    }

    @Test
    void transformNames() {
        startTimer();
        List<String> resultList = paralellStreamsExample.transformNames(names);
        timeTaken();

        assertNotNull(resultList);
        assertEquals(resultList.size(), names.size());
        resultList.forEach(item -> assertTrue(item.contains(" - ")));
    }
}