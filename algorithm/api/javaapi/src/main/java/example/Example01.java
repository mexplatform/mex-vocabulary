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

            mex.Configuration().SamplingMethod().setName(EnumSamplingMethod.Holdout);

            mex.Configuration().addFeature("min");
            mex.Configuration().addFeature("max");
            mex.Configuration().addFeature("ope");
            mex.Configuration().addFeature("clo");

            mex.Configuration().addAlgorithm(MEXEnum.EnumAlgorithm.NaiveBayes);

            String ex1 = "EX001";

            mex.Configuration().addExecutionOverall(ex1, MEXEnum.EnumPhase.TEST);
            mex.Configuration().ExecutionOverall(ex1).setAlgorithm(mex.Configuration().Algorithm(EnumAlgorithm.NaiveBayes));
            mex.Configuration().ExecutionOverall(ex1).addPerformance(EnumMeasures.ACCURACY.toString(), .96);

            MEXSerializer_10.getInstance().parse(mex);
            MEXSerializer_10.getInstance().saveToDisk("ex_simplest.ttl","http://mex.aksw.org/examples/001/", mex);

            //your models call here !

        }

    }
}
