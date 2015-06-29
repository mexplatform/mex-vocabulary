package org.aksw.mex.perf.overall;

/**
 * Created by esteves on 30.06.15.
 */
public abstract class Measure {

    private String _name;
    private double _value;

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public double get_value() {
        return _value;
    }

    public void set_value(double _value) {
        this._value = _value;
    }

    public Measure(){

    }


}
