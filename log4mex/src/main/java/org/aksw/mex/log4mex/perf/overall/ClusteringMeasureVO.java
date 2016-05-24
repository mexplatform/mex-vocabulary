package org.aksw.mex.log4mex.perf.overall;

/**
 * Created by esteves on 26.06.15.
 */
public class ClusteringMeasureVO extends Measure  {

    public void set_chebyschevDistance(Double _chebyschevDistance) {
        this._chebyschevDistance = _chebyschevDistance;
    }

    public void set_hammingDistance(Double _hammingDistance) {
        this._hammingDistance = _hammingDistance;
    }

    public void set_euclideanDistance(Double _euclideanDistance) {
        this._euclideanDistance = _euclideanDistance;
    }

    public void set_manhattanDistance(Double _manhattanDistance) {
        this._manhattanDistance = _manhattanDistance;
    }

    public void set_genSimilarityCoerfficient(Double _genSimilarityCoerfficient) {
        this._genSimilarityCoerfficient = _genSimilarityCoerfficient;
    }

    private Double _chebyschevDistance;
    private Double _hammingDistance;
    private Double _euclideanDistance;
    private Double _manhattanDistance;
    private Double _genSimilarityCoerfficient;

    public ClusteringMeasureVO(){

    }

    @Override
    public String getLabel(){
        return "Clustering measures";
    }

    @Override
    public boolean equals(Object other) {
        return false;
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
