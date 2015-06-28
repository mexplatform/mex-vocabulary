package org.aksw.mex.core;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 25.06.15.
 */
public class ExperimentConfigurationVO {

    private String _id;
    private String _description;

    private List<Execution> _executions;
    private ModelVO _model;
    private SamplingMethodVO _sampling;
    private HardwareConfigurationVO _hard;
    private DataSetVO _ds;
    private ExperimentVO _exp;
    private List<FeatureVO> _feature;

    public ExperimentConfigurationVO(String id, String description) {
        this._id = id;
        this._description = description;
        this._executions = new ArrayList<Execution>();
        this._feature = new ArrayList<FeatureVO>();
    }

    public void setModel(ModelVO model) {
        this._model = model;
    }

    public void setSamplingMethod(SamplingMethodVO value) {
        this._sampling = value;
    }

    public void setHardwareConfiguration(HardwareConfigurationVO value) {
        this._hard = value;
    }

    public void setDataSet(DataSetVO value) {
        this._ds = value;
    }

    public void setExperiment(ExperimentVO value) {
        this._exp = value;
    }


    public ModelVO getModel() {
        return this._model;
    }

    public SamplingMethodVO getSamplingMethod() {
        return this._sampling ;
    }

    public List<FeatureVO> getFeatures(){
        return this._feature;
    }

    public FeatureVO getFeature(Integer index){
        return this._feature.get(index);
    }

    public HardwareConfigurationVO getHardwareConfiguration() {
        return this._hard;
    }

    public DataSetVO getDataSet() {
        return this._ds;
    }

    public boolean addExecution(Execution param){
        return _executions.add(param);
    }

    public boolean removeExecution(Execution param){
        return _executions.remove(param);
    }

    public boolean addFeature(FeatureVO value){
        return this._feature.add(value);
    }

}
