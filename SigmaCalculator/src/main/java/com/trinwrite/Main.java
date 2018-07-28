package com.trinwrite;

import java.util.Arrays;
import static com.trinwrite.AlternativeToFaulhaberFormula.*;

public class Main {

    public static void main(String[] args) {
        MultivariatePolynomial polynomial = new MultivariatePolynomial(
                new MultivariateMonomial(new RationalNumber(2), 'i', 1),
                new MultivariateMonomial(new RationalNumber(-1)))
            .multiply(
                    sumOfKthPowersOfFirstNPositiveIntegers(0).subtract(
                                sumOfKthPowersOfFirstNPositiveIntegers(0).compose('n',
                                        new MultivariatePolynomial(
                                                new MultivariateMonomial(new RationalNumber(1), 'i', 1),
                                        new MultivariateMonomial(new RationalNumber(-1))))));

        System.out.println(polynomial);
    }
}
