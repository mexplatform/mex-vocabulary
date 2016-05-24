package org.aksw.mex.log4mex.core;

import org.aksw.mex.log4mex.InstanceObjects;

import java.util.Date;

/**
 * Created by esteves on 25.06.15.
 */
public class ExperimentVO extends InstanceObjects {

    private String _id;
    private Date _date;
    private String _description;
    private String _dataNormalized;
    private String _outliersRemoved;
    private String _noiseRemoved;
    private String _attributeSelection;

    public String getId() {
        return _id;
    }

    public Date getDate() {
        return _date;
    }

    public String getDescription() {
        return _description;
    }

    public String getDataNormalizedDescription() {
        return _dataNormalized;
    }

    public String getOutliersRemovedDescription() {
        return _outliersRemoved;
    }

    public String getNoiseRemovedDescription() {
        return _noiseRemoved;
    }

    public String getAttributeSelectionDescription() {
        return _attributeSelection;
    }

    public String getTitle() {
        return _title;
    }

    public ApplicationContextVO get_applicationContext() {
        return _applicationContext;
    }

    public void setTitle(String _title) {
        this._title = _title;
    }

    private String _title;

    private ApplicationContextVO _applicationContext;

    public ExperimentVO(String id, ApplicationContextVO app) {
        this._id=id;
        this._applicationContext = app;
        this._date = new Date();
    }

    public ExperimentVO(String id) {
        this._id = id;
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

    @Override
    public boolean equals(Object other) {
        return false;
    }

    @Override
    public int hashCode() {
        return 0;
    }


}
