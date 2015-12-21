package org.aksw.mex.tests.repeating;

import java.lang.annotation.Repeatable;

/**
 * Created by dnes on 14/12/15.
 */
@Repeatable(Schedules.class)
public @interface Schedule {
    String dayOfMonth() default "first";
    String dayOfWeek() default "Mon";
    int hour() default 12;
}