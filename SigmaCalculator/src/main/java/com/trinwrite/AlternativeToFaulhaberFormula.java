package com.trinwrite;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class AlternativeToFaulhaberFormula {

    private static Map<Integer, MultivariatePolynomial> sumOfKthPowersOfFirstNPositiveIntegers = new HashMap<>();

    public static void main(String[] argv) {
        System.out.println(sumOfKthPowersOfFirstNPositiveIntegers(1));
    }

    // sum of the k-th powers of the first n positive integers
    public static MultivariatePolynomial sumOfKthPowersOfFirstNPositiveIntegers(int k) {
        if (sumOfKthPowersOfFirstNPositiveIntegers.get(k) != null) {
            return sumOfKthPowersOfFirstNPositiveIntegers.get(k);
        }
        if (k == 0) {
            MultivariatePolynomial formula = new MultivariatePolynomial(
                    new MultivariateMonomial(new RationalNumber(1), 'n', 1));
            sumOfKthPowersOfFirstNPositiveIntegers.put(k, formula);
            return sumOfKthPowersOfFirstNPositiveIntegers.get(k);
        } else if (k == 1) {
            MultivariatePolynomial formula = new MultivariatePolynomial(
                    Arrays.asList(new MultivariateMonomial(new RationalNumber(1,2), 'n', 1)))
                    .multiply(new MultivariatePolynomial(Arrays.asList(
                            new MultivariateMonomial(RationalNumber.ONE, 'n', 1),
                            MultivariateMonomial.ONE)));
            sumOfKthPowersOfFirstNPositiveIntegers.put(k, formula);
            return sumOfKthPowersOfFirstNPositiveIntegers.get(k);
        } else if (k == 2) {
            throw new UnsupportedOperationException();
        } else {
            throw new UnsupportedOperationException();
        }
    }
}
