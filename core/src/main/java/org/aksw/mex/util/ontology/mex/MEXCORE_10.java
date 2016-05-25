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
package org.aksw.mex.util.ontology.mex;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import org.aksw.mex.util.ontology.IOntology;

/**
 * Created by esteves on 27.06.15.
 */
public abstract class MEXCORE_10 extends MEXCORE implements IOntology {

    protected MEXCORE_10() {
    }

    private static Model m_model = ModelFactory.createDefaultModel();
    public static final String   NS = "http://mex.aksw.org/mex-core#";
    public static final String   PREFIX = "mexcore";
    public static final Resource NAMESPACE;

    public static final Property group;

    public static final Property targetClass;

    public static final Property datasetRow;
    public static final Property datasetColumn;
    public static final Property exampleType;


    public static final Property startsAtPosition;
    public static final Property endsAtPosition;

    public static final Property executionErrorMessage;

    public static final Property startsAtTime;
    public static final Property endsAtTime;

    public static final Property trainSize;
    public static final Property testSize;
    public static final Property folds;
    public static final Property sequential;

    public static final Property cpu;
    public static final Property memory;
    public static final Property cache;
    public static final Property hd;
    public static final Property video;

    public static final Property trustyURI;
    public static final Property experimentHash;

    public static final Property dataNormalizedDescription;
    public static final Property outliersRemovedDescription;
    public static final Property noiseRemovedDescription;
    public static final Property attributeSelectionDescription;



    static {
        NAMESPACE = m_model.createResource(NS);
        group = m_model.createProperty(NS + "group");
        targetClass = m_model.createProperty(NS + "targetClass");
        startsAtPosition = m_model.createProperty(NS + "startsAtPosition");
        endsAtPosition = m_model.createProperty(NS + "endsAtPosition");
        executionErrorMessage = m_model.createProperty(NS + "errorMessage");

        datasetRow = m_model.createProperty(NS + "datasetRow");
        datasetColumn = m_model.createProperty(NS + "datasetColumn");
        exampleType = m_model.createProperty(NS + "exampleType");

        startsAtTime = m_model.createProperty(NS + "startsAtTime");
        endsAtTime = m_model.createProperty(NS + "endsAtTime");
        trainSize = m_model.createProperty(NS + "trainSize");
        testSize = m_model.createProperty(NS + "testSize");
        folds = m_model.createProperty(NS + "folds");
        sequential = m_model.createProperty(NS + "sequential");
        cpu = m_model.createProperty(NS + "cpu");
        memory = m_model.createProperty(NS + "memory");
        cache = m_model.createProperty(NS + "cache");
        hd = m_model.createProperty(NS + "hd");
        video = m_model.createProperty(NS + "video");
        trustyURI = m_model.createProperty(NS + "trustyURI");
        experimentHash = m_model.createProperty(NS + "experimentHash");

        dataNormalizedDescription = m_model.createProperty(NS + "dataNormalizedDescription");
        outliersRemovedDescription = m_model.createProperty(NS + "outliersRemovedDescription");
        noiseRemovedDescription = m_model.createProperty(NS + "noiseRemovedDescription");
        attributeSelectionDescription = m_model.createProperty(NS + "attributeSelectionDescription");

    }

    public class ClasseTypes{
        public static final String APPLICATION_CONTEXT = "ApplicationContext";
        public static final String CONTEXT = "Context";
        public static final String EXPERIMENT = "Experiment";
        public static final String EXPERIMENT_CONFIGURATION = "ExperimentConfiguration";
        public static final String SAMPLING_METHOD = "SamplingMethod";
        public static final String MODEL = "Model";
        public static final String EXECUTION_SINGLE = "ExecutionSingle";
        public static final String EXECUTION_OVERALL = "ExecutionOverall";
        public static final String FEATURE = "Feature";
        public static final String FEATURE_COLLECTION = "FeatureCollection";
        public static final String HARDWARE_CONFIGURATION = "HardwareConfiguration";
        public static final String DATASET = "Dataset";
        public static final String PHASE = "Phase";
        public static final String EXAMPLE = "Example";
        public static final String EXAMPLE_COLLECTION = "ExampleCollection";
        public static final String EXAMPLE_PERFORMANCE_COLLECTION = "ExamplePerformanceCollection";
    }

    public class Predicates{
        //public static final String TRUSTY_URI = "trustyUri";
    }

}
