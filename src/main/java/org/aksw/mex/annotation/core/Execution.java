package org.aksw.mex.annotation.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by dnes on 06/11/15.
 */
@Target(ElementType.LOCAL_VARIABLE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Execution {
    String id();
}
