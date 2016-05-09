package log4mex;

/**
 * Created by esteves on 03.07.15.
 */
import org.aksw.mex.log4mex.MEXSerializer;
import org.aksw.mex.log4mex.MyMEX;
import org.aksw.mex.util.MEXConstant;
import org.aksw.mex.util.MEXEnum;
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

public class ExampleWeka {
    public static BufferedReader readDataFile(String filename) {

        BufferedReader bReader = null;

        try {
            bReader = new BufferedReader(new InputStreamReader(
                    ExampleWeka.class.getResourceAsStream("/" + filename)));

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

    public static void main(String[] args) throws Exception {

        MyMEX mex = new MyMEX();

        String ds = "weather.arff";

        try {
             /* (1) basic authoring provenance */
            mex.setAuthorName("P.Creek");
            mex.setAuthorEmail("creek@example.com");
             /* (2) grouping the executions by configurations */
            String confID = mex.addConfiguration();
             /* (2.1) the dataset */
            mex.Configuration(confID).DataSet().setName(ds);

            BufferedReader datafile = readDataFile(ds);
            Instances data = new Instances(datafile);
            data.setClassIndex(data.numAttributes() - 1);

            /* (2.2) the features */
            for (int i = 0; i < data.numAttributes(); i++) {
                mex.Configuration(confID).addFeature(data.attribute(i).name());}

            Instances[][] split = crossValidationSplit(data, 10);

            /* (2.3) the sampling method */
            mex.Configuration(confID).setSamplingMethod(MEXEnum.EnumSamplingMethods.CROSS_VALIDATION, 10);

            Instances[] trainingSplits = split[0];
            Instances[] testingSplits = split[1];

            Classifier[] models = {new J48(), new PART(), new DecisionTable(), new DecisionStump()};

            /* (2.4) the algorithms and hyperparameters */
            String[] algIDs = new String[4];
            algIDs[0] = mex.Configuration(confID).addAlgorithm("j48", MEXEnum.EnumAlgorithmsClasses.J48);
            algIDs[1] = mex.Configuration(confID).addAlgorithm("part", MEXEnum.EnumAlgorithmsClasses.PART);
            algIDs[2] = mex.Configuration(confID).addAlgorithm("dt", MEXEnum.EnumAlgorithmsClasses.DecisionTable);
            algIDs[3] = mex.Configuration(confID).addAlgorithm("ds", MEXEnum.EnumAlgorithmsClasses.DecisionStump);

            /* (2.5) the executions */
            String[] execIDs = new String[models.length];

            for (int j = 0; j < models.length; j++) {
                FastVector predictions = new FastVector();
                for (int i = 0; i < trainingSplits.length; i++) {
                    Evaluation validation = classify(models[j], trainingSplits[i], testingSplits[i]);
                    predictions.appendElements(validation.predictions());
                    // Uncomment to see the summary for each training-testing pair.
                    //System.out.println(models[j].toString());
                }
                double accuracy = calculateAccuracy(predictions);

                /* (2.6) the performances for the executions */
                execIDs[j] = mex.Configuration(confID).addExecution(MEXEnum.EnumExecutionsType.OVERALL, MEXEnum.EnumPhases.TEST);
                mex.Configuration(confID).Execution(execIDs[j]).setAlgorithm(algIDs[j]);
                mex.Configuration(confID).Execution(execIDs[j]).addPerformance(MEXEnum.EnumMeasures.ACCURACY, accuracy);

                System.out.println("Accuracy of " + models[j].getClass().getSimpleName() + ": " + String.format("%.2f%%", accuracy));
            }

            /* (2.7) saving the mex file */
            try{
                MEXSerializer.getInstance().saveToDisk("./metafiles/log4mex/ex009", "http://mex.aksw.org/examples/", mex, MEXConstant.EnumRDFFormats.TTL);
            }catch (Exception e){
                System.out.print(e.toString());
            }

            System.out.println("The MEX file [ex008] has been successfully created: share it ;-)");

        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}