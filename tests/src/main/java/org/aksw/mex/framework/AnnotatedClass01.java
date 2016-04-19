package org.aksw.mex.framework;

import org.aksw.mex.framework.annotations.Start;
import org.aksw.mex.framework.annotations.algo.Algorithm;
import org.aksw.mex.framework.annotations.core.*;
import org.aksw.mex.framework.annotations.perf.Measure;
import org.aksw.mex.InterfaceLocalVariable;
import org.aksw.mex.repeating.Test;
import org.aksw.mex.util.MEXEnum;
import weka.classifiers.trees.J48;
import weka.core.Instances;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by dnes on 12/12/15.
 */

@ExperimentInfo(
        priority = ExperimentInfo.Priority.HIGH,
        createdBy = "Esteves",
        email = "diegoesteves@gmail.com",
        tags = {"vocabulary","MEX"}
)

public class AnnotatedClass01 {

    @Measure(idMeasure = MEXEnum.EnumMeasures.ACCURACY, algorithmID = "1") public double _accuracy1;
    @Measure(idMeasure = MEXEnum.EnumMeasures.ACCURACY, algorithmID = "2") public double _accuracy2;
    @ExecutionStartTime public long[] _startTime;
    @ExecutionEndTime public long[] _endTime;
    @DatasetName public String _ds = "weather.arff";
    @Algorithm(algorithmType = MEXEnum.EnumAlgorithms.J48, algorithmID = "1", idExecution = "1") public J48 _wekaAlg1;
    @Algorithm(algorithmType = MEXEnum.EnumAlgorithms.PART, algorithmID = "2", idExecution = "2") public J48 _wekaAlg2;

    public static void main(String[] args) {

        //AnnotatedClass01 usr = new AnnotatedClass01();
        //usr.runProcess();
    }

    public AnnotatedClass01(){

    }

    @Start
    public double runProcess(){

        this._startTime[0] = System.currentTimeMillis();

        this._accuracy1 = 56.9;

        this._accuracy2 = 66.1;

        this._endTime[0] = System.currentTimeMillis();

        return 10.8d;
    }

    @Execution(enabled = true)
    public double getAccuracy(boolean p1){
        return 0.325;
    }


    @Test
    public void getAccuracy(){

    }


    public void doSomething(){


        try{

            BufferedReader datafile = readDataFile(_ds);
            Instances data = new Instances(datafile);
            data.setClassIndex(data.numAttributes() - 1);

            @InterfaceLocalVariable
            int teste = 0;

            teste = 10;



        } catch (Exception e) {
            System.out.println(e.toString());
        }


}

    public static BufferedReader readDataFile(String filename) {

        BufferedReader bReader = null;

        try {
            bReader = new BufferedReader(new InputStreamReader(
                    AnnotatedClass01.class.getResourceAsStream("/" + filename)));

        } catch (Exception ex) {
            System.err.println("File not found: " + filename);
        }
        return bReader;
    }


}