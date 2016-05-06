package org.aksw.mex.log4mex.perf.example;

import org.aksw.mex.log4mex.perf.overall.Measure;

/**
 * Created by esteves on 26.06.15.
 */
public class ExamplePerformanceMeasureVO extends Measure {

    private String _id;
    private String _predicted;
    private String _real;

    public ExamplePerformanceMeasureVO(String id, String predictedValue, String realValue) {
        this._id = id;
        this._predicted = predictedValue;
        this._real = realValue;
    }

    @Override
    public String getLabel(){
        return "Performance measure for a single run (predicted x real)";
    }

}
