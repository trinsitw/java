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
                .collect(Collectors.toMap(v->v, v-> new BigDecimal(String.valueOf(Long.MAX_VALUE))));
        D.put(s, BigDecimal.ZERO);
        for (int i = 0; i < inputGraph.vertices().size() -1; i++ ) {
            for (WeightedDirectedEdge edge: inputGraph.edges()) {
                if (D.get(edge.vertex1()).add(edge.weight()).compareTo(D.get(edge.vertex2())) < 0) {
                    D.put(edge.vertex2(), D.get(edge.vertex1()).add(edge.weight()));
                    Optional<WeightedDirectedEdge> cancelledEdge = shortestPath.stream()
                            .filter(e -> e.vertex2().equals(edge.vertex2())).findFirst();
                    cancelledEdge.ifPresent(e -> shortestPath.remove(e));
                    shortestPath.add(edge);
                }
            }
        }
        // Look for negative cycle.
        Map<Vertex, BigDecimal> newD = new HashMap<>(D);
        for (WeightedDirectedEdge edge: inputGraph.edges()) {
            if (newD.get(edge.vertex1()).add(edge.weight()).compareTo(newD.get(edge.vertex2())) < 0) {
                newD.put(edge.vertex2(), newD.get(edge.vertex1()).add(edge.weight()));
            }
        }
        if (!newD.equals(D)) {
            throw new IllegalArgumentException("There exists a negative cycle in the graph. Could not find shortest path.");
        }
        return new WeightedDirectedGraph(inputGraph.vertices(), shortestPath);
    }

    public WeightedDirectedGraph findNegativeCycle(WeightedDirectedGraph inputGraph) {
        Set<Vertex> whiteVertices = new HashSet<>(inputGraph.vertices());
        Set<Vertex> greyVertices = new HashSet<>();
        Set<Vertex> blackVertices = new HashSet<>();

        while (whiteVertices.size() > 0) {
            Vertex vertex = whiteVertices.iterator().next();
            if (dfs(vertex, inputGraph.edges(), whiteVertices, greyVertices, blackVertices)) {
                break;
            }
        }
        Set<WeightedDirectedEdge> greyEdges = inputGraph.edges().stream()
                .filter(edge -> greyVertices.contains(edge.vertex1()) && greyVertices.contains(edge.vertex2()))
                .collect(Collectors.toSet());
        Set<WeightedDirectedEdge> negativeCycleEdges = new HashSet<>(greyEdges);

        while (negativeCycleEdges.stream()
                .anyMatch(edgeA -> negativeCycleEdges.stream()
                        .noneMatch(edgeB -> edgeB.vertex2().equals(edgeA.vertex1())))) {
            WeightedDirectedEdge nonCycleEdge = negativeCycleEdges.stream()
                    .filter(edgeA -> negativeCycleEdges.stream()
                            .noneMatch(edgeB -> edgeB.vertex2().equals(edgeA.vertex1()))).findFirst().get();
            negativeCycleEdges.remove(nonCycleEdge);
        }
        return new WeightedDirectedGraph(inputGraph.vertices(), negativeCycleEdges);
    }

    private boolean dfs(Vertex vertex, Set<WeightedDirectedEdge> edges, Set<Vertex> whiteVertices, Set<Vertex> greyVertices, Set<Vertex> blackVertices) {
        moveVertex(vertex, whiteVertices, greyVertices);
        Set<Vertex> adjacentVertices = edges.stream()
                .filter(edge -> edge.vertex1().equals(vertex))
                .map(edge -> edge.vertex2()).collect(Collectors.toSet());
        for (Vertex neighbor: adjacentVertices) {
            if (blackVertices.contains(neighbor)) {
                continue;
            }
            if (greyVertices.contains(neighbor)) {
                System.out.println("greyVertices: " + greyVertices);
                return true;
            }
            if (dfs(neighbor, edges, whiteVertices, greyVertices, blackVertices)) {
                return true;
            }
        }
        moveVertex(vertex, greyVertices, blackVertices);
        return false;
    }

    private void moveVertex(Vertex vertex, Set<Vertex> sourceSet, Set<Vertex> destinationSet) {
        sourceSet.remove(vertex);
        destinationSet.add(vertex);
    }
}
