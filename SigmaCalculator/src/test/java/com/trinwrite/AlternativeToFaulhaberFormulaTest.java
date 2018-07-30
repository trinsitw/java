package com.trinwrite;

import org.junit.Test;

import java.math.BigInteger;

import static com.trinwrite.AlternativeToFaulhaberFormula.*;
import static junit.framework.TestCase.assertEquals;

public class AlternativeToFaulhaberFormulaTest {
    @Test
    public void sumOfKthPowersOfFirstNPositiveIntegersTest() {
        for (int k = 1; k <= 20; k++) {
            MultivariatePolynomial formula = sumOfKthPowersOfFirstNPositiveIntegers(k);
            BigInteger formulaOutput = formula.evaluate(new BigInteger("100"));
            System.out.println("formulaOutput[" + k + "]: " + formulaOutput);
            BigInteger directCalculation = directCalculation(k, 100);
            System.out.println("directCalculation[" + k + "]: " + directCalculation);
            System.out.println();
            assertEquals(formulaOutput, directCalculation);
        }
    }

    private static BigInteger directCalculation(int k, int n) {
        BigInteger sum = BigInteger.ZERO;
        for (int i = 1; i <= n; i++) {
            sum = sum.add(new BigInteger(String.valueOf(i)).pow(k));
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
