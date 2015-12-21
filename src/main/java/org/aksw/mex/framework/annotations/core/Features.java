package org.aksw.mex.framework.annotations.core;

/**
 * Created by dnes on 16/12/15.
 */
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Features {
    String idExecution() default "";
}
