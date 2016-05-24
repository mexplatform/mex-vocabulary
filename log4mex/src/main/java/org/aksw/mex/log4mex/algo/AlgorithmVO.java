package org.aksw.mex.log4mex.algo;

import org.aksw.mex.log4mex.InstanceObjects;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by esteves on 08.06.15.
 */
public class AlgorithmVO extends InstanceObjects {

    private String _dct_identifier = "";
    private String _rdfs_label = "";
    private String _acroynm = "";
    private String _className = "";
    private URI _uri;

    private List<HyperParameterVO> _parameters;

    public List<HyperParameterVO> getParameters(){
        return this._parameters;
    }

    public AlgorithmVO(String _dct_identifier, String _acroynm, String _rdfs_label, URI _uri, String klass){

        this._dct_identifier = _dct_identifier;
        this._rdfs_label = _rdfs_label;
        this._acroynm = _acroynm;
        this._uri = _uri;

        this._parameters = new ArrayList<>();
        this._className = klass;
    }

    public String getLabel(){
        return this._rdfs_label;
    }

    @Override
    public boolean equals(Object other) {
        return false;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    public String getClassName(){
        return this._className;
    }

    public String getIdentifier() {
        return _dct_identifier;
    }

    public void setIdentifier(String value) {
        this._dct_identifier = value;
    }

    public URI getURI() {
        return _uri;
    }

    public void setURI(URI value) {
        this._uri = value;
    }

    public String getAcronym() {
        return _acroynm;
    }

    public void setAcroynm(String value) {
        this._acroynm = value;
    }

    public boolean addParameter(HyperParameterVO param){
        return _parameters.add(param);
    }

    public boolean addParameter(String[] param){
        int i=0;
        for (String p: param){
            if (i%2==0){
                _parameters.add(new HyperParameterVO(p,param[i+1]));
            }
            i++;
        }
        return true;
    }

    public boolean addParameter(String param, String value){
        return _parameters.add(new HyperParameterVO(param, value));
    }

    public boolean removeParam(HyperParameterVO param){
        return _parameters.remove(param);
    }
}
