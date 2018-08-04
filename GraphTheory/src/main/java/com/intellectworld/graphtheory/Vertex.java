package com.intellectworld.graphtheory;

import java.util.Objects;

public class Vertex implements Comparable<Vertex> {
    private String name;

    public Vertex(String name) {
        this.name = name;
    }

    public String name() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex vertex = (Vertex) o;
        return Objects.equals(name, vertex.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name);
    }

    @Override
    public int compareTo(Vertex that) {
        return this.name.compareTo(that.name);
    }
}
