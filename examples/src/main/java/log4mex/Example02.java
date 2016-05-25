package log4mex;

import org.aksw.mex.log4mex.MEXSerializer;
import org.aksw.mex.log4mex.MyMEX;
import org.aksw.mex.util.MEXConstant;
import org.aksw.mex.util.MEXEnum.*;

/**
 * Created by esteves on 01.07.15.
 * Creating 2 groups of executions for feature impact analyze purposes, each of those running 2 algorithms
 */
public class Example02 {

    public static void main(String[] args) {

        MyMEX mex = new MyMEX();

        System.out.println("starting example 02...");

        try{
            /* (1) basic authoring provenance */
            mex.setAuthorName("D Esteves");
            mex.setAuthorEmail("esteves@informatik.uni-leipzig.de");

            /* (2) grouping the executions by configurations */
            String conf01ID = mex.addConfiguration();
            String conf02ID = mex.addConfiguration();

            mex.Configuration(conf01ID).setTool(EnumTools.DL_LEARNER, "1.0.0");
            mex.Configuration(conf01ID).addToolParameters("param1", "param1val");
            mex.Configuration(conf01ID).addToolParameters("param2", "param2val");
            mex.Configuration(conf01ID).addToolParameters("param3", "param3val");

            mex.Configuration(conf02ID).setTool(EnumTools.DL_LEARNER, "1.0.0");
            mex.Configuration(conf02ID).addToolParameters("param1", "param1val");
            mex.Configuration(conf02ID).addToolParameters("param2", "param2val");
            mex.Configuration(conf02ID).addToolParameters("param3", "param3val");

            /* (2.1) the dataset */
            mex.Configuration(conf01ID).DataSet().setName("mydataset.csv");
            mex.Configuration(conf02ID).DataSet().setName("mydataset.csv");
            /* (2.2) the features */
            String[] features01 = {"min", "max", "ope", "clo"};
            String[] features02 = {"min", "max"};
            mex.Configuration(conf01ID).addFeature(features01);
            mex.Configuration(conf02ID).addFeature(features02);
            /* (2.3) the sampling method */
            mex.Configuration(conf01ID).setSamplingMethod(EnumSamplingMethods.HOLDOUT, 0.8, 0.2);
            mex.Configuration(conf02ID).setSamplingMethod(EnumSamplingMethods.HOLDOUT, 0.8, 0.2);

            /* (2.4) the algorithms and hyperparameters */
            String alg01ID = mex.Configuration(conf01ID).addAlgorithm("nb", EnumAlgorithmsClasses.NaiveBayes);
            String alg02ID = mex.Configuration(conf02ID).addAlgorithm("lr", EnumAlgorithmsClasses.LogisticRegression);
            /* (2.5) the executions */
            String exec01ID = mex.Configuration(conf01ID).addExecution(EnumExecutionsType.OVERALL, EnumPhases.TEST);
            String exec02ID = mex.Configuration(conf02ID).addExecution(EnumExecutionsType.OVERALL, EnumPhases.TEST);
            {
                //your models call here !
            }
            /* (2.6) the performances for the executions */
            mex.Configuration(conf01ID).Execution(exec01ID).setAlgorithm(alg01ID);
            mex.Configuration(conf01ID).Execution(exec01ID).addPerformance(EnumMeasures.ACCURACY, .86);
            mex.Configuration(conf01ID).Execution(exec01ID).addPerformance(EnumMeasures.ERROR, .14);
            mex.Configuration(conf01ID).Execution(exec01ID).addPerformance(EnumMeasures.TRUEPOSITIVE, 3534);

            mex.Configuration(conf02ID).Execution(exec02ID).setAlgorithm(alg02ID);
            mex.Configuration(conf02ID).Execution(exec02ID).addPerformance(EnumMeasures.ACCURACY, .80);
            mex.Configuration(conf02ID).Execution(exec02ID).addPerformance(EnumMeasures.ERROR, .20);
            mex.Configuration(conf02ID).Execution(exec02ID).addPerformance(EnumMeasures.TRUEPOSITIVE, 3143);

            /* (2.7) saving the mex file */
            try{
                MEXSerializer.getInstance().saveToDisk("./metafiles/log4mex/ex002", "http://mex.aksw.org/examples/", mex, MEXConstant.EnumRDFFormats.N3);
            }catch (Exception e){
                System.out.print(e.toString());
            }

            System.out.println("The MEX file [ex002] has been successfully created: share it ;-)");

            System.exit(0);

        }catch (Exception e){
            System.out.println(e.toString());
        }

    }
}


