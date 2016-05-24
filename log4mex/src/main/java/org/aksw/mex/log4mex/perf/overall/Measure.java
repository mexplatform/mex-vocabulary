package org.aksw.mex.log4mex.perf.overall;

import org.aksw.mex.log4mex.InstanceObjects;
import org.aksw.mex.log4mex.perf.IMeasure;

/**
 * Created by esteves on 30.06.15.
 */
public abstract class Measure extends InstanceObjects implements IMeasure {

    private String _name;
    private Double _value;

    public Measure(){}
    public Measure(String measure, Double value){
        this._value = value;
        this._name = measure;
    }


    public void setMeasure(String m, Double v){
        this._name = m;
        this._value=v;
    }

    public String getName() {
        return _name;
    }

    public void setName(String _name) {
        this._name = _name;
    }

    public Double getValue() {
        return _value;
    }

    public void setValue(Double _value) {
        this._value = _value;
    }

    public abstract String getLabel();

}
