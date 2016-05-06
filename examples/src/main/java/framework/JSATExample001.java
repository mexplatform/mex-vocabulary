package framework;

import jsat.ARFFLoader;
import jsat.DataSet;
import jsat.classifiers.CategoricalResults;
import jsat.classifiers.ClassificationDataSet;
import jsat.classifiers.Classifier;
import jsat.classifiers.DataPoint;
import jsat.classifiers.bayesian.NaiveBayes;
import jsat.linear.Vec;
import org.aksw.mex.framework.annotations.InterfaceVersion;
import org.aksw.mex.framework.annotations.Start;
import org.aksw.mex.framework.annotations.algo.Algorithm;
import org.aksw.mex.framework.annotations.core.*;
import org.aksw.mex.framework.annotations.perf.Measure;
import org.aksw.mex.util.MEXEnum;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * This is an example of usage of the MEX Framework v.0.0.1
 * Here, we annotate a generic machine learning class which uses JSAT (https://github.com/EdwardRaff/JSAT)
 * with MEX Annotations. This class will then be processed by the MEX Framework which reads the class and,
 * in run-time, automatically generates the produced metadata
 * see more: https://github.com/AKSW/mexproject
 * @author esteves.
 */


@ExperimentInfo(createdBy = "Esteves", email = "esteves@informatik.uni-leipzig.de", title = "JSAT Lib Example", tags = {"JSAT","Naive Bayes", "NB", "MEX", "Iris"})
@Hardware(cpu = MEXEnum.EnumProcessors.INTEL_COREI7, memory = MEXEnum.EnumRAM.SIZE_8GB, hdType = "SSD")
@SamplingMethod()
@InterfaceVersion(version = MEXEnum.EnumAnnotationInterfaceStyles.M1)
public class JSATExample001 {

    DataSet ds;
    @DatasetName public String filename = "iris.arff"; public ClassificationDataSet cDataSet;
    @Algorithm(algorithmID = "1" , algorithmType = MEXEnum.EnumAlgorithmsClasses.NaiveBayes) public Classifier cNB;
    @Measure(idMeasure = MEXEnum.EnumMeasures.ERROR,    algorithmID = "1", idPhase = MEXEnum.EnumPhases.TEST, executionType = MEXEnum.EnumExecutionsType.OVERALL) public List<Double> errors; //per execution
    @Measure(idMeasure = MEXEnum.EnumMeasures.ACCURACY, algorithmID = "1", idPhase = MEXEnum.EnumPhases.TEST, executionType = MEXEnum.EnumExecutionsType.OVERALL) public List<Double> accuracies; //per execution

    private final static Logger LOG = Logger.getLogger(JSATExample001.class);


    public static void main(String[] args)
    {
        try {
            JSATExample001 e = new JSATExample001();
            e.run();
        }
        catch (Exception e){
            LOG.error("Error: " + e.toString());}
    }

    public JSATExample001(){
        errors = new ArrayList<>();
        errors.add(0,0d);
        accuracies = new ArrayList<>();
        accuracies.add(0,0d);
    }

    @Start
    public void run(){
        try{
            loadDataSet();
            generataClassifier();
            doEvaluation();
        }catch (Exception e){
            LOG.error("Error: " + e.toString());}
    }

    @Features
    public String[] getFeatures(){
        String[] ret = null;
        if (this.ds != null) {
             ret = new String[this.ds.getNumNumericalVars()];
            for (int i = 0; i < this.ds.getNumNumericalVars(); i++)
                ret[i] = this.ds.getNumericName(i);
        }
        return ret;
    }

    @TestDataSet
    public Object[][] getTestData(){
        Object[][] ret = null;
        if (this.ds != null) {
            ret = new String[this.ds.getSampleSize()][this.ds.getNumNumericalVars()];
            for (int i = 0; i < this.ds.getSampleSize(); i++) {
                DataPoint d = this.ds.getDataPoint(i);
                Vec v = d.getNumericalValues();
                for (int c = 0; c < v.length(); c++) {
                    ret[i][c] = String.valueOf(v.get(c));
                }
            }

        }
        return ret;
    }

    @FinalDataSet
    public Object[][] getFinal(){
        Object[][] ret = null;
        int totClassifiers = 1;
        int cg=0;

        if (this.ds != null) {
            ret = new String[this.ds.getSampleSize()][this.ds.getNumNumericalVars() + this.ds.getNumCategoricalVars() + totClassifiers];
            for (int i = 0; i < this.ds.getSampleSize(); i++) {
                DataPoint d = this.ds.getDataPoint(i);
                Vec v = d.getNumericalValues();
                for (int c = 0; c < v.length(); c++) {
                    ret[i][c] = String.valueOf(v.get(c));
                }
                cg = v.length();
                int vc[] = d.getCategoricalValues();
                int caux = cg;
                for (int c = 0; c < vc.length; c++) {
                    ret[i][caux] = String.valueOf(vc[c]);
                    caux++;
                }
                cg+=vc.length;
                CategoricalResults predictionResults = cNB.classify(d);
                ret[i][cg] = String.valueOf(predictionResults.mostLikely());
            }

        }
        return ret;
    }


    public void loadDataSet(){
        //String nominalPath = "uci" + File.separator + "nominal" + File.separator;
        File file = new File(this.getClass().getResource("/" + filename).getFile());
        this.ds = ARFFLoader.loadArffFile(file);
        LOG.debug("There are " + this.ds.getNumFeatures() + " features for this data set.");
        LOG.debug(this.ds.getNumCategoricalVars() + " categorical features");
        LOG.debug("They are:");
        for(int i = 0; i <  this.ds.getNumCategoricalVars(); i++)
            LOG.debug("\t" + this.ds.getCategoryName(i));
        LOG.debug(this.ds.getNumNumericalVars() + " numerical features");
        LOG.debug("They are:");
        for(int i = 0; i <  this.ds.getNumNumericalVars(); i++)
            LOG.debug("\t" + this.ds.getNumericName(i));

        LOG.debug("\nThe whole data set");
        for(int i = 0; i < this.ds.getSampleSize(); i++)
        {
            DataPoint dataPoint = this.ds.getDataPoint(i);
            LOG.debug(dataPoint);
        }


        //We specify '0' as the class we would like to make the target class.
        cDataSet = new ClassificationDataSet(this.ds, 0);

    }

    @TestProcedure
    public void doEvaluation() throws Exception {
        int error = 0;
        for(int i = 0; i < this.ds.getSampleSize(); i++)
        {
            DataPoint dataPoint = cDataSet.getDataPoint(i);//It is important not to mix these up, the class has been removed from data points in 'cDataSet'
            int truth = cDataSet.getDataPointCategory(i);//We can grab the true category from the data set
            //Categorical Results contains the probability estimates for each possible target class value.
            //Classifiers that do not support probability estimates will mark its prediction with total confidence.
            CategoricalResults predictionResults = cNB.classify(dataPoint);
            int predicted = predictionResults.mostLikely();
            if(predicted != truth)
                error++;
            LOG.debug(i + "| True Class: " + truth + ", Predicted: " + predicted + ", Confidence: " + predictionResults.getProb(predicted));
        }
        LOG.debug(error + " errors were made, " + 100.0 * error / this.ds.getSampleSize() + "% error rate");
        errors.set(0, (double) 100.0 * error / this.ds.getSampleSize());
        accuracies.set(0, (double)100 - errors.get(0));

    }

    @TrainingProcedure
    public void generataClassifier() throws Exception {
        if (this.ds == null){
            throw new Exception("Error: no dataset available!");
        }
        cNB = new NaiveBayes();
        cNB.trainC(cDataSet);
    }

}
