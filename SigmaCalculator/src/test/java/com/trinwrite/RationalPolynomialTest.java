package com.trinwrite;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;

public class RationalPolynomialTest {

    @Test
    public void equalsTest() {
        assertFalse(RationalPolynomial.ONE.equals(null));
        assertEquals(
                new RationalPolynomial(
                    RationalNumber.ZERO,
                    RationalNumber.ZERO,
                    RationalNumber.ZERO),
                new RationalPolynomial(RationalNumber.ZERO));
        assertEquals(
                new RationalPolynomial(
                        new RationalNumber(1,2),
                        new RationalNumber(3,4),
                        new RationalNumber(0,1)),
                new RationalPolynomial(
                        new RationalNumber(2,4),
                        new RationalNumber(-6,-8)));
        assertEquals(
                new RationalPolynomial(
                        new RationalNumber(1,2),
                        new RationalNumber(3,4),
                        new RationalNumber(0,1)),
                new RationalPolynomial(
                        new RationalNumber(2,4),
                        new RationalNumber(-6,-8),
                        RationalNumber.ZERO,
                        RationalNumber.ZERO,
                        RationalNumber.ZERO));
        assertEquals(
                new RationalPolynomial(
                        RationalNumber.ZERO,
                        RationalNumber.ZERO,
                        new RationalNumber(3,4),
                        new RationalNumber(0,1)),
                new RationalPolynomial(
                        new RationalNumber(0,4),
                        new RationalNumber(0,-4),
                        new RationalNumber(-6,-8),
                        RationalNumber.ZERO,
                        RationalNumber.ZERO,
                        RationalNumber.ZERO));
        RationalPolynomial p1 = new RationalPolynomial(
                new RationalNumber(3,1),
                new RationalNumber(-3,1),
                new RationalNumber(2,1),
                new RationalNumber(5,1));
        RationalPolynomial p2 = new RationalPolynomial(
                new RationalNumber(3,1),
                new RationalNumber(-3,1),
                new RationalNumber(2,1),
                new RationalNumber(5,1),
                RationalNumber.ZERO,
                RationalNumber.ZERO);
        assertEquals(p1.hashCode(), p2.hashCode());

        assertNotEquals(p1.hashCode(), RationalPolynomial.ONE.hashCode());
    }

    @Test
    public void degreeTest() {
        RationalPolynomial p1 = new RationalPolynomial(
                new RationalNumber(1,1),
                new RationalNumber(2, 1),
                new RationalNumber(3,1),
                RationalNumber.ZERO);
        assertEquals(p1.degree(), 2);
        assertEquals(RationalPolynomial.ZERO.degree(), 0);
        assertEquals(RationalPolynomial.ONE.degree(), 0);
    }

    @Test
    public void leadingCoefficientTest() {
        RationalPolynomial p1 = new RationalPolynomial(
                new RationalNumber(1,1),
                new RationalNumber(2, 1),
                new RationalNumber(3,2),
                RationalNumber.ZERO);
        assertEquals(p1.leadingCoefficient(), new RationalNumber(3,2));
    }

    @Test
    public void addTest() {
        assertEquals(
                new RationalPolynomial(
                    new RationalNumber(0,1),
                    new RationalNumber(1,1),
                    new RationalNumber(2, 1)).add(
                            new RationalPolynomial(
                            new RationalNumber(3,1),
                            new RationalNumber(-4,1),
                            new RationalNumber(0,1),
                            new RationalNumber(5,1),
                            RationalNumber.ZERO,
                            RationalNumber.ZERO)),
                new RationalPolynomial(
                    new RationalNumber(3,1),
                    new RationalNumber(-3,1),
                    new RationalNumber(2,1),
                    new RationalNumber(5,1)));
    }

    @Test
    public void subtractTest() {
        RationalPolynomial p1 = new RationalPolynomial(
                new RationalNumber(1,1),
                new RationalNumber(2,1),
                new RationalNumber(3,1),
                new RationalNumber(4,1),
                new RationalNumber(5,1));
        RationalPolynomial p2 = new RationalPolynomial(
                new RationalNumber(5,1),
                new RationalNumber(4,1),
                new RationalNumber(3,1),
                new RationalNumber(2,1),
                new RationalNumber(1,1),
                RationalNumber.ZERO,
                new RationalNumber(1,1));
        RationalPolynomial p3 = new RationalPolynomial(
                new RationalNumber(-4,1),
                new RationalNumber(-2,1),
                new RationalNumber(0,1),
                new RationalNumber(2,1),
                new RationalNumber(4,1),
                RationalNumber.ZERO,
                new RationalNumber(-1,1));
        assertEquals(p1.subtract(p2), p3);
    }

    @Test
    public void multiplyRationalNumberTest() {
        assertEquals(
                new RationalPolynomial(
                        new RationalNumber(-1, 1),
                        new RationalNumber(0, 1),
                        new RationalNumber(1,1),
                        new RationalNumber(2, 1),
                        new RationalNumber(3, 1),
                        RationalNumber.ZERO)
                    .multiply(new RationalNumber(2,1)),
                new RationalPolynomial(
                    new RationalNumber(-2, 1),
                    new RationalNumber(0, 1),
                    new RationalNumber(2,1),
                    new RationalNumber(4, 1),
                    new RationalNumber(6, 1)));
        assertEquals(
                new RationalPolynomial(
                        new RationalNumber(-1, 1),
                        new RationalNumber(0, 1),
                        new RationalNumber(1,1),
                        new RationalNumber(2, 1),
                        new RationalNumber(3, 1),
                        RationalNumber.ZERO)
                        .multiply(new RationalNumber(0,1)),
                RationalPolynomial.ZERO);
        RationalPolynomial p1 = new RationalPolynomial(
                new RationalNumber(-1, 1),
                new RationalNumber(0, 1),
                new RationalNumber(1,1),
                new RationalNumber(2, 1),
                new RationalNumber(3, 1),
                RationalNumber.ZERO);
        assertEquals(p1.multiply(RationalNumber.ZERO), RationalPolynomial.ZERO);
    }

    @Test
    public void multiplyRationalPolynomialTest() {
        RationalPolynomial p1 = new RationalPolynomial(
                new RationalNumber(-1, 1),
                new RationalNumber(0, 1),
                new RationalNumber(1,1),
                new RationalNumber(2, 1),
                new RationalNumber(3, 1),
                RationalNumber.ZERO);
        assertEquals(p1.multiply(RationalPolynomial.ZERO), RationalPolynomial.ZERO);
        assertEquals(p1.multiply(RationalPolynomial.ONE), p1);

        RationalPolynomial p2 = new RationalPolynomial(
                new RationalNumber(1, 1),
                new RationalNumber(2, 1),
                new RationalNumber(3,1));
        RationalPolynomial p3 = new RationalPolynomial(
                new RationalNumber(1, 1),
                new RationalNumber(2, 1));
        RationalPolynomial p4 = new RationalPolynomial(
                new RationalNumber(1, 1),
                new RationalNumber(4, 1),
                new RationalNumber(7,1),
                new RationalNumber(6,1));
        assertEquals(p2.multiply(p3), p4);

        RationalPolynomial p5 = new RationalPolynomial(
                new RationalNumber(1, 1),
                new RationalNumber(-3, 1));
        RationalPolynomial p6 = new RationalPolynomial(
                new RationalNumber(2, 3),
                new RationalNumber(0, 1),
                new RationalNumber(2, 1));
        RationalPolynomial p7 = new RationalPolynomial(
                new RationalNumber(2, 3),
                new RationalNumber(-2, 1),
                new RationalNumber(2, 1),
                new RationalNumber(-6,1));
        assertEquals(p5.multiply(p6), p7);
    }

    @Test
    public void powTest() {
        RationalPolynomial p1 = new RationalPolynomial(
                new RationalNumber(1, 1),
                new RationalNumber(2, 1),
                new RationalNumber(3, 1),
                new RationalNumber(4,1));
        assertEquals(p1.pow(0), RationalPolynomial.ONE);
        assertEquals(p1.pow(1), p1);
        assertEquals(p1.pow(2), p1.multiply(p1));
        assertEquals(p1.pow(3), p1.multiply(p1).multiply(p1));
    }

    @Test
    public void divideRationalNumberTest() {
        RationalPolynomial p1 = new RationalPolynomial(
                new RationalNumber(-1,1),
                new RationalNumber(0,1),
                new RationalNumber(1,1),
                new RationalNumber(2,1),
                new RationalNumber(3,1),
                new RationalNumber(4,1));
        RationalNumber r1 = new RationalNumber(5, 1);
        RationalPolynomial p2 = new RationalPolynomial(
                new RationalNumber(-1,5),
                new RationalNumber(0,5),
                new RationalNumber(1,5),
                new RationalNumber(2,5),
                new RationalNumber(3,5),
                new RationalNumber(4,5));
        assertEquals(p1.divide(r1), p2);

        RationalPolynomial p3 = new RationalPolynomial(
                new RationalNumber(-1,1),
                new RationalNumber(0,1),
                new RationalNumber(1,1),
                new RationalNumber(2,1),
                new RationalNumber(3,1),
                new RationalNumber(4,1));
        RationalNumber r2 = new RationalNumber(-5, 1);
        RationalPolynomial p4 = new RationalPolynomial(
                new RationalNumber(1,5),
                new RationalNumber(0,5),
                new RationalNumber(-1,5),
                new RationalNumber(-2,5),
                new RationalNumber(-3,5),
                new RationalNumber(-4,5));
        assertEquals(p3.divide(r2), p4);
    }

    @Test
    public void divideRationalPolynomialTest() {
        RationalPolynomial p1 = new RationalPolynomial(new RationalNumber[] {
                new RationalNumber(-1,1),
                new RationalNumber(1,1),
                new RationalNumber(2,1),
                new RationalNumber(3,1)});
        RationalPolynomial p2 = new RationalPolynomial(new RationalNumber[] {
                new RationalNumber(2,1),
                new RationalNumber(2,1)});
        RationalPolynomial p3 = new RationalPolynomial(new RationalNumber[] {
                new RationalNumber(1,1),
                new RationalNumber(-1,2),
                new RationalNumber(3,2)});
        RationalPolynomial p4 = new RationalPolynomial(new RationalNumber[] {
                new RationalNumber(-3,1)});
        RationalPolynomial[] result = p1.divide(p2);
        assertEquals(result[0], p3);
        assertEquals(result[1], p4);
        assertEquals(p2.multiply(result[0]).add(p4), p1);

        assertEquals(p1.divide(RationalPolynomial.ONE)[0], p1);
        assertEquals(p1.divide(RationalPolynomial.ONE)[1], RationalPolynomial.ZERO);

        RationalPolynomial p5 = new RationalPolynomial(
                new RationalNumber(-1,1),
                new RationalNumber(1,1),
                new RationalNumber(2,1),
                new RationalNumber(3,1));
        RationalPolynomial p6 = new RationalPolynomial(
                new RationalNumber(3,1));
        RationalPolynomial p7 = new RationalPolynomial(
                new RationalNumber(-1,3),
                new RationalNumber(1,3),
                new RationalNumber(2,3),
                new RationalNumber(1,1));
        RationalPolynomial[] output2 = p5.divide(p6);
        assertEquals(output2[0], p7);
        assertEquals(output2[1], RationalPolynomial.ZERO);

        RationalPolynomial p8 = new RationalPolynomial(
            new RationalNumber(4, 1),
                new RationalNumber(4, 1));
        RationalPolynomial p9 = new RationalPolynomial(
                new RationalNumber(1, 1),
                new RationalNumber(1, 1));
        assertEquals(p8.divide(p9)[0], new RationalPolynomial(new RationalNumber(4,1)));
        assertEquals(p8.divide(p9)[1], RationalPolynomial.ZERO);

        RationalPolynomial p10 = new RationalPolynomial(
                new RationalNumber(9, 1),
                new RationalNumber(4, 1),
                new RationalNumber(4, 1),
                RationalNumber.ZERO,
                new RationalNumber(1, 1),
                new RationalNumber(1, 1),
                RationalNumber.ZERO);
        RationalPolynomial p11 = new RationalPolynomial(
                new RationalNumber(4, 1),
                new RationalNumber(0, 1),
                new RationalNumber(0, 1),
                new RationalNumber(1, 1));
        RationalPolynomial p12 = new RationalPolynomial(
                new RationalNumber(0, 1),
                new RationalNumber(1, 1),
                new RationalNumber(1, 1));
        RationalPolynomial p13 = new RationalPolynomial(
                new RationalNumber(9, 1));
        assertEquals(p10.divide(p11)[0], p12);
        assertEquals(p10.divide(p11)[1], p13);
        assertEquals(p10.divide(p12)[0], p11);
        assertEquals(p10.divide(p12)[1], p13);

        RationalPolynomial p14 = new RationalPolynomial(
                new RationalNumber(1, 1),
                new RationalNumber(1, 1));
        RationalPolynomial p15 = new RationalPolynomial(
                new RationalNumber(2, 1),
                RationalNumber.ZERO,
                new RationalNumber(1, 1));
        assertEquals(p14.divide(p15)[0], RationalPolynomial.ZERO);
        assertEquals(p14.divide(p15)[1], p14);

    }

    @Test
    public void evaluateTest() {
        RationalPolynomial p1 = new RationalPolynomial(
                new RationalNumber(1, 1),
                new RationalNumber(2, 1),
                new RationalNumber(3, 1),
                new RationalNumber(4,1));
        assertEquals(p1.evaluate(RationalNumber.ONE), new RationalNumber(10,1));
        assertEquals(p1.evaluate(new RationalNumber(2, 1)), new RationalNumber(49,1));
        assertEquals(p1.evaluate(new RationalNumber(-1, 1)), new RationalNumber(-2,1));
    }

    @Test
    public void evaluate2Test() {
        RationalPolynomial p1 = new RationalPolynomial(
                new RationalNumber(1, 1),
                new RationalNumber(2, 1),
                new RationalNumber(3, 1),
                new RationalNumber(4,1));
        assertEquals(p1.evaluate2(RationalNumber.ONE), new RationalNumber(10,1));
        assertEquals(p1.evaluate2(new RationalNumber(2, 1)), new RationalNumber(49,1));
        assertEquals(p1.evaluate2(new RationalNumber(-1, 1)), new RationalNumber(-2,1));
    }

    @Test
    public void toStringTest() {
        assertEquals(
                RationalPolynomial.ZERO.toString(),
                "0");
        assertEquals(
                RationalPolynomial.ONE.toString(),
                "1");
        assertEquals(
                new RationalPolynomial(new RationalNumber(-1,1)).toString(),
                "-1");
        assertEquals(
                new RationalPolynomial(new RationalNumber(-1,2)).toString(),
                "-1/2");
        assertEquals(
                new RationalPolynomial(new RationalNumber(1,2)).toString(),
                "1/2");
        assertEquals(
                new RationalPolynomial(
                        new RationalNumber(1,2),
                        new RationalNumber(1,1)).toString(),
                "x+(1/2)");
        assertEquals(
                new RationalPolynomial(
                        new RationalNumber(-1,2),
                        new RationalNumber(1,1)).toString(),
                "x-(1/2)");
        assertEquals(
                new RationalPolynomial(
                        new RationalNumber(1,2),
                        new RationalNumber(-1,1)).toString(),
                "-x+(1/2)");
        assertEquals(
                new RationalPolynomial(
                        new RationalNumber(-1,2),
                        new RationalNumber(2,1)).toString(),
                "2x-(1/2)");
        assertEquals(
                new RationalPolynomial(
                        new RationalNumber(-1,2),
                        new RationalNumber(-2,1)).toString(),
                "-2x-(1/2)");
        assertEquals(
                new RationalPolynomial(
                        new RationalNumber(-1,2),
                        new RationalNumber(3,5)).toString(),
                "(3/5)x-(1/2)");
        assertEquals(
                new RationalPolynomial(
                        new RationalNumber(-1,2),
                        new RationalNumber(3,-5)).toString(),
                "-(3/5)x-(1/2)");
        assertEquals(
                new RationalPolynomial(
                        new RationalNumber(-1,2),
                        new RationalNumber(3,-5),
                        new RationalNumber(1,1)).toString(),
                "x^2-(3/5)x-(1/2)");
        assertEquals(
                new RationalPolynomial(
                        new RationalNumber(-1,2),
                        new RationalNumber(3,1),
                        new RationalNumber(-1,1)).toString(),
                "-x^2+3x-(1/2)");
        assertEquals(
                new RationalPolynomial(
                        new RationalNumber(-1,2),
                        new RationalNumber(3,1),
                        new RationalNumber(2,3)).toString(),
                "(2/3)x^2+3x-(1/2)");
        assertEquals(
                new RationalPolynomial(
                        new RationalNumber(-1,2),
                        new RationalNumber(3,1),
                        new RationalNumber(-1,3)).toString(),
                "-(1/3)x^2+3x-(1/2)");
    }
}