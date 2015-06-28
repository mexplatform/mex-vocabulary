package org.aksw.mex.algo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 08.06.15.
 */
public class AlgorithmVO {

    private String _individualName;
    private String _dct_identifier;
    private String _dct_description;
    private String _acroynm;
    private List<AlgorithmParameterVO> _parameters;

    public AlgorithmVO(String ind, String _dct_identifier, String _dct_description, String _acroynm){
        this._individualName = ind;
        this._dct_identifier = _dct_identifier;
        this._dct_description = _dct_description;
        this._acroynm = _acroynm;
        this._parameters = new ArrayList<AlgorithmParameterVO>();
    }
    public AlgorithmVO(String ind) {
        this._individualName = ind;
    }

    public String getIdentifier() {
        return _dct_identifier;
    }

    public void setIdentifier(String value) {
        this._dct_identifier = value;
    }

    public String getDescription() {
        return _dct_description;
    }

    public void setDescription(String value) {
        this._dct_description = value;
    }

    public String getAcroynm() {
        return _acroynm;
    }

    public void setAcroynm(String value) {
        this._acroynm = value;
    }

    public boolean addParameter(AlgorithmParameterVO param){
        return _parameters.add(param);
    }

    public boolean removeParam(AlgorithmParameterVO param){
        return _parameters.remove(param);
    }
}
