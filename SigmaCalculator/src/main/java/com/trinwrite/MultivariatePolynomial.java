package com.trinwrite;

import com.sun.org.apache.xpath.internal.operations.Mult;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MultivariatePolynomial {

    public final static MultivariatePolynomial ZERO = new MultivariatePolynomial(
            Arrays.asList(MultivariateMonomial.ZERO));

    public final static MultivariatePolynomial ONE = new MultivariatePolynomial(
            Arrays.asList(MultivariateMonomial.ONE));

    private List<MultivariateMonomial> monomialList;

    public MultivariatePolynomial(List<MultivariateMonomial> monomialList) {
        this.monomialList = monomialList.stream()
                .collect(Collectors.groupingBy(
                        MultivariateMonomial::indeterminateExponentList,
                        Collectors.mapping(MultivariateMonomial::coefficient,
                                Collectors.reducing(RationalNumber.ZERO, (r1, r2) -> r1.add(r2)))))
                .entrySet()
                .stream()
                .map(entry -> new MultivariateMonomial(entry.getValue(), entry.getKey()))
                .filter(monomial -> !monomial.equals(MultivariateMonomial.ZERO))
                .sorted()
                .collect(Collectors.toList());
        if (this.monomialList.size() == 0) {
            this.monomialList.add(MultivariateMonomial.ZERO);
        }
    }

    public MultivariatePolynomial add(MultivariateMonomial augend) {
        return add(new MultivariatePolynomial(Arrays.asList(augend)));
    }

    public MultivariatePolynomial add(MultivariatePolynomial augend) {
        List<MultivariateMonomial> union = new ArrayList(monomialList);
        union.addAll(augend.monomialList);
        return new MultivariatePolynomial(union);
    }

    public MultivariatePolynomial multiply(MultivariateMonomial multiplicandMonomial) {
        return new MultivariatePolynomial(this.monomialList.stream()
                .map(monomial -> multiplicandMonomial.multiply(monomial))
                .collect(Collectors.toList()));
    }

    public MultivariatePolynomial multiply(MultivariatePolynomial multiplicandPolynomial) {
        return this.monomialList.stream()
                .map(monomial -> multiplicandPolynomial.multiply(monomial))
                .reduce(MultivariatePolynomial.ZERO, (polynomial1, polynomial2) -> polynomial1.add(polynomial2));
    }

    public MultivariatePolynomial pow(int exponent) {
        if (exponent < 1) throw new IllegalArgumentException();
        return IntStream.range(0, exponent)
                .mapToObj(i -> this)
                .reduce(MultivariatePolynomial.ONE, (polynomial1, polynomial2) -> polynomial1.multiply(polynomial2));
    }

    public RationalNumber evaluate(RationalNumber x) {
        if (monomialList.stream()
                .flatMap(monomial -> monomial.indeterminateExponentList().stream()
                        .map(ie -> ie.indeterminate())
                        .collect(Collectors.toList())
                        .stream())
                .distinct()
                .collect(Collectors.toList()).size() > 1) {
            throw new IllegalArgumentException("Only univariate polynomial can be evaluated.");
        }
        return this.monomialList.stream()
                .map(monomial -> {
                    if (monomial.indeterminateExponentList().size() == 1) {
                        return monomial.coefficient()
                                .multiply(x.pow(monomial.indeterminateExponentList().get(0).exponent()));
                    } else {
                        return monomial.coefficient();
                    }
                })
                .reduce(RationalNumber.ZERO, (r1, r2) -> r1.add(r2));
    }

    public MultivariatePolynomial compose(char targetIndeterminate, MultivariatePolynomial substitution) {
        if (monomialList.stream().noneMatch(monomial ->
                monomial.indeterminateExponentList().stream().anyMatch(ie -> ie.indeterminate() == targetIndeterminate))) {
            throw new IllegalArgumentException("targetIndeterminate does not exist in the polynomial.");
        }
        List<MultivariateMonomial> monomialsWithoutTargetIndeterminate = this.monomialList.stream()
                .filter(monomial -> monomial.indeterminateExponentList()
                        .stream()
                        .noneMatch(ie -> ie.indeterminate() == targetIndeterminate))
                .collect(Collectors.toList());
        List<MultivariateMonomial> monomialsWithTargetIndeterminate = this.monomialList.stream()
                .filter(monomial -> monomial.indeterminateExponentList()
                        .stream()
                        .anyMatch(ie -> ie.indeterminate() == targetIndeterminate))
                .collect(Collectors.toList());
        MultivariatePolynomial composedPolynomial =
                monomialsWithTargetIndeterminate.stream().map(
                        monomial -> {
                            IndeterminateExponent targetIE = monomial.indeterminateExponentList().stream()
                                    .filter(ie -> ie.indeterminate() == targetIndeterminate)
                                    .findFirst()
                                    .get();
                            List<IndeterminateExponent> ieListWithoutTargetIndeterminate =
                                    new ArrayList(monomial.indeterminateExponentList());
                            ieListWithoutTargetIndeterminate.remove(targetIE);
                            MultivariateMonomial prunedMonomial = new MultivariateMonomial(
                                    monomial.coefficient(),
                                    ieListWithoutTargetIndeterminate);
                            return substitution.pow(targetIE.exponent()).multiply(prunedMonomial);
                        }).reduce(MultivariatePolynomial.ZERO, (polynomial1, polynomial2) -> polynomial1.add(polynomial2));
        return composedPolynomial.add(new MultivariatePolynomial(monomialsWithoutTargetIndeterminate));
    }

    @Override
    public String toString() {
        return String.join(
                " + ",
                monomialList.stream()
                        .map(monomial -> monomial.toString())
                        .collect(Collectors.toList()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MultivariatePolynomial that = (MultivariatePolynomial) o;
        return Objects.equals(monomialList, that.monomialList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(monomialList);
    }
}