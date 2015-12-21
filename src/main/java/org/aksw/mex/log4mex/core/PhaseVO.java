package org.aksw.mex.log4mex.core;

/**
 * Created by esteves on 26.06.15.
 */
public class PhaseVO {

    private String _phase;

    public PhaseVO(String value){
        this._phase = value;
    }

    public String getName(){
        return this._phase;
    }

}
