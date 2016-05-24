package org.aksw.mex.log4mex.core;

import org.aksw.mex.log4mex.InstanceObjects;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 * Created by esteves on 26.06.15.
 */
public class ModelVO extends InstanceObjects {

    private String _id = "";
    private String _description = "";
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

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof ModelVO)) {
            return false;
        }

        ModelVO that = (ModelVO) other;

        boolean ret = this._id.equals(that._id) && this._description.equals(that._description);

        if (this._date != null)
            ret = ret && this._date.equals(that._date);

        return ret;
        
    }

    @Override
    public int hashCode() {
        int hashCode = 1;

        if (StringUtils.isNotEmpty(this._id) && StringUtils.isNotBlank(this._id))
            hashCode = hashCode * 37 + this._id.hashCode();

        if (this._date != null)
            hashCode = hashCode * 37 + this._date.hashCode();

        if (StringUtils.isNotEmpty(this._description) && StringUtils.isNotBlank(this._description))
            hashCode = hashCode * 37 + this._description.hashCode();

        return hashCode;
    }

}
