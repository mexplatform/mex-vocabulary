package org.aksw.mex.core;

import java.util.Date;

/**
 * Created by root on 25.06.15.
 */
public class ExperimentVO {

    private String _id;
    private Date _date;
    private String _description;
    private String _dataNormalized;
    private String _outliersRemoved;
    private String _noiseRemoved;
    private String _attributeSelection;

    private ApplicationContextVO _applicationContext;

    public ExperimentVO(String id, ApplicationContextVO app) {
        this._id=id;
        this._applicationContext = app;
        this._date = new Date();
    }

    public ExperimentVO() {

    }

    public void setApplicationContext(ApplicationContextVO value){
        this._applicationContext = value;
    }

    public void setId(String value){
        this._id= value;
    }
    public void setDate(Date value){
        this._date = value;
    }
    public void setDescription(String value){
        this._description = value;
    }
    public void setDataNormalizationDescription(String value){
        this._dataNormalized = value;
    }
    public void setOutlierDetectionDescription(String value){
        this._outliersRemoved = value;
    }
    public void setNoiseRemovalDescription(String value){
        this._noiseRemoved = value;
    }
    public void setAttributeSelectionDescription(String value){
        this._attributeSelection = value;
    }

}
