package example;

import org.aksw.mex.MEXSerializer_10;
import org.aksw.mex.MyMEX_10;
import org.aksw.mex.util.MEXEnum;
import org.aksw.mex.util.MEXEnum.*;

import java.util.Date;

/**
 * Created by esteves on 01.07.15.
 */
public class Example01 {

    public static void main(String[] args) {


        //the MEX wrapper!
        MyMEX_10 mex = new MyMEX_10();

        //basic authoring provenance
        {
            //change later here the sets for adds
            mex.setAuthorName("D Esteves");
            mex.setAuthorEmail("esteves@informatik.uni-leipzig.de");
        }
        {

            mex.Configuration().setSamplingMethod(EnumSamplingMethod.Holdout);

            String[] features = {"min", "max", "ope clo"};
            mex.Configuration().addFeature(features);


            mex.Configuration().addAlgorithm(MEXEnum.EnumAlgorithm.NaiveBayes);


            String executionId = mex.Configuration().addExecutionOverall(MEXEnum.EnumPhase.TEST);
            mex.Configuration().ExecutionOverall(executionId).setAlgorithm(mex.Configuration().Algorithm(EnumAlgorithm.NaiveBayes));
            mex.Configuration().ExecutionOverall(executionId).addPerformance(EnumMeasures.ACCURACY.toString(), .96);

            try{
                MEXSerializer_10.getInstance().parse(mex);
                MEXSerializer_10.getInstance().saveToDisk("ex001.ttl","http://mex.aksw.org/examples/001/", mex);
            }catch (Exception e){
                System.out.println(e.toString());
            }



            //your models call here !

        }

    }
}
