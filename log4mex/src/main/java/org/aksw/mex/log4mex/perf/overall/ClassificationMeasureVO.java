package org.aksw.mex.log4mex.perf.overall;

/**
 * Created by esteves on 26.06.15.
 */
public class ClassificationMeasureVO extends Measure {

    private Double _accuracy;
    private Double _fMeasure;
    private Double _precision;
    private Double _recall;
    private Double _roc;
    private Double _sensitivity;
    private Double _specificity;
    private Integer _trueNegative;
    private Integer _truePositive;
    private Integer _falseNegative;
    private Integer _falsePositive;
    private Double _falseNegativeRate;
    private Double _falsePositiveRate;
    private Double _trueNegativeRate;
    private Double _truePositiveRate;

    private String _name;
    private Double _value;

    public ClassificationMeasureVO(){
    }

    @Override
    public String getLabel(){
        return "Classification measures";
    }

    @Override
    public boolean equals(Object other) {
        return false;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    public void set_fMeasure(Double _fMeasure) {
        this._fMeasure = _fMeasure;
    }

    public void set_precision(Double _precision) {
        this._precision = _precision;
    }

    public void set_recall(Double _recall) {
        this._recall = _recall;
    }

    public void set_roc(Double _roc) {
        this._roc = _roc;
    }

    public void set_sensitivity(Double _sensitivity) {
        this._sensitivity = _sensitivity;
    }

    public void set_specificity(Double _specificity) {
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

    public void set_falseNegativeRate(Double _falseNegativeRate) {
        this._falseNegativeRate = _falseNegativeRate;
    }

    public void set_falsePositiveRate(Double _falsePositiveRate) {
        this._falsePositiveRate = _falsePositiveRate;
    }

    public void set_trueNegativeRate(Double _trueNegativeRate) {
        this._trueNegativeRate = _trueNegativeRate;
    }

    public void set_truePositiveRate(Double _truePositiveRate) {
        this._truePositiveRate = _truePositiveRate;
    }


    public void setAccuracy(Double value){
        this._accuracy = value;
    }

    public void set_accuracy(Double _accuracy) {
        this._accuracy = _accuracy;
    }

}
