package org.aksw.mex.perf.example;

import org.aksw.mex.perf.IPerformance;

/**
 * Created by root on 26.06.15.
 */
public class ExamplePerformanceVO implements IPerformance {

    private String _id;
    private String _predicted;
    private String _real;

    public ExamplePerformanceVO(String id, String predictedValue, String realValue) {
        this._id = id;
        this._predicted = predictedValue;
        this._real = realValue;
    }

}
