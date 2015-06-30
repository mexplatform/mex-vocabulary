package org.aksw.mex.perf.overall;

/**
 * Created by esteves on 30.06.15.
 */
public abstract class Measure {

    private String _name;
    private double _value;


    public Measure(){}
    public Measure(String measure, double value){
        this._value = value;
        this._name = measure;
    }


    public void setMeasure(String m, double v){
        this._name = m;
        this._value=v;
    }

    public String getName() {
        return _name;
    }
    public void setName(String _name) {
        this._name = _name;
    }
    public double getValue() {
        return _value;
    }
    public void setValue(double _value) {
        this._value = _value;
    }


}
