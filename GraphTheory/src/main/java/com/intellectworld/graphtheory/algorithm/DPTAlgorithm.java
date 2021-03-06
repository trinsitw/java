package com.intellectworld.graphtheory.algorithm;

import com.intellectworld.graphtheory.Vertex;
import com.intellectworld.graphtheory.WeightedDirectedEdge;
import com.intellectworld.graphtheory.WeightedDirectedGraph;

import java.util.*;
import java.util.stream.Collectors;

public class DPTAlgorithm implements CycleDetectionAlgorithm {

    private Set<List<WeightedDirectedEdge>> cycles;

    public Set<List<WeightedDirectedEdge>> findCycles(WeightedDirectedGraph graph, Vertex vertex) {
        cycles = new HashSet<>();
        Set<Vertex> whiteVertices = graph.vertices().stream().collect(Collectors.toSet());
        Stack<Vertex> greyVertices = new Stack<>();
        dpt(vertex, graph.edges(), whiteVertices, greyVertices);
        return cycles;
    }

    private void dpt(
            Vertex vertex,
            Set<WeightedDirectedEdge> edges,
            Set<Vertex> whiteVertices,
            Stack<Vertex> greyVertices)
    {
        whiteVertices.remove(vertex);
        greyVertices.push(vertex);
        Set<Vertex> neighborVertices = edges.stream().filter(edge -> edge.vertex1().equals(vertex))
                    .map(edge -> edge.vertex2()).collect(Collectors.toSet());

        Iterator neihborIterator = neighborVertices.iterator();
        while (neihborIterator.hasNext()) {
            Vertex neighborVertex = (Vertex) neihborIterator.next();
            if (whiteVertices.contains(neighborVertex)) {
                dpt(neighborVertex, edges, new HashSet<>(whiteVertices), (Stack<Vertex>) greyVertices.clone());
            } else if (greyVertices.contains(neighborVertex)) {
                List<WeightedDirectedEdge> cycle = getCycle(neighborVertex, edges, (Stack<Vertex>) greyVertices.clone());
                cycles.add(cycle);
            }
        }
     }

    List<WeightedDirectedEdge> getCycle(
            Vertex neighborVertex,
            Set<WeightedDirectedEdge> edges,
            Stack<Vertex> greyVertices)
    {
        List<WeightedDirectedEdge> cycleEdges = new ArrayList<>();
        Vertex v1 = null;
        Vertex v2 = neighborVertex;
        while ((v1 = greyVertices.pop()) != null) {
            Iterator iterator = edges.iterator();
            WeightedDirectedEdge edge = null;
            while (iterator.hasNext()) {
                edge = (WeightedDirectedEdge) iterator.next();
                if (edge.vertex1().equals(v1) && edge.vertex2().equals(v2)) {
                    cycleEdges.add(edge);
                    break;
                }
            }
            if (v1.equals(neighborVertex)) {
                break;
            }
            v2 = v1;
        }
        Collections.reverse(cycleEdges);
        return cycleEdges;
    }
}
