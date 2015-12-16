package example.tests.framework.perf;

import java.lang.annotation.*;
/**
 * Created by dnes on 13/12/15.
 */
@Retention( RetentionPolicy.RUNTIME )
@Target( ElementType.FIELD )
@Repeatable(Measures.class)
public @interface Measure {
    String idExecution() default "";
}
