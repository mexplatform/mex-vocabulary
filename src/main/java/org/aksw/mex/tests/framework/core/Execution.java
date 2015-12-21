package org.aksw.mex.tests.framework.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by dnes on 14/12/15.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Execution {

    //should ignore this test?
    public boolean enabled() default true;
    public double accuracy() default 0.0;

}

