package com.trinwrite;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AlternativeToFaulhaberFormula {

    private static Map<Integer, MultivariatePolynomial> sumOfKthPowersOfFirstNPositiveIntegers = new HashMap<>();

    public static void main(String[] argv) {
        System.out.println(sumOfKthPowersOfFirstNPositiveIntegers(0));
        System.out.println(sumOfKthPowersOfFirstNPositiveIntegers(1));
        System.out.println(sumOfKthPowersOfFirstNPositiveIntegers(2));
    }

    public static MultivariatePolynomial sumOfIndexedMonomial(MultivariateMonomial monomial) {
        if (monomial.indeterminateExponentList().stream().noneMatch(ie -> ie.indeterminate() == 'i')) {
            return sumOfKthPowersOfFirstNPositiveIntegers(0).multiply(monomial);
        }
        IndeterminateExponent ieOfI = monomial.indeterminateExponentList()
                .stream()
                .filter(ie -> ie.indeterminate() == 'i')
                .findFirst()
                .get();
        List<IndeterminateExponent> prunedIEList = monomial.indeterminateExponentList();
        prunedIEList.remove(ieOfI);
        MultivariateMonomial prunedMonomial = new MultivariateMonomial(monomial.coefficient(), prunedIEList);
        return sumOfKthPowersOfFirstNPositiveIntegers(ieOfI.exponent()).multiply(prunedMonomial);
    }

    public static MultivariatePolynomial sumOfIndexedPolynomial(MultivariatePolynomial polynomial) {
        return polynomial.monomialList().stream().map(monomial -> sumOfIndexedMonomial(monomial))
                .reduce(MultivariatePolynomial.ZERO, (polynomial1, polynomial2) -> polynomial1.add(polynomial2));
    }

    // sum of the k-th powers of the first n positive integers
    public static MultivariatePolynomial sumOfKthPowersOfFirstNPositiveIntegers(int k) {
        if (k < 0) {
            throw new IllegalArgumentException();
        }
        if (sumOfKthPowersOfFirstNPositiveIntegers.get(k) != null) {
            return sumOfKthPowersOfFirstNPositiveIntegers.get(k);
        }
        if (k == 0) {
            MultivariatePolynomial formula = new MultivariatePolynomial(
                    new MultivariateMonomial(new RationalNumber(1), 'n', 1));
            sumOfKthPowersOfFirstNPositiveIntegers.put(k, formula);
            return formula;
        } else if (k == 1) {
            MultivariatePolynomial formula = new MultivariatePolynomial(
                    Arrays.asList(new MultivariateMonomial(new RationalNumber(1,2), 'n', 1)))
                    .multiply(new MultivariatePolynomial(
                            new MultivariateMonomial(RationalNumber.ONE, 'n', 1),
                            MultivariateMonomial.ONE));
            sumOfKthPowersOfFirstNPositiveIntegers.put(k, formula);
            return formula;
        } else if (k == 2) {
            MultivariatePolynomial formula = new MultivariatePolynomial(
                    new MultivariateMonomial(new RationalNumber(2), 'i', 1),
                    new MultivariateMonomial(new RationalNumber(-1)))
                    .multiply(
                            sumOfIndexedMonomial(MultivariateMonomial.ONE).subtract(
                                    sumOfIndexedMonomial(MultivariateMonomial.ONE).compose('n',
                                            new MultivariatePolynomial(
                                                    new MultivariateMonomial(new RationalNumber(1), 'i', 1),
                                                    new MultivariateMonomial(new RationalNumber(-1))

                                            )
                                    )
                            )

                    )
                    ;
            sumOfKthPowersOfFirstNPositiveIntegers.put(k, formula);
            return formula;
        }
        throw new UnsupportedOperationException();

    }
}
