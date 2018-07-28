package com.trinwrite;

import com.sun.istack.internal.NotNull;

import java.util.*;
import java.util.stream.Collectors;

public class MultivariateMonomial { //implements Comparable<MultivariateMonomial> {

    public final static MultivariateMonomial ZERO = new MultivariateMonomial(RationalNumber.ZERO);
    public final static MultivariateMonomial ONE = new MultivariateMonomial(RationalNumber.ONE);

    private RationalNumber coefficient;
    private List<IndeterminateExponent> indeterminateExponentList;

    public MultivariateMonomial(
            @NotNull RationalNumber coefficient,
            @NotNull List<IndeterminateExponent> indeterminateExponentList)
    {
        if (coefficient.equals(RationalNumber.ZERO)) {
            this.coefficient = coefficient;
            this.indeterminateExponentList = Collections.emptyList();
            return;
        }
        this.coefficient = coefficient;
        this.indeterminateExponentList = indeterminateExponentList.stream()
                //.sorted()
                .collect(Collectors.toList());
    }

    public MultivariateMonomial(@NotNull RationalNumber coefficient) {
        this(coefficient, Collections.emptyList());
    }

    public MultivariateMonomial(@NotNull RationalNumber coefficient,
                                char firstIndeterminate,
                                int firstExponent)
    {
        this(coefficient, Arrays.asList(new IndeterminateExponent(firstIndeterminate, firstExponent)));
    }

    public MultivariateMonomial(@NotNull RationalNumber coefficient,
                                char firstIndeterminate,
                                int firstExponent,
                                char secondIndeterminate,
                                int secondExponent)
    {
        this(coefficient, Arrays.asList(
                new IndeterminateExponent(firstIndeterminate, firstExponent),
                new IndeterminateExponent(secondIndeterminate, secondExponent)));
    }

    public RationalNumber coefficient() {
        return coefficient;
    }

    public List<IndeterminateExponent> indeterminateExponentList() {
        return new ArrayList<>(indeterminateExponentList);
    }

    public MultivariateMonomial multiply(MultivariateMonomial multiplicand) {
        List<IndeterminateExponent> union = new ArrayList<>(this.indeterminateExponentList);
        union.addAll(multiplicand.indeterminateExponentList);
        List<IndeterminateExponent> newList = union.stream()
                .collect(Collectors.groupingBy(IndeterminateExponent::indeterminate,
                        Collectors.summingInt(IndeterminateExponent::exponent)))
                .entrySet()
                .stream()
                .map(e -> new IndeterminateExponent(e.getKey(), e.getValue()))
                .collect(Collectors.toList());
        return new MultivariateMonomial(this.coefficient.multiply(multiplicand.coefficient), newList);
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("(" + coefficient.toString() + ")");
        indeterminateExponentList.stream().forEach(ie -> builder.append(ie.toString()));
        return builder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MultivariateMonomial that = (MultivariateMonomial) o;
        return Objects.equals(coefficient, that.coefficient) &&
                new HashSet<>(indeterminateExponentList).containsAll(new HashSet<>(that.indeterminateExponentList))
                && new HashSet<>(that.indeterminateExponentList).containsAll(new HashSet<>(indeterminateExponentList));
    }

    @Override
    public int hashCode() {
        return Objects.hash(coefficient, new HashSet<>(indeterminateExponentList));
    }

    /*@Override
    public int compareTo(MultivariateMonomial that) {
        return Integer.compare(
                Integer.valueOf(that.indeterminateExponentList.stream()
                        .map(monomial -> monomial.exponent())
                        .reduce(0, (a,b)->a+b)),
                Integer.valueOf(this.indeterminateExponentList.stream()
                        .map(monomial -> monomial.exponent())
                        .reduce(0, (a,b)->a+b)));
    }*/
}
