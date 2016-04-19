package org.aksw.mex.simple;

/**
 * Created by dnes on 15/12/15.
 */

public class AnnotatedClass {

    public void method1() {
        System.out.println("method1");
    }

    @ICanRun
    public void method2() {
        System.out.println("method2");
    }

    public void method3() {
        System.out.println("method3");
    }

    @ICanRun
    public void method5() {
        System.out.println("method4");
    }

}