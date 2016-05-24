package org.aksw.mex.log4mex.core;

import org.aksw.mex.log4mex.InstanceObjects;
import org.aksw.mex.util.MEXEnum;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by esteves on 26.06.15.
 */
public class SamplingMethodVO extends InstanceObjects {

    private String _className = "";
    private Double _trainSize;
    private Double _testSize;
    private Integer _folds;
    private Boolean _sequential;
    private String _label = "";


    public SamplingMethodVO(MEXEnum.EnumSamplingMethods sm) {
        //this._individualName = ind;
        this._className = sm.toString();

    }

    public SamplingMethodVO(MEXEnum.EnumSamplingMethods sm, Double train, Double test) {
        //this._individualName = ind;
        this._className = sm.toString();
        this._trainSize = train;
        this._testSize = test;

    }

    public String getClassName() {
        return _className;
    }

    public String getLabel() {
        return _label;
    }

    public void setLabel(String value) {
        this._label = value;
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


    @Override
    public boolean equals(Object other) {
        if (!(other instanceof SamplingMethodVO)) {
            return false;
        }

        SamplingMethodVO that = (SamplingMethodVO) other;

        boolean ret = this._className.equals(that._className);

        if (this._trainSize != null)
            ret = ret && this._trainSize.equals(that._trainSize);
        if (this._testSize != null)
            ret = ret && this._testSize.equals(that._testSize);
        if (this._folds != null)
            ret = ret && this._folds.equals(that._folds);
        if (this._sequential != null)
            ret = ret && this._sequential.equals(that._sequential);

        ret = ret && this._label.equals(that._label);

        return ret;
    }

    @Override
    public int hashCode() {
        int hashCode = 1;

        if (StringUtils.isNotEmpty(this._className) && StringUtils.isNotBlank(this._className))
            hashCode = hashCode * 37 + this._className.hashCode();

        if (this._trainSize != null)
            hashCode = hashCode * 37 + this._trainSize.hashCode();

        if (this._testSize != null)
            hashCode = hashCode * 37 + this._testSize.hashCode();

        if (this._folds != null)
            hashCode = hashCode * 37 + this._folds.hashCode();

        if (this._sequential != null)
            hashCode = hashCode * 37 + this._sequential.hashCode();

        if (StringUtils.isNotEmpty(this._label) && StringUtils.isNotBlank(this._label))
            hashCode = hashCode * 37 + this._label.hashCode();

        return hashCode;
    }



}
