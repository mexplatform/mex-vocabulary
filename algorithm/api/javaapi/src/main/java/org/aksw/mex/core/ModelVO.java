package org.aksw.mex.core;

import java.util.Date;

/**
 * Created by root on 26.06.15.
 */
public class ModelVO {

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
