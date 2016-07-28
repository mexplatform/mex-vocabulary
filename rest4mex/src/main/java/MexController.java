//import javax.json.Json;
import javax.json.JsonObject;
import javax.json.stream.JsonParser;
//import javax.json.JsonReader;
//import javax.json.JsonArray;
//import javax.json.JsonArrayBuilder;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
//import org.json.*;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Consumes;

import org.aksw.mex.log4mex.MyMEX;
import org.aksw.mex.log4mex.MEXSerializer;

import org.aksw.mex.interfaces.annotations.InterfaceVersion;
import org.aksw.mex.interfaces.annotations.Start;
import org.aksw.mex.interfaces.annotations.algo.Algorithm;
import org.aksw.mex.interfaces.annotations.core.*;
import org.aksw.mex.interfaces.annotations.perf.Measure;
import org.aksw.mex.log4mex.MEXSerializer;
import org.aksw.mex.log4mex.MyMEX;
import org.aksw.mex.util.MEXConstant;
import org.aksw.mex.util.MEXEnum;
import org.aksw.mex.log4mex.algo.AlgorithmVO;
import org.aksw.mex.log4mex.core.HardwareConfigurationVO;
import org.aksw.mex.log4mex.core.SamplingMethodVO;
import org.aksw.mex.util.MEXEnum;

import java.io.StringReader;
import java.lang.reflect.Field;
import java.net.URI;
import java.util.Date;
import java.util.*;


import org.aksw.mex.util.MEXEnum;


/**
 * Created by igorcosta on 12/04/16.
 */

@Path("/experiment")
public class MexController {


    private  MyMEX mex = new MyMEX();
    //private  MEXSerializer mexSerializer  = new MEXSerializer();


    //cola
    /*@Path("/")
    @POST
    @Consumes("application/json")
    public String (String content) throws Exception {

        JSONParser parser = new JSONParser();
        Object obj = parser.parse(content);
        JSONObject jsonObject = (JSONObject) obj;

    }*/



    @Path("/algorithm")
    @POST
    @Consumes("application/json")
    public String algorithm (String content) throws Exception {

        JSONParser parser = new JSONParser();
        Object obj = parser.parse(content);
        JSONObject jsonObject = (JSONObject) obj;


        String algorithmID = (String) jsonObject.get("algoritbmID");
        String algorithmName = (String) jsonObject.get("algorithmName") ;
        URI algorithmURI = (URI) jsonObject.get("algorithmURI");
        MEXEnum.EnumAlgorithmsClasses algorithmClass = (MEXEnum.EnumAlgorithmsClasses) jsonObject.get("algorithmClass");
        String idExecution = (String) jsonObject.get("idExecution");

        mex.Configuration().addAlgorithm(algorithmID, algorithmName, algorithmClass, algorithmURI, idExecution);

        return "Algorithm - OK";

    }

    @Path("/setdatasetname")
    @POST
    @Consumes("application/json")
    public String setDatasetName(String content) throws Exception {

        JSONParser parser = new JSONParser();
        Object obj = parser.parse(content);
        JSONObject jsonObject = (JSONObject) obj;

        String name = (String) jsonObject.get("name");
        String URI = (String) jsonObject.get("URI");
        String description = (String) jsonObject.get("description");

        mex.Configuration().setDataSet(URI, description, name);

        return "Dataset - OK";

    }

    /***@Path("/execution")
    @POST
    @Consumes("application/json")
    public String execution (String content) throws Exception {

        JSONParser parser = new JSONParser();
        Object obj = parser.parse(content);
        JSONObject jsonObject = (JSONObject) obj;

        String id = (String) jsonObject.get("id");
        boolean enabled = (boolean) jsonObject.get("enable");
        double accuracy = (double) jsonObject.get("accuracy");

        return "Execution - OK";

    }***/



    @Path("/setexperimentinfo")
    @POST
    @Consumes("application/json")
    public String setExperimentInfo(String content) throws Exception {

        JSONParser parser = new JSONParser();
        Object obj = parser.parse(content);
        JSONObject jsonObject = (JSONObject) obj;

        String experimentId = (String) jsonObject.get("id");
        String experimentTitle = (String) jsonObject.get("title");
        String experimentDescription = (String) jsonObject.get("description");
        String authorEmail= (String) jsonObject.get("email");
        String authorName = (String) jsonObject.get("author");
        Date experimentDate = (Date) jsonObject.get("date");
        MEXEnum.EnumContexts context = (MEXEnum.EnumContexts) jsonObject.get("context");


        mex.setExperimentId(experimentId);
        mex.setExperimentTitle(experimentTitle);
        mex.setExperimentDescription(experimentDescription);
        mex.setAuthorEmail(authorEmail);
        mex.setAuthorName(authorName);
        mex.setExperimentDate(experimentDate);
        mex.setContext(context);


        //MEXSerializer.getInstance().parse(mex);
        MEXSerializer.getInstance().parseAndSave("experiment1","/Users/igorcosta/Downloads/experiment_1.ttl",mex,MEXConstant.EnumRDFFormats.TTL);
        //return Response.status(201);
        return "Experiment info - OK";

    }

    @Path("/setfeatures")
    @POST
    @Consumes("application/json")
    public String setFeatures (String content) throws Exception {

        JSONParser parser = new JSONParser();
        Object obj = parser.parse(content);
        JSONObject jsonObject = (JSONObject) obj;

        String[] features = (String[]) jsonObject.get("features");

        mex.Configuration().addFeature(features);

        return "Features - OK";
    }

    @Path("/sethardware")
    @POST
    @Consumes("application/json")
    public String setHardwareInfo(String content) throws Exception {

        JSONParser parser = new JSONParser();
        String stringToParse = content;
        Object obj = parser.parse(stringToParse);
        JSONObject jsonObject = (JSONObject) obj;

        MEXEnum.EnumProcessors cpu = (MEXEnum.EnumProcessors) jsonObject.get("cpu");
        MEXEnum.EnumRAM memory = (MEXEnum.EnumRAM) jsonObject.get("memory");
        String hd = (String) jsonObject.get("hd");
        MEXEnum.EnumCaches cache = (MEXEnum.EnumCaches) jsonObject.get("cpu");
        String os = (String) jsonObject.get("memory");
        String video = (String) jsonObject.get("hd");


        mex.Configuration().setHardwareConfiguration(os,cpu,memory,hd,cache);



        //MEXSerializer.getInstance().parse(mex);
        //MEXSerializer.getInstance().saveToDisk("/Users/igorcosta/Downloads/experiment_1.ttl","",mex);
        //return Response.status(201);
        return "Hardware info - OK";

    }

    @Path("/setsamplingmethod")
    @POST
    @Consumes("application/json")
    public String setSamplingMethod(String content) throws Exception {


        JSONParser parser = new JSONParser();
        String stringToParse = content;
        Object obj = parser.parse(stringToParse);
        JSONObject jsonObject = (JSONObject) obj;

        SamplingMethod aSM = (SamplingMethod) jsonObject.get("klass");
        double trainSize = (double) jsonObject.get("trainSize");
        double testSize = (double) jsonObject.get("testSize");
        int folds = (int) jsonObject.get("folds");
        boolean sequential = (boolean) jsonObject.get("sequential");



        mex.Configuration().setSamplingMethod(aSM.klass(), trainSize, testSize);
        mex.Configuration().SamplingMethod().setFolds(folds);
        mex.Configuration().SamplingMethod().setSequential(sequential);

        //MEXSerializer.getInstance().parse(mex);
        //MEXSerializer.getInstance().saveToDisk("/Users/igorcosta/Downloads/experiment_1.ttl","",mex);
        //return Response.status(201);
        return "Sampling method - OK";

    }

}




