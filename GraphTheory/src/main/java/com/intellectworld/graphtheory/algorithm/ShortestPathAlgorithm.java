package com.intellectworld.graphtheory.algorithm;

import com.intellectworld.graphtheory.Vertex;
import com.intellectworld.graphtheory.WeightedDirectedGraph;

public interface ShortestPathAlgorithm {
    WeightedDirectedGraph findShortestPath(WeightedDirectedGraph graph, Vertex s);
}
