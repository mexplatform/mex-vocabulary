package org.aksw.mex.tests.omtlab.reflection;

/**
 * Created by dnes on 14/12/15.
 */
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class DetailMain {

    public static void main(String args[])
    {
        System.out.println("getMethodNames------------------------------------------------------\n");
        getMethodNames();

        System.out.println("getConstructorNames--------------------------------------------------------------------\n");
        getConstructorNames();

        System.out.println("getFieldNames--------------------------------------------------------------------\n");
        getFieldNames();

        System.out.println("invokeMethodsAndVariables--------------------------------------------------------------------\n");
        invokeMethodsAndVariables();

        System.out.println("createObjectUsingConstructors--------------------------------------------------------------------\n");
        createObjectUseingConstructors();
    }


    public static void getMethodNames()
    {
        try{
            Class c = Class.forName("example.tests.omtlab.reflection.Vehicle");
            System.out.println("Class Name :"+c.getName()+"\n");
            Method allMethodsArray[] = c.getDeclaredMethods();
            int size = allMethodsArray.length;
            for(int i = 0 ; i < size ; i++)
            {
                System.out.println("Methods ( "+i+" ) :"+allMethodsArray[i]);
            }

        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void getConstructorNames()
    {
        try{
            Class c = Class.forName("example.tests.omtlab.reflection.Vehicle");
            System.out.println("Class Name :"+c.getName()+"\n");
            Constructor allConstructorArray[] = c.getDeclaredConstructors();

            int size = allConstructorArray.length;
            for(int i = 0 ; i < size ; i++)
            {
                System.out.println("Constructor ( "+i+" ) :"+allConstructorArray[i]);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getFieldNames()
    {
        try{
            Class c = Class.forName("example.tests.omtlab.reflection.Vehicle");
            System.out.println("Class Name :"+c.getName()+"\n");
            Field allFieldArray[] = c.getDeclaredFields();

            int size = allFieldArray.length;
            for(int i = 0 ; i < size ; i++)
            {
                System.out.println("Field ( "+i+" ) :"+allFieldArray[i]);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void invokeMethodsAndVariables()
    {
        try{
            Class c = Class.forName("example.tests.omtlab.reflection.Vehicle");
            System.out.println("Class Name :"+c.getName()+"\n");

            //Create Object of class
            //This is same as ::: Vehicle v = new Vehicle();
            Object o = c.newInstance();

            // ALL FIELD RELATED STUFF ----START
            //Here i demonstrate how to assign value to field using reflection
            Field allFieldArray[] = c.getDeclaredFields();
            int size = allFieldArray.length;
            for(int i = 0 ; i < size ; i++)
            {
                System.out.println("Field ( "+i+" ) :"+allFieldArray[i]);
                Field f = allFieldArray[i];
                Class<?> type = f.getType();
                //Here we identify type of field
                if(type.isInstance(new String()))
                {
                    //Here we set value of field
                    System.out.println(""+f.getName()+" :is Type String ");

                    //Only Public Member can be access here
                    //We can not access private or protected member of class, Here name is private so we can not access it
                    if(f.toString().indexOf("private") == -1 && f.toString().indexOf("protected") == -1)
                    {
                        f.set(o, "This is :"+f.getName());
                    }else
                    {
                        System.out.println(f.getName()+" is private or protected member");
                    }

                }else
                if(type.toString().equals("int"))
                {
                    System.out.println(""+f.getName()+" :is Type Integer ");
                    f.set(o, 1);
                }
            }
            // ALL FIELD RELATED STUFF ----END

            // ALL METHODS RELATED STUFF ----START
            //Here i demonstrate how to pass parameter and call methods
            Method allMethodArray[] = c.getDeclaredMethods();
            size = allMethodArray.length;
            for(int i = 0 ; i < size ; i++)
            {
                System.out.println("Methods ( "+i+" ) :"+allMethodArray[i]);
                Method m = allMethodArray[i];
                Class<?> parameterArray[] = m.getParameterTypes();
                Object parameterToPass[] = new Object[parameterArray.length];

                for(int j = 0 ; j < parameterArray.length ; j++)
                {
                    if(parameterArray[j].isInstance(new String()))
                    {
                        parameterToPass[j] = "Passing String in Param For Method:"+m.getName();
                    }else
                    if(parameterArray[j].toString().equals("int"))
                    {
                        parameterToPass[j] = 78;
                    }
                }
                Object returnValue = m.invoke(o, parameterToPass);

                System.out.println("***********RETURN_VALUE:"+returnValue);
            }
            //ALL METHODS RELATED STUFF ----END


        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void createObjectUseingConstructors()
    {
        try{
            Class c = Class.forName("example.tests.omtlab.reflection.Vehicle");
            System.out.println("Class Name :"+c.getName()+"\n");
            Constructor allConstructorArray[] = c.getDeclaredConstructors();

            int size = allConstructorArray.length;
            for(int i = 0 ; i < size ; i++)
            {
                System.out.println("Constructor ( "+i+" ) :"+allConstructorArray[i]);
                Constructor constructor = allConstructorArray[i];
                Class<?> parameterArray[] = constructor.getParameterTypes();
                Object parameterToPass[] = new Object[parameterArray.length];
                for(int j = 0 ; j < parameterArray.length ; j++)
                {
                    if(parameterArray[j].isInstance(new String()))
                    {
                        parameterToPass[j] = "Passing String in Constructor";
                    }else
                    if(parameterArray[j].toString().equals("int"))
                    {
                        parameterToPass[j] = 88;
                    }
                }
                Object o = constructor.newInstance(parameterToPass);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}