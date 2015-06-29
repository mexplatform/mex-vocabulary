package org.aksw.mex.perf.overall;

import org.aksw.mex.perf.IPerformance;

/**
 * Created by esteves on 26.06.15.
 */
public class ClusteringMeasureVO implements IPerformance {

    public void set_chebyschevDistance(double _chebyschevDistance) {
        this._chebyschevDistance = _chebyschevDistance;
    }

    public void set_hammingDistance(double _hammingDistance) {
        this._hammingDistance = _hammingDistance;
    }

    public void set_euclideanDistance(double _euclideanDistance) {
        this._euclideanDistance = _euclideanDistance;
    }

    public void set_manhattanDistance(double _manhattanDistance) {
        this._manhattanDistance = _manhattanDistance;
    }

    public void set_genSimilarityCoerfficient(double _genSimilarityCoerfficient) {
        this._genSimilarityCoerfficient = _genSimilarityCoerfficient;
    }

    private double _chebyschevDistance;
    private double _hammingDistance;
    private double _euclideanDistance;
    private double _manhattanDistance;
    private double _genSimilarityCoerfficient;

    public ClusteringMeasureVO(){

    }
}
