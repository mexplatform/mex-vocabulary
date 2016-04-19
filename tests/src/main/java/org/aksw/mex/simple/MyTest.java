package org.aksw.mex.simple;

/**
 * Created by dnes on 15/12/15.
 */
import java.lang.reflect.Method;

public class MyTest {

    public static void main(String[] args) {

        AnnotatedClass runner = new AnnotatedClass();
        Method[] methods = runner.getClass().getMethods();

        for (Method method : methods) {
            ICanRun annos = method.getAnnotation(ICanRun.class);
            if (annos != null) {
                try {
                    method.invoke(runner);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}