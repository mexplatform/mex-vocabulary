package org.aksw.mex.log4mex.algo;

/**
 * Created by dnes on 10/05/16.
 */
public class ToolParameterVO {

    private String _dct_identifier;
    private String _prov_value;
    private String _label="";

    public ToolParameterVO(String _dct_identifier, String _prov_value){
        this._dct_identifier = _dct_identifier;
        this._prov_value = _prov_value;
        this._label = _dct_identifier;
        if (_dct_identifier.toString().length() > 2)
            this._label = _dct_identifier.toString().substring(0, 1).toUpperCase() + _dct_identifier.toString().substring(1);
    }

    public String getId() {
        return _dct_identifier;
    }

    public void setId(String value) {
        this._dct_identifier = value;
    }

    public String getValue() {
        return _prov_value;
    }

    public String getLabel() {
        return _label;
    }

    public void setValue(String value) {
        this._prov_value = value;
    }

}
