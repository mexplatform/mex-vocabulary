package org.aksw.mex.log4mex.core;

import org.aksw.mex.util.MEXEnum;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by esteves on 26.06.15.
 */
public class SamplingMethodVO {


    private String _individualName;
    private String _className;
    private Double _trainSize;
    private Double _testSize;
    private Integer _folds;
    private Boolean _sequential;


    public SamplingMethodVO(String ind, MEXEnum.EnumSamplingMethods sm) {
        this._individualName = ind;
        this._className = sm.toString();
    }

    public SamplingMethodVO(String ind, MEXEnum.EnumSamplingMethods sm, Double train, Double test) {
        this._individualName = ind;
        this._className = sm.toString();
        this._trainSize = train;
        this._testSize = test;
    }

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

    public void setClassName(MEXEnum.EnumSamplingMethods value){
        this._className = value.toString();
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


    /**
     * must have class name + train and test size
     * @return
     */
    public boolean hasValue(){

        if ((this._className != null && !StringUtils.isBlank(this._className) && !StringUtils.isEmpty(this._className)) &&
            (this._trainSize != null && !StringUtils.isBlank(this._trainSize.toString()) && !StringUtils.isEmpty(this._trainSize.toString())) &&
                (this._testSize != null && !StringUtils.isBlank(this._testSize.toString()) && !StringUtils.isEmpty(this._testSize.toString()))){
            return true;
        }else{
            return false;
        }

    }

}
