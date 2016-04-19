package org.aksw.mex.repeating;

/**
 * Created by dnes on 14/12/15.
 */
public class MainSchedule {

    @Schedule(dayOfMonth="last")
    @Schedule(dayOfWeek="Fri", hour=23)
    public void doPeriodicCleanup() {

    }


}
