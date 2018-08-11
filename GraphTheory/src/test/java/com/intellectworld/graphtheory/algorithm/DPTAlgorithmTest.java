package com.intellectworld.graphtheory.algorithm;

import com.intellectworld.graphtheory.Vertex;
import com.intellectworld.graphtheory.WeightedDirectedEdge;

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
        Set<List<WeightedDirectedEdge>> cycles = cycleDetectionAlgorithm.findCycles(graph, A);
        System.out.println("cycles " + cycles);

        assertEquals(2, cycles.size());
        List<WeightedDirectedEdge> expected1 = Stream.of(BC, CD, DB).collect(Collectors.toList());
        List<WeightedDirectedEdge> expected2 = Stream.of(DB, BC, CD).collect(Collectors.toList());
        assert(cycles.contains(expected1));
        assert(cycles.contains(expected2));
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
        Set<List<WeightedDirectedEdge>> cycles = cycleDetectionAlgorithm.findCycles(graph, A);
        System.out.println(cycles);

        assertEquals(2, cycles.size());
        List<WeightedDirectedEdge> expected1 = Stream.of(AB, BC, CD, DA).collect(Collectors.toList());
        List<WeightedDirectedEdge> expected2 = Stream.of(AC, CD, DA).collect(Collectors.toList());
        assert(cycles.contains(expected1));
        assert(cycles.contains(expected2));
    }

    @Test
    public void findCyclesTest3() {
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
        CycleDetectionAlgorithm cycleDetectionAlgorithm = new DPTAlgorithm();
        Set<List<WeightedDirectedEdge>> cycles = cycleDetectionAlgorithm.findCycles(graph, A);
        System.out.println(cycles);

        assertEquals(3, cycles.size());
        List<WeightedDirectedEdge> expected1 = Stream.of(AB, BC, CD, DE, EA).collect(Collectors.toList());
        List<WeightedDirectedEdge> expected2 = Stream.of(AB, BC, CD, DA).collect(Collectors.toList());
        List<WeightedDirectedEdge> expected3 = Stream.of(AB, BC, CA).collect(Collectors.toList());
        assert(cycles.contains(expected1));
        assert(cycles.contains(expected2));
        assert(cycles.contains(expected3));
    }

    @Test
    public void findCyclesTest4() {
        Vertex A = new Vertex("A");
        Vertex B = new Vertex("B");
        Set<Vertex> vertices = Stream.of(A, B).collect(Collectors.toSet());

        WeightedDirectedEdge AB = new WeightedDirectedEdge("AB", A, B, 1);
        WeightedDirectedEdge BA = new WeightedDirectedEdge("BA", B, A, 1);
        Set<WeightedDirectedEdge> edges = Stream.of(AB, BA).collect(Collectors.toSet());

        WeightedDirectedGraph graph = new WeightedDirectedGraph(vertices, edges);
        CycleDetectionAlgorithm cycleDetectionAlgorithm = new DPTAlgorithm();
        Set<List<WeightedDirectedEdge>> cycles = cycleDetectionAlgorithm.findCycles(graph, A);
        System.out.println(cycles);

        assertEquals(1, cycles.size());
        List<WeightedDirectedEdge> expected1 = Stream.of(AB, BA).collect(Collectors.toList());
        assert(cycles.contains(expected1));
    }

    @Test
    public void findCyclesTest5() {
        Vertex A = new Vertex("A");
        Vertex B = new Vertex("B");
        Vertex C = new Vertex("C");
        Vertex D = new Vertex("D");
        Set<Vertex> vertices = Stream.of(A, B, C, D).collect(Collectors.toSet());

        WeightedDirectedEdge AB = new WeightedDirectedEdge("AB", A, B, 1);
        WeightedDirectedEdge BA = new WeightedDirectedEdge("BA", B, A, 1);
        WeightedDirectedEdge BC = new WeightedDirectedEdge("BC", B, C, 1);
        WeightedDirectedEdge CA = new WeightedDirectedEdge("CA", C, A, 1);
        WeightedDirectedEdge AD = new WeightedDirectedEdge("AD", A, D, 1);
        WeightedDirectedEdge DB = new WeightedDirectedEdge("DB", D, B, 1);
        Set<WeightedDirectedEdge> edges = Stream.of(AB, BA, BC, CA, AD, DB).collect(Collectors.toSet());

        WeightedDirectedGraph graph = new WeightedDirectedGraph(vertices, edges);
        CycleDetectionAlgorithm cycleDetectionAlgorithm = new DPTAlgorithm();
        Set<List<WeightedDirectedEdge>> cycles = cycleDetectionAlgorithm.findCycles(graph, A);
        System.out.println(cycles);

        assertEquals(4, cycles.size());
        List<WeightedDirectedEdge> expected1 = Stream.of(AB, BA).collect(Collectors.toList());
        List<WeightedDirectedEdge> expected2 = Stream.of(AD, DB, BA).collect(Collectors.toList());
        List<WeightedDirectedEdge> expected3 = Stream.of(AB, BC, CA).collect(Collectors.toList());
        List<WeightedDirectedEdge> expected4 = Stream.of(AD, DB, BC, CA).collect(Collectors.toList());
        assert(cycles.contains(expected1));
        assert(cycles.contains(expected2));
        assert(cycles.contains(expected3));
        assert(cycles.contains(expected4));
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
