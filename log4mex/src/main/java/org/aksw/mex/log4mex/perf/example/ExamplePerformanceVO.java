package org.aksw.mex.log4mex.perf.example;

/**
 * Created by esteves on 26.06.15.
 */
public class ExamplePerformanceVO {

    private String _id;
    private String _predicted;
    private String _real;

    public ExamplePerformanceVO(String id, String predictedValue, String realValue) {
        this._id = id;
        this._predicted = predictedValue;
        this._real = realValue;
    }

}
