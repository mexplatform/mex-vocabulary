package org.aksw.mex.log4mex.core;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by esteves on 26.06.15.
 */
public class ExampleCollection implements IDataSetExample {

    public Long getStartIndex() {
        return _startsAt;
    }

    public Long getEndIndex() {
        return _endsAt;
    }

    private Long _startsAt;
    private Long _endsAt;
    private List<ExampleVO> _examples;

    public ExampleCollection(Long startIndex, Long endIndex){
        this._startsAt = startIndex;
        this._endsAt = endIndex;
        this._examples = new ArrayList<>();
    }

    public ExampleCollection(){}

    public void setStartIndex(Long i){
        this._startsAt = i;
    }
    public void setEndIndex(Long i){
        this._endsAt = i;
    }

    public boolean addExample(String id, String value){
        return this._examples.add(new ExampleVO(id, value));
    }

    public boolean addExample(ExampleVO value){
       return this._examples.add(value);
    }

    public List<ExampleVO> getExamples(){
        return this._examples;
    }

}
