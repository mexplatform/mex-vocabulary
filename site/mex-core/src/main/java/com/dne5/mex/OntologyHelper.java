package com.dne5.mex;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;

import java.util.Iterator;


/**
 * Created by esteves on 14.04.15.
 */
public class OntologyHelper {

    public static void main(String[] args){

        OntModel m = ModelFactory.createOntologyModel();
        //OntModel m = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM ); --> https://jena.apache.org/documentation/ontology/



        // create the base model
        String SOURCE = "http://www.eswc2006.org/www.eswc2006.org/technologies/ontology-content/2006-09-21.rdf";
        String NS = SOURCE + "#";
        OntModel base = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM );
        base.read( SOURCE, "RDF/XML" );

        // create the reasoning model using the base
        OntModel inf = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM_MICRO_RULE_INF, base );

        // create a dummy paper for this example
        OntClass paper = base.getOntClass( NS + "Paper" );
        Individual p1 = base.createIndividual( NS + "paper1", paper );


        // list the asserted types
        for (Iterator<Resource> i = p1.listRDFTypes(false); i.hasNext(); ) {
            System.out.println( p1.getURI() + " is asserted in class " + i.next() );
        }

        // list the inferred types
        p1 = inf.getIndividual( NS + "paper1" );
        for (Iterator<Resource> i = p1.listRDFTypes(false); i.hasNext(); ) {
            System.out.println( p1.getURI() + " is inferred to be in class " + i.next() );
        }




    }
}
