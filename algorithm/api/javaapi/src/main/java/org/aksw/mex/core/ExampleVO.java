package org.aksw.mex.core;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by esteves on 26.06.15.
 */
public class ExampleVO implements IDataSetExample{

    private List<FeatureVO> _attributes;

    public ExampleVO(){
        this._attributes = new ArrayList<FeatureVO>();
    }

    public boolean addAttribute(FeatureVO value){
        return this._attributes.add(value);
    }

}
