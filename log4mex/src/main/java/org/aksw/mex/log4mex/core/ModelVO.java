package org.aksw.mex.log4mex.core;

import java.util.Date;

/**
 * Created by esteves on 26.06.15.
 */
public class ModelVO {

    private String _id;
    private String _description;
    private Date _date;



    public String getId() {
        return _id;
    }

    public void setId(String _id) {
        this._id = _id;
    }

    public String getDescription() {
        return _description;
    }

    public void setDescription(String _description) {
        this._description = _description;
    }

    public Date getDate() {
        return _date;
    }

    public void setDate(Date _date) {
        this._date = _date;
    }

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

    public boolean hasValue(){

        if ((this._id != null && !this._id.isEmpty()) ||
                (this._description != null && !this._description.isEmpty())) {
            return true;
        }else{
            return false;
        }

    }

}
