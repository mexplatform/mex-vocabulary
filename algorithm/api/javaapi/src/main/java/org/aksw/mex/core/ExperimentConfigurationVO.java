package org.aksw.mex.core;

import com.google.common.collect.Collections2;
import com.google.common.collect.Iterables;
import org.aksw.mex.algo.AlgorithmVO;
import org.aksw.mex.algo.ImplementationVO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by esteves on 25.06.15.
 */
public class ExperimentConfigurationVO {

    private String _id;
    private String _description;
    private ModelVO _model;
    private SamplingMethodVO _sampling;
    private HardwareConfigurationVO _hard;
    private DataSetVO _ds;
    private ExperimentVO _exp;
    private ImplementationVO _implementation;

    private List<Execution> _executions;
    private List<FeatureVO> _features;
    private List<AlgorithmVO> _algorithms;


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

    public ExperimentConfigurationVO(String id, String description) {
        this._id = id;
        this._description = description;
        this._executions = new ArrayList<Execution>();
        this._features = new ArrayList<FeatureVO>();
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
        return this._features;
    }

    public List<Execution> getExecutions(){
        return this._executions;
    }

    public FeatureVO getFeature(Integer index){
        return this._features.get(index);
    }

    public HardwareConfigurationVO getHardwareConfiguration() {
        return this._hard;
    }

    public DataSetVO getDataSet() {
        return this._ds;
    }


    public void addExecutionOverall(String id, String phase){
        this._executions.add(new ExecutionSetVO(id, new PhaseVO(phase)));
    }

    public void addExecutionSingle(String id, String phase){
        this._executions.add(new ExecutionIndividualVO(id, new PhaseVO(phase)));
    }

    public void setExecutionStartTime(String executionId, Date value){
        try {
            Collection<Execution> t = Collections2.filter(this._executions, p -> p._id.equals(executionId));
            if (t != null){Iterables.get(t, 0).setStartDate(value);}
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
    }

    public void setExecutionEndTime(String executionId, Date value){
        try {
            Collection<Execution> t = Collections2.filter(this._executions, p -> p._id.equals(executionId));
            if (t != null){Iterables.get(t, 0).setEndDate(value);}
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
    }

    public ExecutionSetVO getExecutionOverall(String id){
         ExecutionSetVO r = null;
        try {
            Collection<Execution> t = Collections2.filter(this._executions, p -> p._id.equals(id));
            if (t != null){
                if (Iterables.get(t, 0) instanceof ExecutionSetVO) {
                    r = (ExecutionSetVO) Iterables.get(t, 0);}
            }
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
        return r;
    }

    public Execution getExecution(String id){
        Execution ret  = null;
        try {
            Collection<Execution> t = Collections2.filter(this._executions, p -> p._id.equals(id));
            if (t != null){ret = Iterables.get(t, 0);}
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
        return ret;
    }

    public boolean addExecution(Execution param){
        return _executions.add(param);
    }

    public boolean removeExecution(Execution param){
        return _executions.remove(param);
    }

    public ImplementationVO getImplementation() {
        return this._implementation;
    }

    public void addFeature(String featureName){
        try {
            Collection<FeatureVO> t = Collections2.filter(this._features, p -> p.get_id().equals(featureName));
            if (t != null){throw new Exception("Feature already assigned");}
            else {this._features.add(new FeatureVO(String.valueOf(this._features.size()+1), featureName));}
        } catch (Exception e){
            System.out.println(e.toString());}
    }
    public void addAlgorithm(String algorithmName){
        try {
            Collection<AlgorithmVO> t = Collections2.filter(this._algorithms, p -> p.getIndividualName().equals(algorithmName));
            if (t != null){throw new Exception("Algorithm already assigned");}
            else {this._algorithms.add(new AlgorithmVO(String.valueOf(algorithmName)));}
        } catch (Exception e){
            System.out.println(e.toString());}
    }
    public AlgorithmVO getAlgorithm(String algorithmName){
        AlgorithmVO ret  = null;
        try {
            Collection<AlgorithmVO> t = Collections2.filter(this._algorithms, p -> p.getIndividualName().equals(algorithmName));
            if (t != null){ret = Iterables.get(t, 0);}
           }
        catch (Exception e){
            System.out.println(e.toString());
        }
        return ret;
    }


}
