package org.aksw.mex.log4mex.perf.overall;

/**
 * Created by esteves on 26.06.15.
 */
public class UserDefinedMeasureVO extends Measure{

    private String _id;
    private String _description;
    private String _formula;
    private Double _value;



    public String getId() {
        return _id;
    }

    public void setId(String _id) {
        this._id = _id;
    }

    public String getDescription() {
        return _description;
    }

    public void set_description(String _description) {
        this._description = _description;
    }

    public String getFormula() {
        return _formula;
    }

    public void set_formula(String _formula) {
        this._formula = _formula;
    }

    public Double getValue() {
        return _value;
    }

    public void setValue(Double value) {
        this._value = value;
    }


    public String getCreatorName() {
        return _creatorName;
    }

    public void set_creatorName(String _creatorName) {
        this._creatorName = _creatorName;
    }

    private String _creatorName;

    public UserDefinedMeasureVO(String id, String desc, String formula, Double value){
        this._id = id;
        this._description = desc;
        this._formula = formula;
        this._value = value;
    }

    public UserDefinedMeasureVO(){

    }

    @Override
    public String getLabel(){
        return "Custom measures defined by user";
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
