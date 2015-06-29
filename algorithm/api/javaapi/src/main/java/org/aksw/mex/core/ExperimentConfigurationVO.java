package org.aksw.mex.core;

import com.google.common.collect.Collections2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by esteves on 25.06.15.
 */
public class ExperimentConfigurationVO {

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String get_description() {
        return _description;
    }

    public void set_description(String _description) {
        this._description = _description;
    }

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

    public ExperimentConfigurationVO(String id) {
        this._id = id;
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


    public void addFeature(String featureName){
        try
        {
            Collection<FeatureVO> t
                    = Collections2.filter(this._feature, p -> p.get_id().equals(featureName));

            if (t != null){
                throw new Exception("Feature already assigned");
            }else
            {
                this._feature.add(new FeatureVO(String.valueOf(this._feature.size()+1), featureName));}

        } catch (Exception e){
            System.out.println(e.toString());
        }

    }


}
