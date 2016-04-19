package org.aksw.mex;

/**
 * Created by dnes on 12/12/15.
 */


import org.aksw.mex.framework.annotations.core.ExperimentInfo;
import org.aksw.mex.framework.annotations.perf.Measure;
import org.aksw.mex.framework.AnnotatedClass01;
import org.aksw.mex.repeating.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;


public class Run {


    static void main2(){

        try{
            new AnnotatedClass01();
            Class<AnnotatedClass01> demoClassObj = AnnotatedClass01.class;
            readAnnotationOnExpInfo(demoClassObj);

            Method method = demoClassObj.getMethod("getAccuracy", new Class[]{});
            readAnnotationOnExecutions(method);
        }catch (Exception e){

        }

    }

    static void doSomething(){
        try {

            Class<AnnotatedClass01> cls = AnnotatedClass01.class;

            Method method2 = cls.getMethod("getAccuracy");


            Annotation[] annotations = method2.getAnnotations();

            for (Annotation annotation : method2.getAnnotations()){
                System.out.println("Annotation: " + annotation);
            }

        }catch (Exception e){

        }
    }

    public static void main(String[] args) throws Exception {

        main2();
        System.exit(0);

doSomething();

        System.exit(0);

        System.out.println("Starting the MEX Framework...");

        int passed = 0, failed = 0, count = 0, ignore = 0;

        Class<AnnotatedClass01> obj = AnnotatedClass01.class;

        // Process @ExperimentInfo
        if (obj.isAnnotationPresent(ExperimentInfo.class)) {

            Annotation annotation = obj.getAnnotation(ExperimentInfo.class);
            ExperimentInfo aExpInfo = (ExperimentInfo) annotation;
            System.out.printf(":: Experiment Info ::");
            System.out.printf("%nPriority :%s", aExpInfo.priority());
            System.out.printf("%nCreatedBy :%s", aExpInfo.createdBy());
            System.out.printf("%nTags :");

            int tagLength = aExpInfo.tags().length;
            for (String tag : aExpInfo.tags()) {
                if (tagLength > 1) {
                    System.out.print(tag + ", ");
                } else {
                    System.out.print(tag);
                }
                tagLength--;
            }

        }

        System.out.printf(":: Experiment Info ::");
        // Process @Test
        for (Method method : obj.getDeclaredMethods()) {

            // if method is annotated with @Test
            if (method.isAnnotationPresent(Test.class)) {

                Annotation annotation = method.getAnnotation(Test.class);
                Test test = (Test) annotation;

                // if enabled = true (default)
                if (test.enabled()) {

                    try {
                        method.invoke(obj.newInstance());
                        System.out.printf("%s - Test '%s' - passed %n", ++count, method.getName());
                        passed++;
                    } catch (Throwable ex) {
                        System.out.printf("%s - Test '%s' - failed: %s %n", ++count, method.getName(), ex.getCause());
                        failed++;
                    }

                } else {
                    System.out.printf("%s - Test '%s' - ignored%n", ++count, method.getName());
                    ignore++;
                }

            }

        }
        System.out.printf("%nResult : Total : %d, Passed: %d, Failed %d, Ignore %d%n", count, passed, failed, ignore);

    }







    /*************************************************************************
     * MEXCORE: Experiment Information Annotation
     * @param element
     */
    static void readAnnotationOnExpInfo(AnnotatedElement element)
    {
        try
        {
            System.out.println("\n [MEXCORE:EXPERIMENT CONFIGURATION] -> Finding annotations on " + element.getClass().getName());
            Annotation[] annotations = element.getAnnotations();
            for (Annotation annotation : annotations)
            {
                if (annotation instanceof ExperimentInfo)
                {
                    ExperimentInfo info = (ExperimentInfo) annotation;
                    System.out.println("author :" + info.createdBy());
                    System.out.println("e-mail :" + info.email());
                }
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    static void readAnnotationOnExecutions(AnnotatedElement element)
    {
        try
        {
            System.out.println("\n [MEXCORE:EXECUTION] -> Finding annotations on " + element.getClass().getName());
            Annotation[] annotations = element.getAnnotations();
            for (Annotation annotation : annotations)
            {
                if (annotation instanceof Execution)
                {
                    Execution execution = (Execution) annotation;
                    System.out.println("execution :" + execution.accuracy());
                }

                if (annotation instanceof Measure)
                {
                    Measure acc = (Measure) annotation;
                    System.out.println("accuracy :" + acc.idMeasure().name());
                }

            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}