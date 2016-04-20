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
public @interface Hardware {

    String os() default "";
    MEXEnum.EnumProcessors cpu() default MEXEnum.EnumProcessors.NOT_INFORMED;
    MEXEnum.EnumRAM memory() default MEXEnum.EnumRAM.NOT_INFORMED ;
    String hdType() default "";
    MEXEnum.EnumCaches cpuCache() default MEXEnum.EnumCaches.NOT_INFORMED;
    String video() default "";
}