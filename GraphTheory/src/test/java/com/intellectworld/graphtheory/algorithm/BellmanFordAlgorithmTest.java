package com.intellectworld.graphtheory.algorithm;

import com.intellectworld.graphtheory.Vertex;
import com.intellectworld.graphtheory.WeightedDirectedEdge;
import com.intellectworld.graphtheory.WeightedDirectedGraph;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;

public class BellmanFordAlgorithmTest {

    @Test
    public void findShortestPathTest1() {
        Vertex A = new Vertex("A");
        Vertex B = new Vertex("B");
        Vertex C = new Vertex("C");
        Vertex D = new Vertex("D");
        Vertex E = new Vertex("E");

        Set<Vertex> vertices = Stream.of(A, B, C, D, E).collect(Collectors.toSet());

        WeightedDirectedEdge AB = new WeightedDirectedEdge("AB", A, B, 6);
        WeightedDirectedEdge BA = new WeightedDirectedEdge("BA", B, A, 6);

        WeightedDirectedEdge AD = new WeightedDirectedEdge("AD", A, D, 1);
        WeightedDirectedEdge DA = new WeightedDirectedEdge("DA", D, A, 1);

        WeightedDirectedEdge BC = new WeightedDirectedEdge("BC", B, C, 5);
        WeightedDirectedEdge CB = new WeightedDirectedEdge("CB", C, B, 5);

        WeightedDirectedEdge BD = new WeightedDirectedEdge("BD", B, D, 2);
        WeightedDirectedEdge DB = new WeightedDirectedEdge("DB", D, B, 2);

        WeightedDirectedEdge BE = new WeightedDirectedEdge("BE", B, E, 2);
        WeightedDirectedEdge EB = new WeightedDirectedEdge("EB", E, B, 2);

        WeightedDirectedEdge CE = new WeightedDirectedEdge("CE", C, E, 5);
        WeightedDirectedEdge EC = new WeightedDirectedEdge("EC", E, C, 5);

        WeightedDirectedEdge DE = new WeightedDirectedEdge("DE", D, E, 1);
        WeightedDirectedEdge ED = new WeightedDirectedEdge("ED", E, D, 1);

        Set<WeightedDirectedEdge> edges = Stream
                .of(AB, BA, AD, DA, BC, CB, BD, DB, BE, EB, CE, EC, DE, ED)
                .collect(Collectors.toSet());

        WeightedDirectedGraph inputGraph = new WeightedDirectedGraph(vertices, edges);

        System.out.println("inputGraph: " + inputGraph);

        ShortestPathAlgorithm shortestPathAlgorithm = new BellmanFordAlgorithm();
        WeightedDirectedGraph shortestPath = shortestPathAlgorithm.findShortestPath(inputGraph, A);

        WeightedDirectedGraph expected = new WeightedDirectedGraph(
                vertices,
                Stream.of(AD, DB, DE, EC).collect(Collectors.toSet()));

        System.out.println("shortestPath: " + shortestPath);

        assertEquals(expected, shortestPath);
    }


    @Test
    public void findShortestPathTest2() {
        Vertex E = new Vertex("E");
        Vertex F = new Vertex("F");
        Vertex H = new Vertex("H");
        Vertex K = new Vertex("K");
        Vertex M = new Vertex("M");
        Vertex S = new Vertex("S");
        Set<Vertex> vertices = Stream.of(E, F, H, K, M, S).collect(Collectors.toSet());

        WeightedDirectedEdge EF = new WeightedDirectedEdge("EF", E, F, 1);
        WeightedDirectedEdge EH = new WeightedDirectedEdge("EH", E, H, 3);
        WeightedDirectedEdge EM = new WeightedDirectedEdge("EM", E, M, 1);

        WeightedDirectedEdge FH = new WeightedDirectedEdge("FH", F, H, 1);

        WeightedDirectedEdge HF = new WeightedDirectedEdge("HF", H, F, 3);

        WeightedDirectedEdge KE = new WeightedDirectedEdge("KE", K, E, 1);
        WeightedDirectedEdge KM = new WeightedDirectedEdge("KM", K, M, 4);

        WeightedDirectedEdge ME = new WeightedDirectedEdge("ME", M, E, 2);
        WeightedDirectedEdge MH = new WeightedDirectedEdge("MH", M, H, 2);

        WeightedDirectedEdge SF = new WeightedDirectedEdge("SF", S, F, 2);
        WeightedDirectedEdge SH = new WeightedDirectedEdge("SH", S, H, 1);


        Set<WeightedDirectedEdge> edges = Stream
                .of(EF, EH, EM, FH, HF, KE, KM, ME, MH, SF, SH)
                .collect(Collectors.toSet());

        WeightedDirectedGraph inputGraph = new WeightedDirectedGraph(vertices, edges);

        System.out.println("inputGraph: " + inputGraph);

        ShortestPathAlgorithm shortestPathAlgorithm = new BellmanFordAlgorithm();
        WeightedDirectedGraph shortestPath = shortestPathAlgorithm.findShortestPath(inputGraph, K);

        WeightedDirectedGraph expected = new WeightedDirectedGraph(
                vertices,
                Stream.of(KE, EM, EF, FH).collect(Collectors.toSet()));

        System.out.println("shortestPath: " + shortestPath);

        assertEquals(expected, shortestPath);

    }

    @Test(expected = IllegalArgumentException.class)
    public void findShortestPathTest3() {
        Vertex A = new Vertex("A");
        Vertex B = new Vertex("B");
        Vertex C = new Vertex("C");
        Vertex D = new Vertex("D");
        Vertex E = new Vertex("E");
        Vertex F = new Vertex("F");
        Vertex G = new Vertex("G");
        Vertex H = new Vertex("H");
        Vertex I = new Vertex("I");
        Vertex J = new Vertex("J");
        Set<Vertex> vertices = Stream.of(A, B, C, D, E, F, G, H, I, J).collect(Collectors.toSet());

        WeightedDirectedEdge AB = new WeightedDirectedEdge("AB", A, B, 5);
        WeightedDirectedEdge BC = new WeightedDirectedEdge("BC", B, C, 60);
        WeightedDirectedEdge BD = new WeightedDirectedEdge("BD", B, D, 20);
        WeightedDirectedEdge BH = new WeightedDirectedEdge("BH", B, H, 30);
        WeightedDirectedEdge CI = new WeightedDirectedEdge("CI", C, I, -50);
        WeightedDirectedEdge DE = new WeightedDirectedEdge("DE", D, E, 10);
        WeightedDirectedEdge DF = new WeightedDirectedEdge("DF", D, F, 75);
        WeightedDirectedEdge ED = new WeightedDirectedEdge("ED", E, D, -15);
        WeightedDirectedEdge FG = new WeightedDirectedEdge("FG", F, G, 100);
        WeightedDirectedEdge HC = new WeightedDirectedEdge("HC", H, C, 5);
        WeightedDirectedEdge HF = new WeightedDirectedEdge("HF", H, F, 25);
        WeightedDirectedEdge HJ = new WeightedDirectedEdge("HJ", H, J, 50);
        WeightedDirectedEdge IJ = new WeightedDirectedEdge("IJ", I, J, -10);

        Set<WeightedDirectedEdge> edges = Stream.of(AB, BC, BD, BH, CI, DE, DF, ED, FG, HC, HF, HJ, IJ)
                .collect(Collectors.toSet());
        WeightedDirectedGraph inputGraph = new WeightedDirectedGraph(vertices, edges);

        System.out.println("inputGraph: " + inputGraph);

        ShortestPathAlgorithm shortestPathAlgorithm = new BellmanFordAlgorithm();

        WeightedDirectedGraph shortestPath = shortestPathAlgorithm.findShortestPath(inputGraph, A);
    }


    @Test
    public void findNegativeCycleTest1() {
        Vertex A = new Vertex("A");
        Vertex B = new Vertex("B");
        Vertex C = new Vertex("C");
        Vertex D = new Vertex("D");
        Vertex E = new Vertex("E");
        Vertex F = new Vertex("F");
        Vertex G = new Vertex("G");
        Vertex H = new Vertex("H");
        Vertex I = new Vertex("I");
        Vertex J = new Vertex("J");
        Set<Vertex> vertices = Stream.of(A, B, C, D, E, F, G, H, I, J).collect(Collectors.toSet());

        WeightedDirectedEdge AB = new WeightedDirectedEdge("AB", A, B, 5);
        WeightedDirectedEdge BC = new WeightedDirectedEdge("BC", B, C, 60);
        WeightedDirectedEdge BD = new WeightedDirectedEdge("BD", B, D, 20);
        WeightedDirectedEdge BH = new WeightedDirectedEdge("BH", B, H, 30);
        WeightedDirectedEdge CI = new WeightedDirectedEdge("CI", C, I, -50);
        WeightedDirectedEdge DE = new WeightedDirectedEdge("DE", D, E, 10);
        WeightedDirectedEdge DF = new WeightedDirectedEdge("DF", D, F, 75);
        WeightedDirectedEdge ED = new WeightedDirectedEdge("ED", E, D, -15);
        WeightedDirectedEdge FG = new WeightedDirectedEdge("FG", F, G, 100);
        WeightedDirectedEdge HC = new WeightedDirectedEdge("HC", H, C, 5);
        WeightedDirectedEdge HF = new WeightedDirectedEdge("HF", H, F, 25);
        WeightedDirectedEdge HJ = new WeightedDirectedEdge("HJ", H, J, 50);
        WeightedDirectedEdge IJ = new WeightedDirectedEdge("IJ", I, J, -10);

        Set<WeightedDirectedEdge> edges = Stream.of(AB, BC, BD, BH, CI, DE, DF, ED, FG, HC, HF, HJ, IJ)
                .collect(Collectors.toSet());
        WeightedDirectedGraph inputGraph = new WeightedDirectedGraph(vertices, edges);

        System.out.println("inputGraph: " + inputGraph);

        ShortestPathAlgorithm shortestPathAlgorithm = new BellmanFordAlgorithm();

        WeightedDirectedGraph negativeCycle = shortestPathAlgorithm.findNegativeCycle(inputGraph);

        WeightedDirectedGraph expected = new WeightedDirectedGraph(
                inputGraph.vertices(),
                Stream.of(DE, ED).collect(Collectors.toSet()));
        assertEquals(expected, negativeCycle);
    }

    @Test
    public void findNegativeCycleTest2() {
        // GraphA
        WeightedDirectedGraph inputGraph = createGraphA();
        ShortestPathAlgorithm shortestPathAlgorithm = new BellmanFordAlgorithm();
        WeightedDirectedGraph negativeCycle = shortestPathAlgorithm.findNegativeCycle(inputGraph);
        WeightedDirectedGraph expected = new WeightedDirectedGraph(
                inputGraph.vertices(),
                inputGraph.edges().stream()
                        .filter(edge ->
                                edge.name().equals("BF")
                                        || edge.name().equals("FE")
                                        || edge.name().equals("EB"))
                        .collect(Collectors.toSet()));
        System.out.println("expected: " + expected);
        System.out.println("negativeCycle: " + negativeCycle);
        assertEquals(expected ,negativeCycle);
     }

    @Test
    public void findNegativeCycleTest3() {
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
        WeightedDirectedEdge e45 = new WeightedDirectedEdge("e45", v4, v5, 1);
        WeightedDirectedEdge e56 = new WeightedDirectedEdge("e56", v5, v6, 1);
        WeightedDirectedEdge e64 = new WeightedDirectedEdge("e64", v6, v4, 1);
        Set<WeightedDirectedEdge> edges = Stream.of(e12, e13, e23, e41, e45, e56, e64).collect(Collectors.toSet());

        WeightedDirectedGraph inputGraph = new WeightedDirectedGraph(vertices, edges);
        ShortestPathAlgorithm shortestPathAlgorithm = new BellmanFordAlgorithm();
        WeightedDirectedGraph negativeCycle = shortestPathAlgorithm.findNegativeCycle(inputGraph);
    }


    @Test(expected = IllegalArgumentException.class)
    public void findShortestPathTest4() {
        // GraphA
        WeightedDirectedGraph inputGraph = createGraphA();
        ShortestPathAlgorithm shortestPathAlgorithm = new BellmanFordAlgorithm();
        WeightedDirectedGraph shortestPath = shortestPathAlgorithm.findShortestPath(inputGraph, new Vertex("A"));
        System.out.println(String.join(", ", shortestPath.edges().stream().map(edge -> edge.name()).collect(toList())));
    }

    private WeightedDirectedGraph createGraphA() {
        // GraphA - negative cycle length 3
        Vertex A = new Vertex("A");
        Vertex B = new Vertex("B");
        Vertex C = new Vertex("C");
        Vertex D = new Vertex("D");
        Vertex E = new Vertex("E");
        Vertex F = new Vertex("F");
        Vertex G = new Vertex("G");
        Vertex H = new Vertex("H");
        Vertex I = new Vertex("I");
        Set<Vertex> vertices = Stream.of(A, B, C, D, E, F, G, H, I).collect(Collectors.toSet());

        WeightedDirectedEdge AB = new WeightedDirectedEdge("AB", A, B, 1);
        WeightedDirectedEdge AD = new WeightedDirectedEdge("AD", A, D, 4);
        WeightedDirectedEdge BC = new WeightedDirectedEdge("BC", B, C, 3);
        WeightedDirectedEdge BF = new WeightedDirectedEdge("BF", B, F, 2);
       // WeightedDirectedEdge CF = new WeightedDirectedEdge("CF", C, F, 6);
        WeightedDirectedEdge DE = new WeightedDirectedEdge("DE", D, E, 5);
        WeightedDirectedEdge DG = new WeightedDirectedEdge("DG", D, G, 7);
        WeightedDirectedEdge EB = new WeightedDirectedEdge("EB", E, B, (-3));
        WeightedDirectedEdge EG = new WeightedDirectedEdge("EG", E, G, (-10));
        WeightedDirectedEdge EH = new WeightedDirectedEdge("EH", E, H, 9);
        WeightedDirectedEdge FE = new WeightedDirectedEdge("FE", F, E, (-1));
        WeightedDirectedEdge FI = new WeightedDirectedEdge("FI", F, I, 11);
        WeightedDirectedEdge HG = new WeightedDirectedEdge("HG", H, G, 8);
        WeightedDirectedEdge HI = new WeightedDirectedEdge("HI", H, I, 10);
        Set<WeightedDirectedEdge> edges = Stream.of(AB, AD, BC, BF, DE, DG, EB, EG, EH, FE, FI, HG, HI)
                .collect(Collectors.toSet());

        return new WeightedDirectedGraph(vertices, edges);
    }
}
