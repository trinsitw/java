package com.intellectworld.graphtheory.forex;

import com.intellectworld.graphtheory.Vertex;
import com.intellectworld.graphtheory.WeightedDirectedEdge;
import com.intellectworld.graphtheory.WeightedDirectedGraph;
import com.intellectworld.graphtheory.algorithm.CycleDetectionAlgorithm;
import com.intellectworld.graphtheory.algorithm.DPTAlgorithm;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ForexNetwork {

    private Set<ForexPair> forexPairs;

    public ForexNetwork(Set<ForexPair> forexPairs) {
        this.forexPairs = forexPairs;
    }

    private ForexPair seekForexPair(String currency1, String currency2) {
        return forexPairs.stream()
                .filter(forexPair -> forexPair.currency1().equals(currency1)
                        && forexPair.currency2().equals(currency2))
                .findFirst().get();
    }

    private WeightedDirectedGraph graph() {
        Set<Vertex> vertices = new HashSet<>();
        forexPairs.stream()
                .map(forexPair -> Stream.of(
                        new Vertex(forexPair.currency1()),
                        new Vertex(forexPair.currency2()))
                    .collect(Collectors.toSet()))
                .forEach(set -> vertices.addAll(set));
        Set<WeightedDirectedEdge> edges = forexPairs.stream()
                .map(forexPair ->
                    new WeightedDirectedEdge(
                            forexPair.currency1() + "->" + forexPair.currency2(),
                            new Vertex(forexPair.currency1()),
                            new Vertex(forexPair.currency2()),
                            new BigDecimal(String.valueOf(-Math.log(forexPair.rate().doubleValue())))))
                .collect(Collectors.toSet());
        return new WeightedDirectedGraph(vertices, edges);
    }

    public Set<ForexCycle> profitableCycles(String currency, float minimumCycleRate) {
        WeightedDirectedGraph graph = graph();
        CycleDetectionAlgorithm cycleDetectionAlgorithm = new DPTAlgorithm();
        Set<List<WeightedDirectedEdge>> cycles = cycleDetectionAlgorithm.findCycles(
                graph, new Vertex(currency));
        Set<List<WeightedDirectedEdge>> negativeCycles = cycles.stream().filter(
                cycle -> cycle.stream()
                        .map(edge -> edge.weight())
                        .reduce(BigDecimal.ZERO, (r1, r2) -> r1.add(r2))
                        .compareTo(BigDecimal.ZERO) < 0).collect(Collectors.toSet());
        Set<ForexCycle> profitableCycles = new TreeSet<>(negativeCycles.stream()
                .map(cycle -> {
                    List<ForexPair> forexPairList = cycle.stream()
                            .map(edge -> seekForexPair(
                                    edge.vertex1().name(),
                                    edge.vertex2().name()))
                            .collect(Collectors.toList());
                    return new ForexCycle(forexPairList);
                })
                .filter(cycle -> cycle.rate().compareTo(new BigDecimal(String.valueOf(minimumCycleRate))) >= 0)
                .collect(Collectors.toSet()));

        return profitableCycles;
    }
}
