package org.aksw.mex.tests.framework.perf;

import org.aksw.mex.util.MEXEnum;

import java.lang.annotation.*;
/**
 * Created by dnes on 13/12/15.
 */
@Retention( RetentionPolicy.RUNTIME )
@Target({ElementType.FIELD, ElementType.METHOD})
@Repeatable(Measures.class)
public @interface Measure {
    String idExecution() default "";
    MEXEnum.EnumMeasures idMeasure();
}
