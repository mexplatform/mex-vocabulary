package org.aksw.mex.framework;

import org.aksw.mex.framework.annotations.perf.Performance;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by dnes on 06/11/15.
 */
public class ArgentumTableModel  {

    private final List<?> lista;
    private Class<?> classe;

    public ArgentumTableModel(List<?> lista) {
        this.lista = lista;
        this.classe = lista.get(0).getClass();
    }


    public int getRowCount() {
        return lista.size();
    }

    public int getColumnCount() {
        int colunas = 0;
        for (Method metodo : classe.getDeclaredMethods()) {
            if (metodo.isAnnotationPresent(Performance.class))
                colunas++;
        }
        return colunas;
    }

    public Object getValueAt(int linha, String nome) {
        try {
            Object objeto = lista.get(linha);
            for (Method metodo : classe.getDeclaredMethods()) {
                if (metodo.isAnnotationPresent(Performance.class)) {
                    Performance anotacao = metodo.getAnnotation(Performance.class);
                    if (anotacao.nome() == nome)
                        return metodo.invoke(objeto);
                }
            }
        } catch (Exception e) {
            return "Erro";
        }
        return "";
    }

    public String getColumnName(int id) {
        for (Method metodo : classe.getDeclaredMethods()) {
            if (metodo.isAnnotationPresent(Performance.class)) {
                Performance anotacao = metodo.getAnnotation(Performance.class);
                if (anotacao.value() == id)
                    return anotacao.nome();
            }
        }
        return "";
    }

}