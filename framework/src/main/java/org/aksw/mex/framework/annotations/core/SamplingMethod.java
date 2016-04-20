package org.aksw.mex.framework.annotations.core;

/**
 * Created by dnes on 12/12/15.
 */

import org.aksw.mex.util.MEXEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE) //on class level
public @interface SamplingMethod {

    MEXEnum.EnumSamplingMethods klass() default MEXEnum.EnumSamplingMethods.EVALUATION_ON_TRAINING_DATA;
    double trainSize() default 1;
    double testSize() default 1;
    int folds() default 1;
    boolean sequential() default true;

}