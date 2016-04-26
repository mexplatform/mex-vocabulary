package org.aksw.mex.log4mex.core;

import org.aksw.mex.util.MEXEnum;

/**
 * Created by esteves on 26.06.15.
 */
public class PhaseVO {

    private MEXEnum.EnumPhases _phase;

    public PhaseVO(MEXEnum.EnumPhases value){
        this._phase = value;
    }

    public MEXEnum.EnumPhases getName(){
        return this._phase;
    }

}
