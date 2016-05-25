package org.aksw.mex.log4mex.algo;

import org.aksw.mex.log4mex.InstanceObjects;

/**
 * Created by dnes on 10/05/16.
 */
public class ToolParameterVO extends InstanceObjects {

    private String _dct_identifier = "";
    private String _prov_value = "";

    public ToolParameterVO(String _dct_identifier, String _prov_value){
        this._dct_identifier = _dct_identifier;
        this._prov_value = _prov_value;
        this.setLabel(this._dct_identifier);
        if (_dct_identifier.toString().length() > 2)
            this.setLabel(_dct_identifier.toString().substring(0, 1).toUpperCase() + _dct_identifier.toString().substring(1));
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

    public void setValue(String value) {
        this._prov_value = value;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof ToolParameterVO)) {
            return false;
        }

        ToolParameterVO that = (ToolParameterVO) other;

        return this._dct_identifier.equals(that._dct_identifier)
                && this._prov_value.equals(that._prov_value);

    }


    @Override
    public int hashCode() {
        int hashCode = 1;

        hashCode = hashCode * 37 + this.getIndividualName().hashCode();
        hashCode = hashCode * 37 + this._dct_identifier.hashCode();
        hashCode = hashCode * 37 + this._prov_value.hashCode();

        return hashCode;
    }

}
