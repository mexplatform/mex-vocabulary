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
import org.aksw.mex.log4mex.core.ExampleVO;
import org.aksw.mex.log4mex.core.PhaseVO;
import org.aksw.mex.log4mex.perf.IMeasure;
import org.aksw.mex.log4mex.perf.example.ExamplePerformanceMeasureVO;
import org.aksw.mex.log4mex.perf.overall.*;
import org.aksw.mex.util.MEXEnum;
import org.apache.commons.lang3.EnumUtils;

import java.util.*;

/**
 * Created by esteves on 26.06.15.
 */
public abstract class Execution extends InstanceObjects {

    protected String                    _id;
    protected String                    _targetClass;
    protected Date                      _startedAtTime;
    protected Date                      _endedAtTime;
    protected String                    _errorMessage;

    protected ExperimentConfigurationVO _expConf;
    protected PhaseVO                   _phase;
    protected AlgorithmVO               _algo;

    protected List<ExampleVO>           _examples;
    protected List<Measure>             _performances;

    public Execution(){
        this._performances = new ArrayList<>();
        this._examples = new ArrayList();
    }


    /**********************************************************************************************************************************************
     *                                                                  getters
     **********************************************************************************************************************************************/

    /**
     * gets the id of an execution
     * @return
     */
    protected String getId() {
        return _id;
    }

    /**
     * gets the target class for given execution
     * @return
     */
    protected String getTargetClass() {
        return _targetClass;
    }

    /**
     * gets the start time for given execution
     * @return
     */
    protected Date getStartedAtTime() {
        return _startedAtTime;
    }

    /**
     * gets end time for given execution
     * @return
     */
    protected Date getEndedAtTime() {
        return _endedAtTime;
    }

    /**
     * gets the Phase associated to an Execution
     * @return
     */
    protected PhaseVO getPhase() {
        return _phase;
    }

    /**
     * gets the Algorithm associated to an Execution
     * @return
     */
    protected AlgorithmVO getAlgorithm() {
        return _algo;
    }

    /**
     * gets the ExperimentConfiguration which groups an Execution
     * @return
     */
    private ExperimentConfigurationVO getExpConf() {
        return _expConf;
    }

    /**
     * gets the Performance measures for an Execution
     * @return
     */
    protected List<Measure> getPerformances() {
        return _performances;
    }

    /**
     * gets the Example (in case of mexcore:SingleExecution) or Examples (in case of a mexcore:OverallExecution)
     * associated to an Execution. It is worth to note that we always have a list of Examples once we each cell
     * in a table is an mexcore:Example class. mexcore:datasetColumn and mexcore:datasetRow indicate the position
     * and prov:value contains the desired value of a cell
     * @return
     */
    protected List<ExampleVO> getExamples(){
        return this._examples;
    }

    protected List<Measure> getPerformances(Class<? extends IMeasure> klass){
        List<Measure> _p = new ArrayList<>();
        for(Iterator<? extends Measure> i = this._performances.iterator(); i.hasNext(); ) {
            Measure item = i.next();
            if (item.getClass().equals(klass))
                _p.add(item);
        }
        return _p;
    }

    protected String getErrorMessage(){ return this._errorMessage;}
    
    /**********************************************************************************************************************************************
     *                                                                  setters
     **********************************************************************************************************************************************/

    public abstract void setStartsAtPosition(String value);

    public abstract void setEndsAtPosition(String value);

    public void setTargetClass(String _targetClass) {
        this._targetClass = _targetClass;
    }

    public void setStartDate(Date value){
        this._startedAtTime = value;
    }

    public void setEndDate(Date value){
        this._endedAtTime = value;
    }

    public void setAlgorithm(AlgorithmVO value){
        this._algo = value;
    }

    /**
     *
     * @param algorithmidentifier (instanceName or algorithmID)
     * @return
     * @throws Exception
     */
    public boolean setAlgorithm(String algorithmidentifier) throws Exception{
        try{
            //check whether the algorithm exists into the experiment configuration
            Collection<AlgorithmVO> t = Collections2.filter(this.getExpConf().getAlgorithms(),
                    p -> (p instanceof AlgorithmVO && (p.getIndividualName().equals(algorithmidentifier) || p.getIdentifier().equals(algorithmidentifier))));
            if (t != null && t.size() > 0){
                this._algo = Iterables.get(t, 0);
            }else{
                throw new Exception("The algorithm " + algorithmidentifier + " does not belong to the experiment");
            }

        }catch (Exception e){
            throw new Exception(e);
        }
        return true;
    }

    public void setErrorMessage(String value){
        this._errorMessage = value;
    }

    /**********************************************************************************************************************************************
     *                                                                  functions
     ***********************************************************************************************************************************************/

    /***
     * add UserDefinedMeasure
     * @param id
     * @param description
     * @param formula
     * @param value
     * @return
     */
    public boolean addPerformance(String id, String description, String formula, Double value){
        Measure m = new UserDefinedMeasureVO(id, description, formula, value);
        return this._performances.add(m);
    }

    /**
     * add PerformanceMeasure
     * @param exampleIdentifier
     * @param predictedValue
     * @param realValue
     * @return
     */
    public boolean addPerformance(String exampleIdentifier, String predictedValue, String realValue){
        ExamplePerformanceMeasureVO m = new ExamplePerformanceMeasureVO(exampleIdentifier, predictedValue, realValue);
        return this._performances.add(m);
    }

    /***
     * add PerformanceMeasure (related to OverallExecution)
     * @param m
     * @param v
     * @return
     */
    public void addPerformance(MEXEnum.EnumMeasures m, double v) throws Exception{
        String type = "";
        String p = m.toString().replace("_","").toUpperCase();
        String paux = m.toString().replace("_","");

        boolean ret = false;

        try{
            type = "cla";
            if (EnumUtils.isValidEnum(MEXEnum.EnumClassificationMeasure.class, p) == false){
                type = "reg";
                if (EnumUtils.isValidEnum(MEXEnum.EnumRegressionMeasure.class, p) == false){
                    type = "sta";
                    if (EnumUtils.isValidEnum(MEXEnum.EnumStatisticalMeasure.class, p) == false){
                        type = "clu";
                        if (EnumUtils.isValidEnum(MEXEnum.EnumClusteringMeasure.class, p) == false){
                            throw new Exception("measure has not been found: " + m.toString());}
                    }
                }
            }

            switch (type) {
                case "cla":
                    addClassificationPerformance(paux,v);
                    break;
                case "reg":
                    addRegressionPerformance(paux,v);
                    break;
                case "sta":
                    addStatisticalPerformance(paux,v);
                    break;
                case "clu":
                    addClusteringPerformance(paux,v);
                    break;
                default:
                    throw new Exception("measure has not been found: " + p);
            }

        }catch (Exception e){
            throw (e);
        }

    }

    private boolean addClassificationPerformance(String p, double value) {
        ClassificationMeasureVO m = new ClassificationMeasureVO();
        m.setValue(value);
        m.setName(p);
        return this._performances.add(m);
    }

    private boolean addRegressionPerformance(String p, double value) {
        RegressionMeasureVO m = new RegressionMeasureVO();
        m.setValue(value);
        m.setName(p);
        return this._performances.add(m);
    }

    private boolean addStatisticalPerformance(String p, double value) {
        StatisticalMeasureVO m = new StatisticalMeasureVO();
        m.setValue(value);
        m.setName(p);
        return this._performances.add(m);
    }

    private boolean addClusteringPerformance(String p, double value) {
        ClusteringMeasureVO m = new ClusteringMeasureVO();
        m.setValue(value);
        m.setName(p);
        return this._performances.add(m);
    }

    /**
     * Add a dataset example to the execution
     * @param id
     * @param value value of the example item
     * @param datasetRow indicates the dataset row
     * @param datasetColumn indicates the dataset column
     * @return
     * @throws Exception
     */
    public boolean addDatasetExample(String id, String value, long datasetRow, long datasetColumn, MEXEnum.EnumExamplesType type) throws Exception{
        try {
            ExampleVO example = new ExampleVO();
            example.setId(id);
            example.setValue(value);
            example.setDatasetRow(datasetRow);
            example.setDatasetColumn(datasetColumn);
            example.setExampleType(type.toString());
            this._examples.add(example);
            return true;
        }catch (Exception e){
            throw e;
        }
    }
}
