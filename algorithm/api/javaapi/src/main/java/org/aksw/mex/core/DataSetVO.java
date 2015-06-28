package org.aksw.mex.core;

/**
 * Created by root on 26.06.15.
 */
public class DataSetVO {

    private String _name;
    private String _description;
    private String _uri;

    public DataSetVO(String name){
        this._name = name;
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

}
