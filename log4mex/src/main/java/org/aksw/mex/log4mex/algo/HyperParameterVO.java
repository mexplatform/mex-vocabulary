package org.aksw.mex.log4mex.algo;

import org.aksw.mex.log4mex.InstanceObjects;

/**
 * Created by esteves on 08.06.15.
 */
public class HyperParameterVO extends InstanceObjects {
    private String _dct_identifier = "";
    private String _prov_value = "";
    private String _label = "";

    public HyperParameterVO(String _dct_identifier, String _prov_value){
        this._dct_identifier = _dct_identifier;
        this._prov_value = _prov_value;
        this._label = _dct_identifier;
        if (_dct_identifier.toString().length() > 2)
            this._label = _dct_identifier.toString().substring(0, 1).toUpperCase() + _dct_identifier.toString().substring(1);
    }

    public String getIdentifier() {
        return _dct_identifier;
    }

    public void setIdentifier(String value) {
        this._dct_identifier = value;
    }

    public String getValue() {
        return _prov_value;
    }

    public String getLabel() {
        return _label;
    }

    @Override
    public boolean equals(Object other) {
        return false;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    public void setValue(String value) {
        this._prov_value = value;
    }

}
