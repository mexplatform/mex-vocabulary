package org.aksw.mex.core;

import org.aksw.mex.algo.AlgorithmVO;
import org.aksw.mex.perf.IPerformance;

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
