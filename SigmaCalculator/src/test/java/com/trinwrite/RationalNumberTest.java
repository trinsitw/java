package com.trinwrite;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RationalNumberTest {

    @Test
    public void equalsTest() {
        RationalNumber r1 = new RationalNumber(1,2);
        RationalNumber r2 = new RationalNumber(2,4);
        assertEquals(r1, r2);

        RationalNumber r3 = new RationalNumber(-1,2);
        RationalNumber r4 = new RationalNumber(2,-4);
        assertEquals(r3, r4);

        RationalNumber r5 = new RationalNumber(-1,-2);
        RationalNumber r6 = new RationalNumber(2,4);
        assertEquals(r5, r6);

        RationalNumber r7 = new RationalNumber(0,1);
        RationalNumber r8 = new RationalNumber(0,-2);
        assertEquals(r7, r8);
    }

    @Test
    public void gcdTest() {
        assertEquals(
                RationalNumber.gcd(2,3),
                1);
        assertEquals(
                RationalNumber.gcd(2,4),
                2);
        assertEquals(
                RationalNumber.gcd(12,18),
                6);
        assertEquals(
                RationalNumber.gcd(1,1),
                1);
    }

    @Test
    public void addTest() {
        assertEquals(
                new RationalNumber(1,2).add(new RationalNumber(3,4)),
                new RationalNumber(10,8));
        assertEquals(
                new RationalNumber(4,2).add(new RationalNumber(-1,2)),
                new RationalNumber(-6,-4));
    }

    @Test
    public void subtractTest() {
        assertEquals(
                new RationalNumber(3,4).subtract(new RationalNumber(3,4)),
                RationalNumber.ZERO);
        assertEquals(
                new RationalNumber(3,4).subtract(new RationalNumber(2,4)),
                new RationalNumber(1,4));
        assertEquals(
                new RationalNumber(3,-4).subtract(new RationalNumber(1,4)),
                new RationalNumber(5,-5));
    }

    @Test
    public void multiplyTest() {
        assertEquals(
                new RationalNumber(12,15).multiply(new RationalNumber(4,3)),
                new RationalNumber(16,15));
        assertEquals(
                new RationalNumber(3,1).multiply(new RationalNumber(4,-1)),
                new RationalNumber(-12,1));
        assertEquals(
                new RationalNumber(3,1).multiply(RationalNumber.ZERO),
                RationalNumber.ZERO);
    }

    @Test
    public void powTest() {
        RationalNumber r1 = new RationalNumber(1,2);
        assertEquals(r1.pow(0), RationalNumber.ONE);
        assertEquals(r1.pow(1), r1);
        assertEquals(r1.pow(2), r1.multiply(r1));
        assertEquals(r1.pow(3), r1.multiply(r1).multiply(r1));
    }

    @Test
    public void divideTest() {
        assertEquals(
                new RationalNumber(3,1).divide(new RationalNumber(5,1)),
                new RationalNumber(-6,-10));
        assertEquals(
                RationalNumber.ZERO.divide(new RationalNumber(1,1)),
                RationalNumber.ZERO);
    }
}
