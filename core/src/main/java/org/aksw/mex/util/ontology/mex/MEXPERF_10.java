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
public class MEXPERF_10  extends MEXPERF implements IOntology {

    protected MEXPERF_10() {
    }
    private static Model m_model = ModelFactory.createDefaultModel();
    public static final String NS = "http://mex.aksw.org/mex-perf#";
    public static final String PREFIX = "mexperf";
    public static final Resource NAMESPACE;

    public static final Property pearsonCorrelation;
    public static final Property chiSquare;
    public static final Property error;
    public static final Property kolmogorovSmirnov;
    public static final Property mean;
    public static final Property nemenyi;
    public static final Property standardDeviation;
    public static final Property wilcoxon;
    public static final Property variance;
    public static final Property friedman;
    public static final Property median;
    public static final Property kappaStatistics;
    public static final Property mode;
    public static final Property L2norm;
    public static final Property L1norm;
    public static final Property Linfnorm;
    public static final Property MCC;

    public static final Property accuracy;
    public static final Property fMeasure;
    public static final Property precision;
    public static final Property recall;
    public static final Property roc;
    public static final Property sensitivity;
    public static final Property specificity;
    public static final Property trueNegative;
    public static final Property truePositive;
    public static final Property falseNegative;
    public static final Property falsePositive;
    public static final Property falseNegativeRate;
    public static final Property falsePositiveRate;
    public static final Property trueNegativeRate;
    public static final Property truePositiveRate;

    public static final Property chebyschevDistance;
    public static final Property hammingDistance;
    public static final Property euclideanDistance;
    public static final Property manhattanDistance;
    public static final Property genSimilarityCoerfficient;


    public static final Property meanAbsoluteDeviation;
    public static final Property meanSquareError;
    public static final Property residual;
    public static final Property totalError;
    public static final Property medianAbsoluteDeviation;
    public static final Property relativeAbsoluteError;
    public static final Property rootRelativeSquaredError;
    public static final Property rootMeanSquaredError;
    public static final Property correlationCoefficient;

    public static final Property formula;

    public static final Property predictedValue;
    public static final Property realValue;


    static {
        NAMESPACE = m_model.createResource(NS);
        pearsonCorrelation = m_model.createProperty(NS + "pearsonCorrelation");
        chiSquare = m_model.createProperty(NS + "chiSquare");
        error = m_model.createProperty(NS + "error");
        kolmogorovSmirnov = m_model.createProperty(NS + "kolmogorovSmirnov");
        mean = m_model.createProperty(NS + "mean");
        nemenyi = m_model.createProperty(NS + "nemenyi");
        standardDeviation = m_model.createProperty(NS + "standardDeviation");
        wilcoxon = m_model.createProperty(NS + "wilcoxon");
        variance = m_model.createProperty(NS + "variance");
        friedman = m_model.createProperty(NS + "friedman");
        median = m_model.createProperty(NS + "median");
        kappaStatistics = m_model.createProperty(NS + "kappaStatistics");
        mode = m_model.createProperty(NS + "mode");
        L2norm = m_model.createProperty(NS + "L2norm");
        L1norm = m_model.createProperty(NS + "L1norm");
        Linfnorm = m_model.createProperty(NS + "Linfnorm");
        MCC = m_model.createProperty(NS + "mcc");

        accuracy = m_model.createProperty(NS + "accuracy");
        fMeasure = m_model.createProperty(NS + "fMeasure");
        precision = m_model.createProperty(NS + "precision");
        recall = m_model.createProperty(NS + "recall");
        roc = m_model.createProperty(NS + "roc");
        sensitivity = m_model.createProperty(NS + "sensitivity");
        specificity = m_model.createProperty(NS + "specificity");
        trueNegative = m_model.createProperty(NS + "trueNegative");
        truePositive = m_model.createProperty(NS + "truePositive");
        falseNegative = m_model.createProperty(NS + "falseNegative");
        falsePositive = m_model.createProperty(NS + "falsePositive");
        falseNegativeRate = m_model.createProperty(NS + "falseNegativeRate");
        falsePositiveRate = m_model.createProperty(NS + "falsePositiveRate");
        trueNegativeRate = m_model.createProperty(NS + "trueNegativeRate");
        truePositiveRate = m_model.createProperty(NS + "truePositiveRate");

        chebyschevDistance = m_model.createProperty(NS + "chebyschevDistance");
        hammingDistance = m_model.createProperty(NS + "hammingDistance");
        euclideanDistance = m_model.createProperty(NS + "euclideanDistance");
        manhattanDistance = m_model.createProperty(NS + "manhattanDistance");
        genSimilarityCoerfficient = m_model.createProperty(NS + "genSimilarityCoerfficient");


        meanAbsoluteDeviation = m_model.createProperty(NS + "meanAbsoluteDeviation");
        meanSquareError = m_model.createProperty(NS + "meanSquareError");
        residual = m_model.createProperty(NS + "residual");
        totalError = m_model.createProperty(NS + "totalError");
        medianAbsoluteDeviation = m_model.createProperty(NS + "medianAbsoluteDeviation");
        relativeAbsoluteError = m_model.createProperty(NS + "relativeAbsoluteError");
        rootRelativeSquaredError = m_model.createProperty(NS + "rootRelativeSquaredError");
        rootMeanSquaredError = m_model.createProperty(NS + "rootMeanSquaredError");
        correlationCoefficient = m_model.createProperty(NS + "correlationCoefficient");

        formula = m_model.createProperty(NS + "formula");

        predictedValue = m_model.createProperty(NS + "predictedValue");
        realValue = m_model.createProperty(NS + "realValue");
    }

    public class ClasseTypes{
        public static final String PERFORMANCE_MEASURE = "PerformanceMeasure";
        public static final String STATISTICAL_MEASURE = "StatisticalMeasure";
        public static final String REGRESSION_MEASURE = "RegressionMeasure";
        public static final String CLASSIFICATION_MEASURE = "ClassificationMeasure";
        public static final String CLUSTERING_MEASURE = "ClusteringMeasure";

        public static final String EXAMPLE_PERFORMANCE_COLLECTION = "ExamplePerformanceCollection";
        public static final String EXAMPLE_PERFORMANCE_MEASURE = "ExamplePerformanceMeasure";

        public static final String USER_DEFINED_MEASURE_COLLECTION = "UserDefinedMeasureCollection";
        public static final String USER_DEFINED_MEASURE = "UserDefinedMeasure";

    }

    public class Predicates{

    }

}