package example;

import org.aksw.mex.MEXSerializer_10;
import org.aksw.mex.MyMEX_10;
import org.aksw.mex.util.MEXEnum;
import org.aksw.mex.util.MEXEnum.*;

import java.util.Date;

/**
 * Created by root on 01.07.15.
 */
public class Example02 {

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

        String eid="EX001CONF1";
        mex.addConfiguration(eid);
        mex.Configuration(eid).SamplingMethod().setName(EnumSamplingMethod.Holdout);

        mex.Configuration(eid).addFeature("min");
        mex.Configuration(eid).addFeature("max");
        mex.Configuration(eid).addFeature("ope");
        mex.Configuration(eid).addFeature("clo");

        mex.Configuration(eid).addAlgorithm(MEXEnum.EnumAlgorithm.NaiveBayes);

        String ex1 = "EX001";

        mex.Configuration(eid).addExecutionOverall(ex1, MEXEnum.EnumPhase.TEST);
        mex.Configuration(eid).ExecutionOverall(ex1).setAlgorithm(mex.Configuration(eid).Algorithm(EnumAlgorithm.NaiveBayes));
        mex.Configuration(eid).ExecutionOverall(ex1).addPerformance(EnumMeasures.ACCURACY.toString(), .96);

        try{
            MEXSerializer_10.getInstance().parse(mex);
        }catch (Exception e){

        }

        MEXSerializer_10.getInstance().saveToDisk("ex_simplest.ttl","http://mex.aksw.org/examples/001/", mex);

        //your models call here !

    }

    }
}
