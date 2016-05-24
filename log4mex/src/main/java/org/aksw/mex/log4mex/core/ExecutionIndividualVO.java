package org.aksw.mex.log4mex.core;

import org.aksw.mex.log4mex.Execution;
import org.aksw.mex.log4mex.ExperimentConfigurationVO;

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

    @Override
    public void setStartsAtPosition(String value){}

    @Override
    public void setEndsAtPosition(String value){}


    @Override
    public boolean equals(Object other) {
        return false;
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
