package org.aksw.mex.perf.overall;

import org.aksw.mex.perf.IPerformance;

/**
 * Created by esteves on 26.06.15.
 */
public class ClassificationMeasureVO implements IPerformance {

    private double _accuracy;
    private double _fMeasure;
    private double _precision;
    private double _recall;
    private double _roc;
    private double _sensitivity;
    private double _specificity;
    private Integer _trueNegative;
    private Integer _truePositive;
    private Integer _falseNegative;
    private Integer _falsePositive;
    private double _falseNegativeRate;
    private double _falsePositiveRate;
    private double _trueNegativeRate;
    private double _truePositiveRate;


    public ClassificationMeasureVO(){

    }

    public void set_fMeasure(double _fMeasure) {
        this._fMeasure = _fMeasure;
    }

    public void set_precision(double _precision) {
        this._precision = _precision;
    }

    public void set_recall(double _recall) {
        this._recall = _recall;
    }

    public void set_roc(double _roc) {
        this._roc = _roc;
    }

    public void set_sensitivity(double _sensitivity) {
        this._sensitivity = _sensitivity;
    }

    public void set_specificity(double _specificity) {
        this._specificity = _specificity;
    }

    public void set_trueNegative(Integer _trueNegative) {
        this._trueNegative = _trueNegative;
    }

    public void set_truePositive(Integer _truePositive) {
        this._truePositive = _truePositive;
    }

    public void set_falseNegative(Integer _falseNegative) {
        this._falseNegative = _falseNegative;
    }

    public void set_falsePositive(Integer _falsePositive) {
        this._falsePositive = _falsePositive;
    }

    public void set_falseNegativeRate(double _falseNegativeRate) {
        this._falseNegativeRate = _falseNegativeRate;
    }

    public void set_falsePositiveRate(double _falsePositiveRate) {
        this._falsePositiveRate = _falsePositiveRate;
    }

    public void set_trueNegativeRate(double _trueNegativeRate) {
        this._trueNegativeRate = _trueNegativeRate;
    }

    public void set_truePositiveRate(double _truePositiveRate) {
        this._truePositiveRate = _truePositiveRate;
    }


    public void setAccuracy(double value){
        this._accuracy = value;
    }

    public void set_accuracy(double _accuracy) {
        this._accuracy = _accuracy;
    }

}
