package org.aksw.mex.framework.annotations.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * Created by dnes on 13/12/15.
 */
@Retention( RetentionPolicy.RUNTIME )
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface ExecutionEndTime {
    String idExecution() default ""; //not required, could be set to control individually instead of by array
}
