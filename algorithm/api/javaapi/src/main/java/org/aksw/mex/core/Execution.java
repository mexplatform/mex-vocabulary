package org.aksw.mex.core;

import org.aksw.mex.algo.AlgorithmVO;
import org.aksw.mex.perf.IPerformance;

import java.util.Date;
import java.util.List;

/**
 * Created by esteves on 26.06.15.
 */
public abstract class Execution {

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Boolean get_grouped() {
        return _grouped;
    }

    public void set_grouped(Boolean _grouped) {
        this._grouped = _grouped;
    }

    public Date get_startedAtTime() {
        return _startedAtTime;
    }

    public void set_startedAtTime(Date _startedAtTime) {
        this._startedAtTime = _startedAtTime;
    }

    public Date get_endedAtTime() {
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

    public PhaseVO get_phase() {
        return _phase;
    }

    public void set_phase(PhaseVO _phase) {
        this._phase = _phase;
    }

    public AlgorithmVO get_algo() {
        return _algo;
    }

    public void set_algo(AlgorithmVO _algo) {
        this._algo = _algo;
    }

    public IDataSetExample get_example() {
        return _example;
    }

    public void set_example(IDataSetExample _example) {
        this._example = _example;
    }

    public List<IPerformance> get_performance() {
        return _performance;
    }

    public void set_performance(List<IPerformance> _performance) {
        this._performance = _performance;
    }

    protected String _id;
    protected Boolean _grouped;
    protected Date _startedAtTime;
    protected Date _endedAtTime;

    protected ExperimentConfigurationVO _expConf;
    protected PhaseVO _phase;
    protected AlgorithmVO _algo;
    protected IDataSetExample _example;

    protected List<IPerformance> _performance;


    public Execution(){

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
    public void setExamples(IDataSetExample value){
        this._example = value;
    }

    public void setPhase(PhaseVO value){
        this._phase = value;
    }

    public boolean addPerformance(IPerformance p) {
        return this._performance.add(p);
    }

}
