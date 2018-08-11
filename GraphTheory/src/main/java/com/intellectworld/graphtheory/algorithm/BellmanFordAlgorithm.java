package com.intellectworld.graphtheory.algorithm;

import com.intellectworld.graphtheory.Vertex;
import com.intellectworld.graphtheory.WeightedDirectedEdge;
import com.intellectworld.graphtheory.WeightedDirectedGraph;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class BellmanFordAlgorithm implements  ShortestPathAlgorithm {

    public WeightedDirectedGraph findShortestPath(WeightedDirectedGraph inputGraph, Vertex s) {
        Set<WeightedDirectedEdge> shortestPath = new TreeSet<>();
        Map<Vertex, BigDecimal> D = inputGraph.vertices().stream()
                .collect(Collectors.toMap(v -> v, v -> new BigDecimal(String.valueOf(Long.MAX_VALUE))));
        D.put(s, BigDecimal.ZERO);
        for (int i = 0; i < inputGraph.vertices().size() - 1; i++) {
            for (WeightedDirectedEdge edge : inputGraph.edges()) {
                if (D.get(edge.vertex1()).add(edge.weight()).compareTo(D.get(edge.vertex2())) < 0) {
                    D.put(edge.vertex2(), D.get(edge.vertex1()).add(edge.weight()));
                    Optional<WeightedDirectedEdge> cancelledEdge = shortestPath.stream()
                            .filter(e -> e.vertex2().equals(edge.vertex2())).findFirst();
                    cancelledEdge.ifPresent(e -> shortestPath.remove(e));
                    shortestPath.add(edge);
                }
            }
        }
        Map<Vertex, BigDecimal> newD = new HashMap<>(D);
        for (WeightedDirectedEdge edge : inputGraph.edges()) {
            if (newD.get(edge.vertex1()).add(edge.weight()).compareTo(newD.get(edge.vertex2())) < 0) {
                newD.put(edge.vertex2(), newD.get(edge.vertex1()).add(edge.weight()));
            }
        }
        if (!newD.equals(D)) {
            throw new IllegalArgumentException("There exists a negative cycle in the graph. Could not find shortest path.");
        }
        return new WeightedDirectedGraph(inputGraph.vertices(), shortestPath);
    }
}