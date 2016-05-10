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
    public static final Property hasHyperParameter;
    public static final Property hasToolParameter;
    public static final Property hasAlgorithmClass;

    public MEXALGO_10() {
    }


    static {
        NAMESPACE = m_model.createResource(NS);
        acronym = m_model.createProperty(NS + "acronym");
        hasHyperParameter = m_model.createProperty(NS + Predicates.HAS_HYPER_PARAMETER);
        hasToolParameter = m_model.createProperty(NS + Predicates.HAS_TOOL_PARAMETER);
        hasAlgorithmClass = m_model.createProperty(NS + Predicates.HAS_ALGORITHM_CLASS);
    }

    public class ClasseTypes{

        public static final String TOOL_PARAMETER = "ToolParameter";
        public static final String TOOL_PARAMETER_COLLECTION = "ToolParameterCollection";
        public static final String ALGORITHM = "Algorithm";
        public static final String HYPER_PARAMETER_COLLECTION = "HyperParameterCollection";
        public static final String HYPER_PARAMETER = "HyperParameter";
    }

    public class Predicates{
        public static final String HAS_HYPER_PARAMETER = "hasHyperParameter";
        public static final String HAS_TOOL_PARAMETER = "hasToolParameter";
        public static final String HAS_ALGORITHM_CLASS = "hasAlgorithmClass";
    }

}