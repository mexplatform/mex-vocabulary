package org.aksw.mex.util.ontology;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;

/**
 * Created by esteves on 28.06.15.
 */
public class FOAF {
    private static Model m_model = ModelFactory.createDefaultModel();
    public static final String NS = "http://xmlns.com/foaf/0.1/";
    public static final Resource NAMESPACE;
    public static final Property givenName;
    public static final Property mbox;


    public FOAF() {
    }

    public static String getURI() {
        return "http://xmlns.com/foaf/0.1/";
    }

    static {
        NAMESPACE = m_model.createResource("http://xmlns.com/foaf/0.1/");
        givenName = m_model.createProperty("http://xmlns.com/foaf/0.1/givenName");
        mbox = m_model.createProperty("http://xmlns.com/foaf/0.1/mbox");

    }


}
