package com.trinwrite;

import org.junit.Test;

import static com.trinwrite.AlternativeToFaulhaberFormula.*;
import static junit.framework.TestCase.assertEquals;

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

    @Test
    public void sumOfIndexedMonomialTest() {
        MultivariateMonomial mm1 = new MultivariateMonomial(new RationalNumber(1));
        System.out.println("mm1: " + mm1);
        MultivariatePolynomial mp2 = new MultivariatePolynomial(
                new MultivariateMonomial(new RationalNumber(1), 'n', 1));
        System.out.println("mp2: " + mp2);
        System.out.println("sumOfIndexedMonomial(mm1): " + sumOfIndexedMonomial(mm1));
        assertEquals(sumOfIndexedMonomial(mm1), mp2);

        MultivariateMonomial mm3 = new MultivariateMonomial(new RationalNumber(1), 'n', 1);
        System.out.println("mm3: " + mm3);
        MultivariatePolynomial mp4 = new MultivariatePolynomial(
                new MultivariateMonomial(new RationalNumber(1), 'n', 2));
        System.out.println("mp4: " + mp4);
        System.out.println("sumOfIndexedMonomial(mm3): " + sumOfIndexedMonomial(mm3));
        assertEquals(sumOfIndexedMonomial(mm3), mp4);

        MultivariateMonomial mm5 = new MultivariateMonomial(new RationalNumber(1), 'i', 1);
        System.out.println("mm5: " + mm5);
        MultivariatePolynomial mp6 = new MultivariatePolynomial(
                new MultivariateMonomial(new RationalNumber(1,2), 'n', 2),
                new MultivariateMonomial(new RationalNumber(1,2), 'n', 1));
        System.out.println("mp6: " + mp6);
        System.out.println("sumOfIndexedMonomial(mm5): " + sumOfIndexedMonomial(mm5));
        assertEquals(sumOfIndexedMonomial(mm5), mp6);
    }

    @Test
    public void sumOfIndexedPolynomialTest() {
        MultivariatePolynomial mp1 = new MultivariatePolynomial(
                new MultivariateMonomial(new RationalNumber(1), 'n', 1),
                new MultivariateMonomial(new RationalNumber(1), 'i', 1));
        System.out.println("mp1: " + mp1);
        MultivariatePolynomial mp2 = new MultivariatePolynomial(
                new MultivariateMonomial(new RationalNumber(3, 2), 'n', 2),
                new MultivariateMonomial(new RationalNumber(1, 2), 'n', 1));
        System.out.println("mp2: " + mp2);
        System.out.println("sumOfIndexedPolynomial(mp1): " + sumOfIndexedPolynomial(mp1));
        assertEquals(sumOfIndexedPolynomial(mp1), mp2);
    }
}
