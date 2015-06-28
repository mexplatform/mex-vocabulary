package org.aksw.mex.perf.overall;

/**
 * Created by root on 26.06.15.
 */
public class RegressionMeasureVO {

    public void setMeanAbsoluteDeviation(double _meanAbsoluteDeviation) {
        this._meanAbsoluteDeviation = _meanAbsoluteDeviation;
    }

    public void setMeanSquareError(double _meanSquareError) {
        this._meanSquareError = _meanSquareError;
    }

    public void setResidual(double _residual) {
        this._residual = _residual;
    }

    public void setTotalError(double _totalError) {
        this._totalError = _totalError;
    }

    public void setMedianAbsoluteDeviation(double _medianAbsoluteDeviation) {
        this._medianAbsoluteDeviation = _medianAbsoluteDeviation;
    }

    public void setRelativeAbsoluteError(double _relativeAbsoluteError) {
        this._relativeAbsoluteError = _relativeAbsoluteError;
    }

    public void setRootRelativeSquaredError(double _rootRelativeSquaredError) {
        this._rootRelativeSquaredError = _rootRelativeSquaredError;
    }

    public void setRootMeanSquaredError(double _rootMeanSquaredError) {
        this._rootMeanSquaredError = _rootMeanSquaredError;
    }

    public void setCorrelationCoefficient(double _correlationCoefficient) {
        this._correlationCoefficient = _correlationCoefficient;
    }

    private double _meanAbsoluteDeviation;
    private double _meanSquareError;
    private double _residual;
    private double _totalError;
    private double _medianAbsoluteDeviation;
    private double _relativeAbsoluteError;
    private double _rootRelativeSquaredError;
    private double _rootMeanSquaredError;
    private double _correlationCoefficient;


    public RegressionMeasureVO(){

    }
}
