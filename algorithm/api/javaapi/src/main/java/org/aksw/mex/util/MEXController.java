package org.aksw.mex.util;

/**
 * Created by esteves on 03.07.15.
 */
public class MEXController {
    private int _numberOfExperimentConfigurations;

    private static MEXController instance = null;
    protected MEXController() {
        _numberOfExperimentConfigurations = 0;
    }
    public static MEXController getInstance() {
        if(instance == null) {
            instance = new MEXController();
        }
        return instance;
    }

    public int getNumberOfExperimentConfigurations(){
        return this._numberOfExperimentConfigurations;
    }
    public void addExperimentConfiguration(){
        this._numberOfExperimentConfigurations ++;
    }


}
