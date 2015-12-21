package org.aksw.mex.log4mex.perf.overall;

/**
 * Created by esteves on 26.06.15.
 */
public class UserDefinedMeasure{

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String get_description() {
        return _description;
    }

    public void set_description(String _description) {
        this._description = _description;
    }

    public String get_formula() {
        return _formula;
    }

    public void set_formula(String _formula) {
        this._formula = _formula;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    private String _id;
    private String _description;
    private String _formula;
    private String value;

    public String get_creatorName() {
        return _creatorName;
    }

    public void set_creatorName(String _creatorName) {
        this._creatorName = _creatorName;
    }

    private String _creatorName;

    public UserDefinedMeasure(String id, String value){
        this._id = id;
        this.value = value;
    }

    public UserDefinedMeasure(){

    }
}
