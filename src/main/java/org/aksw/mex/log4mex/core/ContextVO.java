package org.aksw.mex.log4mex.core;

/**
 * Created by esteves on 25.06.15.
 */
public class ContextVO {

    public String get_context() {
        return _context;
    }

    private String _context="";
    public ContextVO(String param) {
        this._context = param;
    }

    public void setContext(String value) {
        this._context = value;
    }

}
