package org.aksw.mex.log4mex.perf.overall;

/**
 * Created by esteves on 26.06.15.
 */
public class RegressionMeasureVO extends Measure {

    public void setMeanAbsoluteDeviation(Double _meanAbsoluteDeviation) {
        this._meanAbsoluteDeviation = _meanAbsoluteDeviation;
    }

    public void setMeanSquareError(Double _meanSquareError) {
        this._meanSquareError = _meanSquareError;
    }

    public void setResidual(Double _residual) {
        this._residual = _residual;
    }

    public void setTotalError(Double _totalError) {
        this._totalError = _totalError;
    }

    public void setMedianAbsoluteDeviation(Double _medianAbsoluteDeviation) {
        this._medianAbsoluteDeviation = _medianAbsoluteDeviation;
    }

    public void setRelativeAbsoluteError(Double _relativeAbsoluteError) {
        this._relativeAbsoluteError = _relativeAbsoluteError;
    }

    public void setRootRelativeSquaredError(Double _rootRelativeSquaredError) {
        this._rootRelativeSquaredError = _rootRelativeSquaredError;
    }

    public void setRootMeanSquaredError(Double _rootMeanSquaredError) {
        this._rootMeanSquaredError = _rootMeanSquaredError;
    }

    public void setCorrelationCoefficient(Double _correlationCoefficient) {
        this._correlationCoefficient = _correlationCoefficient;
    }


    private Double _meanAbsoluteDeviation;
    private Double _meanSquareError;
    private Double _residual;
    private Double _totalError;
    private Double _medianAbsoluteDeviation;
    private Double _relativeAbsoluteError;
    private Double _rootRelativeSquaredError;
    private Double _rootMeanSquaredError;
    private Double _correlationCoefficient;


    public RegressionMeasureVO(){

    }

    @Override
    public String getLabel(){
        return "Regression measures";
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
