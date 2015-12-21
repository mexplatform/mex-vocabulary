package org.aksw.mex.tests.repeating;

import java.lang.annotation.*;

/**
 * Created by dnes on 13/12/15.
 */
@Retention( RetentionPolicy.RUNTIME )
@Target( ElementType.TYPE_USE )
@Repeatable( RepeatedValues.class )
public @interface CanBeRepeated
{

    String value();
}
