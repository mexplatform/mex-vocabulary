package log4mex;

import log4mex.tests.ModelSimulatorExample;
import org.aksw.mex.log4mex.MEXSerializer;
import org.aksw.mex.log4mex.MyMEX;
import org.aksw.mex.util.MEXConstant;
import org.aksw.mex.util.MEXEnum.*;

import java.util.Date;

/**
 * Created by esteves on 09.06.15.
 */
public class ExampleGenericProblemSimulator {

    public static void main(String[] args) {

        MyMEX mex = new MyMEX();

        try{

            mex.setAuthorName("D Esteves");
            mex.setAuthorEmail("esteves@informatik.uni-leipzig.de");
            mex.setContext(EnumContexts.COMPUTATIONAL_FINANCE);
            mex.setExperimentDescription("PETR4 stock market prediction");
            mex.setExperimentId("BOVESPA_PETR4_20150515");
            mex.setExperimentTitle("BOVESPA PETR4 Analysis");
            mex.setExperimentDataNormalizationDescription("min-max");
            mex.setExperimentOutlierDetectionDescription("split and inplit removed");

            mex.Configuration().setModel("MSVM003201502");

            mex.Configuration().setHardwareConfiguration("ubuntu", EnumProcessors.INTEL_COREI7, EnumRAM.SIZE_16GB, "SSD", EnumCaches.CACHE_3MB);

            mex.Configuration().DataSet().setName("bovespa");
            mex.Configuration().DataSet().setDescription("brazilian stock market 2013");
            mex.Configuration().DataSet().setURI("http://www.bmfbovespa.com.br/shared/iframe.aspx?idioma=pt-br&url=http://www.bmfbovespa.com.br/pt-br/cotacoes-historicas/FormSeriesHistoricas.asp");

            mex.Configuration().setSamplingMethod(EnumSamplingMethods.CROSS_VALIDATION, 10);
            mex.Configuration().SamplingMethod().setTrainSize(0.8);
            mex.Configuration().SamplingMethod().setTestSize(0.2);
            mex.Configuration().SamplingMethod().setSequential(true);


            mex.Configuration().setTool(EnumTools.WEKA, "3.6.6");


            String alg1 = mex.Configuration().addAlgorithm("nb", EnumAlgorithmsClasses.NaiveBayes);

            String exec1 = mex.Configuration().addExecution(EnumExecutionsType.OVERALL, EnumPhases.TRAIN);
            String exec2 = mex.Configuration().addExecution(EnumExecutionsType.OVERALL, EnumPhases.TEST);

            mex.Configuration().Execution(exec1).setAlgorithm(alg1);
            mex.Configuration().Execution(exec2).setAlgorithm(alg1);

            //simulation for training phase...

            mex.Configuration().Execution(exec1).setStartDate(new Date());
            ModelSimulatorExample.getInstance().run("train");
            mex.Configuration().Execution(exec1).setEndDate(new Date());
            double accTrain = ModelSimulatorExample.getInstance().getAccuracyTrain();
            double fMeasureTrain =ModelSimulatorExample.getInstance().getfMeasureTrain();

            //simulation for test...
            mex.Configuration().Execution(exec2).setStartDate(new Date());
            ModelSimulatorExample.getInstance().run("test");
            mex.Configuration().Execution(exec2).setEndDate(new Date());
            double accTest = ModelSimulatorExample.getInstance().getAccuracyTest();
            double fMeasureTest =ModelSimulatorExample.getInstance().getfMeasureTest();


            mex.Configuration().Execution(exec1).addPerformance(EnumMeasures.ACCURACY, accTrain);
            mex.Configuration().Execution(exec1).addPerformance(EnumMeasures.F1MEASURE, fMeasureTrain);

            mex.Configuration().Execution(exec2).addPerformance(EnumMeasures.ACCURACY, accTest);
            mex.Configuration().Execution(exec2).addPerformance(EnumMeasures.F1MEASURE, fMeasureTest);


        try{
            System.out.println("saving the metafile...wait");
            MEXSerializer.getInstance().saveToDisk("./metafiles/log4mex/ex006", "http://mex.aksw.org/examples/", mex, MEXConstant.EnumRDFFormats.TTL);
        }catch (Exception e){
            System.out.print(e.toString());
        }

        }catch (Exception e){

        }


    }
}




