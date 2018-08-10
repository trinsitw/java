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
    /*    {
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
            Vertex v1 = new Vertex("v1");
            Vertex v2 = new Vertex("v2");
            Vertex v3 = new Vertex("v3");
            Vertex v4 = new Vertex("v4");
            Vertex v5 = new Vertex("v5");
            Vertex v6 = new Vertex("v6");
            Set<Vertex> vertices = Stream.of(v1, v2, v3, v4, v5, v6).collect(Collectors.toSet());
            WeightedDirectedEdge e12 = new WeightedDirectedEdge("e12", v1, v2, 1);
            WeightedDirectedEdge e13 = new WeightedDirectedEdge("e13", v1, v3, 1);
            WeightedDirectedEdge e23 = new WeightedDirectedEdge("e23", v2, v3, 1);
            WeightedDirectedEdge e41 = new WeightedDirectedEdge("e41", v4, v1, 1);
            WeightedDirectedEdge e45 = new WeightedDirectedEdge("e45", v4, v5, -1);
            WeightedDirectedEdge e56 = new WeightedDirectedEdge("e56", v5, v6, -1);
            WeightedDirectedEdge e64 = new WeightedDirectedEdge("e64", v6, v4, -1);
            Set<WeightedDirectedEdge> edges = Stream.of(e12, e13, e23, e41, e45, e56, e64).collect(Collectors.toSet());

            WeightedDirectedGraph inputGraph = new WeightedDirectedGraph(vertices, edges);
            System.out.println("inputGraph: " + inputGraph);
            NegativeCycleDetectionAlgorithm negativeCycleDetectionAlgorithm = new DfsAlgorithm();
            WeightedDirectedGraph negativeCycle = negativeCycleDetectionAlgorithm.findNegativeCycle(inputGraph);
            System.out.println("negativeCycle: " + negativeCycle);
        }*/
    }
}
