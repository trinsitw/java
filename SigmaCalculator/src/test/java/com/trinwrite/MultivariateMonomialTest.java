package com.trinwrite;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;

public class MultivariateMonomialTest {

    @Test
    public void equalsTest() {
        System.out.println(new MultivariateMonomial(new RationalNumber(1,2)));

        MultivariateMonomial mm1 = new MultivariateMonomial(new RationalNumber(1,2), 'n', 2, 'i', 3);
        MultivariateMonomial mm2 = new MultivariateMonomial(new RationalNumber(1,2), 'n', 2, 'i', 3);
        System.out.println("mm1: " + mm1);
        System.out.println("mm2: " + mm2);
        assertEquals(mm1, mm2);

        MultivariateMonomial mm3 = new MultivariateMonomial(new RationalNumber(1,2), 'n', 2, 'i', 3);
        MultivariateMonomial mm4 = new MultivariateMonomial(new RationalNumber(1,2), 'i', 3, 'n', 2);
        System.out.println("mm3: " + mm3);
        System.out.println("mm4: " + mm4);
        assertEquals(mm3, mm4);

        MultivariateMonomial mm5 = new MultivariateMonomial(new RationalNumber(1,2), 'n', 2, 'i', 3);
        MultivariateMonomial mm6 = new MultivariateMonomial(new RationalNumber(3,4), 'i', 3, 'n', 2);
        System.out.println("mm5: " + mm5);
        System.out.println("mm6: " + mm6);
        assertNotEquals(mm5, mm6);

        MultivariateMonomial mm7 = new MultivariateMonomial(new RationalNumber(1,2), 'n', 2);
        MultivariateMonomial mm8 = new MultivariateMonomial(new RationalNumber(1,2), 'n', 2, 'i', 3);
        System.out.println("mm7: " + mm7);
        System.out.println("mm8: " + mm8);
        assertNotEquals(mm7, mm8);
    }

    @Test
    public void multiplyTest() {
        MultivariateMonomial mm1 = new MultivariateMonomial(
                new RationalNumber(2),
                'n', 3,
                'i', 4);
        MultivariateMonomial mm2 = new MultivariateMonomial(
                new RationalNumber(5),
                'n', 6,
                'i', 7);
        MultivariateMonomial mm3 = new MultivariateMonomial(
                new RationalNumber(10),
                'n', 9,
                'i', 11);
        System.out.println("mm1: " + mm1);
        System.out.println("mm2: " + mm2);
        System.out.println("mm3: " + mm3);
        System.out.println("mm1.multiply(mm2): " + mm1.multiply(mm2));
        assertEquals(mm1.multiply(mm2), mm3);

        MultivariateMonomial mm4 = new MultivariateMonomial(
                new RationalNumber(2),
                'n', 3);
        MultivariateMonomial mm5 = new MultivariateMonomial(
                new RationalNumber(-5),
                'i', 7);
        MultivariateMonomial mm6 = new MultivariateMonomial(
                new RationalNumber(-10),
                'n', 3,
                'i', 7);
        System.out.println("mm4: " + mm4);
        System.out.println("mm5: " + mm5);
        System.out.println("mm6: " + mm6);
        System.out.println("mm4.multiply(mm5): " + mm4.multiply(mm5));
        assertEquals(mm4.multiply(mm5), mm6);
    }
}
