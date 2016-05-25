package log4mex;

import org.aksw.mex.log4mex.MEXSerializer;
import org.aksw.mex.log4mex.MyMEX;
import org.aksw.mex.util.MEXConstant;
import org.aksw.mex.util.MEXEnum.*;

/**
 * Created by esteves on 01.07.15.
 * The simplest example of MEX
 */
public class Example01 {

    public static void main(String[] args) {


        System.out.println("starting example 01...");

        MyMEX mex = new MyMEX();

        try{
            /* (1) basic authoring provenance */
            mex.setAuthorName("D Esteves");
            mex.setAuthorEmail("esteves@informatik.uni-leipzig.de");
            String confID = mex.addConfiguration();
            /* (2) the dataset */
            mex.Configuration(confID).DataSet().setName("mydataset.csv");
            /* (3) the features */
            String[] features = {"min", "max", "ope", "clo"};
            mex.Configuration(confID).addFeature(features);
            /* (4) the algorithms and hyperparameters */

            mex.Configuration(confID).setTool(EnumTools.DL_LEARNER, "1.0.0");
            mex.Configuration(confID).addToolParameters("param1", "param1val");
            mex.Configuration(confID).addToolParameters("param2", "param2val");
            mex.Configuration(confID).addToolParameters("param3", "param3val");


            String alg01ID = "golem-alg";
            String algorithmName = "golem Algorithm";
            mex.Configuration(confID).addAlgorithm(alg01ID , algorithmName);

            //String alg01ID = mex.Configuration(confID).addAlgorithm("alg", EnumAlgorithmsClasses.NaiveBayes);
            /* (5) the executions */


            String execID = mex.Configuration(confID).addExecution(EnumExecutionsType.SINGLE, EnumPhases.VALIDATION);
            {
                //your models call here !
            }
            /* (6) the performances for the executions */
            mex.Configuration(confID).Execution(execID).setAlgorithm(alg01ID);
            mex.Configuration(confID).Execution(execID).addPerformance(EnumMeasures.ACCURACY, .96);
            mex.Configuration(confID).Execution(execID).addPerformance(EnumMeasures.ERROR, .04);
            mex.Configuration(confID).Execution(execID).setErrorMessage("An error occurred");

             /* (7) saving the mex file */
            MEXSerializer.getInstance().saveToDisk("./metafiles/log4mex/ex001",
                    "http://mex.aksw.org/examples/", mex, MEXConstant.EnumRDFFormats.TTL);

            System.out.println("The MEX file [ex001] has been successfully created: share it ;-)");

            System.exit(0);

        }catch (Exception e){
            System.out.println(e.toString());
        }



    }
}
