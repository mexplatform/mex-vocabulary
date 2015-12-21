package org.aksw.mex.tests;

/**
 * Created by dnes on 14/12/15.
 */
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface InterfaceField {

    public String name() default "";

}