package org.aksw.mex.log4mex.perf.overall;

/**
 * Created by esteves on 26.06.15.
 */
public class StatisticalMeasureVO extends Measure {

    public Double getPearsonCorrelation() {
        return pearsonCorrelation;
    }

    public void setPearsonCorrelation(Double pearsonCorrelation) {
        this.pearsonCorrelation = pearsonCorrelation;
    }

    public Double getChiSquare() {
        return chiSquare;
    }

    public void setChiSquare(Double chiSquare) {
        this.chiSquare = chiSquare;
    }

    public Double getError() {
        return error;
    }

    public void setError(Double error) {
        this.error = error;
    }

    public Double getKolmogorovSmirnov() {
        return kolmogorovSmirnov;
    }

    public void setKolmogorovSmirnov(Double kolmogorovSmirnov) {
        this.kolmogorovSmirnov = kolmogorovSmirnov;
    }

    public Double getMean() {
        return mean;
    }

    public void setMean(Double mean) {
        this.mean = mean;
    }

    public Double getNemenyi() {
        return nemenyi;
    }

    public void setNemenyi(Double nemenyi) {
        this.nemenyi = nemenyi;
    }

    public Double getStandardDeviation() {
        return standardDeviation;
    }

    public void setStandardDeviation(Double standardDeviation) {
        this.standardDeviation = standardDeviation;
    }

    public Double getWilcoxon() {
        return wilcoxon;
    }

    public void setWilcoxon(Double wilcoxon) {
        this.wilcoxon = wilcoxon;
    }

    public Double getVariance() {
        return variance;
    }

    public void setVariance(Double variance) {
        this.variance = variance;
    }

    public Double getFriedman() {
        return friedman;
    }

    public void setFriedman(Double friedman) {
        this.friedman = friedman;
    }

    public Double getMedian() {
        return median;
    }

    public void setMedian(Double median) {
        this.median = median;
    }

    public Double getKappaStatistics() {
        return kappaStatistics;
    }

    public void setKappaStatistics(Double kappaStatistics) {
        this.kappaStatistics = kappaStatistics;
    }

    public Double getMode() {
        return mode;
    }

    public void setMode(Double mode) {
        this.mode = mode;
    }

    public Double getL2norm() {
        return L2norm;
    }

    public void setL2norm(Double l2norm) {
        L2norm = l2norm;
    }

    public Double getL1norm() {
        return L1norm;
    }

    public void setL1norm(Double l1norm) {
        L1norm = l1norm;
    }

    public Double getLinfnorm() {
        return Linfnorm;
    }

    public void setLinfnorm(Double linfnorm) {
        Linfnorm = linfnorm;
    }

    private Double pearsonCorrelation;
    private Double chiSquare;
    private Double error;
    private Double kolmogorovSmirnov;
    private Double mean;
    private Double nemenyi;
    private Double standardDeviation;
    private Double wilcoxon;
    private Double variance;
    private Double friedman;
    private Double median;
    private Double kappaStatistics;
    private Double mode;
    private Double L2norm;
    private Double L1norm;
    private Double Linfnorm;

    public StatisticalMeasureVO(){

    }

    @Override
    public String getLabel(){
        return "Statistical measures";
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
