package example;

import org.aksw.mex.MEXModel;
import org.aksw.mex.MyMEX_10;

/**
 * Created by esteves on 27.06.15.
 */
public class ExampleSimple {

    public static void main(String[] args) {

        MyMEX_10 mex = new MyMEX_10();
        mex.setAuthorName("D Esteves");
        mex.setAuthorEmail("esteves@informatik.uni-leipzig.de");



        //mex.getAlgorithmParameterCollection().getParameters().add(new AlgorithmParameterVO("C", "2"));


        MEXModel.getInstance().parse();
        MEXModel.getInstance().saveToDisk("teste.ttl","http://mex.aksw.org/examples/001/", mex);
        System.exit(0);

    }

}
