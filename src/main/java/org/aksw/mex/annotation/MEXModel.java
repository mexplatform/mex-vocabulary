package org.aksw.mex.annotation;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by dnes on 06/11/15.
 */
public class MEXModel {

    private final List<?> lista;
    private Class<?> classe;

    public MEXModel(List<?> lista) {
        this.lista = lista;
        this.classe = lista.get(0).getClass();
    }

    /**
     * searches for annotations in methods and variables that might contains a value of a performance measure
     * @param t
     * @param name
     * @return
     * @throws Exception
     */
    public double getPerformanceMeasure (ElementType t, String name) throws Exception{

        Object ret = null;

      /*
      if (t.equals(ElementType.METHOD)) {
            for (Method metodo : classe.getDeclaredMethods()) {
                if (metodo.isAnnotationPresent(Performance.class)) {
                    Performance anotacao = metodo.getAnnotation(Performance.class);
                    if (anotacao.nome() == name)
                        ret = metodo.invoke();
                }
            }
        } else if (t.equals(ElementType.LOCAL_VARIABLE)) {

        } else {
            throw new Exception("Annotation type not allowed: " + t.name());
        }

*/

        return 0d;

    }

    //getAnnotatedMethodsCountPerClass(Performance.class)

    public int getAnnotatedMethodsCountPerClass(Class<? extends Annotation> c) {
        int aux = 0;
        for (Method metodo : classe.getDeclaredMethods()) {
            if (metodo.isAnnotationPresent(c))
                aux++;
        }
        return aux;
    }

}
