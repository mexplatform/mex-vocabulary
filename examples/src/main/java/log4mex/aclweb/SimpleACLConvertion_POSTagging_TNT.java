package log4mex.aclweb;

import org.aksw.mex.log4mex.MyMEX;
import org.aksw.mex.util.MEXEnum;

/**
 * Created by dnes on 29/06/16.
 */
public class SimpleACLConvertion_POSTagging_TNT {

    public static void main(String[] args) {

        MyMEX mex = new MyMEX();

        try {

            mex.setAuthorName("Brants");
            mex.setAuthorEmail("emailexample@aclweb.org");
            mex.setContext(MEXEnum.EnumContexts.POS_TAGGING);
            mex.setOrganization("Universitat des Saarlandes");
            mex.setExperimentTitle("ACL POS Tagging - TnT");

            mex.addConfiguration();

            mex.Configuration().setSamplingMethod(MEXEnum.EnumSamplingMethods.NOT_INFORMED, 0.76, 0.12);
            mex.Configuration().SamplingMethod().setSequential(true);

            mex.Configuration().DataSet().setName("WSJ");
            mex.Configuration().DataSet().setDescription("Wall Street Journal (WSJ) release 3 (LDC99T42)");
            mex.Configuration().DataSet().setURI("https://catalog.ldc.upenn.edu/LDC99T42");

        } catch (Exception e) {

        }


    }
}