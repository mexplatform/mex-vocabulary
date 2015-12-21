package org.aksw.mex.tests.framework;


import examples.framework.ExampleJSAT;
import org.aksw.mex.framework.annotations.Start;
import org.aksw.mex.tests.Execution;
import org.aksw.mex.tests.MethodAnnotation;
import org.aksw.mex.framework.annotations.core.Dataset;
import org.aksw.mex.framework.annotations.core.ExperimentInfo;
import org.aksw.mex.framework.annotations.core.Features;
import org.aksw.mex.framework.annotations.core.Hardware;
import org.aksw.mex.framework.annotations.perf.Measure;
import org.aksw.mex.tests.mex.MEXCORE_Feature;
import org.aksw.mex.tests.mex.types.FieldAnnotation;
import org.aksw.mex.tests.mex.types.ParameterAnnotation;
import org.aksw.mex.tests.mex.types.TypeAnnotation;
import org.apache.log4j.Logger;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by dnes on 13/12/15.
 */
public class MEXService {

    //private static Class<AnnotatedClass01> obj = AnnotatedClass01.class; //change it for args[0]
    private static Class<ExampleJSAT> obj = ExampleJSAT.class; //change it for args[0]
    private final static String javaDocURL = "http://mex.aksw.org/framework/doc";
    private final static Logger LOG = Logger.getLogger(MEXService.class);


    public static void main(String[] args) {

        long START_TIME = System.currentTimeMillis();

        Method _mainMethod;
        Field _dataset;
        List<Field> _accuracies;

        Method _getFeatures;
        String[] _features;

        try{


            //AnnotatedClass01 aaa = new AnnotatedClass01();
            ExampleJSAT aaa = new ExampleJSAT();


            LOG.info("MEX Framework -> more info at: http://mex.aksw.org");
            LOG.info("starting the meta annotation for class named: " + obj.getSimpleName());

            _mainMethod = getMethodAnnotatedWith(obj, Start.class);
            if (_mainMethod == null)
                throw new Exception("error: missing annotation @Start, please see " + javaDocURL + " for more information");

            if (obj.isAnnotationPresent(ExperimentInfo.class)) {
                Annotation annotation = obj.getAnnotation(ExperimentInfo.class);
                ExperimentInfo aExpInfo = (ExperimentInfo) annotation;
                System.out.println("************************************************************");
                System.out.println(":::: Experiment Info ::::");
                System.out.println("Identifier :" + aExpInfo.identifier());
                System.out.println("Description :" + aExpInfo.description());
                System.out.println("CreatedBy :" + aExpInfo.createdBy());
                System.out.println("E-mail :" + aExpInfo.email());
                System.out.println("Context :" + aExpInfo.context());
                System.out.printf("%nPriority :%s", aExpInfo.priority());
                System.out.printf("%nTags :");
                int tagLength = aExpInfo.tags().length;
                for (String tag : aExpInfo.tags()) {
                    if (tagLength > 1) {System.out.print(tag + ", ");} else {
                        System.out.print(tag);}
                    tagLength--;
                }
                System.out.println();

            }
            if (obj.isAnnotationPresent(Hardware.class)) {
                Annotation annotationHard = obj.getAnnotation(Hardware.class);
                Hardware aHard = (Hardware) annotationHard;
                System.out.println("************************************************************");
                System.out.println(":::: Hardware Info ::::");
                System.out.println("CPU :" + aHard.cpu());
                System.out.println("CPU Cache :" + aHard.cpuCache());
                System.out.println("HD :" + aHard.hdType());
                System.out.println("Memory :" + aHard.memory());
                System.out.println("Operational System :" + aHard.os());
                System.out.println("Video :" + aHard.video());
            }



            LOG.info("invoking the main method: " + _mainMethod.getName());

            //method.invoke(annotation, (Object[])null)
            Object returnValue = _mainMethod.invoke(aaa);



            _getFeatures = getMethodAnnotatedWith(obj, Features.class);
            if (_getFeatures == null)
                throw new Exception("error: missing annotation @Features, please see " + javaDocURL + " for more information");
            LOG.info("invoking the features method: " + _getFeatures.getName());
            Object retFeatures = _getFeatures.invoke(aaa);
            _features = (String[]) retFeatures;
            for(int i = 0; i < _features.length; i++)
                System.out.println(_features[i]);

            System.exit(0);



            _dataset = getFieldAnnotatedWith(obj, Dataset.class);
            if (_dataset == null)
                throw new Exception("error: missing annotation @Dataset, please see " + javaDocURL + " for more information");

            _accuracies = getFieldsAnnotatedWith(obj, Measure.class);
            if (_accuracies == null)
                throw new Exception("error: missing annotation @Accuracy, please see " + javaDocURL + " for more information");


            LOG.info("dataset = " + _dataset.getAnnotation(Dataset.class).toString());
            for (Field fa: _accuracies){
                LOG.info(" id exec: " + fa.getAnnotation(Measure.class).idExecution());
                LOG.info("acc1 = " + fa.getDouble(aaa));
            }



            LOG.debug("ret = " + returnValue.toString());


            long END_TIME = System.currentTimeMillis();
            long TOTAL_EXECUTION_TIME = END_TIME - START_TIME;
            LOG.info("process execution time (s): " + TOTAL_EXECUTION_TIME / 1000);


            System.exit(0);

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




        } catch (Exception e) {
            e.printStackTrace();
        }

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

    static void setPropertyByAnnotationName(final Object object, final String name, final Object value) throws Exception {
        final Field[] fields = object.getClass().getDeclaredFields();
        for (final Field field : fields) {
            final FieldAnnotation execAnnotation = field.getAnnotation(FieldAnnotation.class);
            if(execAnnotation!=null && name.equals(execAnnotation.value())){
                field.set(object, value);
            }
        }
    }

    public boolean isAliased(final Class type) {
        return (type.getAnnotation(TypeAnnotation.class) != null);
    }

    static Object callMethodByAnnotation(final Object object, final Object value) throws Exception{
        Object ret = null;
        final Method[] methods = object.getClass().getDeclaredMethods();
        for (final Method method : methods) {
            final MethodAnnotation methodAnnotation = method.getAnnotation(MethodAnnotation.class);
            if(methodAnnotation!=null){
                ret = method.invoke(object, value);
            }
        }

        return ret;
    }


    public static void callMethodByAnnotation(final Object object, final Map map) throws Exception{
        final Method[] methods = object.getClass().getDeclaredMethods();
        for (final Method method : methods) {
            final MethodAnnotation methodAnnotation = method.getAnnotation(MethodAnnotation.class);
            if (methodAnnotation != null) {

                final Annotation[][] parameterAnnotations = method.getParameterAnnotations();
                final Object[] parameters = new Object[parameterAnnotations.length];
                for (int i = 0; i < parameterAnnotations.length; i++) {
                    parameters[i] = null;

                    final Annotation[] annotations = parameterAnnotations[i];
                    for (final Annotation annotation : annotations) {
                        if (annotation instanceof ParameterAnnotation) {
                            parameters[i] = map.get(((ParameterAnnotation) annotation).value());
                        }
                    }
                }
                method.invoke(object, parameters);
            }
        }
    }

    public static class ExecutionAnnotationParser {
        public void parse(Class<?> clazz) throws Exception {
            Method[] methods = clazz.getMethods();
            int pass = 0;
            int fail = 0;
            for (Method method : methods) {
                if (method.isAnnotationPresent(org.aksw.mex.framework.annotations.core.Execution.class)) {
                    Execution ex = method.getAnnotation(Execution.class);
                    Boolean enable = ex.enabled();
                    try {
                        if (enable.equals(true))
                            method.invoke(AnnotatedClass01.class.newInstance(), enable);
                        pass++;
                    } catch (Exception e) {
                        if (enable) {
                            fail++;
                        } else {
                            pass++;
                        }
                    }
                }
            }
        }
    }



}
