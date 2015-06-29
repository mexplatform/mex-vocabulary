package org.aksw.mex.perf.example;

import org.aksw.mex.perf.IPerformance;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by esteves on 26.06.15.
 */
public class ExamplePerformanceCollection implements IPerformance {

    private List<ExamplePerformanceVO> performances;

    public ExamplePerformanceCollection(){
        performances = new ArrayList<>();
    }

    public boolean addExamplePerformance(ExamplePerformanceVO example){
        return this.performances.add(example);
    }

    public void addPerformanceExample(String name, double value){
        this.performances.add(new )
    }
}
