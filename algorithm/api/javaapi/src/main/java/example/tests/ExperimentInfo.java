package example.tests;

/**
 * Created by dnes on 12/12/15.
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE) //on class level
public @interface ExperimentInfo {

    public enum Priority {
        LOW, MEDIUM, HIGH
    }

    Priority priority() default Priority.LOW;

    String[] tags() default "";

    String createdBy() default "";

    String lastModified() default "";

    String email() default "";

}