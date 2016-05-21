package org.aksw.mex.log4mex.core;

import org.aksw.mex.log4mex.InstanceObjects;
import org.aksw.mex.util.MEXEnum;

/**
 * Created by esteves on 26.06.15.
 */
public class PhaseVO extends InstanceObjects {

    private MEXEnum.EnumPhases _phase;

    public PhaseVO(MEXEnum.EnumPhases value){
        this._phase = value;
        if (value.toString().length() > 2){
            this.setLabel(value.toString().substring(0, 1).toUpperCase() + value.toString().substring(1));
            this.setIndividualName("phase_" + this.getLabel());
        }

    }

    public MEXEnum.EnumPhases getName(){
        return this._phase;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof PhaseVO)) {
            return false;
        }

        PhaseVO that = (PhaseVO) other;

        return this.getLabel().equals(that.getLabel())
                && this._phase.equals(that._phase);
    }

    @Override
    public int hashCode() {
        int hashCode = 1;

        hashCode = hashCode * 37 + this.getLabel().hashCode();
        hashCode = hashCode * 37 + this._phase.toString().hashCode();

        return hashCode;
    }

}
