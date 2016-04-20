package log4mex.tests;

/**
 * Created by esteves on 26.06.15.
 */
public class ModelSimulatorExample {
    private static ModelSimulatorExample instance = null;
    protected ModelSimulatorExample() {
    }
    public static ModelSimulatorExample getInstance() {
        if(instance == null) {
            instance = new ModelSimulatorExample();
        }
        return instance;
    }

    public void run(String phase){
        System.out.println(phase + " model running...wait");
        try {
            if (phase.equals("train")){
                Thread.sleep(2000);
            }else {
                Thread.sleep(4000);
            }
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();}
    }

    public double getAccuracyTrain(){
        System.out.println("accuracy = 0.88");
        return 0.88;
    }

    public double getAccuracyTest(){
        System.out.println("accuracy = 0.65");
        return 0.65;
    }

    public double getfMeasureTrain(){
        System.out.println("accuracy = 0.7");
        return 0.7;
    }

    public double getfMeasureTest(){
        System.out.println("accuracy = 0.57");
        return 0.57;
    }

    public Integer getTruePositive(){
        return 124;
    }

    public Integer getFalsePositive(){
        return 54;
    }



}
