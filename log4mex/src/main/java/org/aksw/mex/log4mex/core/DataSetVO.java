package org.aksw.mex.log4mex.core;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by esteves on 26.06.15.
 */
public class DataSetVO {

    public String getName() {return _name;}
    public String getDescription() {return _description;}
    public String getURI() {return _uri;}
    public String getLabel() {return _label;}

    private String _name;
    private String _description;
    private String _uri;
    private String _label;

    public DataSetVO(String name){
        this._name = name;
    }

    public DataSetVO(){

    }

    public void setName(String value){
        this._name = value;
    }

    public void setDescription(String value){
        this._description =value;
    }

    public void setURI(String value){
        this._uri = value;
    }

    /**
     * must have name or uri
     * @return
     */
    public boolean hasValue(){

        if ((this._name != null && !StringUtils.isBlank(this._name) && !StringUtils.isEmpty(this._name)) ||
                (this._uri != null && !StringUtils.isBlank(this._uri) && !StringUtils.isEmpty(this._uri))){
            if (this._name != null && !StringUtils.isBlank(this._name) && !StringUtils.isEmpty(this._name)) {
                this._label = this._name;
            } else{
                this._label = this._uri;
            }
            return true;
        }else{
            return false;
        }

    }

}
