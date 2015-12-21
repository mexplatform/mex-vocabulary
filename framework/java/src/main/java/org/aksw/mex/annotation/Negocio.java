package org.aksw.mex.annotation;

import org.aksw.mex.annotation.perf.Performance;

/**
 * Created by dnes on 06/11/15.
 */
public final class Negocio {

    double accuracy, fmeasure;

    // atributos e construtores
    @Performance(nome="Accuracy", value=0)
    public double getAccuracy() {
        return accuracy;
    }

    @Performance(nome="F-Measure", value=1)
    public double getFMeasure() {
        return fmeasure;
    }

}
