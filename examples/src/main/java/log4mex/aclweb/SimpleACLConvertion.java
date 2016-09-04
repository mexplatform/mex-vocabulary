package log4mex.aclweb;


import org.aksw.mex.log4mex.MEXSerializer;
import org.aksw.mex.log4mex.MyMEX;
import org.aksw.mex.util.MEXConstant;
import org.aksw.mex.util.MEXEnum;
import org.aksw.mex.util.MEXEnum.EnumContexts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;

/**
 * Created by dnes on 29/06/16.
 */
public class SimpleACLConvertion {

    /*
    TODO: convert http://www.aclweb.org/aclwiki/index.php?title=State_of_the_art as use case
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {

        //generate_POS_Tagging();
        String path="C:\\Users\\andre\\Dropbox\\WASOTA\\mexproject-master\\metafiles\\log4mex\\";
        File f = new File(path + "experiments.csv");
        generateAllPOSTagging(f);

    }


    private static void generateAllPOSTagging(File f) throws FileNotFoundException, IOException {
    	String line;
    	int count = 0;
    	try (BufferedReader br = new BufferedReader(new FileReader(f))) {
			while (((line = br.readLine()) != null)) {
				if(count == 0)	{
					count++;
					continue;
				}
				
				MyMEX mex = new MyMEX();
				String[] fields = line.split("\t");
				mex.setAuthorName(fields[0]);
	            mex.setAuthorEmail(fields[1]);
	            //mex.setContext("fact finder", "defacto", "fact validation##############################2);
	            //mex.setBenchmark("mc8509", "dbpediachal2015");
	            mex.setContext(MEXEnum.EnumContexts.POS_TAGGING);
	            mex.setOrganization(fields[2]);
	            mex.setExperimentTitle(fields[3]);

	            mex.Configuration().setSamplingMethod(MEXEnum.EnumSamplingMethods.NOT_INFORMED, 0.76, 0.12);
	            mex.Configuration().SamplingMethod().setSequential(true);

	            mex.Configuration().DataSet().setName(fields[4]);
	            mex.Configuration().DataSet().setDescription(fields[5]);
	            mex.Configuration().DataSet().setURI(fields[6]);

	            mex.Configuration().setTool(fields[7], fields[8]);
	            String algID = mex.Configuration().addAlgorithm(fields[9], new URI(fields[10]));

	            String exTID = mex.Configuration().addExecution(MEXEnum.EnumExecutionsType.OVERALL, MEXEnum.EnumPhases.TEST);
	            mex.Configuration().Execution(exTID).setAlgorithm(algID);
	            mex.Configuration().Execution(exTID).setTargetClass(fields[11]);
	            mex.Configuration().Execution(exTID).setStartsAtPosition(fields[12]);
	            mex.Configuration().Execution(exTID).setEndsAtPosition(fields[13]);

	            mex.Configuration().Execution(exTID).addPerformance(MEXEnum.EnumMeasures.ACCURACY,Double.parseDouble(fields[14]));

	            String exTID2 = mex.Configuration().addExecution(MEXEnum.EnumExecutionsType.OVERALL, MEXEnum.EnumPhases.TEST);
	            mex.Configuration().Execution(exTID2).setAlgorithm(algID);
	            mex.Configuration().Execution(exTID2).setTargetClass(fields[15]);
	            mex.Configuration().Execution(exTID2).setStartsAtPosition(fields[16]);
	            mex.Configuration().Execution(exTID2).setEndsAtPosition(fields[17]);
	            mex.Configuration().Execution(exTID2).addPerformance(MEXEnum.EnumMeasures.ACCURACY, Double.parseDouble(fields[18]));

	            try{
	            	String path="C:\\Users\\andre\\Dropbox\\WASOTA\\mexproject-master\\metafiles\\log4mex\\expAndre" + count;
	                MEXSerializer.getInstance().saveToDisk(path, "http://mex.aksw.org/examples/", mex, MEXConstant.EnumRDFFormats.TTL);
	                count++;
	            }catch (Exception e){
	                System.out.print(e.toString());
	            }
			}
    	} catch (Exception e) {
			e.printStackTrace();
		}
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
            	String path="C:\\Users\\andre\\Dropbox\\WASOTA\\mexproject-master\\metafiles\\log4mex\\ex001nt";
                MEXSerializer.getInstance().saveToDisk(path, "http://mex.aksw.org/examples/", mex, MEXConstant.EnumRDFFormats.NTRIPLE);
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
