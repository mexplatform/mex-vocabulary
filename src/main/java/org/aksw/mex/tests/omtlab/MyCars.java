package org.aksw.mex.tests.omtlab;

/**
 * Created by dnes on 14/12/15.
 */
public class MyCars {

    @Cars
    String colorRed = "Red car";

    String colorGreen = "Green car";

    @Cars(carName = { Cars.CarName.FERRARI, Cars.CarName.LAMBORGHINI }, color = "red")
    public void myCars() {
        System.out.println("I have following cars.");
    }

    public void notMyCars() {
        System.out.println("I don't have cars.");
    }

}
