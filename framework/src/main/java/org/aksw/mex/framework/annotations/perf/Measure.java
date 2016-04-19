package org.aksw.mex.framework.annotations.perf;

import org.aksw.mex.util.MEXEnum;



import java.lang.annotation.*;
/**
 * Created by dnes on 13/12/15.
 */
@Retention( RetentionPolicy.RUNTIME )
@Target({ElementType.FIELD, ElementType.METHOD})
@Repeatable(Measures.class)
public @interface Measure {
    MEXEnum.EnumMeasures idMeasure();
    String algorithmID() default ""; //in case user want to create n-variables for controlling the measures, e.g.: acc1, acc2 instead of acc[]
    MEXEnum.EnumPhases idPhase() default MEXEnum.EnumPhases.TEST;
    MEXEnum.EnumExecutionsType executionType() default MEXEnum.EnumExecutionsType.OVERALL;
}
