package framework;

/**
 * This is an example of usage of the MEX Framework v.0.0.1
 * Here, we annotate a generic machine learning class which uses Weka (http://weka.sourceforge.net/doc.stable/)
 * with MEX Annotations. This class will then be processed by the MEX Framework which reads the class and,
 * in run-time, automatically generates the produced metadata
 * see more: https://github.com/AKSW/mexproject
 * @author esteves.
 */
import org.aksw.mex.framework.annotations.InterfaceVersion;
import org.aksw.mex.framework.annotations.Start;
import org.aksw.mex.framework.annotations.algo.Algorithm;
import org.aksw.mex.framework.annotations.core.*;
import org.aksw.mex.framework.annotations.perf.Measure;
import org.aksw.mex.util.MEXEnum;
import org.apache.log4j.Logger;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.evaluation.NominalPrediction;
import weka.classifiers.rules.DecisionTable;
import weka.classifiers.rules.PART;
import weka.classifiers.trees.DecisionStump;
import weka.classifiers.trees.J48;
import weka.core.FastVector;
import weka.core.Instances;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@ExperimentInfo(createdBy = "Esteves", email = "esteves@informatik.uni-leipzig.de", title = "Weka Lib Example", tags = {"WEKA","J48", "DecisionTable", "MEX", "Iris"})
@Hardware(cpu = MEXEnum.EnumProcessors.INTEL_COREI7, memory = MEXEnum.EnumRAM.SIZE_8GB, hdType = "SSD")
@SamplingMethod(klass = MEXEnum.EnumSamplingMethods.CROSS_VALIDATION, trainSize = 0.5, testSize = 0.5, folds = 10)
@InterfaceVersion(version = MEXEnum.EnumAnnotationInterfaceStyles.M1)
public class WekaExample001 {

    private final static Logger LOG = Logger.getLogger(WekaExample001.class);


    @DatasetName
    public String ds = "iris.arff"; Instances data;
    @Algorithm(algorithmType = MEXEnum.EnumAlgorithmsClasses.J48,           algorithmID = "1") public J48 wekaJ48;
    @Algorithm(algorithmType = MEXEnum.EnumAlgorithmsClasses.PART,          algorithmID = "2") public PART wekaPART;
    @Algorithm(algorithmType = MEXEnum.EnumAlgorithmsClasses.DecisionTable, algorithmID = "3") public DecisionTable wekaDecisionTable;
    @Algorithm(algorithmType = MEXEnum.EnumAlgorithmsClasses.DecisionStump, algorithmID = "4") public DecisionStump wekaDecisionStump;
    @Measure(idMeasure = MEXEnum.EnumMeasures.ERROR) public List<Double> errors;
    @Measure(idMeasure = MEXEnum.EnumMeasures.ACCURACY) public List<Double> accuracies;


    public static void main(String[] args)
    {
        try {
            WekaExample001 e = new WekaExample001();
            e.start();
        }
        catch (Exception e){
            LOG.error("Error: " + e.toString());}
    }

    public WekaExample001(){
        wekaJ48 = new J48();
        wekaPART = new PART();
        wekaDecisionTable = new DecisionTable();
        wekaDecisionStump = new DecisionStump();
        errors = new ArrayList<>();
        accuracies = new ArrayList<>();
    }

    public static BufferedReader readDataFile(String filename) {

        BufferedReader bReader = null;

        try {
            bReader = new BufferedReader(new InputStreamReader(
                    WekaExample001.class.getResourceAsStream("/" + filename)));

        } catch (Exception ex) {
            System.err.println("File not found: " + filename);
        }
        return bReader;
    }
    public static Evaluation classify(Classifier model, Instances trainingSet, Instances testingSet) throws Exception {
        Evaluation evaluation = new Evaluation(trainingSet);

        model.buildClassifier(trainingSet);
        evaluation.evaluateModel(model, testingSet);

        return evaluation;
    }

    @Features
    public String[] getFeatures(){
        String[] ret = null;
        if (this.ds != null) {
            ret = new String[this.data.numAttributes()-1];
            for (int i = 0; i < this.data.numAttributes() - 1; i++)
                ret[i] = this.data.attribute(i).name();
        }
        return ret;
    }

    public static double calculateAccuracy(FastVector predictions) {
        double correct = 0;

        for (int i = 0; i < predictions.size(); i++) {
            NominalPrediction np = (NominalPrediction) predictions.elementAt(i);
            if (np.predicted() == np.actual()) {
                correct++;
            }
        }

        return 100 * correct / predictions.size();
    }
    public static Instances[][] crossValidationSplit(Instances data, int numberOfFolds) {
        Instances[][] split = new Instances[2][numberOfFolds];

        for (int i = 0; i < numberOfFolds; i++) {
            split[0][i] = data.trainCV(numberOfFolds, i);
            split[1][i] = data.testCV(numberOfFolds, i);
        }

        return split;
    }

    @Start
    public void start(){

        try {

            BufferedReader datafile = readDataFile(ds);
            data = new Instances(datafile);
            data.setClassIndex(data.numAttributes() - 1);

            Instances[][] split = crossValidationSplit(data, 10);
            @TestDataSet2
            Instances[] trainingSplits = split[0];
            Instances[] testingSplits = split[1];

            Classifier[] models = {this.wekaJ48, this.wekaPART, this.wekaDecisionTable, this.wekaDecisionStump};

            for (int j = 0; j < models.length; j++) {
                FastVector predictions = new FastVector();
                for (int i = 0; i < trainingSplits.length; i++) {
                    Evaluation validation = classify(models[j], trainingSplits[i], testingSplits[i]);
                    predictions.appendElements(validation.predictions());
                    LOG.debug(models[j].toString());
                }

                accuracies.add(calculateAccuracy(predictions));
                errors.add(100-accuracies.get(j));

                LOG.info("Accuracy of " + models[j].getClass().getSimpleName() + ": " + String.format("%.2f%%", accuracies.get(j).doubleValue()) + " - Error: " + String.format("%.2f%%", errors.get(j).doubleValue()));
            }

        } catch (Exception e) {
            LOG.error(e.toString());
        }
    }
}