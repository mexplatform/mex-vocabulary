package org.aksw.mex.log4mex.core;

import org.aksw.mex.log4mex.InstanceObjects;

/**
 * Created by esteves on 26.06.15.
 */
public class FeatureVO extends InstanceObjects {

    public String getId() {
        return _id;
    }

    public void setId(String _index) {
        this._id = _index;
    }

    public String getName() {
        return _name;
    }

    public void setName(String _feature) {
        this._name = _feature;
    }

    private String _id;
    private String _name;

    public FeatureVO(String id, String name){
        this._id = id;
        this._name = name;
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
