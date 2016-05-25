package examples.log4mex;

import org.aksw.mex.log4mex.MEXSerializer;
import org.aksw.mex.log4mex.MyMEXVO;
import org.aksw.mex.log4mex.algo.ImplementationVO;
import org.aksw.mex.log4mex.core.*;
import org.aksw.mex.log4mex.perf.overall.ClassificationMeasureVO;
import org.aksw.mex.util.MEXEnum;
import org.aksw.mex.util.MEXEnum.*;

import java.util.Date;

/**
 * Created by esteves on 09.06.15.
 */
public class Exampleold2 {

    public static void main(String[] args) {

        MyMEXVO mex = null;

        MEXSerializer.getInstance().saveToDisk("d.ttl", "http://aksw.org/sample/", mex);
        System.exit(0);

        //ajustar no loop de busca das subclasses...tem que ser metodo recursivo para pegar todos os filhos!

        /***************************************************************
         * MyMEXVO Wrapper org.aksw.mex.example.Exampleold
         ***************************************************************/
        /* step 1: author and context */

        ApplicationContextVO mcContext = new ApplicationContextVO("D Esteves", "esteves@informatik.uni-leipzig.de");
        mcContext.setContext(MEXEnum.EnumContext.ComputationalFinance);

            //adding experimental information
            ExperimentVO mcExp = new ExperimentVO("EXP001", mcContext);
            mcExp.setDescription("PETR4 stock market prediction");
            mcExp.setDataNormalizationDescription("min-max");
            mcExp.setOutlierDetectionDescription("split and inplit removed");

            mcContext.addExperiment(mcExp);

        //PAREI: SEGUIR AQUI COM A LOGICA DO CAMINHO, DESDE O APPLICATION_CONTEXT ATE O RESULTADO!
        //PASSAR ISSO COMO UM VO PARA A CLASSE DE ESCREVER O ARQUIVO TTL
        // EM JENA

        /* step 2: grouping executions by logical divisions*/

        //creating logical division 01
        ExperimentConfigurationVO mcExpConf1 = new ExperimentConfigurationVO("EXP001", "grouping desc");
        //mcExpConf1.setExperiment(mcExp);

        mcExpConf1.setModel(new ModelVO("MSVM003201502"));

        mcExpConf1.setHardwareConfiguration(new HardwareConfigurationVO(
                EnumProcessor.INTEL_COREI7,
                EnumRandomAccessMemory.SIZE_8GB,
                EnumCache.CACHE_3MB));

        mcExpConf1.setDataSet(new DataSetVO("BOVESPA_20110101_20131201"));

        mcExpConf1.setSamplingMethod(new SamplingMethodVO("",MEXEnum.EnumSamplingMethod.CrossValidation));
        mcExpConf1.SamplingMethod().setTrainSize(0.8);
        mcExpConf1.SamplingMethod().setTrainSize(0.2);
        mcExpConf1.SamplingMethod().setFolds(10);
        mcExpConf1.SamplingMethod().setSequential(true);

        //mcExpConf1.addFeature(new FeatureVO(1, "open_value"));
        //mcExpConf1.addFeature(new FeatureVO(2, "close_value"));
        //mcExpConf1.addFeature(new FeatureVO(3, "min_value"));
        //mcExpConf1.addFeature(new FeatureVO(4, "max_value"));

        /* step 3: define the algorithms */

        ImplementationVO software = new ImplementationVO(EnumImplementation.Weka);
        software.setRevision("3.6.6");

        //AlgorithmVO algSVM = new AlgorithmVO(EnumAlgorithm.SupportVectorMachines);
        //algSVM.addParameter(new AlgorithmParameterVO("C", "10^2"));
        //algSVM.addParameter(new AlgorithmParameterVO("alpha", "0.1"));

        //AlgorithmVO algNB = new AlgorithmVO(EnumAlgorithm.NaiveBayes);

        /* step 4: control the executions */

        // train
        ExecutionSetVO exec1 = new ExecutionSetVO(null, "E001", new PhaseVO(EnumPhases.TRAIN.name()));
        exec1.setExperimentConfiguration(mcExpConf1);
        //exec1.setExamples(new ExampleCollection(0l, 114l));

        // test
        ExecutionSetVO exec2 = new ExecutionSetVO(null, "E002", new PhaseVO(EnumPhases.TEST.name()));
        exec2.setExperimentConfiguration(mcExpConf1);
        //exec2.setExamples(new ExampleCollection(115l, 160l));

        /*****************************************************************
         * YOUR SIMULATION'S RUN - BEGIN
         *****************************************************************/
        //simulation for train...

        exec1.setStartDate(new Date());
        ModelSimulatorExample.getInstance().run("train");
        exec1.setEndDate(new Date());
        double accTrain = ModelSimulatorExample.getInstance().getAccuracyTrain();
        double fMeasureTrain =ModelSimulatorExample.getInstance().getfMeasureTrain();

        //simulation for test...
        exec2.setStartDate(new Date());
        ModelSimulatorExample.getInstance().run("test");
        exec2.setEndDate(new Date());
        double accTest = ModelSimulatorExample.getInstance().getAccuracyTest();
        double fMeasureTest =ModelSimulatorExample.getInstance().getfMeasureTest();


        /*****************************************************************
         * YOUR SIMULATION'S RUN - END
         *****************************************************************/

         /* step 5: store the performance */

        ClassificationMeasureVO p1 = new ClassificationMeasureVO();
        p1.setAccuracy(accTrain);
        p1.set_fMeasure(fMeasureTrain);
        //exec1.addPerformance(p1);

        ClassificationMeasureVO p2 = new ClassificationMeasureVO();
        p2.setAccuracy(accTrain);
        p2.set_fMeasure(fMeasureTest);
        //exec2.addPerformance(p2);

        /* save the file */


        //MEXSerializer.getInstance().parse(mex);
        MEXSerializer.getInstance().saveToDisk("/home/esteves/Desktop/mexfile1.ttl", "url.com", mex);




    }
}




