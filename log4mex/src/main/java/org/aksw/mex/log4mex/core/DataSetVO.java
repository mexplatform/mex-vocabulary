package org.aksw.mex.log4mex.core;

/**
 * Created by esteves on 26.06.15.
 */
public class DataSetVO {

    public String getName() {return _name;}
    public String getDescription() {return _description;}
    public String getURI() {return _uri;}

    private String _name;
    private String _description;
    private String _uri;

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

}
