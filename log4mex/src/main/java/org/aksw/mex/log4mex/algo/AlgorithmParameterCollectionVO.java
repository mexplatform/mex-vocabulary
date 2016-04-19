package org.aksw.mex.log4mex.algo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by esteves on 08.06.15.
 */
public class AlgorithmParameterCollectionVO {

    private List<AlgorithmParameterVO> _algorithmParameter;
    private String _identifier;

    AlgorithmParameterCollectionVO(){
        this._algorithmParameter = new ArrayList<AlgorithmParameterVO>()  ;
        this._identifier = "";
    }

    AlgorithmParameterCollectionVO(String identifier){
        this._algorithmParameter = new ArrayList<AlgorithmParameterVO>()  ;
        this._identifier = identifier;
    }


    public String getIdentifier() {
        return _identifier;
    }

    public void setIdentifier(String value) {
        _identifier = value;
    }

    public List<AlgorithmParameterVO> getParameters(){
        return this._algorithmParameter;
    }



}


