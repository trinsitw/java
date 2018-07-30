package com.trinwrite;

import com.sun.istack.internal.NotNull;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MultivariatePolynomial {

    public final static MultivariatePolynomial ZERO = new MultivariatePolynomial(MultivariateMonomial.ZERO);
    public final static MultivariatePolynomial ONE = new MultivariatePolynomial(MultivariateMonomial.ONE);

    private List<MultivariateMonomial> monomialList;

    public MultivariatePolynomial(@NotNull MultivariateMonomial... monomials) {
        this(Arrays.asList(monomials));
    }

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

    public List<MultivariateMonomial> monomialList() {
        return new ArrayList<>(monomialList);
    }

    public MultivariatePolynomial add(MultivariateMonomial augend) {
        return add(new MultivariatePolynomial(augend));
    }

    public MultivariatePolynomial add(MultivariatePolynomial augend) {
        List<MultivariateMonomial> union = new ArrayList(monomialList);
        union.addAll(augend.monomialList);
        return new MultivariatePolynomial(union);
    }

    public MultivariatePolynomial subtract(MultivariateMonomial subtrahend) {
        return this.add(subtrahend.multiply(new MultivariateMonomial(new RationalNumber(-1))));
    }

    public MultivariatePolynomial subtract(MultivariatePolynomial subtrahend) {
        return this.add(subtrahend.multiply(new MultivariateMonomial(new RationalNumber(-1))));
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

    public BigInteger evaluate(BigInteger x) {
        if (monomialList.stream()
                .flatMap(monomial -> monomial.indeterminateExponentList().stream()
                        .map(ie -> ie.indeterminate())
                        .collect(Collectors.toList())
                        .stream())
                .distinct()
                .collect(Collectors.toList()).size() > 1) {
            throw new IllegalArgumentException("Only univariate polynomial can be evaluated.");
        }
        List<BigInteger> denominatorList = this.monomialList.stream()
                .map(monomial -> new BigInteger(String.valueOf(monomial.coefficient().denominator())))
                .collect(Collectors.toList());
        BigInteger denominatorLcm = lcm(denominatorList.toArray(new BigInteger[0]));

        BigInteger numerator = this.monomialList.stream()
                .map(monomial -> denominatorLcm
                    .divide(new BigInteger(String.valueOf(monomial.coefficient().denominator())))
                    .multiply(new BigInteger(String.valueOf(monomial.coefficient().numerator())))
                    .multiply(
                            monomial.indeterminateExponentList().size() > 0?
                                    new BigInteger(String.valueOf(x.pow(monomial.indeterminateExponentList().get(0).exponent())))
                                    :BigInteger.ONE))
                .reduce(BigInteger.ZERO, (bi1, bi2) -> bi1.add(bi2));
        return numerator.divide(denominatorLcm);
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
        return new HashSet<>(monomialList).equals(new HashSet<>(that.monomialList));
    }

    @Override
    public int hashCode() {
        return Objects.hash(new HashSet<>(monomialList));
    }

    protected static BigInteger gcd(BigInteger a, BigInteger b) {
        return a.gcd(b);
    }

    protected static BigInteger lcm(BigInteger a, BigInteger b) {
        return a.multiply(b).divide(gcd(a, b));
    }

    protected static BigInteger lcm(BigInteger[] input) {
        BigInteger result = input[0];
        for(int i = 1; i < input.length; i++) result = lcm(result, input[i]);
        return result;
    }
}