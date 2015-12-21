package org.aksw.mex.tests;

/**
 * Created by dnes on 13/12/15.
 */
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;

// A simple annotation type.
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotation {
    String stringValue();

    int intValue();
}

@Retention(RetentionPolicy.RUNTIME)
@interface What {
    String description();
}

@What(description = "An annotation test class")
@MyAnnotation(stringValue = "for class", intValue = 100)
public class MainClass {
    // Annotate a method.
    @What(description = "An annotation test method")
    @MyAnnotation(stringValue = "Annotation Example", intValue = 100)
    public static void myMethod() {
    }

    public static void main(String[] arg) {
        try {
            MainClass ob = new MainClass();

            Method m = ob.getClass( ).getMethod("myMethod");
            Annotation[] annos = m.getAnnotations();

            System.out.println("All annotations for myMeth:");
            for(Annotation a : annos)
                System.out.println(a);


        } catch (Exception exc) {
        }
    }
}