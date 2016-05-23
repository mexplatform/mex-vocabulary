package org.aksw.mex.log4mex.core;

import org.aksw.mex.log4mex.InstanceObjects;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by esteves on 26.06.15.
 */
public class DataSetVO extends InstanceObjects {

    public String getName() {return _name;}
    public String getDescription() {return _description;}
    public String getURI() {return _uri;}
    public String getLabel() {return _label;}

    private String _name = "";
    private String _description = "";
    private String _uri = "";
    private String _label = "";

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


    @Override
    public boolean equals(Object other) {
        if (!(other instanceof DataSetVO)) {
            return false;
        }

        DataSetVO that = (DataSetVO) other;

        return this._name.equals(that._name)
                && this._description.equals(that._description)
                && this._uri.equals(that._uri)
                && this._label.equals(that._label);

    }

    @Override
    public int hashCode() {
        int hashCode = 1;

        if (StringUtils.isNotEmpty(this._name) && StringUtils.isNotBlank(this._name))
            hashCode = hashCode * 37 + this._name.hashCode();

        if (StringUtils.isNotEmpty(this._description) && StringUtils.isNotBlank(this._description))
            hashCode = hashCode * 37 + this._description.toString().hashCode();

        if (StringUtils.isNotEmpty(this._uri) && StringUtils.isNotBlank(this._uri))
            hashCode = hashCode * 37 + this._uri.toString().hashCode();

        if (StringUtils.isNotEmpty(this._label) && StringUtils.isNotBlank(this._label))
          hashCode = hashCode * 37 + this._label.toString().hashCode();

        return hashCode;
    }

}
