package org.aksw.mex.log4mex.algo;

/**
 * Created by esteves on 08.06.15.
 */
public class AlgorithmParameterVO {
    private String _dct_identifier;
    private String _prov_value;

    public AlgorithmParameterVO(String _dct_identifier, String _prov_value){
        this._dct_identifier = _dct_identifier;
        this._prov_value = _prov_value;
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

    public void setValue(String value) {
        this._prov_value = value;
    }
}
