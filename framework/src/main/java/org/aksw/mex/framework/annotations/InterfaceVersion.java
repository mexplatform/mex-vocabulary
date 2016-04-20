package org.aksw.mex.framework.annotations;

import org.aksw.mex.util.MEXEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by dnes on 13/12/15.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface InterfaceVersion {
    MEXEnum.EnumAnnotationInterfaceStyles version() default MEXEnum.EnumAnnotationInterfaceStyles.M1;
}
