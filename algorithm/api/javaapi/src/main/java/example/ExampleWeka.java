package example;

/**
 * Created by esteves on 03.07.15.
 */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;

import org.aksw.mex.MEXSerializer_10;
import org.aksw.mex.MyMEX_10;
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

        MyMEX_10 mex = new MyMEX_10();

        String ds = "weather.txt";

        try {
            mex.setAuthorName("P.Creek");
            String confID = mex.addConfiguration();
            mex.Configuration(confID).DataSet().setName(ds);

            BufferedReader datafile = readDataFile(ds);

            Instances data = new Instances(datafile);
            data.setClassIndex(data.numAttributes() - 1);

            for (int i = 0; i < data.numAttributes(); i++) {
                mex.Configuration(confID).addFeature(data.attribute(i).name());
            }

            // Do 10-split cross validation
            Instances[][] split = crossValidationSplit(data, 10);

               /* (2.3) the sampling method */
            mex.Configuration(confID).addSamplingMethod(MEXEnum.EnumSamplingMethod.CrossValidation, 10);

            // Separate split into training and testing arrays
            Instances[] trainingSplits = split[0];
            Instances[] testingSplits = split[1];

            // Use a set of classifiers
            Classifier[] models = {
                    new J48(), // a decision tree
                    new PART(),
                    new DecisionTable(),//decision table majority classifier
                    new DecisionStump() //one-level decision tree
            };
            /* (4) the algorithms and hyperparameters */
            String[] algIDs = new String[4];

            algIDs[0] = mex.Configuration(confID).addAlgorithm(MEXEnum.EnumAlgorithm.J48);
            algIDs[1] = mex.Configuration(confID).addAlgorithm(MEXEnum.EnumAlgorithm.PART);
            algIDs[2] = mex.Configuration(confID).addAlgorithm(MEXEnum.EnumAlgorithm.DecisionTable);
            algIDs[3] = mex.Configuration(confID).addAlgorithm(MEXEnum.EnumAlgorithm.DecisionStump);

            String[] execIDs = new String[models.length];

            // Run for each model
            for (int j = 0; j < models.length; j++) {

                // Collect every group of predictions for current model in a FastVector
                FastVector predictions = new FastVector();

                // For each training-testing split pair, train and test the classifier
                for (int i = 0; i < trainingSplits.length; i++) {
                    Evaluation validation = classify(models[j], trainingSplits[i], testingSplits[i]);

                    predictions.appendElements(validation.predictions());

                    // Uncomment to see the summary for each training-testing pair.
                    //System.out.println(models[j].toString());

                }

                // Calculate overall accuracy of current classifier on all splits
                double accuracy = calculateAccuracy(predictions);

                execIDs[j] = mex.Configuration(confID).addExecution(MEXEnum.EnumExecutionType.OVERALL, MEXEnum.EnumPhase.TEST);
                mex.Configuration(confID).Execution(execIDs[j]).setAlgorithm(algIDs[j]);
                mex.Configuration(confID).Execution(execIDs[j]).addPerformance(MEXEnum.EnumMeasures.ACCURACY.toString(), accuracy);

                // Print current classifier's name and accuracy in a complicated,
                // but nice-looking way.
                System.out.println("Accuracy of " + models[j].getClass().getSimpleName() + ": " + String.format("%.2f%%", accuracy));
                        //+ "\n---------------------------------");
            }

            MEXSerializer_10.getInstance().parse(mex);
            /* (2.8) saving the mex file */
            MEXSerializer_10.getInstance().saveToDisk("exweka.ttl", "http://mex.aksw.org/examples/Weka/", mex);

            System.out.println("The MEX file has been successfully created: share it ;-)");

        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}