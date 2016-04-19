package org.aksw.mex.framework.annotations.perf;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by dnes on 15/12/15.
 */
@Retention( RetentionPolicy.RUNTIME )
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface Measures {
    Measure[] value();
}
