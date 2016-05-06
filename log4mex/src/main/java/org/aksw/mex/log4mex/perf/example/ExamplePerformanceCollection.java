package org.aksw.mex.log4mex.perf.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by esteves on 26.06.15.
 */
public class ExamplePerformanceCollection {

    private List<ExamplePerformanceMeasureVO> performances;

    public ExamplePerformanceCollection(){
        performances = new ArrayList<>();
    }

    public boolean addExamplePerformance(ExamplePerformanceMeasureVO example){
        return this.performances.add(example);
    }

    //public void addPerformanceExample(String name, double value){
     //   this.performances.add(new )
   // }
}
