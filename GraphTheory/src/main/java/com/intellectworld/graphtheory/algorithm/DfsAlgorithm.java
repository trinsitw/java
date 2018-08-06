package com.intellectworld.graphtheory.algorithm;

import com.intellectworld.graphtheory.Vertex;
import com.intellectworld.graphtheory.WeightedDirectedEdge;
import com.intellectworld.graphtheory.WeightedDirectedGraph;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class DfsAlgorithm implements NegativeCycleDetectionAlgorithm {

    private Set<WeightedDirectedEdge> negativeCycleEdges = null;

    public WeightedDirectedGraph findNegativeCycle(WeightedDirectedGraph inputGraph) {
        negativeCycleEdges = null;

        Set<Vertex> whiteVertices = new HashSet<>(inputGraph.vertices());
        Set<Vertex> greyVertices = new HashSet<>();
        Set<Vertex> blackVertices = new HashSet<>();

        while (whiteVertices.size() > 0) {
            Vertex vertex = whiteVertices.iterator().next();
            if (dfs(vertex, inputGraph.edges(), whiteVertices, greyVertices, blackVertices)) {
                return new WeightedDirectedGraph(inputGraph.vertices(), new HashSet<>(negativeCycleEdges));
            }
        }
        throw new IllegalArgumentException("There exists no negative cycle in the graph.");
    }

    private boolean dfs(Vertex vertex, Set<WeightedDirectedEdge> edges, Set<Vertex> whiteSet, Set<Vertex> greySet, Set<Vertex> blackSet) {
        moveVertex(vertex, whiteSet, greySet);
        Set<Vertex> adjacentVertices = edges.stream()
                .filter(edge -> edge.vertex1().equals(vertex))
                .map(edge -> edge.vertex2()).collect(Collectors.toSet());
        for (Vertex neighbor : adjacentVertices) {
            if (blackSet.contains(neighbor)) {
                continue;
            }
            if (greySet.contains(neighbor)) {
                Set<WeightedDirectedEdge> cycleEdges = findCycle(greySet, edges);
                if (cycleEdges.stream().map(edge -> edge.weight())
                        .reduce(BigDecimal.ZERO, (weight1, weight2) -> weight1.add(weight2))
                        .compareTo(BigDecimal.ZERO) < 0) {
                    negativeCycleEdges = cycleEdges;
                    return true;
                }
                return false;
            }
            if (dfs(neighbor, edges, whiteSet, greySet, blackSet)) {
                return true;
            }
        }
        moveVertex(vertex, greySet, blackSet);
        return false;
    }

    private Set<WeightedDirectedEdge> findCycle(Set<Vertex> greyVertices, Set<WeightedDirectedEdge> edges) {
        Set<WeightedDirectedEdge> greyEdges = edges.stream()
                .filter(edge -> greyVertices.contains(edge.vertex1()) && greyVertices.contains(edge.vertex2()))
                .collect(Collectors.toSet());
        Set<WeightedDirectedEdge> cycleEdges = new HashSet<>(greyEdges);

        while (cycleEdges.stream()
                .anyMatch(edgeA -> cycleEdges.stream()
                        .noneMatch(edgeB -> edgeB.vertex2().equals(edgeA.vertex1())))) {
            WeightedDirectedEdge nonCycleEdge = cycleEdges.stream()
                    .filter(edgeA -> cycleEdges.stream()
                            .noneMatch(edgeB -> edgeB.vertex2().equals(edgeA.vertex1()))).findFirst().get();
            cycleEdges.remove(nonCycleEdge);
        }
        return cycleEdges;
    }

    private void moveVertex(Vertex vertex, Set<Vertex> sourceSet, Set<Vertex> destinationSet) {
        sourceSet.remove(vertex);
        destinationSet.add(vertex);
    }
}