package log4mex;

import org.aksw.mex.log4mex.MEXSerializer;
import org.aksw.mex.log4mex.MyMEX;
import org.aksw.mex.util.MEXConstant;
import org.aksw.mex.util.MEXEnum;

/**
 * Created by dnes on 22/06/16.
 */
public class ExampleIterationSML {

    public static void main(String[] args) {


        System.out.println("starting example SML iteration...");

        MyMEX mex = new MyMEX();
        String[] datasets = {"ds1", "ds2", "ds1"};
        String[] tools = {"aleph", "dllearner"};


        try{


            mex.setAuthor("Name", "e@mail");


            for (String dataset : datasets) {
                for (String tool : tools) {
                    String conf = mex.addConfiguration();
                    String exec = mex.Configuration(conf).addExecution(
                            MEXEnum.EnumExecutionsType.SINGLE, MEXEnum.EnumPhases.VALIDATION);


                    mex.Configuration(conf).setDataSet("http://example.com/" + dataset,
                            "A dataset", dataset);
                    mex.Configuration(conf).setTool(tool, "0.0");
                    String alg = mex.Configuration(conf).addAlgorithm(tool + "-alg", tool + " Algorithm");
                    mex.Configuration(conf).Execution(exec).setAlgorithm(alg);
                    mex.Configuration(conf).Execution(exec).addPerformance(
                            MEXEnum.EnumMeasures.TRUEPOSITIVE, 23);
                }
            }


             /* (7) saving the mex file */
            MEXSerializer.getInstance().saveToDisk("./metafiles/log4mex/exsmliteration",
                    "http://mex.aksw.org/examples/", mex, MEXConstant.EnumRDFFormats.TTL);

            System.out.println("The MEX file [exsmliteration] has been successfully created: share it ;-)");

            System.exit(0);


        }catch (Exception e){

        }

    }

}
