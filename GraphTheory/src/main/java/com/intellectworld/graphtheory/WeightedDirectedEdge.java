package com.intellectworld.graphtheory;

import java.math.BigDecimal;
import java.util.Objects;

public class WeightedDirectedEdge implements Comparable<WeightedDirectedEdge> {
    private String name;
    private Vertex vertex1;
    private Vertex vertex2;
    private BigDecimal weight;

    public WeightedDirectedEdge(String name, Vertex vertex1, Vertex vertex2, BigDecimal weight) {
        this.name = name;
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.weight = weight;
    }

    public WeightedDirectedEdge(String name, Vertex vertex1, Vertex vertex2, Integer weight) {
        this(name,vertex1,vertex2,new BigDecimal(String.valueOf(weight)));
    }

    public String name() {
        return name;
    }

    public Vertex vertex1() {
        return vertex1;
    }

    public Vertex vertex2() {
        return vertex2;
    }

    public BigDecimal weight() {
        return weight;
    }

    @Override
    public String toString() {
        return name + "(" +weight + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeightedDirectedEdge that = (WeightedDirectedEdge) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(vertex1, that.vertex1) &&
                Objects.equals(vertex2, that.vertex2) &&
                Objects.equals(weight, that.weight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, vertex1, vertex2, weight);
    }

    @Override
    public int compareTo(WeightedDirectedEdge that) {
        return this.name.compareTo(that.name);
    }
}
