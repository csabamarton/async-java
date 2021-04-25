package com.learnjava.parallelstreams;

import com.learnjava.util.DataSet;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.learnjava.util.CommonUtil.*;
import static com.learnjava.util.LoggerUtil.log;

public class ParalellStreamsExample {

    public List<String> transformNames(List<String> names, boolean isParallel) {
        Stream<String> nameStream = names.stream();

        setStreamProcess(nameStream, isParallel);
        return transformStream(nameStream);
    }

    public void setStreamProcess(Stream<String> stream, boolean isParallel) {
        if(isParallel)
            stream.parallel();
        else
            stream.sequential();
    }

    public List<String> transformStream(Stream<String> stream) {
        return stream.map(this::addNameLengthTransform)
                .collect(Collectors.toList());
    }



    public static void main(String[] args) {
        List<String> names = DataSet.namesList();
        ParalellStreamsExample paralellStreamsExample = new ParalellStreamsExample();

        startTimer();

        List<String> resultList = paralellStreamsExample.transformNames(names, true);
        log("ResultList: " + resultList);

        timeTaken();
    }

    private String addNameLengthTransform(String name) {
        delay(500);
        return name.length()+" - "+name ;
    }
}
