package com.intellectworld.graphtheory.forex;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class ForexCycle implements Comparable<ForexCycle> {
    private List<ForexPair> cycle;

    public ForexCycle(List<ForexPair> cycle) {
        this.cycle = cycle;
    }

    public BigDecimal rate() {
        return cycle.stream().map(pair -> pair.rate()).reduce(BigDecimal.ONE, (r1, r2) -> r1.multiply(r2));
    }

    @Override
    public int compareTo(ForexCycle that) {
        return that.rate().compareTo(this.rate());
    }

    @Override
    public String toString() {
        return String.join("->", cycle.stream().map(pair -> pair.toString()).collect(Collectors.toList()));
    }
}
