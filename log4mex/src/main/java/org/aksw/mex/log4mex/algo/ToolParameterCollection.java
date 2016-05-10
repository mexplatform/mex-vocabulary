package org.aksw.mex.log4mex.algo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dnes on 10/05/16.
 */
public class ToolParameterCollection {

    private List<ToolParameterVO> _toolParameter;
    private String _identifier;

    ToolParameterCollection(){
        this._toolParameter = new ArrayList<>()  ;
        this._identifier = "";
    }

    ToolParameterCollection(String identifier){
        this._toolParameter = new ArrayList<>()  ;
        this._identifier = identifier;
    }


    public String getIdentifier() {
        return _identifier;
    }

    public void setIdentifier(String value) {
        _identifier = value;
    }

    public List<ToolParameterVO> getParameters(){
        return this._toolParameter;
    }

}
