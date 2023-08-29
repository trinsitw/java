package com.intellectworld.graphtheory.forex;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Example {
    public static void main(String[] args) {
        Set<ForexPair> forexPairs = Stream.of(
                new ForexPair("USD", "EUR", 0.741),
                new ForexPair("EUR", "USD", 1.349),

                new ForexPair("EUR", "GBP", 0.888),
                new ForexPair("GBP", "EUR", 1.126),

                new ForexPair("GBP", "CHF", 1.614),
                new ForexPair("CHF", "GBP", 0.619),

                new ForexPair("CHF", "CAD", 0.953),
                new ForexPair("CAD", "CHF", 1.049),

                new ForexPair("CAD", "USD", 0.995),
                new ForexPair("USD", "CAD", 1.005),

                new ForexPair("USD", "GBP", 0.657),
                new ForexPair("GBP", "USD", 1.521),

                new ForexPair("USD", "CHF", 1.061),
                new ForexPair("CHF", "USD", 0.942),

                new ForexPair("EUR", "CAD", 1.366),
                new ForexPair("CAD", "EUR", 0.732),

                new ForexPair("EUR", "CHF", 1.433),
                new ForexPair("CHF", "EUR", 0.698),

                new ForexPair("GBP", "CAD", 1.538),
                new ForexPair("CAD", "GBP", 0.650))
            .collect(Collectors.toSet());
        ForexNetwork forexNetwork = new ForexNetwork(forexPairs);
        Set<ForexCycle> profitableCycles = forexNetwork.profitableCycles("USD", 1.007f);
        profitableCycles.stream().forEach(cycle -> System.out.println(cycle + " => " + cycle.rate()));

    }
}
