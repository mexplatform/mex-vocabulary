package org.aksw.mex.framework;


import org.aksw.mex.log4mex.MyMEX;
import org.apache.log4j.Logger;

/**
 * Created by dnes on 13/12/15.
 */
public class MetaGeneration {

    /* general */
    private final static String javaDocURL = "http://mex.aksw.org/framework/doc";
    private final static Logger LOG = Logger.getLogger(MetaGeneration.class);
    private final static MyMEX mex = new MyMEX();

    /* performance issues */
    private static long START_TIME;
    private static long END_TIME;
    private static long TOTAL_EXECUTION_TIME;

    public static void main(String[] args) {

/*
        CommandLineParser parser = new DefaultParser();

        try {

            Options opt = new Options();

            opt.addOption("cp", true, "");
            opt.addOption("uc", true, "The user class containing machine learning code");
            opt.addOption("out", true, "The (full) path for the output file (mex metadata)");
            opt.addOption("uri", false, "The URI");

            CommandLine cl = parser.parse(opt, args);

            if (!cl.hasOption("uc") || !cl.hasOption("out")) {
                HelpFormatter f = new HelpFormatter();
                f.printHelp("MetaGeneration", opt);
                String usage = "Usage:  MetaGeneration -uc userclass.java -out \\home\\user\\mymexout.ttl [-uri] mex.aksw.org\\example";
                LOG.info(usage);
            }
            else{
                LOG.info("Starting the process: MetaGeneration -uc " + cl.getOptionValue("uc") + " -out " + cl.getOptionValue("out"));
                process(cl.getOptionValue("uc"), cl.getOptionValue("out"));
            }

        }
        catch( ParseException exp ) {
            LOG.error("Parsing failed.  Reason: " + exp.getMessage());
        }

        */

    }

    /*
    private static void process(String uc, String mexfile){

        START_TIME = System.currentTimeMillis();

        Method _mainMethod;
        Field _dataset;
        List<Field> _measures, _algorithms;

        double[] measure;
        Method _getFeatures, _getTestData, _getFinalTestData;
        String[] _features;
        String[][] _testData, _finalTestData;

        try{

            Class<?> klass = Class.forName(uc);
            //Class<?> klass = WekaExample001.class;
            Object ins = klass.newInstance();

            //String outputPath = "/Users/dnes/github/mexproject/metafiles/framework/";
            //String outputFileName = "exWeka001.ttl";
            //mexfile = /Users/dnes/github/mexproject/metafiles/framework/exWeka001.ttl

            LOG.info("********************** MEX Framework **********************");
            LOG.info("                                                           ");
            LOG.info("                    http://mex.aksw.org                    ");
            LOG.info("                                                           ");
            LOG.info("Starting the meta annotation for class named: " + klass.getSimpleName());

            _mainMethod = getMethodAnnotatedWith(klass, Start.class);
            if (_mainMethod == null)
                throw new Exception("error: missing annotation @Start, please see " + javaDocURL + " for more information");

            if (!klass.isAnnotationPresent(InterfaceVersion.class)) {
                throw new Exception("error: missing annotation @InterfaceVersion, please see " + javaDocURL + " for more information");
            }

            *//* EXPERIMENT INFORMATION *//*

            if (klass.isAnnotationPresent(ExperimentInfo.class)) {
                Annotation annotation = klass.getAnnotation(ExperimentInfo.class);
                ExperimentInfo aExpInfo = (ExperimentInfo) annotation;
                LOG.info("@ExperimentInfo - OK");

                mex.setExperimentId(aExpInfo.identifier());
                mex.setExperimentTitle(aExpInfo.title());
                mex.setExperimentDescription(aExpInfo.description());
                mex.setAuthorEmail(aExpInfo.email());
                mex.setAuthorName(aExpInfo.createdBy());
                mex.setExperimentDate(new Date());
                mex.setContext(aExpInfo.context());

                //LOG.info("Priority: " + aExpInfo.priority().toString());
                int tagLength = aExpInfo.tags().length;
                String tags = "";
                for (String tag : aExpInfo.tags()) {
                    if (tagLength > 1) {tags+= tag + ", ";} else {
                        tags += tag;}
                    tagLength--;
                }
                //LOG.info("Tags :" + tags);
            }else{
                LOG.info("@ExperimentInfo - Not Found");
            }

            *//* HARDWARE *//*
            if (klass.isAnnotationPresent(Hardware.class)) {
                Annotation annotationHard = klass.getAnnotation(Hardware.class);
                Hardware aHard = (Hardware) annotationHard;

                LOG.info("@Hardware - OK");

                HardwareConfigurationVO h = new HardwareConfigurationVO();
                h.setCPU(aHard.cpu());
                h.setCache(aHard.cpuCache());
                h.setHD(aHard.hdType());
                h.setMemory(aHard.memory());
                h.setOperationalSystem(aHard.os());
                h.setVideoGraph(aHard.video());

               // mex.Configuration().setHardwareConfiguration(h);

            }else{
                LOG.info("@Hardware - Not Found");
            }

            *//* SAMPLING METHOD *//*

            if (klass.isAnnotationPresent(SamplingMethod.class)) {
                Annotation annotationSM = klass.getAnnotation(SamplingMethod.class);
                SamplingMethod aSM = (SamplingMethod) annotationSM;

                LOG.info("@SamplingMethod - OK");

                SamplingMethodVO s = new SamplingMethodVO("sm", aSM.klass());
                s.setTrainSize(aSM.trainSize());
                s.setTestSize(aSM.testSize());
                s.setFolds(aSM.folds());
                s.setSequential(aSM.sequential());

                //mex.Configuration().setSamplingMethod(s);

            }else{
                LOG.info("@SamplingMethod - Not Found");
            }



            LOG.info("invoking the main method: " + _mainMethod.getName());

            //method.invoke(annotation, (Object[])null)
            Object returnValue = _mainMethod.invoke(ins);


            *//********************* TEST WITH LOCAL VARIABLE ***************************//*

            Field _tst = getFieldAnnotatedWith(klass, TestDataSet2.class);
            if (_tst == null)
                throw new Exception("error: missing annotation @TestDataSet2, please see " + javaDocURL + " for more information");


            if (klass.isAnnotationPresent(TestDataSet2.class)) {
                Annotation annotationTest = klass.getAnnotation(TestDataSet2.class);
                TestDataSet2 aSM = (TestDataSet2) annotationTest;

                getValueOfPrivatedField("@TestDataSet2", ins);
            }

            *//********************* TEST WITH LOCAL VARIABLE ***************************//*


            *//* FEATURES *//*
            _getFeatures = getMethodAnnotatedWith(klass, Features.class);
            if (_getFeatures == null)
                throw new Exception("error: missing annotation @Features, please see " + javaDocURL + " for more information");


            LOG.info("invoking the features method: " + _getFeatures.getName());
            Object retFeatures = _getFeatures.invoke(ins);
            _features = (String[]) retFeatures;
            for(int i = 0; i < _features.length; i++){
                LOG.debug(_features[i]);
            }
            mex.Configuration().addFeature(_features);


            _getTestData = getMethodAnnotatedWith(klass, TestDataSet.class);
            if (_getTestData == null) {
                LOG.info(" -> missing annotation @TestDataSet, please see " + javaDocURL + " for more information");
            }else {
                LOG.info("invoking the features method: " + _getTestData.getName());
                Object retTestData = _getTestData.invoke(ins);
                _testData = (String[][]) retTestData;
                for (int i = 0; i < _testData.length; i++) {
                    String l = "";
                    for (int c = 0; c < _testData[0].length; c++) {
                        l += " " + String.valueOf(_testData[i][c]);
                    }
                    LOG.debug("line " + i + ":" + l);
                }
            }

            _getFinalTestData = getMethodAnnotatedWith(klass, FinalDataSet.class);
            if (_getFinalTestData == null) {
                LOG.info(" -> missing annotation @FinalDataSet, please see " + javaDocURL + " for more information");
            }else {
                LOG.info("invoking the @FinalDataSet method: " + _getFinalTestData.getName());

                Object rofds = _getFinalTestData.invoke(ins);
                _finalTestData = (String[][]) rofds;
                for(int i = 0; i < _finalTestData.length; i++){
                    String l = "";
                    for(int c = 0; c < _finalTestData[0].length; c++){
                        l+=" " + String.valueOf(_finalTestData[i][c]);
                    }
                    LOG.debug("line " + i + ":" + l);
                }
            }


            *//* DATASET *//*

            _dataset = getFieldAnnotatedWith(klass, DatasetName.class);
            if (_dataset == null)
                throw new Exception("error: missing annotation @DatasetName, please see " + javaDocURL + " for more information");

            //mex.Configuration().setDataset("", "", (String)_dataset.get(ins));

            LOG.info("@DataSet - OK");

            *//* ALGORITHM *//*

            _algorithms = getFieldsAnnotatedWith(klass, Algorithm.class);
            if (_algorithms == null)
                throw new Exception("error: missing annotation @Algorithm, please see " + javaDocURL + " for more information");

            HashMap<String, String> algorithms = new HashMap<>();


            LOG.info("@Algorithm - OK");
            for (Field fa: _algorithms) {
                String alg = mex.Configuration().addAlgorithm(fa.getAnnotation(Algorithm.class).algorithmType(),
                        fa.getAnnotation(Algorithm.class).algorithmID());

                algorithms.put(fa.getAnnotation(Algorithm.class).algorithmID(), alg);
            }


            *//* EXECUTIONS and MEASURES *//*

            _measures = getFieldsAnnotatedWith(klass, Measure.class);
            if (_measures == null)
                throw new Exception("error: missing annotation @Measure, please see " + javaDocURL + " for more information");

            addExecutions(_measures, ins, algorithms);


            if (returnValue!=null){
                LOG.debug("ret = " + returnValue.toString());
            }

            MEXSerializer.getInstance().saveToDisk(mexfile, "http://mex.aksw.org/examples/ISWC/001/", mex, MEXConstant.EnumRDFFormats.TTL);
            LOG.info("The MEX file has been successfully created: share it ;-)");

            END_TIME = System.currentTimeMillis();
            TOTAL_EXECUTION_TIME = END_TIME - START_TIME;
            LOG.info("process execution time (s): " + TOTAL_EXECUTION_TIME / 1000);


            *//*
            MEXCORE_Feature f = new MEXCORE_Feature();

            setPropertyByAnnotationName(f, "p1", "novo valor setado");
            setPropertyByAnnotationName(f, "p2", "valor 2 novo setado");
            callMethodByAnnotation(f, "novo valor setado");
            Map map = new HashMap<>();
            map.put("meuparametro", "novo valor setado");
            MEXCORE_Feature foo = new MEXCORE_Feature();
            callMethodByAnnotation(foo,map);
            ExecutionAnnotationParser parser = new ExecutionAnnotationParser();
            parser.parse(AnnotatedClass01.class);
            *//*

        } catch (Exception e) {
            LOG.error(e.toString());
        }

    }

    *//**
     * Create the executions based on the information mapped into the user class (@Measure)
     * TODO: have to extend this method to consider SingleExecutions as well. So far, just OverallExecution is allowed.
     * @param _measures
     * @param ins
     * @param algorithms
     * @return
     * @throws Exception
     *//*
    private static boolean addExecutions(List<Field> _measures, Object ins, HashMap<String, String> algorithms) throws Exception{


        HashMap<ExecutionHelperKey, Integer> vetExecutions = new HashMap<>();
        //HashMap<Integer, ExecutionHelperKey> vetExecutionsInv = new HashMap<>();
        int totalExecutions = 0;

        try{

            if (_measures == null || _measures.size() == 0)
                throw new Exception("You should declare at least one @Measure variable in your script :/");


            //assume that all the vectors of measures have the same size, once makes no sense to otherwise and we assume that would be a user error.
            //TODO: here we are going to control the SingleExecutions later...
            //check first the simplest case


            //the user has defined a vector to keep the measures
            if ((_measures.get(0).getAnnotation(Measure.class).algorithmID().equals("")) &&
                    (_measures.get(0).getAnnotation(Measure.class).idPhase().equals(MEXEnum.EnumPhases.TEST))){
                List<?> vectorExecutions = (List) _measures.get(0).get(ins);
                totalExecutions = vectorExecutions.size();

                if (algorithms.size() != totalExecutions)
                    throw new Exception("something is wrong...the number of algorithms (" + algorithms.size() + ") can not be different from the total of executions (" + totalExecutions + ")");

                for (int i=0; i<totalExecutions; i++){
                    ;

                    String idExecution = mex.Configuration().addExecution(MEXEnum.EnumExecutionsType.OVERALL,
                            MEXEnum.EnumPhases.TEST);

                    AlgorithmVO algtemp = algorithms.get(i);
                    mex.Configuration().Execution(String.valueOf(i+1)).setAlgorithm(algtemp);

                    //adding measures
                    for (Field fa : _measures) {

                        MEXEnum.EnumPhases p = MEXEnum.EnumPhases.values()[fa.getAnnotation(Measure.class).idPhase().ordinal()];
                        MEXEnum.EnumMeasures m = MEXEnum.EnumMeasures.values()[fa.getAnnotation(Measure.class).idMeasure().ordinal()];

                        List<?> mValue = (List) fa.get(ins);

                        mex.Configuration().Execution(String.valueOf(idExecution)).addPerformance(
                                m, Double.parseDouble(mValue.get(i).toString()));


                        LOG.info(m.toString() + " of Execution " + idExecution + " : " + mValue.get(i).toString());
                    }

                }

            }

            //the user has associate each algorithms and measures by using ``algorithmID'' value
            else {
                //n = get distinct of total of algorithms x phases
                int executionID = 1;
                for (Field fa : _measures) {


                    String k1 = fa.getAnnotation(Measure.class).algorithmID();
                    MEXEnum.EnumPhases k2 = MEXEnum.EnumPhases.values()[fa.getAnnotation(Measure.class).idPhase().ordinal()];

                    ExecutionHelperKey k = new ExecutionHelperKey(k1, k2);

                    //TODO: here I have to extend and consider when describing SingleExecutions
                    if (vetExecutions.putIfAbsent(k, executionID) == null) {
                        //success on add
                        executionID++;
                        //vetExecutionsInv.put(executionID, k);
                    }

                }

                //set n-executions and set its algorithm
                for (Map.Entry<ExecutionHelperKey, Integer> entry : vetExecutions.entrySet()) {

                    Integer value = entry.getValue();
                    ExecutionHelperKey key = entry.getKey();

                    mex.Configuration().addExecution(MEXEnum.EnumExecutionsType.OVERALL,
                            key.phaseID());

                    AlgorithmVO algtemp = algorithms.get(key.algorithmID());
                    mex.Configuration().Execution(String.valueOf(value)).setAlgorithm(algtemp);

                }

                //adding measures
                for (Field fa : _measures) {
                    String k1 = fa.getAnnotation(Measure.class).algorithmID();
                    MEXEnum.EnumPhases k2 = MEXEnum.EnumPhases.values()[fa.getAnnotation(Measure.class).idPhase().ordinal()];
                    MEXEnum.EnumMeasures m = MEXEnum.EnumMeasures.values()[fa.getAnnotation(Measure.class).idMeasure().ordinal()];


                    MEXEnum.EnumPhases phase = MEXEnum.EnumPhases.valueOf(k2.name());
                    executionID = vetExecutions.get(new ExecutionHelperKey(k1, phase));
                    List<?> mValue = (List) fa.get(ins);

                    //TODO: as noticed, so far considering just OverallExecution, therefore .get(0)
                    mex.Configuration().Execution(String.valueOf(executionID)).addPerformance(
                            m, Double.parseDouble(mValue.get(0).toString()));


                    LOG.info(m.toString() + " of Execution " + executionID + " : " + mValue.get(0).toString());
                }
            }

            return true;

        }catch (Exception e){
            LOG.error(e.getMessage());
            return false;
        }


    }

    private static Hashtable getValueOfPrivatedField(String fieldName, Object obj){

        Hashtable ret = null;

        try{

            Field f = obj.getClass().getDeclaredField(fieldName); //NoSuchFieldException
            f.setAccessible(true);
            ret = (Hashtable) f.get(obj); //IllegalAccessException

        }catch (NoSuchFieldException e1){
            LOG.error("name does not correspond to a declared field: " + e1.getMessage());
        }catch (IllegalAccessException e2){
            LOG.error("field was not accessible: " + e2.getMessage());
        }catch (Exception e3){
            LOG.error(e3.getMessage());
        }

        return ret;

    }

    private static Method getMethodAnnotatedWith(final Class<?> type, final Class<? extends Annotation> annotation) throws Exception{
        List<Method> methods = getMethodsAnnotatedWith(type, annotation);
        if ((methods != null) && (!methods.isEmpty())){
            if (methods.size() != 1){
                throw new Exception("The class should have just one annotation of " + annotation.toString() + ". Please see " + javaDocURL + " for more info.");
            }
            return methods.get(0);
        }
        return null;
    }

    public static List<Method> getMethodsAnnotatedWith(final Class<?> type, final Class<? extends Annotation> annotation) {
        LOG.debug("looking for method annotation of " + annotation.getSimpleName());

        final List<Method> methods = new ArrayList<>();
        Class<?> klass = type;
        while (klass != Object.class) {
            final List<Method> allMethods = new ArrayList<>(Arrays.asList(klass.getDeclaredMethods()));
            for (final Method method : allMethods) {
                if (method.isAnnotationPresent(annotation)) {
                    Annotation annotInstance = method.getAnnotation(annotation);
                    //if (annotInstance.accuracy() == 3 && annotInstance.enabled() == false) { }
                    methods.add(method);
                }
            }
            klass = klass.getSuperclass();
        }
        return methods;
    }

    private static Field getFieldAnnotatedWith(final Class<?> type, final Class<? extends Annotation> annotation) throws Exception{
        List<Field> fields = getFieldsAnnotatedWith(type, annotation);
        if ((fields != null) && (!fields.isEmpty())){
            if (fields.size() != 1){
                throw new Exception("The class should have just one annotation of " + annotation.toString() + ". Please see " + javaDocURL + " for more info.");
            }
            return fields.get(0);
        }
        return null;
    }

    public static List<Field> getFieldsAnnotatedWith(final Class<?> type, final Class<? extends Annotation> annotation) {
        LOG.debug("looking for field annotation of " + annotation.getSimpleName());

        final List<Field> fields = new ArrayList<>();
        Class<?> klass = type;
        while (klass != Object.class) {
            final List<Field> allFields = new ArrayList<>(Arrays.asList(klass.getDeclaredFields()));
            for (final Field field : allFields) {
                if (field.isAnnotationPresent(annotation)) {
                    Class fieldType = field.getType();
                    String fieldName = field.getName();
                    Annotation annotInstance = field.getAnnotation(annotation);
                    //if (annotInstance.accuracy() == 3 && annotInstance.enabled() == false) { }
                    fields.add(field);
                }
            }
            klass = klass.getSuperclass();
        }
        return fields;
    }

*/

}
