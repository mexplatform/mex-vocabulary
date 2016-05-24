package org.aksw.mex.log4mex.core;

import org.aksw.mex.log4mex.Execution;
import org.aksw.mex.log4mex.ExperimentConfigurationVO;

/**
 * Created by esteves on 26.06.15.
 */
public class ExecutionSetVO extends Execution {

    private String _startPosition;
    private String _endPosition;

    public ExecutionSetVO(String id){
        this._id = id;

    }

    public ExecutionSetVO(ExperimentConfigurationVO ep, String id, PhaseVO phase){
        this._id = id;
        this._phase = phase;
        this._expConf = ep;
    }

    @Override
    public void setStartsAtPosition(String value){
        this._startPosition = value;
    }

    @Override
    public void setEndsAtPosition(String value){
        this._endPosition = value;
    }

    public String getStartsAtPosition(){
        return this._startPosition;
    }

    public String getEndsAtPosition(){
        return this._endPosition;
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
