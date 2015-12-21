package org.aksw.mex.util;

/**
 * Created by esteves on 03.07.15.
 */
public class MEXController {
    private int _numberOfExperimentConfigurations;
    private int _numberOfSamplingMethods;
    private int _numberOfAlgorithms;


    private static MEXController instance = null;
    protected MEXController() {
        _numberOfExperimentConfigurations = 0;
        _numberOfSamplingMethods=0;
        _numberOfAlgorithms=0;
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
    public void addExperimentConfigurationCounter(){
        this._numberOfExperimentConfigurations ++;
    }

    public int getNumberOfSamplingMethods(){
        return this._numberOfSamplingMethods;
    }
    public void addSamplingMethodCounter(){
        this._numberOfSamplingMethods ++;
    }

    public int getNumberOfAlgorithms(){
        return this._numberOfAlgorithms;
    }
    public void addAlgorithmCounter(){
        this._numberOfAlgorithms ++;
    }

}
