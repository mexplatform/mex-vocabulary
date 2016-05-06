package org.aksw.mex.framework.annotations.algo;

/**
 * Created by dnes on 16/12/15.
 */
import org.aksw.mex.util.MEXEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Algorithm {
    String algorithmID() default "";
    MEXEnum.EnumAlgorithmsClasses algorithmType();
    String idExecution() default ""; //not required, could be set to control individually instead of by array

}
