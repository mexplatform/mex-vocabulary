package org.aksw.mex.log4mex.core;

/**
 * Created by esteves on 25.06.15.
 */
public class ExecutionIndividualVO extends Execution {

    private IDataSetExample _example;

    public ExecutionIndividualVO(){

    }

    public ExecutionIndividualVO(ExperimentConfigurationVO ep, String id, PhaseVO phase){
        this._id = id;
        this._phase = phase;
        this._expConf = ep;
    }



}
