package org.aksw.mex.core;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by esteves on 28.06.15.
 */
public class FeatureVOCollection {

    private List<FeatureVO> _examples;

    public FeatureVOCollection(){

        this._examples = new ArrayList<>();
    }

    public boolean addExample(FeatureVO value){
        return this._examples.add(value);
    }
}
