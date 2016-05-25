package org.aksw.mex.log4mex.core;

/**
 * Created by esteves on 26.06.15.
 */
public class SamplingMethodVO {

    public String getIndividualName() {
        return _individualName;
    }

    public String getClassName() {
        return _className;
    }

    public Double getTrainSize() {
        return _trainSize;
    }

    public Double getTestSize() {
        return _testSize;
    }

    public Integer getFolds() {
        return _folds;
    }

    public Boolean getSequential() {
        return _sequential;
    }

    private String _individualName;
    private String _className;
    private Double _trainSize;
    private Double _testSize;
    private Integer _folds;
    private Boolean _sequential;

    public SamplingMethodVO(String ind, String classname) {
        this._individualName = ind;
        this._className = classname;
    }

    public SamplingMethodVO(String ind, String classname, Double train, Double test) {
        this._individualName = ind;
        this._className = classname;
        this._trainSize = train;
        this._testSize = test;
    }

    public void setClassName(String value){
        this._className = value;
    }

    public void setIndividualName(String value){
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
