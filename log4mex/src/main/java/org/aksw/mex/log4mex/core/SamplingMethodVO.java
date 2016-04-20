package org.aksw.mex.log4mex.core;

import org.aksw.mex.util.MEXEnum;

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

    public SamplingMethodVO(String ind, MEXEnum.EnumSamplingMethods sm) {
        this._individualName = ind;
        this._className = sm.name();
    }

    public SamplingMethodVO(String ind, MEXEnum.EnumSamplingMethods sm, Double train, Double test) {
        this._individualName = ind;
        this._className = sm.name();
        this._trainSize = train;
        this._testSize = test;
    }

    public void setClassName(MEXEnum.EnumSamplingMethods value){
        this._className = value.name();
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
