package com.intellectworld.graphtheory.algorithm;

import com.intellectworld.graphtheory.Vertex;
import com.intellectworld.graphtheory.WeightedDirectedEdge;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.intellectworld.graphtheory.WeightedDirectedGraph;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DPTAlgorithmTest {

    @Test
    public void findCyclesTest1() {
        Vertex A = new Vertex("A");
        Vertex B = new Vertex("B");
        Vertex C = new Vertex("C");
        Vertex D = new Vertex("D");
        Set<Vertex> vertices = Stream.of(A, B, C, D).collect(Collectors.toSet());

        WeightedDirectedEdge AB = new WeightedDirectedEdge("AB", A, B, 1);
        WeightedDirectedEdge BC = new WeightedDirectedEdge("BC", B, C, 1);
        WeightedDirectedEdge CD = new WeightedDirectedEdge("CD", C, D, 1);
        WeightedDirectedEdge DB = new WeightedDirectedEdge("DB", D, B, 1);
        WeightedDirectedEdge AD = new WeightedDirectedEdge("AD", A, D, 1);
        Set<WeightedDirectedEdge> edges = Stream.of(AB, BC, CD, DB, AD).collect(Collectors.toSet());

        WeightedDirectedGraph graph = new WeightedDirectedGraph(vertices, edges);
        CycleDetectionAlgorithm cycleDetectionAlgorithm = new DPTAlgorithm();
        Set<List<WeightedDirectedEdge>> cycles = cycleDetectionAlgorithm.findCycles(graph);
        assertEquals(1, cycles.size());

        Set<WeightedDirectedEdge> expected = Stream.of(CD, DB, BC).collect(Collectors.toSet());
        assertEquals(expected, new HashSet<WeightedDirectedEdge>(cycles.stream().findFirst().get()));
    }

    @Test
    public void findCyclesTest2() {
        Vertex A = new Vertex("A");
        Vertex B = new Vertex("B");
        Vertex C = new Vertex("C");
        Vertex D = new Vertex("D");
        Set<Vertex> vertices = Stream.of(A, B, C, D).collect(Collectors.toSet());

        WeightedDirectedEdge AB = new WeightedDirectedEdge("AB", A, B, 1);
        WeightedDirectedEdge BC = new WeightedDirectedEdge("BC", B, C, 1);
        WeightedDirectedEdge CD = new WeightedDirectedEdge("CD", C, D, 1);
        WeightedDirectedEdge DA = new WeightedDirectedEdge("DA", D, A, 1);
        WeightedDirectedEdge AC = new WeightedDirectedEdge("AC", A, C, 1);
        Set<WeightedDirectedEdge> edges = Stream.of(AB, BC, CD, DA, AC).collect(Collectors.toSet());

        WeightedDirectedGraph graph = new WeightedDirectedGraph(vertices, edges);
        CycleDetectionAlgorithm cycleDetectionAlgorithm = new DPTAlgorithm();
        Set<List<WeightedDirectedEdge>> cycles = cycleDetectionAlgorithm.findCycles(graph);
        System.out.println(cycles);
        assertEquals(2, cycles.size());

    }

    @Test
    public void getCycleTest() {
        Vertex A = new Vertex("A");
        Vertex B = new Vertex("B");
        Vertex C = new Vertex("C");
        Vertex D = new Vertex("D");

        WeightedDirectedEdge AB = new WeightedDirectedEdge("AB", A, B, 1);
        WeightedDirectedEdge BC = new WeightedDirectedEdge("BC", B, C, 1);
        WeightedDirectedEdge CD = new WeightedDirectedEdge("CD", C, D, 1);
        WeightedDirectedEdge DB = new WeightedDirectedEdge("DB", D, B, 1);
        WeightedDirectedEdge AD = new WeightedDirectedEdge("AD", A, D, 1);
        Set<WeightedDirectedEdge> edges = Stream.of(AB, BC, CD, DB, AD).collect(Collectors.toSet());

        Stack<Vertex> greyVertices = new Stack<>();
        greyVertices.push(A);
        greyVertices.push(B);
        greyVertices.push(C);
        greyVertices.push(D);

        List<WeightedDirectedEdge> cycle = new DPTAlgorithm().getCycle(B, edges, greyVertices);
        List<WeightedDirectedEdge> expected = Stream.of(BC, CD, DB).collect(Collectors.toList());
        assertEquals(expected, cycle);
  }
}
