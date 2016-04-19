package org.aksw.mex.log4mex.core;

import java.util.Date;

/**
 * Created by esteves on 26.06.15.
 */
public class ModelVO {

    public String get_id() {
        return _id;
    }

    public void setId(String _id) {
        this._id = _id;
    }

    public String get_description() {
        return _description;
    }

    public void setDescription(String _description) {
        this._description = _description;
    }

    public Date get_date() {
        return _date;
    }

    public void setDate(Date _date) {
        this._date = _date;
    }

    private String _id;
    private String _description;
    private Date _date;

    public ModelVO(String id, String description, Date date) {
        this._id = id;
        this._description = description;
        this._date = date;
    }

    public ModelVO(String id) {
        this._id = id;
    }

    public ModelVO() {

    }

}
