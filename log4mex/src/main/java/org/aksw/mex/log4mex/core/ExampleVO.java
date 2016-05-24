package org.aksw.mex.log4mex.core;

import org.aksw.mex.log4mex.InstanceObjects;

/**
 * Created by esteves on 26.06.15.
 */
public class ExampleVO extends InstanceObjects implements IDataSetExample{

    public String getId() {
        return _id;
    }

    public void setId(String _id) {
        this._id = _id;
    }

    public String getValue() {
        return _value;
    }

    public void setValue(String _value) {
        this._value = _value;
    }

    public long getDatasetColumn() {
        return _dsColumn;
    }

    public void setDatasetColumn(long _id) {
        this._dsColumn = _id;
    }

    public long getDatasetRow() {
        return _dsRow;
    }

    public void setDatasetRow(long _value) {
        this._dsRow = _value;
    }

    public String getExampleType() {
        return _type;
    }

    public void setExampleType(String _value) {
        this._type = _value;
    }


    private String _id;
    private String _value;
    private long _dsColumn;
    private long _dsRow;
    private String _type;

    public ExampleVO(){
    }

    public ExampleVO(String id, String value){
        this._id = id;
        this._value=value;
    }


    @Override
    public boolean equals(Object other) {
        return false;
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
