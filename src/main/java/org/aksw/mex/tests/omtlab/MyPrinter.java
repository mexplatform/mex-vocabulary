package org.aksw.mex.tests.omtlab;

/**
 * Created by dnes on 14/12/15.
 */
public class MyPrinter {

    private String name = "omt";
    private String address = "This is address";

    @PrintIt(isPrint = true)
    public String getName() {
        return name;
    }
    @PrintIt
    public String getAddress() {
        return address;
    }

}