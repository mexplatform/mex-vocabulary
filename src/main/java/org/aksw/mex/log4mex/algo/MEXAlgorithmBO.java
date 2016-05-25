package org.aksw.mex.log4mex.algo;

import com.hp.hpl.jena.ontology.*;
import com.hp.hpl.jena.rdf.model.*;
import org.aksw.mex.util.MEXConstant;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by esteves on 08.06.15.
 */
public class MEXAlgorithmBO {

    private List<ImplementationVO> _implementations;
    //private AlgorithmParameterCollectionVO _algorithmParameters;
    //private Model model= ModelFactory.createDefaultModel();
    private OntModel _model= ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM);
    private InputStream is;
    private String _base, _rdfs, _doap;

    public MEXAlgorithmBO(){

        try {
            //_algorithmParameters = new AlgorithmParameterCollectionVO();
            _implementations = new ArrayList<ImplementationVO>();

            //is = new URL(org.aksw.mex.util.MEXConstant.MEX_ALGO_URL).openStream();
            is = new FileInputStream("/home/esteves/Desktop/mex-algo1.0.ttl");

            _model.read(is,null, MEXConstant.FILE_FORMAT);

            _base = _model.getNsPrefixURI("");
            _rdfs = _model.getNsPrefixURI("rdfs");
            _doap = _model.getNsPrefixURI("doap");


        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {

        }


    }

   /* public ImplementationVO getImplementation(String name) {
        List<ImplementationVO> ret = getImplementations(name);
        if (ret.size() >= 1) {
            return ret.get(0);
        }else {return null;}
    }
    */


    /**
     * get all the individuals for the Implementation Class
     * @param name A string containing the name of a specific individual
     * @return implementations  a list of individuals
     */
    /*public List<ImplementationVO> getImplementations(String name) {

        List<ImplementationVO> implementations = new ArrayList<ImplementationVO>();
        RDFNode val;
        OntProperty iProperty;
        OntClass iClass;

        name = name.toLowerCase();

        try {
            ImplementationVO vo;

            iClass =_model.getOntClass(_base + MEXConstant.CLS_IMPLEMENTATION);
            for (ExtendedIterator<? extends OntResource> it= iClass.listInstances(true); it.hasNext();) {
                Individual ind = (Individual) it.next();

                vo = new ImplementationVO();

                if (name == "" || ind.getLocalName().toString().toLowerCase().equals(name)) {

                    vo.setIndividualName(ind.getLocalName());

                    ArrayList<String> objPropList = vo.getListOfProperty();
                    for (String objProp : objPropList){

                        objProp.replace("rdfs:",_rdfs.toString());
                        objProp.replace("doap:",_doap.toString());

                        if (_rdfs != null && objProp.contains("rdfs:")) {
                            iProperty = _model.getOntProperty(objProp);
                            if (iProperty != null) {
                                val = ind.getPropertyValue(iProperty);
                                if (val != null) {
                                    vo.setProperty(objProp, val.toString());
                                }
                            }
                        }

                        if (_doap != null && objProp.contains("doap:")) {
                            iProperty = _model.getOntProperty(objProp);
                            if (iProperty != null) {
                                val = ind.getPropertyValue(iProperty);
                                if (val != null) {
                                    vo.setProperty(objProp, val.toString());
                                }
                            }
                        }

                    }


/*
                    if (_doap != null) {
                        iProperty = _model.getOntProperty(_doap + "name");
                        if (iProperty != null) {
                            val = ind.getPropertyValue(iProperty);
                            if (val != null) {
                                vo.setName(val.toString());
                            }
                        }
                        iProperty = _model.getOntProperty(_doap + "homepage");
                        if (iProperty != null) {
                            val = ind.getPropertyValue(iProperty);
                            if (val != null) {
                                vo.setHomepage(val.toString());
                            }
                        }
                        iProperty = _model.getOntProperty(_doap + "revision");
                        if (iProperty != null) {
                            val = ind.getPropertyValue(iProperty);
                            if (val != null) {
                                vo.setRevision(val.toString());
                            }
                        }
                        iProperty = _model.getOntProperty(_doap + "description");
                        if (iProperty != null) {
                            val = ind.getPropertyValue(iProperty);
                            if (val != null) {
                                vo.setDescription(val.toString());
                            }
                        }
                        iProperty = _model.getOntProperty(_doap + "programming-language");
                        if (iProperty != null) {
                            val = ind.getPropertyValue(iProperty);
                            if (val != null) {
                                vo.setProgrammingLanguage(val.toString());
                            }
                        }
                }

*/
  //              implementations.add(vo);
//
  //              }
   //         }

    //}

  //  catch(Exception e){
    //    System.out.println(e.toString());
  //  }
   //     return implementations;
  //  }

    //public boolean addAlgorithmParameter(org.aksw.mex.org.aksw.mex.log4mex.core.algo.AlgorithmParameterVO algorithm){
        //return this._algorithmParameters.getParameters().add(algorithm);
    //}

}

