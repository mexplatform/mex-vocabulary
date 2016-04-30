package log4mex;

import org.aksw.mex.log4mex.MEXSerializer;
import org.aksw.mex.log4mex.MyMEX;
import org.aksw.mex.log4mex.algo.AlgorithmVO;
import org.aksw.mex.util.MEXConstant;
import org.aksw.mex.util.MEXEnum;

/**
 * Created by dnes on 01/05/16.
 */
public class Resource2Vec {

    public static void main(String[] args) {

        MyMEX mex = new MyMEX();

        try{

            mex.setAuthor("Tommaso Soru", "tsoru@informatik.uni-leipzig.de");

            AlgorithmVO idAlgo =  mex.Configuration().addAlgorithm(MEXEnum.EnumAlgorithms.RESCAL);
            mex.Configuration().setDataSet("tmp/resource2vec/datasets/yagoSchema.ttl", "yagoSchema");
            mex.Configuration().Algorithm(MEXEnum.EnumAlgorithms.RESCAL).addParameter("rank", "2");
            mex.Configuration().addFeature("http://w3id.org/resource2vec/embeddings/" + "justanexampleofhash".hashCode());

            String idExec = mex.Configuration().addExecution(MEXEnum.EnumExecutionsType.SINGLE, MEXEnum.EnumPhases.TEST);
            mex.Configuration().Execution(idExec).setAlgorithm(idAlgo);

            mex.Configuration().Execution(idExec).addPerformance(MEXEnum.EnumMeasures.PROCESSINGTIME, 3245);

            MEXSerializer.getInstance().saveToDisk(
                    "resource2vec","http://w3id.org/resource2vec/", mex, MEXConstant.EnumRDFFormats.JSON_LD);

            System.out.print("here you go! just share it ;-)");

        }catch (Exception e){
            System.out.print(e.toString());
        }

    }


}
