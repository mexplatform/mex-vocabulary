package examples.framework;

import org.aksw.mex.tests.framework.Start;
import org.aksw.mex.tests.framework.algo.Algorithm;
import org.aksw.mex.tests.framework.core.*;
import org.aksw.mex.tests.framework.perf.Measure;
import jsat.ARFFLoader;
import jsat.DataSet;
import jsat.classifiers.CategoricalResults;
import jsat.classifiers.ClassificationDataSet;
import jsat.classifiers.Classifier;
import jsat.classifiers.DataPoint;
import jsat.classifiers.bayesian.NaiveBayes;
import org.aksw.mex.util.MEXEnum;

import java.io.File;

/**
 * Created by dnes on 18/12/15.
 */

@ExperimentInfo(createdBy = "Esteves", email = "esteves@informatik.uni-leipzig.de", tags = {"JSAT","Naive Bayes", "NB", "MEX", "Iris"})
@Hardware(cpu = "Intel Core i7", memory = "8 GB", hdType = "SSD")
@SamplingMethod(trainSize = 1.0, testSize = 1.0)
public class ExampleJSAT {

    @Dataset String filename = "iris.arff";
    DataSet ds;
    @Algorithm(idExecution = "1", idAlgorithm = MEXEnum.EnumAlgorithms.NaiveBayes) Classifier classifier; ClassificationDataSet cDataSet;
    @Measure(idExecution = "001", idMeasure = MEXEnum.EnumMeasures.ERROR) int errors = 0;

    public static void main(String[] args)
    {

    }


    @Start
    public void doMachineLearning(){
        try{
            //ExampleJSAT e = new ExampleJSAT();
            loadDataSet();
            Training();
            Test();
        }catch (Exception e){
            System.out.print("Error: " + e.toString());}
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

    public void loadDataSet(){
        //String nominalPath = "uci" + File.separator + "nominal" + File.separator;
        File file = new File(this.getClass().getResource("/" + filename).getFile());
        this.ds = ARFFLoader.loadArffFile(file);
        System.out.println("There are " + this.ds.getNumFeatures() + " features for this data set.");
        System.out.println(this.ds.getNumCategoricalVars() + " categorical features");
        System.out.println("They are:");
        for(int i = 0; i <  this.ds.getNumCategoricalVars(); i++)
            System.out.println("\t" + this.ds.getCategoryName(i));
        System.out.println(this.ds.getNumNumericalVars() + " numerical features");
        System.out.println("They are:");
        for(int i = 0; i <  this.ds.getNumNumericalVars(); i++)
            System.out.println("\t" + this.ds.getNumericName(i));

        System.out.println("\nThe whole data set");
        for(int i = 0; i < this.ds.getSampleSize(); i++)
        {
            DataPoint dataPoint = this.ds.getDataPoint(i);
            System.out.println(dataPoint);
        }


        //We specify '0' as the class we would like to make the target class.
        cDataSet = new ClassificationDataSet(this.ds, 0);

    }

    @TestProcedure
    public void Test() throws Exception {

        for(int i = 0; i < this.ds.getSampleSize(); i++)
        {
            DataPoint dataPoint = cDataSet.getDataPoint(i);//It is important not to mix these up, the class has been removed from data points in 'cDataSet'
            int truth = cDataSet.getDataPointCategory(i);//We can grab the true category from the data set
            //Categorical Results contains the probability estimates for each possible target class value.
            //Classifiers that do not support probability estimates will mark its prediction with total confidence.
            CategoricalResults predictionResults = classifier.classify(dataPoint);
            int predicted = predictionResults.mostLikely();
            if(predicted != truth)
                errors++;
            System.out.println( i + "| True Class: " + truth + ", Predicted: " + predicted + ", Confidence: " + predictionResults.getProb(predicted) );
        }
        System.out.println(errors + " errors were made, " + 100.0*errors/this.ds.getSampleSize() + "% error rate" );
    }

    @TrainingProcedure
    public void Training() throws Exception {
        if (this.ds == null){
            throw new Exception("Error: no dataset available!");
        }
        int errors = 0;
        classifier = new NaiveBayes();
        classifier.trainC(cDataSet);
    }

}
