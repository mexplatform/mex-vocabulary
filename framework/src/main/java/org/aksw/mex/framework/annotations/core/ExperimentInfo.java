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
public @interface ExperimentInfo {

    public enum Priority {
        LOW, MEDIUM, HIGH
    }

    String identifier() default "";
    String date() default "";
    String description() default "";
    String title() default "";

    String createdBy() default "";
    String email() default "";
    MEXEnum.EnumContexts context() default MEXEnum.EnumContexts.NOT_INFORMED;

    Priority priority() default Priority.LOW;
    String[] tags() default "";




}