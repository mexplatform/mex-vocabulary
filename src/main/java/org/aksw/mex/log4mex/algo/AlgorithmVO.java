package org.aksw.mex.log4mex.algo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by esteves on 08.06.15.
 */
public class AlgorithmVO {

    private String _individualName;
    private String _dct_identifier;
    private String _dct_description;
    private String _acroynm;
    private List<AlgorithmParameterVO> _parameters;
    private String _className;

    public List<AlgorithmParameterVO> getParameters(){
        return this._parameters;
    }

    public AlgorithmVO(String mexclass, String ind, String _dct_identifier, String _dct_description, String _acroynm){
        this._individualName = ind;
        this._dct_identifier = _dct_identifier;
        this._dct_description = _dct_description;
        this._acroynm = _acroynm;
        this._parameters = new ArrayList<>();
        this._className = mexclass;
    }
    public AlgorithmVO(String mexclass, String ind) {
        this._individualName = ind;
        this._parameters = new ArrayList<>();
        this._className = mexclass;
    }

    public AlgorithmVO(String mexclass, String ind, String identifier) {
        this._individualName = ind;
        this._dct_identifier = identifier;
        this._parameters = new ArrayList<>();
        this._className = mexclass;
    }

    public String getClassName(){
        return this._className;
    }

    public String getIdentifier() {
        return _dct_identifier;
    }

    public String getIndividualName() {
        return this._individualName;
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

    public boolean addParameter(String[] param){
        int i=0;
        for (String p: param){
            if (i%2==0){
                _parameters.add(new AlgorithmParameterVO(p,param[i+1]));
            }
            i++;
        }
        return true;
    }

    public boolean addParameter(String param, String value){
        return _parameters.add(new AlgorithmParameterVO(param, value));
    }

    public boolean removeParam(AlgorithmParameterVO param){
        return _parameters.remove(param);
    }
}
