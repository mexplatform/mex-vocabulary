package org.aksw.mex.tests.omtlab;

/**
 * Created by dnes on 14/12/15.
 */
import java.lang.reflect.Method;

public class Main {

    public static void main(String[] args) {

        MyPrinter myPrinter = new MyPrinter();

        Method[] methods = myPrinter.getClass().getMethods();

        for (Method m : methods) {

            if (m.isAnnotationPresent(PrintIt.class)) {

                PrintIt printIt = m.getAnnotation(PrintIt.class);

                if (printIt != null) //& (printIt.value() == true) {
                    try {

                        System.out.println("" + m.invoke(myPrinter, null));

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }