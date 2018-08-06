package com.intellectworld.graphtheory.algorithm;

import com.intellectworld.graphtheory.WeightedDirectedGraph;

public interface NegativeCycleDetectionAlgorithm {
    WeightedDirectedGraph findNegativeCycle(WeightedDirectedGraph graph);
}
