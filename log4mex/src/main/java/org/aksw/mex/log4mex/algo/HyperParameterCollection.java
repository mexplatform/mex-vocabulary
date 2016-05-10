package org.aksw.mex.log4mex.algo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by esteves on 08.06.15.
 */
public class HyperParameterCollection {

    private List<HyperParameterVO> _algorithmParameter;
    private String _identifier;

    HyperParameterCollection(){
        this._algorithmParameter = new ArrayList<HyperParameterVO>()  ;
        this._identifier = "";
    }

    HyperParameterCollection(String identifier){
        this._algorithmParameter = new ArrayList<HyperParameterVO>()  ;
        this._identifier = identifier;
    }


    public String getIdentifier() {
        return _identifier;
    }

    public void setIdentifier(String value) {
        _identifier = value;
    }

    public List<HyperParameterVO> getParameters(){
        return this._algorithmParameter;
    }



}


