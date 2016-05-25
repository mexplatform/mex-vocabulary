/**
 * Copyright (C) 2014 - 2016, Diego Esteves
 *
 * This file is part of LOG4MEX.
 *
 * LOG4MEX is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * LOG4MEX is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

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

    private class EnumRDFFormat{
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

    public enum EnumRDFFormats{

        TURTLE(EnumRDFFormat.TURTLE.toString()),
        TTL(EnumRDFFormat.TTL.toString()),
        NTRIPLES(EnumRDFFormat.NTRIPLES.toString()),
        NTRIPLE(EnumRDFFormat.NTRIPLE.toString()),
        NT(EnumRDFFormat.NT.toString()),
        RDF_XML_ABBREV(EnumRDFFormat.RDF_XML_ABBREV.toString()),
        RDF_XML(EnumRDFFormat.RDF_XML.toString()),
        N3(EnumRDFFormat.N3.toString()),
        JSON_LD(EnumRDFFormat.JSON_LD.toString()),
        RDF_JSON(EnumRDFFormat.RDF_JSON.toString());

        private final String text;
        /**
         * @param text
         */
        private EnumRDFFormats(final String text) {
            this.text = text;
        }

        /* (non-Javadoc)
         * @see java.lang.Enum#toString()
         */
        @Override
        public String toString() {return text;}

    }

    private MEXConstant(){
        throw new AssertionError();
    }

}
