package org.aksw.mex.tests.omtlab;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by dnes on 14/12/15.
 */
public class MainCar {

    public static void main(String[] args) {
        MyCars myCars = new MyCars();

        Field[] fields = myCars.getClass().getDeclaredFields();

        // All Fields ----START
        for (Field field : fields) {

            if (field.isAnnotationPresent(Cars.class)) {

                try {

                    System.out.println("Field: " + field.get(myCars));

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
        // All Fields ----END

        // All Methods ----START

        Method[] methods = myCars.getClass().getDeclaredMethods();

        for (Method method : methods) {

            if (method.isAnnotationPresent(Cars.class)) {
                try {

                    method.invoke(myCars, null);

                    Cars cars = method.getAnnotation(Cars.class);

                    Cars.CarName[] carNameArray = cars.carName();

                    for (Cars.CarName carName : carNameArray) {
                        System.out.println("" + carName + " " + cars.color());
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        }

        // All Methods ----END

    }
}
