package com.trinwrite;

import org.junit.Test;

import static com.trinwrite.AlternativeToFaulhaberFormula.*;

public class AlternativeToFaulhaberFormulaTest {
    @Test
    public void sumOfKthPowersOfFirstNPositiveIntegersTest() {

        MultivariatePolynomial formula = sumOfKthPowersOfFirstNPositiveIntegers(1);
        System.out.println(formula.evaluate(new RationalNumber(100)));
        System.out.println(directCalculation(1, 100));
    }

    private static RationalNumber directCalculation(int k, int n) {
        RationalNumber sum = RationalNumber.ZERO;
        for (int i = 0; i <= n; i++) {
            sum = sum.add(new RationalNumber(i).pow(k));
        }
        return sum;
    }
}
