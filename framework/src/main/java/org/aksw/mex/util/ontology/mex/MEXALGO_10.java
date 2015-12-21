package org.aksw.mex.util.ontology.mex;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import org.aksw.mex.util.ontology.IOntology;

/**
 * Created by esteves on 27.06.15.
 */
public class MEXALGO_10 extends MEXALGO implements IOntology {

    private static Model m_model = ModelFactory.createDefaultModel();
    public static final String NS = "http://mex.aksw.org/mex-algo#";
    public static final String PREFIX = "mexalgo";
    public static final Resource NAMESPACE;
    public static final Property acronym;
    public static final Property hasParameter;

    public MEXALGO_10() {
    }


    static {
        NAMESPACE = m_model.createResource(NS);
        acronym = m_model.createProperty(NS + "acronym");
        hasParameter = m_model.createProperty(NS + Predicates.HAS_PARAMETER);
    }

    public class ClasseTypes{
        public static final String LEARNING_METHOD = "LearningMethod";
        public static final String LEARNING_PROBLEM = "LearningProblem";
        public static final String ALGORITHM_CLASS = "AlgorithmClass";
        public static final String IMPLEMENTATION = "Implementation";
        public static final String ALGORITHM = "Algorithm";
        public static final String ALGORITHM_PARAMETER_COLLECTION = "AlgorithmParameterCollection";
        public static final String ALGORITHM_PARAMETER = "AlgorithmParameter";
    }

    public class Predicates{
        public static final String HAS_LEARNING_METHOD = "hasLearningMethod";
        public static final String HAS_LEARNING_PROBLEM = "hasLearningProblem";
        public static final String HAS_CLASS = "hasClass";
        public static final String HAS_PARAMETER_COLLECTION = "hasParameterCollection";
        public static final String HAS_PARAMETER = "hasParameterCollection";
    }

}