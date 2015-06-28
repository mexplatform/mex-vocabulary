package org.aksw.mex.core;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by esteves on 26.06.15.
 */
public class DataSetExampleVO implements IDataSetExample{

    private List<DataSetAttributeVO> _attributes;

    public DataSetExampleVO(){
        this._attributes = new ArrayList<DataSetAttributeVO>();
    }

    public boolean addAttribute(DataSetAttributeVO value){
        return this._attributes.add(value);
    }

}
