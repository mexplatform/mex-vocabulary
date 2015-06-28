package org.aksw.mex.core;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by esteves on 26.06.15.
 */
public class DataSetExampleCollection implements IDataSetExample {

    private Long _startsAt;
    private Long _endsAt;
    private List<DataSetExampleVO> _examples;

    public DataSetExampleCollection(Long startIndex, Long endIndex){
        this._startsAt = startIndex;
        this._endsAt = endIndex;
        this._examples = new ArrayList<DataSetExampleVO>();
    }

    public boolean addExample(DataSetExampleVO value){
       return this._examples.add(value);
    }


}
