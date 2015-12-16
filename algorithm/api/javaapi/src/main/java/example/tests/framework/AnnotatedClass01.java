package example.tests.framework;

import example.tests.*;
import example.tests.mex.ExecutionEndTime;
import example.tests.mex.ExecutionStartTime;
import example.tests.repeating.Test;
import weka.core.Instances;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by dnes on 12/12/15.
 */

@ExperimentInfo(
        priority = ExperimentInfo.Priority.HIGH,
        createdBy = "Esteves",
        email = "diegoesteves@gmail.com",
        tags = {"vocabulary","MEX"}
)

public class AnnotatedClass01 {


    private double accuracy = 0;
    private static String dataset_name = "weather.txt";

    public static void main(String[] args) {

        //AnnotatedClass01 usr = new AnnotatedClass01();
        //usr.runProcess();
    }

    public AnnotatedClass01(){

    }

    @Start
    public void runProcess(){

        @ExecutionStartTime
        long START_TIME = System.currentTimeMillis();


        @ExecutionEndTime
        long END_TIME = System.currentTimeMillis();

        this.accuracy = 10;
    }

    @Execution(enabled = true)
    public double getAccuracy(boolean p1){
        return 0.325;
    }



    public void doSomething(){


        try{

            BufferedReader datafile = readDataFile(dataset_name);
            Instances data = new Instances(datafile);
            data.setClassIndex(data.numAttributes() - 1);

            @InterfaceLocalVariable
            int teste = 0;

            teste = 10;



        } catch (Exception e) {
            System.out.println(e.toString());
        }


}

    public static BufferedReader readDataFile(@Dataset String filename) {


        String f = filename;

        BufferedReader bReader = null;

        try {
            bReader = new BufferedReader(new InputStreamReader(
                    AnnotatedClass01.class.getResourceAsStream("/" + filename)));

        } catch (Exception ex) {
            System.err.println("File not found: " + filename);
        }
        return bReader;
    }

    @Test
    void testA() {
        if (true)
            throw new RuntimeException("This test always failed");
    }

    @Test(enabled = false)
    void testB() {
        if (false)
            throw new RuntimeException("This test always passed");
    }

    @Test(enabled = true)
    void testC() {
        if (10 > 1) {
            // do nothing, this test always passed.
        }
    }

}