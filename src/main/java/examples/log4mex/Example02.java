package examples.log4mex;

import org.aksw.mex.log4mex.MEXSerializer;
import org.aksw.mex.log4mex.MyMEXVO;
import org.aksw.mex.util.MEXEnum.*;

/**
 * Created by esteves on 01.07.15.
 * Creating 2 groups of executions for feature impact analyze purposes, each of those running 2 algorithms
 */
public class Example02 {

    public static void main(String[] args) {

        MyMEXVO mex = new MyMEXVO();

        System.out.println("starting example 02...");

        try{
            /* (1) basic authoring provenance */
            mex.setAuthorName("D Esteves");
            mex.setAuthorEmail("esteves@informatik.uni-leipzig.de");
            /* (2) grouping the executions by configurations */
            String conf01ID = mex.addConfiguration();
            String conf02ID = mex.addConfiguration();
            /* (2.1) the dataset */
            mex.Configuration(conf01ID).DataSet().setName("mydataset.csv");
            mex.Configuration(conf02ID).DataSet().setName("mydataset.csv");
            /* (2.2) the features */
            String[] features01 = {"min", "max", "ope", "clo"};
            String[] features02 = {"min", "max"};
            mex.Configuration(conf01ID).addFeature(features01);
            mex.Configuration(conf02ID).addFeature(features02);
            /* (2.3) the sampling method */
            mex.Configuration(conf01ID).addSamplingMethod(EnumSamplingMethod.Holdout, 0.8, 0.2);
            mex.Configuration(conf02ID).addSamplingMethod(EnumSamplingMethod.Holdout, 0.8, 0.2);

            /* (2.4) the algorithms and hyperparameters */
            String alg01ID = mex.Configuration(conf01ID).addAlgorithm(EnumAlgorithm.NaiveBayes).getIdentifier();
            String alg02ID = mex.Configuration(conf02ID).addAlgorithm(EnumAlgorithm.RegressionAnalysis).getIdentifier();
            /* (2.5) the executions */
            String exec01ID = mex.Configuration(conf01ID).addExecution(EnumExecutionsType.OVERALL.name(), EnumPhases.TEST.name());
            String exec02ID = mex.Configuration(conf02ID).addExecution(EnumExecutionsType.OVERALL.name(), EnumPhases.TEST.name());
            {
                //your models call here !
            }
            /* (2.6) the performances for the executions */
            mex.Configuration(conf01ID).ExecutionOverall(exec01ID).setAlgorithm(alg01ID);
            mex.Configuration(conf01ID).ExecutionOverall(exec01ID).addPerformance(EnumMeasures.ACCURACY.toString(), .86);
            mex.Configuration(conf01ID).ExecutionOverall(exec01ID).addPerformance(EnumMeasures.ERROR.toString(), .14);
            mex.Configuration(conf01ID).ExecutionOverall(exec01ID).addPerformance(EnumMeasures.TRUEPOSITIVE.toString(), 3534);

            mex.Configuration(conf02ID).ExecutionOverall(exec02ID).setAlgorithm(alg02ID);
            mex.Configuration(conf02ID).ExecutionOverall(exec02ID).addPerformance(EnumMeasures.ACCURACY.toString(), .80);
            mex.Configuration(conf02ID).ExecutionOverall(exec02ID).addPerformance(EnumMeasures.ERROR.toString(), .20);
            mex.Configuration(conf01ID).ExecutionOverall(exec02ID).addPerformance(EnumMeasures.TRUEPOSITIVE.toString(), 3143);

            /* (2.7) parsing the mex file */
            MEXSerializer.getInstance().parse(mex);
            /* (2.8) saving the mex file */
            MEXSerializer.getInstance().saveToDisk("/home/esteves/iswcdemo/ex002.ttl", "http://mex.aksw.org/examples/002/", mex);

            System.out.println("The MEX file [ex002.ttl] has been successfully created: share it ;-)");

            System.exit(0);

        }catch (Exception e){
            System.out.println(e.toString());
        }

    }
}


