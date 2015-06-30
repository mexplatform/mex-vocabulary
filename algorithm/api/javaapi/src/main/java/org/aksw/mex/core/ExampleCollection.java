package org.aksw.mex.core;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by esteves on 26.06.15.
 */
public class ExampleCollection implements IDataSetExample {

    private Long _startsAt;
    private Long _endsAt;
    private List<ExampleVO> _examples;

    public ExampleCollection(Long startIndex, Long endIndex){
        this._startsAt = startIndex;
        this._endsAt = endIndex;
        this._examples = new ArrayList<ExampleVO>();
    }

    public boolean addExample(ExampleVO value){
       return this._examples.add(value);
    }


}
