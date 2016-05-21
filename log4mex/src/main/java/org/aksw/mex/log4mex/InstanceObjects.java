package org.aksw.mex.log4mex;

/**
 * Created by dnes on 22/05/16.
 */
public abstract class InstanceObjects {

    String _individualName;
    String _label;

    public String getIndividualName(){
        return this._individualName;
    }

    public void setIndividualName(String value){
        this._individualName = value;
    }

    public String getLabel(){
        return this._label;
    }

    public void setLabel(String value){
        this._label = value;
    }


    public abstract boolean equals(Object other);

    public abstract int hashCode();

}
