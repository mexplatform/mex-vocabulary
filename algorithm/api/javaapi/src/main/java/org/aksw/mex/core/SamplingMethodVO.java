package org.aksw.mex.core;

/**
 * Created by esteves on 26.06.15.
 */
public class SamplingMethodVO {

    private String _individualName;
    private Double _trainSize;
    private Double _testSize;
    private Integer _folds;
    private Boolean _sequential;

    public SamplingMethodVO(String value) {
        this._individualName = value.toString();
    }

    public void setName(String value){
        this._individualName = value;
    }

    public void setTrainSize(Double value){
        this._trainSize = value;
    }
    public void setTestSize(Double value){
        this._testSize = value;
    }
    public void setFolds(Integer value){
        this._folds = value;
    }
    public void setSequential(Boolean value){
        this._sequential = value;
    }


}
