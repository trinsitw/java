package com.trinwrite;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MainTest {

    @Test
    public void RationalNumberEqualsTest() {
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
        assertEquals(RationalNumber.gcd(2,3), 1);
        assertEquals(RationalNumber.gcd(2,4), 2);
        assertEquals(RationalNumber.gcd(12,18), 6);
    }
}
