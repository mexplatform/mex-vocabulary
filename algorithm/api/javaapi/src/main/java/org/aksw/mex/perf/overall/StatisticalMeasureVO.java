package org.aksw.mex.perf.overall;

/**
 * Created by esteves on 26.06.15.
 */
public class StatisticalMeasureVO {

    public double getPearsonCorrelation() {
        return pearsonCorrelation;
    }

    public void setPearsonCorrelation(double pearsonCorrelation) {
        this.pearsonCorrelation = pearsonCorrelation;
    }

    public double getChiSquare() {
        return chiSquare;
    }

    public void setChiSquare(double chiSquare) {
        this.chiSquare = chiSquare;
    }

    public double getError() {
        return error;
    }

    public void setError(double error) {
        this.error = error;
    }

    public double getKolmogorovSmirnov() {
        return kolmogorovSmirnov;
    }

    public void setKolmogorovSmirnov(double kolmogorovSmirnov) {
        this.kolmogorovSmirnov = kolmogorovSmirnov;
    }

    public double getMean() {
        return mean;
    }

    public void setMean(double mean) {
        this.mean = mean;
    }

    public double getNemenyi() {
        return nemenyi;
    }

    public void setNemenyi(double nemenyi) {
        this.nemenyi = nemenyi;
    }

    public double getStandardDeviation() {
        return standardDeviation;
    }

    public void setStandardDeviation(double standardDeviation) {
        this.standardDeviation = standardDeviation;
    }

    public double getWilcoxon() {
        return wilcoxon;
    }

    public void setWilcoxon(double wilcoxon) {
        this.wilcoxon = wilcoxon;
    }

    public double getVariance() {
        return variance;
    }

    public void setVariance(double variance) {
        this.variance = variance;
    }

    public double getFriedman() {
        return friedman;
    }

    public void setFriedman(double friedman) {
        this.friedman = friedman;
    }

    public double getMedian() {
        return median;
    }

    public void setMedian(double median) {
        this.median = median;
    }

    public double getKappaStatistics() {
        return kappaStatistics;
    }

    public void setKappaStatistics(double kappaStatistics) {
        this.kappaStatistics = kappaStatistics;
    }

    public double getMode() {
        return mode;
    }

    public void setMode(double mode) {
        this.mode = mode;
    }

    public double getL2norm() {
        return L2norm;
    }

    public void setL2norm(double l2norm) {
        L2norm = l2norm;
    }

    public double getL1norm() {
        return L1norm;
    }

    public void setL1norm(double l1norm) {
        L1norm = l1norm;
    }

    public double getLinfnorm() {
        return Linfnorm;
    }

    public void setLinfnorm(double linfnorm) {
        Linfnorm = linfnorm;
    }

    private double pearsonCorrelation;
    private double chiSquare;
    private double error;
    private double kolmogorovSmirnov;
    private double mean;
    private double nemenyi;
    private double standardDeviation;
    private double wilcoxon;
    private double variance;
    private double friedman;
    private double median;
    private double kappaStatistics;
    private double mode;
    private double L2norm;
    private double L1norm;
    private double Linfnorm;

    public StatisticalMeasureVO(){

    }


}
