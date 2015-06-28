package org.aksw.mex.util.ontology;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;

/**
 * Created by esteves on 28.06.15.
 */
public class DOAP {
    private static Model m_model = ModelFactory.createDefaultModel();
    public static final String NS = "http://usefulinc.com/ns/doap#";
    public static final Resource NAMESPACE;
    public static final Property givenName;
    public static final Property mbox;


    public DOAP() {
    }

    public static String getURI() {
        return "http://usefulinc.com/ns/doap#";
    }

    static {
        NAMESPACE = m_model.createResource("http://usefulinc.com/ns/doap#");
        givenName = m_model.createProperty("http://usefulinc.com/ns/doap#givenName");
        mbox = m_model.createProperty("http://usefulinc.com/ns/doap#mbox");

    }


}
