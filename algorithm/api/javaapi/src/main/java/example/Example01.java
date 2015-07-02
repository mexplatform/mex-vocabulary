package example;

import org.aksw.mex.MEXSerializer_10;
import org.aksw.mex.MyMEX_10;
import org.aksw.mex.util.MEXEnum;
import org.aksw.mex.util.MEXEnum.*;

import java.util.Date;

/**
 * Created by esteves on 01.07.15.
 * The simplest example of MEX
 */
public class Example01 {

    public static void main(String[] args) {

        MyMEX_10 mex = new MyMEX_10();

        /* (1) basic authoring provenance */
        mex.setAuthorName("D Esteves");
        /* (2) the dataset */
        mex.Configuration().DataSet().setName("mydataset.csv");
        /* (3) the features */
        String[] features = {"min", "max", "ope clo"};
        mex.Configuration().addFeature(features);
        /* (4) the algorithms and hyperparameters */
        mex.Configuration().addAlgorithm(MEXEnum.EnumAlgorithm.NaiveBayes);
        /* (5) the executions */
        String executionId = mex.Configuration().addExecutionOverall(MEXEnum.EnumPhase.TEST);
        {
            //your models call here !
        }
        /* (6) the performances for the executions */
        mex.Configuration().ExecutionOverall(executionId).setAlgorithm(mex.Configuration().Algorithm(EnumAlgorithm.NaiveBayes));
        mex.Configuration().ExecutionOverall(executionId).addPerformance(EnumMeasures.ACCURACY.toString(), .96);

        try{
            /* (7) parsing the mex file */
            MEXSerializer_10.getInstance().parse(mex);
            /* (8) saving the mex file */
            MEXSerializer_10.getInstance().saveToDisk("ex001.ttl","http://mex.aksw.org/examples/001/", mex);
        }catch (Exception e){
            System.out.println(e.toString());
        }

    }
}
