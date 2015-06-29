package org.aksw.mex.perf.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by esteves on 26.06.15.
 */
public class ExamplePerformanceCollection {

    private List<ExamplePerformanceVO> examples;

    public ExamplePerformanceCollection(){
        examples = new ArrayList<>();
    }

    public boolean addExamplePerformance(ExamplePerformanceVO example){
        return this.examples.add(example);
    }
}
