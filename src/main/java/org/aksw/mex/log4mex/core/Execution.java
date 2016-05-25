package org.aksw.mex.log4mex.core;

import com.google.common.collect.Collections2;
import com.google.common.collect.Iterables;
import org.aksw.mex.log4mex.algo.AlgorithmVO;
import org.aksw.mex.log4mex.perf.overall.*;
import org.aksw.mex.util.MEXEnum;
import org.apache.commons.lang3.EnumUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by esteves on 26.06.15.
 */
public abstract class Execution {

    protected String _id;
    protected Boolean _grouped;
    protected Date _startedAtTime;
    protected Date _endedAtTime;
    protected ExperimentConfigurationVO _expConf;
    protected PhaseVO _phase;
    protected AlgorithmVO _algo;
    //protected ExampleCollection _exampleCollection;
    protected List<ExampleVO> _examples;
    protected List<Measure> _performances;


    public Execution(){
        this._performances = new ArrayList<>();
        this._examples = new ArrayList();
    }

    public List<ExampleVO> getExamples(){
        return this._examples;
    }

    /*public ExampleCollection getExampleCollection(){
        return this._exampleCollection;
    }
    */

    public String getId() {
        return _id;
    }
    public void set_id(String _id) {
        this._id = _id;
    }
    public Boolean getGrouped() {
        return _grouped;
    }
    public void set_grouped(Boolean _grouped) {
        this._grouped = _grouped;
    }
    public Date getStartedAtTime() {
        return _startedAtTime;
    }
    public void set_startedAtTime(Date _startedAtTime) {
        this._startedAtTime = _startedAtTime;
    }
    public Date getEndedAtTime() {
        return _endedAtTime;
    }
    public void set_endedAtTime(Date _endedAtTime) {
        this._endedAtTime = _endedAtTime;
    }
    public ExperimentConfigurationVO get_expConf() {
        return _expConf;
    }

    public void set_expConf(ExperimentConfigurationVO _expConf) {
        this._expConf = _expConf;
    }
    public PhaseVO getPhase() {
        return _phase;
    }
    public void set_phase(PhaseVO _phase) {
        this._phase = _phase;
    }
    public AlgorithmVO getAlgorithm() {
        return _algo;
    }
    public void set_algo(AlgorithmVO _algo) {
        this._algo = _algo;
    }
    public void setId(String value){
        this._id = value;
    }
    public void setStartDate(Date value){
        this._startedAtTime = value;
    }
    public void setEndDate(Date value){
        this._endedAtTime = value;
    }
    public void setExperimentConfiguration(ExperimentConfigurationVO value){
        this._expConf = value;
    }
    public void setAlgorithm(AlgorithmVO value){
        this._algo = value;
    }
    public boolean setAlgorithm(String id) throws Exception{
        try{
            //check whether the algorithm exists into the experiment configuration
            Collection<AlgorithmVO> t
                    = Collections2.filter(this.get_expConf().getAlgorithms(id), p -> p instanceof AlgorithmVO);
            if (t != null && t.size() > 0){
                this._algo = Iterables.get(t, 0);
            }else{
                throw new Exception("The algorithm (id=" + id + ") does not exists for the experiment");
            }

        }catch (Exception e){
            throw new Exception(e);
        }
        return true;
    }
    public void setPhase(PhaseVO value){
        this._phase = value;
    }
    public boolean addPerformance(String p, double v){
        String type = "";
        boolean ret = false;

            try{
                type = "cla";
                if (EnumUtils.isValidEnum(MEXEnum.EnumClassificationMeasure.class, p.toUpperCase()) == false){
                    type = "reg";
                    if (EnumUtils.isValidEnum(MEXEnum.EnumRegressionMeasure.class, p.toUpperCase()) == false){
                        type = "sta";
                        if (EnumUtils.isValidEnum(MEXEnum.EnumStatisticalMeasure.class, p.toUpperCase()) == false){
                            type = "clu";
                            if (EnumUtils.isValidEnum(MEXEnum.EnumClusteringMeasure.class, p.toUpperCase()) == false){
                                return false;}
                        }
                    }
                }


                switch (type) {
                    case "cla":
                        ret = addClassificationPerformance(p,v);
                        break;
                        /*
                        ClassificationMeasureVO mc = new ClassificationMeasureVO();
                        if (p.equals("accuracy")){
                            mc.set_accuracy(v);
                        }else if (p.equals("fMeasure")) {
                            mc.set_fMeasure(v);
                        }else if (p.equals("precision")) {
                            mc.set_precision(v);
                        }else if (p.equals("recall")) {
                            mc.set_recall(v);
                        }else if (p.equals("roc")) {
                            mc.set_roc(v);
                        }else if (p.equals("sensitivity")) {
                            mc.set_sensitivity(v);
                        }else if (p.equals("specificity")) {
                            mc.set_specificity(v);
                        }else if (p.equals("trueNegative")) {
                            mc.set_trueNegative((int)v);
                        }else if (p.equals("truePositive")) {
                            mc.set_truePositive((int)v);
                        }else if (p.equals("falseNegative")) {
                            mc.set_falseNegative((int)v);
                        }else if (p.equals("falsePositive")) {
                            mc.set_falsePositive((int)v);
                        }else if (p.equals("falseNegativeRate")) {
                            mc.set_falseNegativeRate(v);
                        }else if (p.equals("falsePositiveRate")) {
                            mc.set_falsePositiveRate(v);
                        }else if (p.equals("trueNegativeRate")) {
                            mc.set_trueNegativeRate(v);
                        }else if (p.equals("truePositiveRate")) {
                            mc.set_truePositiveRate(v);
                        }else{
                            throw new Exception("sorry, the measure has not been found!");
                        }
                        this._performances.add(mc);
                        */


                    case "reg":

                       /* RegressionMeasureVO mr = new RegressionMeasureVO();
                        if (p.equals("meanAbsoluteDeviation")){
                            mr.setMeanAbsoluteDeviation(v);
                        }else if (p.equals("meanSquareError")) {
                            mr.setMeanSquareError(v);
                        }else if (p.equals("residual")) {
                            mr.setResidual(v);
                        }else if (p.equals("totalError")) {
                            mr.setTotalError(v);
                        }else if (p.equals("relativeAbsoluteError")) {
                            mr.setRelativeAbsoluteError(v);
                        }else if (p.equals("rootRelativeSquaredError")) {
                            mr.setRootRelativeSquaredError(v);
                        }else if (p.equals("rootMeanSquaredError")) {
                            mr.setRootMeanSquaredError(v);
                        }else if (p.equals("correlationCoefficient")) {
                            mr.setCorrelationCoefficient(v);
                        }else{
                            throw new Exception("measure has not been found");
                        }
                        this._performances.add(mr);
                        */

                        ret =  addRegressionPerformance(p,v);
                        break;
                    case "sta":

                        /*StatisticalMeasureVO ms = new StatisticalMeasureVO();

                        if (p.equals("pearsonCorrelation")){
                            ms.setPearsonCorrelation(v);
                        }else if (p.equals("chiSquare")) {
                            ms.setChiSquare(v);
                        }else if (p.equals("error")) {
                            ms.setError(v);
                        }else if (p.equals("kolmogorovSmirnov")) {
                            ms.setKolmogorovSmirnov(v);
                        }else if (p.equals("mean")) {
                            ms.setMean(v);
                        }else if (p.equals("nemenyi")) {
                            ms.setNemenyi(v);
                        }else if (p.equals("standardDeviation")) {
                            ms.setStandardDeviation(v);
                        }else if (p.equals("wilcoxon")) {
                            ms.setWilcoxon(v);
                        }else if (p.equals("variance")) {
                            ms.setVariance(v);
                        }else if (p.equals("friedman")) {
                            ms.setFriedman(v);
                        }else if (p.equals("median")) {
                            ms.setMedian(v);
                        }else if (p.equals("kappaStatistics")) {
                            ms.setKappaStatistics(v);
                        }else if (p.equals("mode")) {
                            ms.setMode(v);
                        }else if (p.equals("L2norm")) {
                            ms.setL2norm(v);
                        }else if (p.equals("L1norm")) {
                            ms.setL1norm(v);
                        }else if (p.equals("Linfnorm")) {
                            ms.setLinfnorm(v);
                        }else{
                            throw new Exception("the measure has not been found");
                        }

                        this._performances.add(ms);*/


                        ret = addStatisticalPerformance(p,v);
                        break;
                    case "clu":

                       /* ClusteringMeasureVO mcl = new ClusteringMeasureVO();

                        if (p.equals("chebyschevDistance")){
                            mcl.set_chebyschevDistance(v);
                        }else if (p.equals("hammingDistance")) {
                            mcl.set_hammingDistance(v);
                        }else if (p.equals("euclideanDistance")) {
                            mcl.set_euclideanDistance(v);
                        }else if (p.equals("manhattanDistance")) {
                            mcl.set_manhattanDistance(v);
                        }else if (p.equals("genSimilarityCoefficient")) {
                            mcl.set_genSimilarityCoerfficient(v);
                        }else{
                            throw new Exception("the measure has not been found");
                        }

                        this._performances.add(mcl);*/

                        ret = addClusteringPerformance(p,v);
                        break;
                    default:
                        ret = false;
                        break;
                }

            }catch (Exception e){
                return false;}

            finally {
                return ret;}

    }
    private boolean addClassificationPerformance(String p, double value) {
        ClassificationMeasureVO m = new ClassificationMeasureVO();
        m.setValue(value);
        m.setName(p);
        return this._performances.add(m);
    }
    private boolean addRegressionPerformance(String p, double value) {
        RegressionMeasureVO m = new RegressionMeasureVO();
        m.setValue(value);
        m.setName(p);
        return this._performances.add(m);
    }
    private boolean addStatisticalPerformance(String p, double value) {
        StatisticalMeasureVO m = new StatisticalMeasureVO();
        m.setValue(value);
        m.setName(p);
        return this._performances.add(m);
    }
    private boolean addClusteringPerformance(String p, double value) {
        ClusteringMeasureVO m = new ClusteringMeasureVO();
        m.setValue(value);
        m.setName(p);
        return this._performances.add(m);
    }
    public List<ClassificationMeasureVO> getClassificationPerformance() {
        List<ClassificationMeasureVO> classifications = null;
        Collection<Measure> t
                = Collections2.filter(this._performances, p -> p instanceof ClassificationMeasureVO);
        if (t != null && t.size() >0){
            classifications = new ArrayList(t);
        }
        return classifications;
    }
    public List<Measure> getPerformances() {
        return _performances;
    }

}
