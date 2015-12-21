package examples.log4mex;

import com.hp.hpl.jena.ontology.*;
import com.hp.hpl.jena.rdf.model.*;
import com.hp.hpl.jena.util.FileManager;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import org.aksw.mex.log4mex.algo.ImplementationVO;
import org.aksw.mex.log4mex.algo.MEXAlgorithmBO;
import org.aksw.mex.util.MEXConstant;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

/**
 * Created by esteves on 09.06.15.
 */
public class Exampleold {

    public static void main(String[] args) {
        System.out.println("org.aksw.mex.example.Exampleold for interchange results of machine learning!");


        MEXAlgorithmBO algBO = new MEXAlgorithmBO();
        List<ImplementationVO> imp ; //algBO.getImplementations("");

        //System.out.println(imp.size());


        FileManager.get().addLocatorClassLoader(Exampleold.class.getClassLoader());
        Model model = FileManager.get().loadModel("/home/esteves/teste.ttl", null, "TURTLE");
        StmtIterator iter = model.listStatements();
        Property p1 = model.getProperty("http://www.w3.org/1999/02/22-rdf-syntax-ns#type1");

        try {
            InputStream is;
            is = new FileInputStream("/home/esteves/teste.ttl");
            OntModel ontModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM);
            ontModel.read(is, null, MEXConstant.FILE_FORMAT);

            OntProperty iProperty = ontModel.getOntProperty("http://mex.org#dataProperty1");

            System.out.println(iProperty.toString());

            OntClass iClass =ontModel.getOntClass("http://mex.org#classe2");
            for (ExtendedIterator<? extends OntResource> it= iClass.listInstances(true);it.hasNext();) {
                Individual ins = (Individual) it.next();

                RDFNode iValue = ins.getPropertyValue(iProperty);

                System.out.println(iValue.toString());
            }


        }
        catch (Exception e){
            System.out.println(e.toString());
        }


        try {
            while (iter.hasNext()) {
                Statement stmt = iter.next();

                Resource s = stmt.getSubject();
                Resource p = stmt.getPredicate();
                RDFNode o = stmt.getObject();

                System.out.print(s.toString() + " - ");
                System.out.print(p.toString() + " - ");
                System.out.println(o.toString());

            }
        }finally {
            if ( iter != null ) iter.close();
        }
    }

}


