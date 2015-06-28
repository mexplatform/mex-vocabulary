package org.aksw.mex;

import java.io.FileWriter;
import java.text.SimpleDateFormat;

import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.vocabulary.*;
import org.aksw.mex.util.Constants;
import org.aksw.mex.util.ontology.*;
import org.aksw.mex.util.ontology.mex.MEXALGO_10;
import org.aksw.mex.util.ontology.mex.MEXCORE_10;
import org.aksw.mex.util.ontology.mex.MEXPERF_10;

/**
 * Created by esteves on 25.06.15.
 */
public class MEXModel {
    private boolean _valid;

    private static MEXModel instance = null;
    protected MEXModel() {
        _valid=false;
    }
    public static MEXModel getInstance() {
        if(instance == null) {
            instance = new MEXModel();
        }
        return instance;
    }
    public boolean parse(){
        if (1==1) {
            _valid = true;
        }
        return _valid;
    }

    public void saveToDisk(String filename, String URIbase, MyMEX_10 mex){
        if (_valid){
            writeJena(filename, URIbase, mex);
        }
    }

    private void setHeaders(Model model, String URIbase){
        //setting the headers
        model.setNsPrefix(PROVO.PREFIX, PROVO.NS);
        model.setNsPrefix(MEXCORE_10.PREFIX, MEXCORE_10.NS);
        model.setNsPrefix(MEXPERF_10.PREFIX, MEXPERF_10.NS);
        model.setNsPrefix(MEXALGO_10.PREFIX, MEXALGO_10.NS);
        model.setNsPrefix("dc", DC_11.NS);
        model.setNsPrefix("owl", OWL.NS);
        model.setNsPrefix("this", URIbase);
        model.setNsPrefix("foaf", FOAF.NS);
        model.setNsPrefix("rdfs", RDFS.getURI());
        model.setNsPrefix("xsd", XSD.getURI());
        model.setNsPrefix("dct", DCTerms.NS);
        model.setNsPrefix("doap", DOAP.getURI());
        model.setNsPrefix("dcat", DCAT.getURI());
    }

    private void writeJena(String filename, String URIbase, MyMEX_10 mex){

        Model model = ModelFactory.createDefaultModel();
        setHeaders(model, URIbase);

        //common resources
        Resource provAgent = model.createResource(PROVO.NS + PROVO.ClasseTypes.AGENT);
        Resource provPerson = model.createResource(PROVO.NS + PROVO.ClasseTypes.PERSON);
        Resource provOrganization = model.createResource(PROVO.NS + PROVO.ClasseTypes.ORGANIZATION);
        Resource mexcore_APC = model.createResource(MEXCORE_10.NS + MEXCORE_10.ClasseTypes.APPLICATION_CONTEXT);

        //mex-core
        Resource appContext;

        //gets
        if (mex.getApplicationContext() != null){
            appContext = model.createResource(URIbase+ "application")
                    .addProperty(RDF.type, provAgent)
                    .addProperty(RDF.type, provPerson)
                    .addProperty(RDF.type, provOrganization)
                    .addProperty(RDF.type, mexcore_APC)
                    .addProperty(DCTerms.dateCopyrighted, new SimpleDateFormat("yyyy.MM.dd HH:mm:ss z").format(mex.getApplicationContext().get_fileDate()));


            if (mex.getApplicationContext().get_givenName() != null) {
                appContext.addProperty(FOAF.givenName, mex.getApplicationContext().get_givenName());}
            if (mex.getApplicationContext().get_mbox() != null) {
                appContext.addProperty(FOAF.mbox, mex.getApplicationContext().get_mbox());}

            if (mex.getApplicationContext().get_homepage() != null) {
                appContext.addProperty(DOAP.givenName, mex.getApplicationContext().get_givenName());}
            if (mex.getApplicationContext().get_description() != null) {
                appContext.addProperty(DOAP.mbox, mex.getApplicationContext().get_mbox());}
            if (mex.getApplicationContext().get_category() != null) {
                appContext.addProperty(DOAP.givenName, mex.getApplicationContext().get_givenName());}
            if (mex.getApplicationContext().get_location() != null) {
                appContext.addProperty(DOAP.mbox, mex.getApplicationContext().get_mbox());}

            if (mex.getApplicationContext().get_trustyURI() != null) {
                appContext.addProperty(MEXCORE_10.trustyURI, mex.getApplicationContext().get_mbox());}
        }


        //mex-algo


        //mex-perf

        Resource context = model.createResource(URIbase + "context");

        Resource john = model.createResource( URIbase + "John" );
        //Property hasSurname = model.createProperty( PROVO.NS + "hasSurname" );
        model.add( john, PROVO.wasAttributedTo, context );

        final Resource clsApplicationContext = model.createResource(MEXCORE_10.NS + MEXCORE_10.ClasseTypes.APPLICATION_CONTEXT );
        model.add(clsApplicationContext, PROVO.wasAttributedTo,"teste");

        Resource appcontext = model.createResource(URIbase + "xxx")
                .addProperty(DC.title, "dddddd")
                .addProperty(PROVO.wasAttributedTo, context)
                .addProperty(DC.description, "zzzzzzz");


        FileWriter out;
        try {

            out = new FileWriter(filename);
            model.write( out, Constants.EnumRDFFormat.TURTLE );
            out.close();
        }
        catch (Exception e) {
            System.out.println("error: " + e.toString());
        }

    }
}