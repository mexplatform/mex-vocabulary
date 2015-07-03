package org.aksw.mex.util;

/**
 * Created by esteves on 08.06.15.
 */
public final class MEXConstant {

    public static final String TESTE_TTL = "/home/esteves/teste.ttl";
    public static final String MEX_ALGO_URL = "http://mex.aksw.org/mex-algo#";
    public static final String MEX_CORE_URL = "http://mex.aksw.org/mex-core#";
    public static final String MEX_PERF_URL = "http://mex.aksw.org/mex-perf#";

    public static final String FILE_FORMAT = "TTL";

    public static final String CLS_IMPLEMENTATION = "Implementation";
    public static final String CLS_CONTEXT = "Context";
    public static final String CLS_NAMED_ALGORITHM = "NamedAlgorithm";
    public static final String CLS_SAMPLING_METHOD = "SamplingMethod";
    public static final String CLS_PERFORMANCE_MEASURE = "PerformanceMeasure";

    public static final String DEFAULT_EXEC_ID = "MEX_EXEC_D";
    public static final String DEFAULT_EXP_CONFIGURATION_ID = "MEX_EXP_CONF_D";
    public static final String DEFAULT_EXP_ID = "MEX_EXPERIMENT";

    public class EnumRDFFormat{
        public static final String TURTLE = "TURTLE";
        public static final String TTL = "TTL";
        public static final String NTRIPLES =  "N-TRIPLES";
        public static final String NTRIPLE = "N-TRIPLE";
        public static final String NT = "NT";
        public static final String RDF_XML_ABBREV = "RDF/XML-ABBREV";
        public static final String RDF_XML = "RDF/XML";
        public static final String N3 = "N3";
        public static final String JSON_LD = "JSON-LD";
        public static final String RDF_JSON = "RDF/JSON";
    }

    private MEXConstant(){
        throw new AssertionError();
    }

}
