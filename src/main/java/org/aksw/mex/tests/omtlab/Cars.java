package org.aksw.mex.tests.omtlab;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by dnes on 14/12/15.
 */
@Documented
@Inherited
@Target({ ElementType.FIELD, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Cars {
    public enum CarName {
        BMW("Bmw"), FERRARI("Ferrari"), LAMBORGHINI("Lamborghini"), FORD("Ford");
        String name;

        private CarName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return this.name;
        }
    }

    String color() default "White";

    CarName[] carName() default { CarName.FERRARI };
}