package org.aksw.mex.annotation;

import org.aksw.mex.annotation.perf.Performance;

import java.lang.reflect.Method;

/**
 * Created by dnes on 06/11/15.
 */
public class Service {

    public boolean generateMetadata(Object o) throws Exception{

        Class<?> classe = o.getClass();

        int colunas = 0;
        for (Method metodo : classe.getDeclaredMethods()) {
            if (metodo.isAnnotationPresent(Performance.class)) {
                colunas++;
            }
        }
        return true;
    }

    public int getColumnCount() {
        return 0;
    }
}
