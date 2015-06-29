package org.aksw.mex.core;

/**
 * Created by esteves on 26.06.15.
 */
public class FeatureVO {

    public String get_id() {
        return _id;
    }

    public void set_id(String _index) {
        this._id = _index;
    }

    public String get_feature() {
        return _feature;
    }

    public void set_feature(String _feature) {
        this._feature = _feature;
    }

    private String _id;
    private String _feature;

    public FeatureVO(String id, String name){
        this._id = id;
        this._feature = name;
    }



}
