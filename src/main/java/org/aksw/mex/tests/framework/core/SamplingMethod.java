package org.aksw.mex.tests.framework.core;

/**
 * Created by dnes on 12/12/15.
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE) //on class level
public @interface SamplingMethod {

    double trainSize() default 0.0;
    double testSize() default 0.0;
    int folds() default 1;
    boolean sequential() default true;

}