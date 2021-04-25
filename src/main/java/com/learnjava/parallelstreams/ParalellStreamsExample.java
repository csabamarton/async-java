package com.learnjava.parallelstreams;

import com.learnjava.util.DataSet;

import java.util.List;
import java.util.stream.Collectors;

import static com.learnjava.util.CommonUtil.*;
import static com.learnjava.util.LoggerUtil.log;

public class ParalellStreamsExample {

    public List<String> transformNames(List<String> names) {
        return names
                .parallelStream()
                .map(this::addNameLengthTransform)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<String> names = DataSet.namesList();
        ParalellStreamsExample paralellStreamsExample = new ParalellStreamsExample();

        startTimer();

        List<String> resultList = paralellStreamsExample.transformNames(names);
        log("ResultList: " + resultList);

        timeTaken();
    }

    private String addNameLengthTransform(String name) {
        delay(500);
        return name.length()+" - "+name ;
    }
}
