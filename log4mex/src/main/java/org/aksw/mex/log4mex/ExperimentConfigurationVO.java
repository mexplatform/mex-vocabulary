/**
 * Copyright (C) 2014 - 2016, Diego Esteves
 *
 * This file is part of LOG4MEX.
 *
 * LOG4MEX is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * LOG4MEX is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.aksw.mex.log4mex;

import com.google.common.collect.Collections2;
import com.google.common.collect.Iterables;
import org.aksw.mex.log4mex.algo.AlgorithmVO;
import org.aksw.mex.log4mex.algo.ToolParameterVO;
import org.aksw.mex.log4mex.algo.ToolVO;
import org.aksw.mex.log4mex.core.*;
import org.aksw.mex.util.MEXConstant;
import org.aksw.mex.util.MEXController;
import org.aksw.mex.util.MEXEnum;
import org.aksw.mex.util.ontology.mex.MEXALGO_10;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by esteves on 25.06.15.
 */
public class ExperimentConfigurationVO extends InstanceObjects {

    private String                     _id;
    private String                     _description;
    private Integer                    _seq = 0;

    private ModelVO                    _model;
    private PhaseVO                    _phase;
    private SamplingMethodVO           _sampling;
    private HardwareConfigurationVO    _hard;
    private DataSetVO                  _ds;
    private ToolVO                     _tool;

    private List<ToolParameterVO>      _toolParameters;
    private List<Execution>            _executions;
    private List<FeatureVO>            _features;
    private List<AlgorithmVO>          _algorithms;

    private int                        _maxLabel = 30;
    private static String              _userHash;
    private static final Logger        LOGGER = LoggerFactory.getLogger(ExperimentConfigurationVO.class);



    public ExperimentConfigurationVO(String id, String description, Integer sequential) {
        this._id = id;
        this._description = description;
        this._executions = new ArrayList<>();
        this._features = new ArrayList<>();
        this._algorithms = new ArrayList<>();
       // this._ds = new DataSetVO();
        this._label = _description + "...";
        if (this._label.length() > _maxLabel){
            this._label = this._label.substring(0,_maxLabel-1)+ "...";
        }
        this._seq = sequential;
        this._individualName = "exp_cf_" + sequential.toString() + "_";
        this._label = "Configuration for a set of Executions";
    }

    public ExperimentConfigurationVO(String id, Integer sequential) {
        this._id = id;
        this._executions = new ArrayList<>();
        this._features = new ArrayList<>();
        this._algorithms = new ArrayList<>();
       // this._ds = new DataSetVO();
        this._seq = sequential;
        this._individualName = "exp_cf_" + sequential.toString() + "_";
        this._label = "Configuration for a set of Executions";
    }

    /**********************************************************************************************************************************************
     *                                                                  getters
     **********************************************************************************************************************************************/

    /**
     * returns the label for an experiment configuration
     * @return
     */
    public String getLabel(){
        return this._label;
    }

    /**
     * returns the id of an experiment configuration
     * @return
     */
    public String getId() {
        return _id;
    }

    /**
     * returns the description of an experiment configuration
     * @return
     */
    public String getDescription() {
        return _description;
    }

    /* complex objects */

    /**
     * gets the Model of a configuration
     * @return
     */
    public ModelVO Model() {
        if (this._model == null){
            this._model = new ModelVO();
            this._model.setIndividualName("mod_cf_" + this._seq + "_");
        }
        return this._model;
    }

    /**
     * gets the Phase of a configuration
     * @return
     */
    public PhaseVO Phase() {
        if (this._phase == null){
            this._phase = new PhaseVO(MEXEnum.EnumPhases.NOT_INFORMED);
            this._phase.setIndividualName("phase_cf_" + this._seq + "_");
        }
        return this._phase;
    }

    /**
     * gets the Sampling Method of a configuration
     * @return
     */
    public SamplingMethodVO SamplingMethod(){
        try {
            if (this._sampling == null) {
                this.setSamplingMethod(MEXEnum.EnumSamplingMethods.NOT_INFORMED, null);
                this._sampling.setIndividualName("sm_cf_" + this._seq + "_");
            }
            return this._sampling;
        }
        catch (Exception e){
            return null;
        }
    }

    /**
     * gets the Hardware of a configuration
     * @return
     */
    public HardwareConfigurationVO HardwareConfiguration() {
        try {
            if (this._hard == null) {
                this.setHardwareConfiguration(null, MEXEnum.EnumProcessors.NOT_INFORMED, MEXEnum.EnumRAM.NOT_INFORMED, null, MEXEnum.EnumCaches.NOT_INFORMED);
                this._hard.setIndividualName("hard_cf_" + this._seq + "_");
            }
            return this._hard;
        }
        catch (Exception e){
            return null;
        }
    }

    /**
     * gets a Software Implementation of a configuration
     * @return
     */
    public ToolVO Tool() {
        try {
            if (this._tool == null) {
                this.setTool("", null);
            }
            return this._tool;
        }
        catch (Exception e){
            return null;
        }
    }

    /**
     * gets the Dataset of a configuration
     * @return
     */
    public DataSetVO DataSet() {
        if (_ds == null) {
            this._ds = new DataSetVO();
            this._ds.setIndividualName("ds_cf_" + this._seq + "_");
        }
        return this._ds;
    }

    /**
     * gets a specific algorithm of a configuration based on the algorithm class
     * @param algo
     * @return
     */
    public AlgorithmVO Algorithm(MEXEnum.EnumAlgorithmsClasses algo){
        if (this._algorithms == null) {
            this._algorithms = new ArrayList<>();}

        AlgorithmVO ret  = null;
        try {
            Collection<AlgorithmVO> t = Collections2.filter(this._algorithms, p -> p.getClassName().equals(algo.name()));
            if (t != null && t.size() > 0){
                ret = Iterables.get(t, 0);
            }
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
        return ret;
    }

    /**
     * gets a specific algorithm of a configuration based on the algorithm class
     * @param algoInstanceName
     * @return
     */
    public AlgorithmVO Algorithm(String algoInstanceName){
        if (this._algorithms == null) {
            this._algorithms = new ArrayList<>();}

        AlgorithmVO ret  = null;
        try {
            Collection<AlgorithmVO> t = Collections2.filter(this._algorithms, p -> p.getIndividualName().equals(algoInstanceName));
            if (t != null && t.size() > 0){
                ret = Iterables.get(t, 0);
            }
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
        return ret;
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

    /**
     * returns all the algorithms of a configuration
     * @return
     */
    protected List<AlgorithmVO> getAlgorithms(){
        return this._algorithms;
    }

    protected List<FeatureVO> getFeatures(){
        return this._features;
    }

    protected List<ToolParameterVO> getToolParameters(){
        return this._toolParameters;
    }

    protected List<Execution> getExecutions(){
        return this._executions;
    }

    /**********************************************************************************************************************************************
     *                                                                  setters
     **********************************************************************************************************************************************/

    /**
     * set the experiment configuration id
     * @param _id
     */
    public void setId(String _id) {
        this._id = _id;
    }

    /**
     * set the experiment configuration description
     * @param _description
     */
    public void setDescription(String _description) {
        this._description = _description;
        this._label = _description + "...";
        if (this._label.length() > _maxLabel){
            this._label = this._label.substring(0,_maxLabel-1) + "...";
        }
    }

    /**
     * set the start time for given execution
     * @param executionId
     * @param value
     */
    public void setExecutionStartTime(String executionId, Date value){
        try {
            Collection<Execution> t = Collections2.filter(this._executions, p -> p._id.equals(executionId));
            if (t != null && t.size() >0){Iterables.get(t, 0).setStartDate(value);}
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
    }

    /**
     * set the end time for given execution
     * @param executionId
     * @param value
     */
    public void setExecutionEndTime(String executionId, Date value){
        try {
            Collection<Execution> t = Collections2.filter(this._executions, p -> p._id.equals(executionId));
            if (t != null && t.size() > 0){Iterables.get(t, 0).setEndDate(value);}
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
    }


    /**
     * set the Execution identification
     * @param index
     * @param id
     */
    public void setExecutionId(Integer index, String id){
        this._executions.get(index)._id = id;
    }

    /**********************************************************************************************************************************************
     *                                                                  functions
     ***********************************************************************************************************************************************/

    /**
     * add one Execution to the List of Executions in the ExecutionConfiguration
     * @param type
     * @param phase
     * @return
     * @throws Exception
     */
    public String addExecution(MEXEnum.EnumExecutionsType type, MEXEnum.EnumPhases phase) throws Exception{

        Integer total;
        String execCode;

        total = this._executions.size() + 1;

        if (type.toString().equals(MEXEnum.EnumExecutionsType.SINGLE.toString())) {
            this._executions.add(new ExecutionIndividualVO(this, "C" + this._seq.toString() + "_" + MEXConstant.DEFAULT_EXEC_ID + String.valueOf(total), new PhaseVO(phase)));
        }
        if (type.toString().equals(MEXEnum.EnumExecutionsType.OVERALL.toString())) {
            this._executions.add(new ExecutionSetVO(this, "C" + this._seq.toString() + "_" + MEXConstant.DEFAULT_EXEC_ID + String.valueOf(total), new PhaseVO(phase)));
        }
        execCode = "C" + this._seq.toString() + "_" + MEXConstant.DEFAULT_EXEC_ID + total.toString();
        LOGGER.debug(execCode);
        return execCode;

    }


    /**
     * add a tool parameter associated to a set of executions
     * @param key
     * @param value
     */
    public void addToolParameters(String key, String value) throws Exception{

        //int indexaux = 0;

        try {

            if (this._toolParameters == null) {
                this._toolParameters = new ArrayList<>();}
            else {
                //indexaux = this._toolParameters.size() + 1;
            }

            Collection<ToolParameterVO> t = Collections2.filter(this._toolParameters, p -> p.getId().equals(key));

            if (t != null && t.size() > 0){
                throw new Exception("Tool parameter key " + key + " already assigned");
            }

            else {
                ToolParameterVO tp = new ToolParameterVO(key, value);
                //tp.setIndividualName("tool_par_" + String.valueOf(indexaux + 1) + "_cf_" + this._seq + "_");
                this._toolParameters.add(tp);
            }

        } catch (Exception e){
            throw new Exception(e);
        }

    }


    private void _addFeatures(String[] featuresName){
        if (this._features == null) {
            this._features = new ArrayList<>();}

        int size = featuresName.length;
        for (int i=0; i<size; i++)
        {
            String feature = featuresName[i];
            try {
                Collection<FeatureVO> t = Collections2.filter(this._features, p -> p.getId().equals(feature));
                if (t != null && t.size() > 0){throw new Exception("Features already assigned");}
                else {
                    this._features.add(new FeatureVO(String.valueOf(this._features.size()+1), feature));
                }
            } catch (Exception e){
                System.out.println(e.toString());
            }
        }
    }

    /**
     * add a feature associated to a set of executions
     * @param featureName
     */
    public void addFeature(String featureName){
       _addFeatures(new String[]{featureName});
    }

    /**
     * add recursively a set of features associated to a set of executions
     * @param featuresName
     */
    public void addFeature(String[] featuresName){
        _addFeatures(featuresName);
    }

    /**
     * Set the Sampling Method for a given Experiment Configuration.
     * There is an automatic counter in order to deal with more the 1 experiment configuration grouped in 1 experiment, i.e.,
     * We would have (for instance) 2 Sampling Methods instances at the same model. Therefore, different id's are required.
     * @param sm
     * @param train
     * @param test
     * @throws Exception
     */
    public void setSamplingMethod(MEXEnum.EnumSamplingMethods sm, Double train, Double test) throws Exception{

        try{

            if (this._sampling == null) {
                //String individualName = MEXCORE_10.ClasseTypes.SAMPLING_METHOD.toLowerCase() +
                //        String.valueOf(MEXController.getInstance().getNumberOfSamplingMethods() + 1);

                this._sampling = new SamplingMethodVO(sm, train, test);

                MEXController.getInstance().addSamplingMethodCounter();
            }



        }catch (Exception e){
            System.out.println(e.toString());
        }

    }

    /**
     * Set the Sampling Method for a given Experiment Configuration.
     * There is an automatic counter in order to deal with more the 1 experiment configuration grouped in 1 experiment, i.e.,
     * We would have (for instance) 2 Sampling Methods instances at the same model. Therefore, different id's are required.
     * @param sm
     * @param folds
     * @throws Exception
     */
    public void setSamplingMethod(MEXEnum.EnumSamplingMethods sm, Integer folds) throws Exception{

        try{

            if (this._sampling == null) {
                //String individualName = MEXCORE_10.ClasseTypes.SAMPLING_METHOD.toLowerCase() +
                //        String.valueOf(MEXController.getInstance().getNumberOfSamplingMethods() + 1);

                this._sampling = new SamplingMethodVO(sm);
                if (folds != null) this._sampling.setFolds(folds);

                MEXController.getInstance().addSamplingMethodCounter();
            }

        }catch (Exception e){
            System.out.println(e.toString());
        }

    }

    /**
     * set the Model associated to a set of executions
     * @param id
     * @param description
     * @param date
     */
    public void setModel(String id, String description, Date date){
        if (this._model == null){
            this._model = new ModelVO();
        }
        this._model.setDate(date);
        this._model.setDescription(description);
        this._model.setId(id);
    }

    /**
     * set the Model associated to a set of executions
     * @param id
     * @param description
     */
    public void setModel(String id, String description){
        if (this._model == null){
            this._model = new ModelVO();
        }
        this._model.setDescription(description);
        this._model.setId(id);
    }

    /**
     * set the Model associated to a set of executions
     * @param id
     */
    public void setModel(String id){
        if (this._model == null){
            this._model = new ModelVO();
        }
        this._model.setId(id);
    }

    /**
     * set the Hardware Configuration associated to a set of executions
     * @param os
     * @param cpu
     * @param mb
     * @param hd
     * @param cache
     */
    public void setHardwareConfiguration(String os, MEXEnum.EnumProcessors cpu, MEXEnum.EnumRAM mb, String hd, MEXEnum.EnumCaches cache){
        if (this._hard == null){
            this._hard = new HardwareConfigurationVO();
        }
        this._hard.setCache(cache);
        this._hard.setOperationalSystem(os);
        this._hard.setCPU(cpu);
        this._hard.setMemory(mb);
        this._hard.setHD(hd);

    }

    /**
     * set the Dataset associated to a set of executions
     * @param URI
     * @param description
     * @param name
     */
    public void setDataSet(String URI, String description, String name){
        if (this._ds == null){
            this._ds = new DataSetVO();
        }
        this._ds.setDescription(description);
        this._ds.setName(name);
        this._ds.setURI(URI);
    }

    /**
     * set the Dataset associated to a set of executions
     * @param URI
     * @param name
     */
    public void setDataSet(String URI, String name){
        if (this._ds == null){
            this._ds = new DataSetVO();
        }
        this._ds.setName(name);
        this._ds.setURI(URI);
    }

    /**
     * set the Software Implementation associated to a set of executions
     * @param name
     * @param version
     */
    public void setTool(MEXEnum.EnumTools name, String version){
        if (this._tool == null){
            this._tool = new ToolVO();
        }
        this._tool.setRevision(version);
        this._tool.setName(name.name());
    }

    /**
     * set the Software Implementation associated to a set of executions
     * @param name
     * @param version
     */
    public void setTool(String name, String version){
        if (this._tool == null){
            this._tool = new ToolVO();
        }
        this._tool.setRevision(version);
        this._tool.setName(name);
    }

    /**
     * add an Algorithm available in an Experiment Configuration
     * @param algorithmId
     * @param algorithmURI
     * @return algorithm's instance name
     * @throws Exception
     */
    public String addAlgorithm(String algorithmId, URI algorithmURI) throws Exception {
        return _addAlgorithm(algorithmId, null, null, algorithmURI, null).getIndividualName();
    }

    /**
     * add an Algorithm available in an Experiment Configuration
     * @param algorithmId
     * @param algorithmLabel
     * @return algorithm's instance name
     * @throws Exception
     */
    public String addAlgorithm(String algorithmId, String algorithmLabel) throws Exception {
        return _addAlgorithm(algorithmId, algorithmLabel, null, null, null).getIndividualName();
    }

    /**
     * add an Algorithm available in an Experiment Configuration
     * @param algorithmId
     * @param algorithmName
     * @param algorithmURI
     * @return algorithm's instance name
     * @throws Exception
     */
    public String addAlgorithm(String algorithmId, String algorithmName, URI algorithmURI) throws Exception {
        return _addAlgorithm(algorithmId, algorithmName, null, algorithmURI, null).getIndividualName();
    }

    /**
     * add an Algorithm available in an Experiment Configuration
     * @param algorithmId
     * @param algorithmClass
     * @return algorithm's instance name
     * @throws Exception
     */
    public String addAlgorithm(String algorithmId, MEXEnum.EnumAlgorithmsClasses algorithmClass) throws Exception {
        return _addAlgorithm(algorithmId, null, algorithmClass, null, null).getIndividualName();
    }

    /**
     * add an Algorithm available in an Experiment Configuration
     * @param algorithmId
     * @param algorithmClass
     * @param algorithmLabel
     * @return algorithm's instance name
     * @throws Exception
     */
    public String addAlgorithm(String algorithmId, MEXEnum.EnumAlgorithmsClasses algorithmClass, String algorithmLabel) throws Exception {
        return _addAlgorithm(algorithmId, algorithmLabel, algorithmClass, null, null).getIndividualName();
    }

    /**
     * add an Algorithm available in an Experiment Configuration
     * @param algorithmId
     * @param algorithmName
     * @param algorithmClass
     * @param algorithmURI
     * @return algorithm's instance name
     * @throws Exception
     */
    public String addAlgorithm(String algorithmId, String algorithmName, MEXEnum.EnumAlgorithmsClasses algorithmClass, URI algorithmURI) throws Exception {
        return _addAlgorithm(algorithmId, algorithmName, algorithmClass, algorithmURI, null).getIndividualName();
    }

    /**
     * add an Algorithm available in an Experiment Configuration
     * @param algorithmId
     * @param algorithmAcronym
     * @param algorithmClass
     * @param algorithmURI
     * @param algorithmLabel
     * @return algorithm's instance name
     * @throws Exception
     */
    public String addAlgorithm(String algorithmId, String algorithmLabel, MEXEnum.EnumAlgorithmsClasses algorithmClass, URI algorithmURI, String algorithmAcronym) throws Exception {
        return _addAlgorithm(algorithmId, algorithmLabel, algorithmClass, algorithmURI, algorithmAcronym).getIndividualName();
    }

    /**
     * add an Algorithm available in an Experiment Configuration
     * @param algorithmId
     * @param algorithmClass
     * @param algorithmURI
     * @return algorithm's instance name
     * @throws Exception
     */
    public String addAlgorithm(String algorithmId, URI algorithmURI, MEXEnum.EnumAlgorithmsClasses algorithmClass) throws Exception {
        return _addAlgorithm(algorithmId, null, algorithmClass, algorithmURI, null).getIndividualName();
    }

    private AlgorithmVO _addAlgorithm(String id, String algorithmLabel, MEXEnum.EnumAlgorithmsClasses algorithmClass, URI algorithmURI, String algorithmAcronym) throws Exception{

        AlgorithmVO algo = null;
        String klass = "";
        try{

            try{
                if (algorithmClass != null && StringUtils.isNotEmpty(algorithmClass.name()))
                {
                    MEXEnum.EnumAlgorithmsClasses.valueOf(algorithmClass.toString());
                    klass = algorithmClass.name().toString();
                }
            }catch (Exception e){
                throw new Exception(algorithmClass.toString() + " does not exist in MEXEnum.EnumAlgorithmsClasses. Please, check the method you're invoking...");
            }

            if (this._algorithms == null) {
                this._algorithms = new ArrayList<>();}

            String idaux = MEXALGO_10.ClasseTypes.ALGORITHM.toLowerCase() +
                    String.valueOf(MEXController.getInstance().getNumberOfAlgorithms() + 1);

            if (id == "") id = idaux;

            if (algorithmLabel == null || algorithmLabel == "") algorithmLabel = idaux;


            algo = new AlgorithmVO(id, algorithmAcronym, algorithmLabel, algorithmURI, klass);
            this._algorithms.add(algo);

            MEXController.getInstance().addAlgorithmCounter();

        }catch (Exception e){
            System.out.println(e.toString());
        }

        return algo;

    }

    @Override
    public boolean equals(Object other){
        return false;
    }

    @Override
    public int hashCode(){
        return 0;
    }

}
