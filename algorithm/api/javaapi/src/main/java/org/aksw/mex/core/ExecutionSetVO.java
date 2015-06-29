package org.aksw.mex.core;

/**
 * Created by esteves on 26.06.15.
 */
public class ExecutionSetVO extends Execution {

    public ExecutionSetVO(String id){
        this._id = id;
    }

    public ExecutionSetVO(String id, PhaseVO phase){
        this._id = id;
        this._phase = phase;
    }

    public ExecutionSetVO(){

    }

}
