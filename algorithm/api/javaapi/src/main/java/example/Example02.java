package example;

import org.aksw.mex.MEXSerializer_10;
import org.aksw.mex.MyMEX_10;
import org.aksw.mex.util.MEXEnum;
import org.aksw.mex.util.MEXEnum.*;

/**
 * Created by esteves on 01.07.15.
 *
 */
public class Example02 {

    public static void main(String[] args) {

        MyMEX_10 mex = new MyMEX_10();

        /* (1) basic authoring provenance */
        mex.setAuthorName("D Esteves");
        mex.setAuthorEmail("esteves@informatik.uni-leipzig.de");
        /* (2) grouping the executions by configurations */
        String eid = "EX001CONF1";
        mex.addConfiguration(eid);
        /* (2.1) the dataset */
        mex.Configuration(eid).DataSet().setName("mydataset.csv");
        /* (2.2) the features */
        String[] features = {"min", "max", "ope clo"};
        mex.Configuration(eid).addFeature(features);
        /* (2.3) the sampling method */
        mex.Configuration(eid).SamplingMethod().setName(EnumSamplingMethod.Holdout);
        mex.Configuration(eid).SamplingMethod().setTrainSize(0.8);
        mex.Configuration(eid).SamplingMethod().setTestSize(0.2);
        /* (2.4) the algorithms and hyperparameters */
        mex.Configuration(eid).addAlgorithm(MEXEnum.EnumAlgorithm.NaiveBayes);

        String ex1 = "EX001";
        mex.Configuration(eid).addExecutionOverall(ex1, MEXEnum.EnumPhase.TEST);
        mex.Configuration(eid).ExecutionOverall(ex1).setAlgorithm(mex.Configuration(eid).Algorithm(EnumAlgorithm.NaiveBayes));
        mex.Configuration(eid).ExecutionOverall(ex1).addPerformance(EnumMeasures.ACCURACY.toString(), .96);
        try {
            MEXSerializer_10.getInstance().parse(mex);
        } catch (Exception e) {
        }

        MEXSerializer_10.getInstance().saveToDisk("ex_simplest.ttl", "http://mex.aksw.org/examples/001/", mex);

        //your models call here !
    }
}


