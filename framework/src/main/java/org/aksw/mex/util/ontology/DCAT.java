package org.aksw.mex.util.ontology;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;

/**
 * Created by esteves on 28.06.15.
 */
public class DCAT {
    private static Model m_model = ModelFactory.createDefaultModel();
    public static final String NS = "http://www.w3.org/ns/dcat#";
    public static final Resource NAMESPACE;
    public static final Property landingPage;


    public DCAT() {
    }

    public static String getURI() {
        return "http://www.w3.org/ns/dcat#";
    }

    static {
        NAMESPACE = m_model.createResource("http://www.w3.org/ns/dcat#");
        landingPage = m_model.createProperty("http://www.w3.org/ns/dcat#landingPage");

    }


}
