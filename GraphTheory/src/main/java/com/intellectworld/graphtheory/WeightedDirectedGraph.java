package com.intellectworld.graphtheory;

import java.util.List;
import java.util.Objects;
import java.util.Set;


public class WeightedDirectedGraph {
    private Set<Vertex> vertices;
    private Set<WeightedDirectedEdge> edges;

    public WeightedDirectedGraph(Set<Vertex> vertices, Set<WeightedDirectedEdge> edges) {
        this.vertices = vertices;
        this.edges = edges;
    }

    public Set<Vertex> vertices() {
        return vertices;
    }

    public Set<WeightedDirectedEdge> edges() {
        return edges;
    }

    @Override
    public String toString() {
        return "WeightedDirectedGraph{" +
                "vertices=" + vertices +
                ", edges=" + edges +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeightedDirectedGraph that = (WeightedDirectedGraph) o;
        return Objects.equals(vertices, that.vertices) &&
                Objects.equals(edges, that.edges);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vertices, edges);
    }
}