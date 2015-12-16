package example.tests.framework;


import example.tests.*;
import example.tests.mex.MEXCORE_Feature;
import example.tests.mex.types.FieldAnnotation;
import example.tests.mex.types.ParameterAnnotation;
import example.tests.mex.types.TypeAnnotation;
import org.apache.log4j.Logger;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by dnes on 13/12/15.
 */
public class MEXService {

    private static Class klassTarget = AnnotatedClass01.class; //change it for args[0]
    private final static String javaDocURL = "http://mex.aksw.org/framework/doc";
    private final static Logger LOG = Logger.getLogger(MEXService.class);

    //https://docs.oracle.com/javase/8/docs/api/java/lang/annotation/ElementType.html
    //http://docs.oracle.com/javase/tutorial/reflect/member/methodInvocation.html
    //http://docs.oracle.com/javase/tutorial/reflect/class/classMembers.html

    /*
    http://tutorials.jenkov.com/java-reflection/index.html
    http://docs.oracle.com/javase/1.5.0/docs/guide/language/annotations.html
    http://docs.oracle.com/javase/tutorial/reflect/class/classMembers.html
    https://docs.oracle.com/javase/8/docs/api/java/lang/annotation/ElementType.html
    http://docs.oracle.com/javase/tutorial/reflect/member/methodInvocation.html

     */


    public static void main(String[] args) {

        long START_TIME = System.currentTimeMillis();

        Method _mainMethod;
        List<Field> _dataset;


        try{
            LOG.info("MEX Framework -> more info at: http://mex.aksw.org");
            LOG.info("starting the meta annotation for class named: " + klassTarget.getSimpleName());

            _mainMethod = getMethodAnnotatedWith(klassTarget, Start.class);
            if (_mainMethod == null)
                throw new Exception("error: missing annotation @MEX_START, please see " + javaDocURL + " for more information");

            _dataset = getFieldsAnnotatedWith(klassTarget, Dataset.class);
            if (_mainMethod == null)
                throw new Exception("error: missing annotation @MEX_START, please see " + javaDocURL + " for more information");

            LOG.info("invoking the main method: " + _mainMethod.getName());
            Object returnValue = _mainMethod.invoke(null    , null);
            LOG.debug("ret = " + returnValue.toString());


            long END_TIME = System.currentTimeMillis();
            long TOTAL_EXECUTION_TIME = END_TIME - START_TIME;
            LOG.info("process execution time (s): " + TOTAL_EXECUTION_TIME / 1000);

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

    static void callMethodByAnnotation(final Object object, final Object value) throws Exception{
        final Method[] methods = object.getClass().getDeclaredMethods();
        for (final Method method : methods) {
            final MethodAnnotation methodAnnotation = method.getAnnotation(MethodAnnotation.class);
            if(methodAnnotation!=null){
                method.invoke(object, value);
            }
        }
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
                if (method.isAnnotationPresent(example.tests.mex.Execution.class)) {
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
