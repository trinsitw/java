package com.intellectworld.graphtheory.algorithm;

import com.intellectworld.graphtheory.Vertex;
import com.intellectworld.graphtheory.WeightedDirectedEdge;
import com.intellectworld.graphtheory.WeightedDirectedGraph;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Example {

    public static void main(String[] args) {
        {
            Vertex v1 = new Vertex("v1");
            Vertex v2 = new Vertex("v2");
            Vertex v3 = new Vertex("v3");
            Vertex v4 = new Vertex("v4");
            Vertex v5 = new Vertex("v5");
            Vertex v6 = new Vertex("v6");
            Set<Vertex> vertices = Stream.of(v1, v2, v3, v4, v5, v6).collect(Collectors.toSet());
            WeightedDirectedEdge e12 = new WeightedDirectedEdge("e12", v1, v2, 1);
            WeightedDirectedEdge e13 = new WeightedDirectedEdge("e13", v1, v3, 2);
            WeightedDirectedEdge e14 = new WeightedDirectedEdge("e14", v1, v4, 4);
            WeightedDirectedEdge e23 = new WeightedDirectedEdge("e23", v2, v3, 3);
            WeightedDirectedEdge e45 = new WeightedDirectedEdge("e45", v4, v5, 3);
            WeightedDirectedEdge e46 = new WeightedDirectedEdge("e46", v4, v6, 1);
            WeightedDirectedEdge e65 = new WeightedDirectedEdge("e65", v6, v5, 1);
            Set<WeightedDirectedEdge> edges = Stream.of(e12, e13, e14, e23, e45, e46, e65).collect(Collectors.toSet());

            WeightedDirectedGraph inputGraph = new WeightedDirectedGraph(vertices, edges);
            System.out.println("inputGraph: " + inputGraph);
            ShortestPathAlgorithm shortestPathAlgorithm = new BellmanFordAlgorithm();
            WeightedDirectedGraph shortestPath = shortestPathAlgorithm.findShortestPath(inputGraph, v1);
            System.out.println("shortestPath: " + shortestPath);
        }
        System.out.println("------------------------------------------------");

        {
            Vertex A = new Vertex("A");
            Vertex B = new Vertex("B");
            Vertex C = new Vertex("C");
            Vertex D = new Vertex("D");
            Vertex E = new Vertex("E");
            Set<Vertex> vertices = Stream.of(A, B, C, D, E).collect(Collectors.toSet());

            WeightedDirectedEdge AB = new WeightedDirectedEdge("AB", A, B, 1);
            WeightedDirectedEdge BC = new WeightedDirectedEdge("BC", B, C, 1);
            WeightedDirectedEdge CD = new WeightedDirectedEdge("CD", C, D, 1);
            WeightedDirectedEdge DE = new WeightedDirectedEdge("DE", D, E, 1);
            WeightedDirectedEdge EA = new WeightedDirectedEdge("EA", E, A, 1);
            WeightedDirectedEdge DA = new WeightedDirectedEdge("DA", D, A, 1);
            WeightedDirectedEdge CA = new WeightedDirectedEdge("CA", C, A, 1);
            Set<WeightedDirectedEdge> edges = Stream.of(AB, BC, CD, DE, EA, DA, CA).collect(Collectors.toSet());

            WeightedDirectedGraph graph = new WeightedDirectedGraph(vertices, edges);
            System.out.println("graph: " + graph);
            CycleDetectionAlgorithm cycleDetectionAlgorithm = new DPTAlgorithm();
            Set<List<WeightedDirectedEdge>> cycles = cycleDetectionAlgorithm.findCycles(graph, A);
            System.out.println("cycles: " + cycles);

        }
    }
}
