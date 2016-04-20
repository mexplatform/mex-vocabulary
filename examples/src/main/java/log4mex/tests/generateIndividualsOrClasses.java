package log4mex.tests;

import com.hp.hpl.jena.ontology.*;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import org.aksw.mex.util.MEXConstant;

import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Created by esteves on 26.06.15.
 */
public class generateIndividualsOrClasses {

    public static void main(String[] args){

        //class to helps to generate the enums for org.aksw.mex.util.MEXEnum

        generateIndividualsOrClassesForEnum("/home/esteves/Desktop/mex-algo1.0.ttl", MEXConstant.CLS_IMPLEMENTATION, "I");
        generateIndividualsOrClassesForEnum("/home/esteves/Desktop/mex-core1.0.ttl", MEXConstant.CLS_CONTEXT, "I");
        generateIndividualsOrClassesForEnum("/home/esteves/Desktop/mex-algo1.0.ttl", MEXConstant.CLS_NAMED_ALGORITHM, "SC");
        generateIndividualsOrClassesForEnum("/home/esteves/Desktop/mex-core1.0.ttl", MEXConstant.CLS_SAMPLING_METHOD, "SC");
        generateIndividualsOrClassesForEnum("/home/esteves/Desktop/mex-perf1.0.ttl", MEXConstant.CLS_PERFORMANCE_MEASURE, "SC");

    }

    private static void generateIndividualsOrClassesForEnum(String file, String name, String type) {
        InputStream is2;
        String base2;
        OntProperty iProperty2;
        OntModel ontModel2;

        try{
            //is = new URL(org.aksw.mex.util.MEXConstant.MEX_ALGO_URL).openStream();
            is2 = new FileInputStream(file);
            ontModel2 = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM);
            ontModel2.read(is2, null, MEXConstant.FILE_FORMAT);
            base2 = ontModel2.getNsPrefixURI("");
            //iProperty2 = ontModel2.getOntProperty(base2 + "acronym");
            //System.out.println(iProperty2.toString());
            OntClass iClass =ontModel2.getOntClass(base2 + name);

            if (type.equals("SC")) {
                for (ExtendedIterator<? extends OntResource> it= iClass.listSubClasses(true);it.hasNext();) {
                    OntClass cls = (OntClass) it.next();
                    //RDFNode iValue = ins.getPropertyValue(iProperty2);
                    System.out.println(" public static final String " + cls.getLocalName().replace("-","").toUpperCase() + " = \""+ cls.getLocalName() +"\";");
                }
            }else if (type.equals("I")) {
                for (ExtendedIterator<? extends OntResource> it= iClass.listInstances(true);it.hasNext();) {
                    Individual ins = (Individual) it.next();
                    //RDFNode iValue = ins.getPropertyValue(iProperty2);
                    System.out.println(" public static final String " + ins.getLocalName().replace("-", "").toUpperCase() + " = \""+ ins.getLocalName() +"\";");
                }
            }


        }
        catch (Exception e){
            System.out.println(e.toString());
        }
    }
}
