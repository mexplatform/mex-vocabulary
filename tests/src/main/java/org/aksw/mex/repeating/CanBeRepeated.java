package org.aksw.mex.repeating;

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
