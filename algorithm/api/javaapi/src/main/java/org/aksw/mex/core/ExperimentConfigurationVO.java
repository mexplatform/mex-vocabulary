package org.aksw.mex.core;

import com.google.common.collect.Collections2;
import com.google.common.collect.Iterables;
import org.aksw.mex.algo.AlgorithmVO;
import org.aksw.mex.algo.ImplementationVO;
import org.aksw.mex.util.Global;

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
    //private ExperimentVO _exp;
    private ImplementationVO _implementation;

    private List<Execution> _executions;
    private List<FeatureVO> _features;
    private List<AlgorithmVO> _algorithms;

    public ExperimentConfigurationVO(String id, String description) {
        this._id = id;
        this._description = description;
        this._executions = new ArrayList<>();
        this._features = new ArrayList<>();
        this._algorithms = new ArrayList<>();
    }

    public ExperimentConfigurationVO(String id) {
        this._id = id;
        this._executions = new ArrayList<>();
        this._features = new ArrayList<>();
        this._algorithms = new ArrayList<>();
    }


    public String getId() {
        return _id;
    }
    public void setId(String _id) {
        this._id = _id;
    }
    public String getDescription() {
        return _description;
    }
    public void setDescription(String _description) {
        this._description = _description;
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
    //public void setExperiment(ExperimentVO value) {
    //    this._exp = value;
    //}

    public ModelVO Model() {
        if (this._model == null){
            this._model = new ModelVO();
        }
        return this._model;
    }
    public SamplingMethodVO SamplingMethod() {
        if (this._sampling == null){
            this._sampling = new SamplingMethodVO(Global.EnumSamplingMethod.Holdout);
        }
        return this._sampling ;
    }
    public HardwareConfigurationVO HardwareConfiguration() {
        if (this._hard == null){
            this._hard = new HardwareConfigurationVO();
        }
        return this._hard;
    }
    public DataSetVO DataSet() {
        if (this._ds == null){
            this._ds = new DataSetVO();
        }
        return this._ds;
    }
    public AlgorithmVO Algorithm(String algorithmName){
        if (this._algorithms == null) {
            this._algorithms = new ArrayList<>();}

        AlgorithmVO ret  = null;
        try {
            Collection<AlgorithmVO> t = Collections2.filter(this._algorithms, p -> p.getIndividualName().equals(algorithmName));
            if (t != null && t.size() > 0){
                ret = Iterables.get(t, 0);
            }
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
        return ret;
    }
    public ImplementationVO Implementation() {
        if (this._implementation ==null){
            this._implementation = new ImplementationVO();
        }
        return this._implementation;
    }
    public ExecutionSetVO ExecutionOverall(String id){
        ExecutionSetVO r = null;
        try {
            Collection<Execution> t = Collections2.filter(this._executions, p -> p._id.equals(id));
            if (t != null && t.size() >0){
                if (Iterables.get(t, 0) instanceof ExecutionSetVO) {
                    r = (ExecutionSetVO) Iterables.get(t, 0);}
            }
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
        return r;
    }

    public void addExecutionOverall(String id, String phase){
        this._executions.add(new ExecutionSetVO(id, new PhaseVO(phase)));
    }
    public void addFeature(String featureName){
        try {
            Collection<FeatureVO> t = Collections2.filter(this._features, p -> p.get_id().equals(featureName));
            if (t != null && t.size() > 0){throw new Exception("Feature already assigned");}
            else {this._features.add(new FeatureVO(String.valueOf(this._features.size()+1), featureName));}
        } catch (Exception e){
            System.out.println(e.toString());}
    }
    public void addAlgorithm(String algorithmName){
        if (this._algorithms == null) {
            this._algorithms = new ArrayList<>();}

        try {
            Collection<AlgorithmVO> t = Collections2.filter(this._algorithms, p -> p.getIndividualName().equals(algorithmName));
            if (t != null && t.size() > 0){throw new Exception("Algorithm already assigned");}
            else {
                this._algorithms.add(new AlgorithmVO(String.valueOf(algorithmName)));
            }
        } catch (Exception e){
            System.out.println(e.toString());}
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
    public void addExecutionSingle(String id, String phase){
        this._executions.add(new ExecutionIndividualVO(id, new PhaseVO(phase)));
    }
    public void setExecutionStartTime(String executionId, Date value){
        try {
            Collection<Execution> t = Collections2.filter(this._executions, p -> p._id.equals(executionId));
            if (t != null && t.size() >0){Iterables.get(t, 0).setStartDate(value);}
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
    }
    public void setExecutionEndTime(String executionId, Date value){
        try {
            Collection<Execution> t = Collections2.filter(this._executions, p -> p._id.equals(executionId));
            if (t != null && t.size() > 0){Iterables.get(t, 0).setEndDate(value);}
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
    }
    public Execution Execution(String id){
        Execution ret  = null;
        try {
            Collection<Execution> t = Collections2.filter(this._executions, p -> p._id.equals(id));
            if (t != null && t.size() >0){ret = Iterables.get(t, 0);}
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

}
