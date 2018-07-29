package com.trinwrite;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;
import java.util.Arrays;

import static junit.framework.TestCase.assertEquals;
import static com.trinwrite.MultivariatePolynomial.*;

public class MultivariatePolynomialTest {
    @Test
    public void constructorTest() {
        MultivariatePolynomial mp1 = new MultivariatePolynomial(
                new MultivariateMonomial(new RationalNumber(1), 'i', 2),
                new MultivariateMonomial(new RationalNumber(2), 'i', 2),
                new MultivariateMonomial(new RationalNumber(3), 'i', 2),
                new MultivariateMonomial(new RationalNumber(4), 'n', 3),
                new MultivariateMonomial(new RationalNumber(5), 'n', 4),
                new MultivariateMonomial(new RationalNumber(6), 'n', 3, 'i', 2),
                new MultivariateMonomial(new RationalNumber(7), 'n', 3, 'i', 3)
        );
        System.out.println(mp1);

    }

    @Test
    public void addMonomialTest() {
        MultivariatePolynomial mp1 = new MultivariatePolynomial(
                new MultivariateMonomial(new RationalNumber(1), 'i', 2),
                new MultivariateMonomial(new RationalNumber(2), 'i', 2),
                new MultivariateMonomial(new RationalNumber(3), 'i', 2),
                new MultivariateMonomial(new RationalNumber(4), 'n', 3));
        MultivariateMonomial mm2 = new MultivariateMonomial(new RationalNumber(1), 'i', 2);
        MultivariatePolynomial mp3 = new MultivariatePolynomial(
                new MultivariateMonomial(new RationalNumber(7), 'i', 2),
                new MultivariateMonomial(new RationalNumber(4), 'n', 3));
        System.out.println("mp1: " + mp1);
        System.out.println("mm2: " + mm2);
        System.out.println("mp3: " + mp3);
        System.out.println("mp1.add(mm2): " + mp1.add(mm2));
        assertEquals(mp1.add(mm2), mp3);

        MultivariatePolynomial mp4 = new MultivariatePolynomial(
                new MultivariateMonomial(new RationalNumber(1), 'i', 2));
        MultivariateMonomial mm5 = new MultivariateMonomial(new RationalNumber(-1), 'i', 2);
        MultivariatePolynomial mp6 = MultivariatePolynomial.ZERO;
        System.out.println("mp4: " + mp4);
        System.out.println("mm5: " + mm5);
        System.out.println("mp6: " + mp6);
        System.out.println("mp4.add(mm5): " + mp4.add(mm5));
        assertEquals(mp4.add(mm5), mp6);

        System.out.println("mp4: " + mp4);
        System.out.println("mp4.add(ZERO): " + mp4.add(MultivariateMonomial.ZERO));
        assertEquals(mp4.add(MultivariateMonomial.ZERO), mp4);
    }

    @Test
    public void addPolynomialTest() {
        MultivariatePolynomial mp1 = new MultivariatePolynomial(
                new MultivariateMonomial(new RationalNumber(1), 'i', 1),
                new MultivariateMonomial(new RationalNumber(2), 'i', 2),
                new MultivariateMonomial(new RationalNumber(3), 'i', 3),
                new MultivariateMonomial(new RationalNumber(4), 'n', 4));
        MultivariatePolynomial mp2 = new MultivariatePolynomial(
                new MultivariateMonomial(new RationalNumber(5), 'i', 2),
                new MultivariateMonomial(new RationalNumber(6), 'i', 3),
                new MultivariateMonomial(new RationalNumber(7), 'i', 4),
                new MultivariateMonomial(new RationalNumber(8), 'n', 5));
        MultivariatePolynomial mp3 = new MultivariatePolynomial(
                new MultivariateMonomial(new RationalNumber(1), 'i', 1),
                new MultivariateMonomial(new RationalNumber(7), 'i', 2),
                new MultivariateMonomial(new RationalNumber(9), 'i', 3),
                new MultivariateMonomial(new RationalNumber(7), 'i', 4),
                new MultivariateMonomial(new RationalNumber(4), 'n', 4),
                new MultivariateMonomial(new RationalNumber(8), 'n', 5));
        System.out.println("mp1: " + mp1);
        System.out.println("mp2: " + mp2);
        System.out.println("mp3: " + mp3);
        System.out.println("mp1.add(mp2): " + mp1.add(mp2));
        assertEquals(mp1.add(mp2), mp3);

        System.out.println("mp3: " + mp3);
        System.out.println("mp3.add(ZERO: " + mp3.add(MultivariatePolynomial.ZERO));
        assertEquals(mp3, mp3.add(MultivariatePolynomial.ZERO));

        MultivariatePolynomial mp4 = new MultivariatePolynomial(
                new MultivariateMonomial(new RationalNumber(1), 'i', 1),
                new MultivariateMonomial(new RationalNumber(2), 'i', 2),
                new MultivariateMonomial(new RationalNumber(3), 'i', 3),
                new MultivariateMonomial(new RationalNumber(4), 'n', 4));

        MultivariatePolynomial mp5 = new MultivariatePolynomial(
                new MultivariateMonomial(new RationalNumber(-1), 'i', 1),
                new MultivariateMonomial(new RationalNumber(-2), 'i', 2),
                new MultivariateMonomial(new RationalNumber(-3), 'i', 3),
                new MultivariateMonomial(new RationalNumber(-4), 'n', 4));
        System.out.println("mp4: " + mp4);
        System.out.println("mp5: " + mp5);
        System.out.println("mp4.add(mp5): " + mp4.add(mp5));
        assertEquals(mp4.add(mp5), MultivariatePolynomial.ZERO);
    }

    @Test
    public void subtractMonomialTest() {
        MultivariatePolynomial mp1 = new MultivariatePolynomial(
                new MultivariateMonomial(new RationalNumber(1), 'i', 1),
                new MultivariateMonomial(new RationalNumber(2), 'i', 2),
                new MultivariateMonomial(new RationalNumber(3), 'i', 3),
                new MultivariateMonomial(new RationalNumber(4), 'n', 4));
        MultivariateMonomial mm2 = new MultivariateMonomial(new RationalNumber(3), 'i', 2);
        System.out.println("mp1: " + mp1);
        System.out.println("mm2: " + mm2);
        MultivariatePolynomial mp3 = new MultivariatePolynomial(
                new MultivariateMonomial(new RationalNumber(1), 'i', 1),
                new MultivariateMonomial(new RationalNumber(-1), 'i', 2),
                new MultivariateMonomial(new RationalNumber(3), 'i', 3),
                new MultivariateMonomial(new RationalNumber(4), 'n', 4));
        System.out.println("mp3: " + mp3);
        assertEquals(mp1.subtract(mm2), mp3);
    }

    @Test
    public void subtractPolynomialTest() {
        MultivariatePolynomial mp1 = new MultivariatePolynomial(
                new MultivariateMonomial(new RationalNumber(1), 'i', 1),
                new MultivariateMonomial(new RationalNumber(2), 'i', 2),
                new MultivariateMonomial(new RationalNumber(3), 'i', 3),
                new MultivariateMonomial(new RationalNumber(4), 'n', 4),
                new MultivariateMonomial(new RationalNumber(5), 'n', 5));

        MultivariatePolynomial mp2 = new MultivariatePolynomial(
                new MultivariateMonomial(new RationalNumber(5), 'i', 1),
                new MultivariateMonomial(new RationalNumber(4), 'i', 2),
                new MultivariateMonomial(new RationalNumber(3), 'i', 3),
                new MultivariateMonomial(new RationalNumber(2), 'n', 4),
                new MultivariateMonomial(new RationalNumber(1), 'n', 5));

        MultivariatePolynomial mp3 = new MultivariatePolynomial(
                new MultivariateMonomial(new RationalNumber(-4), 'i', 1),
                new MultivariateMonomial(new RationalNumber(-2), 'i', 2),
                new MultivariateMonomial(new RationalNumber(2), 'n', 4),
                new MultivariateMonomial(new RationalNumber(4), 'n', 5));
        System.out.println("mp1: " + mp1);
        System.out.println("mp2: " + mp2);
        System.out.println("mp3: " + mp3);
        assertEquals(mp1.subtract(mp2), mp3);
    }

    @Test
    public void multiplyMonomialTest() {
        MultivariatePolynomial mp1 = new MultivariatePolynomial(
                new MultivariateMonomial(new RationalNumber(1), 'i', 1),
                new MultivariateMonomial(new RationalNumber(2), 'i', 2),
                new MultivariateMonomial(new RationalNumber(3), 'n', 3),
                new MultivariateMonomial(new RationalNumber(4), 'n', 4));
        MultivariateMonomial mm2 = new MultivariateMonomial(new RationalNumber(2), 'i', 3);
        MultivariatePolynomial mp3 = new MultivariatePolynomial(
                new MultivariateMonomial(new RationalNumber(2), 'i', 4),
                new MultivariateMonomial(new RationalNumber(4), 'i', 5),
                new MultivariateMonomial(new RationalNumber(6), 'n', 3, 'i', 3),
                new MultivariateMonomial(new RationalNumber(8), 'n', 4, 'i', 3));
        System.out.println("mp1: " + mp1);
        System.out.println("mm2: " + mm2);
        System.out.println("mp3: " + mp3);
        assertEquals(mp1.multiply(mm2), mp3);

        System.out.println("mp1: " + mp1);
        System.out.println("mp1.multiply(ZERO): " + mp1.multiply(MultivariateMonomial.ZERO));
        assertEquals(mp1.multiply(MultivariateMonomial.ZERO), MultivariatePolynomial.ZERO);

        System.out.println("mp3: " + mp3);
        System.out.println("mp3.multiply(ZERO): " + mp3.multiply(MultivariateMonomial.ZERO));
        assertEquals(mp3.multiply(MultivariateMonomial.ZERO), MultivariatePolynomial.ZERO);


        System.out.println("mp1: " + mp1);
        System.out.println("mp1.multiply(ONE): " + mp1.multiply(MultivariateMonomial.ONE));
        assertEquals(mp1.multiply(MultivariateMonomial.ONE), mp1);

        System.out.println("mp3: " + mp3);
        System.out.println("mp3.multiply(ONE): " + mp3.multiply(MultivariateMonomial.ONE));
        assertEquals(mp3.multiply(MultivariateMonomial.ONE), mp3);

        System.out.println("ZERO.multiply(ONE): " + MultivariatePolynomial.ZERO.multiply(MultivariateMonomial.ONE));
        assertEquals(MultivariatePolynomial.ZERO.multiply(MultivariateMonomial.ONE), MultivariatePolynomial.ZERO);
    }

    @Test
    public void multiplyPolynomialTest() {
        MultivariatePolynomial mp1 = new MultivariatePolynomial(
                new MultivariateMonomial(new RationalNumber(2), 'i', 1),
                new MultivariateMonomial(new RationalNumber(3), 'n', 2));

        MultivariatePolynomial mp2 = new MultivariatePolynomial(
                new MultivariateMonomial(new RationalNumber(4), 'i', 2),
                new MultivariateMonomial(new RationalNumber(12), 'n', 2, 'i', 1),
                new MultivariateMonomial(new RationalNumber(9), 'n', 4));

        System.out.println("mp1: " + mp1);
        System.out.println("mp1.multiply(mp1): " + mp1.multiply(mp1));
        System.out.println("mp2: " + mp2);
        assert(mp1.multiply(mp1).equals(mp2));
    }

    @Test
    public void powTest() {
        MultivariatePolynomial mp1 = new MultivariatePolynomial(
                new MultivariateMonomial(new RationalNumber(2), 'i', 1),
                new MultivariateMonomial(new RationalNumber(3), 'n', 2));
        System.out.println("mp1: " + mp1);
        System.out.println("mp1.pow(1): " + mp1.pow(1));
        assertEquals(mp1.pow(1), mp1);

        System.out.println("mp1.multiply(mp1): " + mp1.multiply(mp1));
        System.out.println("mp1.pow(2): " + mp1.pow(2));
        assertEquals(mp1.pow(2), mp1.multiply(mp1));

        System.out.println("mp1.multiply(mp1).multiply(mp1): " + mp1.multiply(mp1).multiply(mp1));
        System.out.println("mp1.pow(3): " + mp1.pow(3));
        assertEquals(mp1.pow(3), mp1.multiply(mp1).multiply(mp1));
    }

    @Test
    public void composeTest() {
        MultivariatePolynomial mp1 = new MultivariatePolynomial(
                    new MultivariateMonomial(new RationalNumber(1,2), 'n', 1))
                .multiply(new MultivariatePolynomial(
                        new MultivariateMonomial(RationalNumber.ONE, 'n', 1),
                        MultivariateMonomial.ONE));
        System.out.println("mp1: " + mp1);

        MultivariatePolynomial mp2 = new MultivariatePolynomial(
                new MultivariateMonomial(RationalNumber.ONE, 'i', 1),
                new MultivariateMonomial(new RationalNumber(-1)));
        System.out.println("mp2: " + mp2);

        MultivariatePolynomial mp3 = new MultivariatePolynomial(
                new MultivariateMonomial(new RationalNumber(1,2), 'i', 2),
                new MultivariateMonomial(new RationalNumber(-1,2), 'i', 1));
        System.out.println("mp3: " + mp3);
        System.out.println("mp1.compose('n', mp2): " + mp1.compose('n', mp2));
        assertEquals(mp1.compose('n', mp2), mp3);

        MultivariatePolynomial mp4 = new MultivariatePolynomial(
                new MultivariateMonomial(RationalNumber.ONE, 'x', 2),
                new MultivariateMonomial(new RationalNumber(2), 'x', 1),
                MultivariateMonomial.ONE);
        System.out.println("mp4: " + mp4);
        MultivariatePolynomial mp5 = new MultivariatePolynomial(
                new MultivariateMonomial(new RationalNumber(2), 'x', 1));
        System.out.println("mp5: " + mp5);
        MultivariatePolynomial mp6 = new MultivariatePolynomial(
                new MultivariateMonomial(new RationalNumber(4), 'x', 2),
                new MultivariateMonomial(new RationalNumber(4), 'x', 1),
                MultivariateMonomial.ONE);
        System.out.println("mp6: " + mp6);

        System.out.println("mp4.compose('x', mp5): " + mp4.compose('x', mp5));
        assertEquals(mp4.compose('x', mp5), mp6);
    }

    @Test
    public void evaluateTest() {
        MultivariatePolynomial mp1 = new MultivariatePolynomial(
                new MultivariateMonomial(RationalNumber.ONE, 'x', 2),
                new MultivariateMonomial(new RationalNumber(2), 'x', 1),
                MultivariateMonomial.ONE);
        System.out.println(mp1);
        System.out.println("mp1.evaluate(3): " + mp1.evaluate(new BigInteger("3")));
        assertEquals(mp1.evaluate(new BigInteger("3")), new BigInteger("16"));

        System.out.println("mp1.evaluate(-1): " + mp1.evaluate(new BigInteger("-2")));
        assertEquals(mp1.evaluate(new BigInteger("-2")), BigInteger.ONE);

        MultivariatePolynomial mp2 = new MultivariatePolynomial(
                new MultivariateMonomial(RationalNumber.ONE, 'x', 2),
                new MultivariateMonomial(new RationalNumber(2), 'x', 1),
                new MultivariateMonomial(new RationalNumber(2), 'x', 1),
                MultivariateMonomial.ONE);
        System.out.println("mp2: " + mp2);
        System.out.println("mp2.evaluate(RationalNumber.ONE): " + mp2.evaluate(BigInteger.ONE));
        assertEquals(mp2.evaluate(BigInteger.ONE), new BigInteger("6"));

        MultivariatePolynomial mp3 = MultivariatePolynomial.ONE;
        System.out.println("mp3: " + mp3);
        System.out.println("mp3.evaluate(RationalNumber.ZERO): " + mp3.evaluate(BigInteger.ZERO));
        assertEquals(mp3.evaluate(BigInteger.ZERO), BigInteger.ONE);

    }

    @Test
    public void gcdTest() {
        Assert.assertEquals(
                gcd(new BigInteger("3"), new BigInteger("2")),
                BigInteger.ONE);
        Assert.assertEquals(
                gcd(new BigInteger("2"),new BigInteger("3")),
                BigInteger.ONE);
        Assert.assertEquals(
                gcd(new BigInteger("2"),new BigInteger("4")),
                new BigInteger("2"));
        Assert.assertEquals(
                gcd(new BigInteger("12"),new BigInteger("18")),
                new BigInteger("6"));
        Assert.assertEquals(
                gcd(new BigInteger("18"),new BigInteger("12")),
                new BigInteger("6"));
        Assert.assertEquals(
                gcd(BigInteger.ONE,BigInteger.ONE),
                BigInteger.ONE);
    }

    @Test
    public void lcmTest() {
        BigInteger[] bigIntegers1 = new BigInteger[] {
                new BigInteger("2"),
                new BigInteger("6"),
                new BigInteger("12") };
        assertEquals(lcm(bigIntegers1), new BigInteger("12"));

        BigInteger[] bigIntegers2 = new BigInteger[] {
                new BigInteger("3"),
                new BigInteger("5"),
                new BigInteger("6") };
        assertEquals(lcm(bigIntegers2), new BigInteger("30"));

    }

    @Test
    public void alternativePolynomialProductTest() {
        MultivariatePolynomial p1 = new MultivariatePolynomial(
          new MultivariateMonomial(new RationalNumber(1,5), 'n', 5),
                new MultivariateMonomial(new RationalNumber(1,2), 'n', 4),
                new MultivariateMonomial(new RationalNumber(1,3), 'n', 3),
         new MultivariateMonomial(new RationalNumber(-1,30), 'n',1));
        System.out.println("p1: " + p1);
        System.out.println("p1.alternativePolynomialProduct()[0]: " + p1.alternativePolynomialProduct()[0]);
        System.out.println("p1.alternativePolynomialProduct()[1]: " + p1.alternativePolynomialProduct()[1]);
    }
}