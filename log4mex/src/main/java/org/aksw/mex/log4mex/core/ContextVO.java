package org.aksw.mex.log4mex.core;

import org.aksw.mex.util.MEXEnum;

/**
 * Created by esteves on 25.06.15.
 */
public class ContextVO {

    public MEXEnum.EnumContexts get_context() {
        return _context;
    }

    private MEXEnum.EnumContexts _context;

    public ContextVO(MEXEnum.EnumContexts param) {
        this._context = param;
    }

    public void setContext(MEXEnum.EnumContexts value) {
        this._context = value;
    }

}
