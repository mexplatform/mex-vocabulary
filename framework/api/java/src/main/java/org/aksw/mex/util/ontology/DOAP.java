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
    public static final Property name;
    public static final Property homepage;
    public static final Property revision;
    public static final Property description;
    public static final Property programming_language;
    public static final Property os;
    public static final Property category;
    public static final Property location;


    public DOAP() {
    }

    public static String getURI() {
        return "http://usefulinc.com/ns/doap#";
    }

    static {
        NAMESPACE = m_model.createResource("http://usefulinc.com/ns/doap#");
        name = m_model.createProperty("http://usefulinc.com/ns/doap#name");
        homepage = m_model.createProperty("http://usefulinc.com/ns/doap#homepage");
        revision = m_model.createProperty("http://usefulinc.com/ns/doap#revision");
        description = m_model.createProperty("http://usefulinc.com/ns/doap#description");
        programming_language = m_model.createProperty("http://usefulinc.com/ns/doap#programming-language");
        os = m_model.createProperty("http://usefulinc.com/ns/doap#os");
        category = m_model.createProperty("http://usefulinc.com/ns/doap#category");
        location = m_model.createProperty("http://usefulinc.com/ns/doap#location");

    }


}
