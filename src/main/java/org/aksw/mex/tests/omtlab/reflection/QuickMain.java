package org.aksw.mex.tests.omtlab.reflection;

/**
 * Created by dnes on 14/12/15.
 */
import java.lang.reflect.Method;

public class QuickMain {

    public static void main(String[] args) {

        try {
            Class c = Class.forName("example.tests.omtlab.reflection.Cars");
            Method m[] = c.getDeclaredMethods();
            Object carObj = null;
            carObj = c.newInstance();

            int size = m.length;

            for(int i = 0 ; i < size ; i++)
            {
                System.out.println("Class Name : "+c.getSimpleName()+" and Method Name :"+m[i]);
                Method  callM = m[i];
                callM.invoke(carObj, null);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}