//import javax.json.Json;
import javax.json.JsonObject;
import javax.json.stream.JsonParser;
//import javax.json.JsonReader;
//import javax.json.JsonArray;
//import javax.json.JsonArrayBuilder;

import org.aksw.mex.interfaces.MetaGeneration;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
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
import org.apache.log4j.Logger;

import java.io.FileReader;
import java.io.FileWriter;
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

    /* general */
    private final static String javaDocURL = "http://mex.aksw.org/interfaces/doc";
    private final static Logger LOG = Logger.getLogger(MetaGeneration.class);
    private final static MyMEX mex = new MyMEX();

    /* performance issues */
    private static long START_TIME;
    private static long END_TIME;
    private static long TOTAL_EXECUTION_TIME;


    //CommandLine cl = parser.parse(opt, args);

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

        try(FileWriter file = new FileWriter("/Users/igorcosta/mexproject/rest4mex/cache/algorithm.txt")) {
            file.write(jsonObject.toJSONString());
            System.out.println(jsonObject);
        }

        return "Algorithm - OK";

    }

    @Path("/setdatasetname")
    @POST
    @Consumes("application/json")
    public String setDatasetName(String content) throws Exception {

        JSONParser parser = new JSONParser();
        Object obj = parser.parse(content);
        JSONObject jsonObject = (JSONObject) obj;

        try(FileWriter file = new FileWriter("/Users/igorcosta/mexproject/rest4mex/cache/datasetname.txt")) {
            file.write(jsonObject.toJSONString());
            System.out.println(jsonObject);
        }

        return "Dataset - OK";

    }

    @Path("/execution")
    @POST
    @Consumes("application/json")
    public String execution (String content) throws Exception {

        JSONParser parser = new JSONParser();
        Object obj = parser.parse(content);
        JSONObject jsonObject = (JSONObject) obj;

        try(FileWriter file = new FileWriter("/Users/igorcosta/mexproject/rest4mex/cache/execution.txt")) {
            file.write(jsonObject.toJSONString());
            System.out.println(jsonObject);
        }

        return "Execution - OK";

    }



    @Path("/setexperimentinfo")
    @POST
    @Consumes("application/json")
    public String setExperimentInfo(String content) throws Exception {

        JSONParser parser = new JSONParser();
        Object obj = parser.parse(content);
        JSONObject jsonObject = (JSONObject) obj;

        try(FileWriter file = new FileWriter("/Users/igorcosta/mexproject/rest4mex/cache/experimentinfo.txt")) {
            file.write(jsonObject.toJSONString());
            System.out.println(jsonObject);
        }

        return "Experiment info - OK";

    }

    @Path("/setfeatures")
    @POST
    @Consumes("application/json")
    public String setFeatures (String content) throws Exception {

        JSONParser parser = new JSONParser();
        Object obj = parser.parse(content);
        JSONObject jsonObject = (JSONObject) obj;

        try(FileWriter file = new FileWriter("/Users/igorcosta/mexproject/rest4mex/cache/features.txt")) {
            file.write(jsonObject.toJSONString());
            System.out.println(jsonObject);
        }

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

        try(FileWriter file = new FileWriter("/Users/igorcosta/mexproject/rest4mex/cache/hardware.txt")) {
            file.write(jsonObject.toJSONString());
            System.out.println(jsonObject);
        }

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

        try(FileWriter file = new FileWriter("/Users/igorcosta/mexproject/rest4mex/cache/samplingmethod.txt")) {
            file.write(jsonObject.toJSONString());
            System.out.println(jsonObject);
        }


        return "Sampling method - OK";

    }

    @Path("/setinterfaceversion")
    @POST
    @Consumes("application/json")
    public String setInterfaceVersion (String content) throws Exception {

        JSONParser parser = new JSONParser();
        Object obj = parser.parse(content);
        JSONObject jsonObject = (JSONObject) obj;

        MEXEnum.EnumAnnotationInterfaceStyles version;

        return "Interface version - OK";


    }



    @Path("/measure")
    @POST
    @Consumes("application/json")
    public String measure (String content) throws Exception {

        JSONParser parser = new JSONParser();
        Object obj = parser.parse(content);
        JSONObject jsonObject = (JSONObject) obj;

        MEXEnum.EnumMeasures idMeasure = (MEXEnum.EnumMeasures) jsonObject.get("idMeasure");
        String algorithmID = (String) jsonObject.get("algorithmID");
        MEXEnum.EnumPhases idPhase = (MEXEnum.EnumPhases) jsonObject.get("idPhase");
        MEXEnum.EnumExecutionsType executionType = (MEXEnum.EnumExecutionsType) MEXEnum.EnumExecutionsType.valueOf((String) jsonObject.get("executionType"));


        return "Measure - OK";

    }

    public static void main(String[] args) throws Exception {
        //CommandLineParser parser = new DefaultParser();
        MEXSerializer.getInstance().saveToDisk("/Users/igorcosta/Downloads/experiment_1.ttl", "", mex, MEXConstant.EnumRDFFormats.TTL);

    }


    @Path("/start")
    @POST
    //@Consumes("application/json")
    public String start (String content) throws Exception {
        JSONParser parser = new JSONParser();
        String mexfile = "/Users/igorcosta/mexproject/rest4mex/cache/test1.ttl";

        //algorithm

        Object algorithm_ = parser.parse(new FileReader("/Users/igorcosta/mexproject/rest4mex/cache/algorithm.txt"));
        JSONObject jsonAlgorithm = (JSONObject) algorithm_;

        String algorithmID = (String) jsonAlgorithm.get("algoritbmID");
        String algorithmName = (String) jsonAlgorithm.get("algorithmName") ;
        String stringURL = (String) jsonAlgorithm.get("algorithmURI");
        URI algorithmURI =  new URI(stringURL);
        MEXEnum.EnumAlgorithmsClasses algorithmClass = MEXEnum.EnumAlgorithmsClasses.valueOf((String)jsonAlgorithm.get("algorithmClass"));
        String idExecution = (String) jsonAlgorithm.get("idExecution");

        mex.Configuration().addAlgorithm(algorithmID, algorithmName, algorithmClass, algorithmURI, idExecution);

        //Dataset Name

        Object datasetName = parser.parse(new FileReader("/Users/igorcosta/mexproject/rest4mex/cache/datasetname.txt"));
        JSONObject jsonDatasetName = (JSONObject) datasetName;

        String name = (String) jsonDatasetName.get("name");
        String URI = (String) jsonDatasetName.get("URI");
        String description = (String) jsonDatasetName.get("description");

        mex.Configuration().setDataSet(URI, description, name);


        //Execution

        Object execution_ = parser.parse(new FileReader("/Users/igorcosta/mexproject/rest4mex/cache/execution.txt"));
        JSONObject jsonExecution = (JSONObject) execution_;

        String id = (String) jsonExecution.get("id");
        //boolean enabled = (boolean) jsonExecution.get("enable");
        //double accuracy = (double) jsonExecution.get("accuracy");

        //mex.Configuration().addExecution(MEXEnum.EnumExecutionsType.OVERALL, key.phaseID());
        //mex.Configuration().Execution(String.valueOf(value)).setAlgorithm(algtemp);
        //mex.Configuration().Execution(String.valueOf(executionID)).addPerformance(m, Double.parseDouble(mValue.get(0).toString()));


        //Experiment Info

        Object experimentInfo = parser.parse(new FileReader("/Users/igorcosta/mexproject/rest4mex/cache/experimentinfo.txt"));
        JSONObject jsonExperimentInfo = (JSONObject) experimentInfo;

        String experimentId = (String) jsonExperimentInfo.get("id");
        String experimentTitle = (String) jsonExperimentInfo.get("title");
        String experimentDescription = (String) jsonExperimentInfo.get("description");
        String authorEmail= (String) jsonExperimentInfo.get("email");
        String authorName = (String) jsonExperimentInfo.get("author");
        Date experimentDate = (Date) jsonExperimentInfo.get("date");
        MEXEnum.EnumContexts context = (MEXEnum.EnumContexts) jsonExperimentInfo.get("context");


        mex.setExperimentId(experimentId);
        mex.setExperimentTitle(experimentTitle);
        mex.setExperimentDescription(experimentDescription);
        mex.setAuthorEmail(authorEmail);
        mex.setAuthorName(authorName);
        mex.setExperimentDate(experimentDate);
        mex.setContext(context);


        //Features

        Object features_ = parser.parse(new FileReader("/Users/igorcosta/mexproject/rest4mex/cache/features.txt"));
        JSONObject jsonFeatures = (JSONObject) features_;

//        String[] features = (String[]) jsonFeatures.get("features");
//        mex.Configuration().addFeature(features);

        //Hardware

        Object hardware = parser.parse(new FileReader("/Users/igorcosta/mexproject/rest4mex/cache/hardware.txt"));
        JSONObject jsonHardware = (JSONObject) hardware;

        MEXEnum.EnumProcessors cpu = MEXEnum.EnumProcessors.valueOf((String)jsonHardware.get("cpu"));
        MEXEnum.EnumRAM memory = MEXEnum.EnumRAM.valueOf((String)jsonHardware.get("memory"));
        String hd = (String) jsonHardware.get("hd");
        MEXEnum.EnumCaches cache = MEXEnum.EnumCaches.valueOf((String) jsonHardware.get("cache"));
        String os = (String) jsonHardware.get("memory");
        String video = (String) jsonHardware.get("hd");

        mex.Configuration().setHardwareConfiguration(os,cpu,memory,hd,cache);

        //Sampling Method

        Object samplingMethod = parser.parse(new FileReader("/Users/igorcosta/mexproject/rest4mex/cache/samplingmethod.txt"));
        JSONObject jsonSamplingMethod = (JSONObject) samplingMethod;

        SamplingMethod aSM = (SamplingMethod) jsonSamplingMethod.get("klass");
        double trainSize = (double) jsonSamplingMethod.get("trainSize");
        double testSize = (double) jsonSamplingMethod.get("testSize");
        int folds = (int) jsonSamplingMethod.get("folds");
        boolean sequential = (boolean) jsonSamplingMethod.get("sequential");

        mex.Configuration().setSamplingMethod(aSM.klass(), trainSize, testSize);
        mex.Configuration().SamplingMethod().setFolds(folds);
        mex.Configuration().SamplingMethod().setSequential(sequential);


        //interface version

        //measure


        MEXSerializer.getInstance().saveToDisk(mexfile, "/Users/igorcosta/Downloads/experiment_1.ttl", mex, MEXConstant.EnumRDFFormats.TTL);


        return "Your experiment has been generated.";
    }



}




