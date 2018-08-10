package com.intellectworld.graphtheory.algorithm;

import com.intellectworld.graphtheory.WeightedDirectedEdge;
import com.intellectworld.graphtheory.WeightedDirectedGraph;

import java.util.List;
import java.util.Set;

public interface CycleDetectionAlgorithm {
    Set<List<WeightedDirectedEdge>> findCycles(WeightedDirectedGraph graph);
}
