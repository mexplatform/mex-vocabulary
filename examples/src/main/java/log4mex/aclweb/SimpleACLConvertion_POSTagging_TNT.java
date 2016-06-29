package log4mex.aclweb;

import org.aksw.mex.log4mex.MEXSerializer;
import org.aksw.mex.log4mex.MyMEX;
import org.aksw.mex.util.MEXConstant;
import org.aksw.mex.util.MEXEnum;

import java.net.URI;

/**
 * Created by dnes on 29/06/16.
 */
public class SimpleACLConvertion_POSTagging_TNT {

    /*
    TODO: convert http://www.aclweb.org/aclwiki/index.php?title=State_of_the_art as use case
     */
    public static void main(String[] args) {

        generate_POS_Tagging();
        

    }


    /**
     * http://www.aclweb.org/aclwiki/index.php?title=POS_Tagging_(State_of_the_art)
     */
    private static void generate_POS_Tagging(){
        ex_POS_Tagging_01();
        ex_POS_Tagging_02();
    }

    private static void ex_POS_Tagging_01(){

        MyMEX mex = new MyMEX();

        try {

            mex.setAuthorName("Brants");
            mex.setAuthorEmail("emailexample@aclweb.org");
            mex.setContext(MEXEnum.EnumContexts.POS_TAGGING);
            mex.setOrganization("Universitat des Saarlandes");
            mex.setExperimentTitle("ACL POS Tagging - TnT");

            mex.Configuration().setSamplingMethod(MEXEnum.EnumSamplingMethods.NOT_INFORMED, 0.76, 0.12);
            mex.Configuration().SamplingMethod().setSequential(true);

            mex.Configuration().DataSet().setName("WSJ");
            mex.Configuration().DataSet().setDescription("Wall Street Journal (WSJ) release 3 (LDC99T42)");
            mex.Configuration().DataSet().setURI("https://catalog.ldc.upenn.edu/LDC99T42");

            mex.Configuration().setTool("TnT", "26 Oct 1998");
            String algID = mex.Configuration().addAlgorithm("tnt", new URI("http://www.coli.uni-saarland.de/~thorsten/tnt/"));

            String exTID = mex.Configuration().addExecution(MEXEnum.EnumExecutionsType.OVERALL, MEXEnum.EnumPhases.TEST);
            mex.Configuration().Execution(exTID).setAlgorithm(algID);
            mex.Configuration().Execution(exTID).setTargetClass("All tokens");
            mex.Configuration().Execution(exTID).setStartsAtPosition("section 0");
            mex.Configuration().Execution(exTID).setEndsAtPosition("section 18");

            mex.Configuration().Execution(exTID).addPerformance(MEXEnum.EnumMeasures.ACCURACY, 0.9646);

            String exTID2 = mex.Configuration().addExecution(MEXEnum.EnumExecutionsType.OVERALL, MEXEnum.EnumPhases.TEST);
            mex.Configuration().Execution(exTID2).setAlgorithm(algID);
            mex.Configuration().Execution(exTID2).setTargetClass("Unknown words");
            mex.Configuration().Execution(exTID2).setStartsAtPosition("section 0");
            mex.Configuration().Execution(exTID2).setEndsAtPosition("section 18");
            mex.Configuration().Execution(exTID2).addPerformance(MEXEnum.EnumMeasures.ACCURACY, 0.8586);


            try{
                MEXSerializer.getInstance().saveToDisk("./metafiles/log4mex/acl/pos_tagging/ex001", "http://mex.aksw.org/examples/", mex, MEXConstant.EnumRDFFormats.TTL);
            }catch (Exception e){
                System.out.print(e.toString());
            }

            System.out.println("The MEX file has been successfully created: share it ;-)");

            System.exit(0);

        } catch (Exception e) {
            System.out.println(e.toString());

        }

    }

    private static void ex_POS_Tagging_02(){

        // ....

    }


    /**
     * http://www.aclweb.org/aclwiki/index.php?title=NP_Chunking_(State_of_the_art)
     */
    private static void generate_NP_Chunking(){

    }


}