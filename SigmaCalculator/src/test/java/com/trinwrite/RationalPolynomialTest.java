package com.trinwrite;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RationalPolynomialTest {

    @Test
    public void equalsTest() {
        assertEquals(
                new RationalPolynomial(new RationalNumber[] {
                        new RationalNumber(1,2),
                        new RationalNumber(3,4),
                        new RationalNumber(0,1)}),
                new RationalPolynomial(new RationalNumber[] {
                        new RationalNumber(2,4),
                        new RationalNumber(-6,-8)}));
        assertEquals(
                new RationalPolynomial(new RationalNumber[] {
                        new RationalNumber(1,2),
                        new RationalNumber(3,4),
                        new RationalNumber(0,1)}),
                new RationalPolynomial(new RationalNumber[] {
                        new RationalNumber(2,4),
                        new RationalNumber(-6,-8),
                        RationalNumber.ZERO,
                        RationalNumber.ZERO,
                        RationalNumber.ZERO}));
        assertEquals(
                new RationalPolynomial(new RationalNumber[] {
                        RationalNumber.ZERO,
                        RationalNumber.ZERO,
                        new RationalNumber(3,4),
                        new RationalNumber(0,1)}),
                new RationalPolynomial(new RationalNumber[] {
                        new RationalNumber(0,4),
                        new RationalNumber(0,-4),
                        new RationalNumber(-6,-8),
                        RationalNumber.ZERO,
                        RationalNumber.ZERO,
                        RationalNumber.ZERO}));

    }

    @Test
    public void addTest() {
        assertEquals(
                new RationalPolynomial(new RationalNumber[] {
                     new RationalNumber(0,1),
                     new RationalNumber(1,1),
                     new RationalNumber(2, 1)}).add(new RationalPolynomial(new RationalNumber[] {
                             new RationalNumber(3,1),
                            new RationalNumber(-4,1),
                            new RationalNumber(0,1),
                            new RationalNumber(5,1),
                            RationalNumber.ZERO,
                            RationalNumber.ZERO})),
                new RationalPolynomial(new RationalNumber[] {
                    new RationalNumber(3,1),
                    new RationalNumber(-3,1),
                    new RationalNumber(2,1),
                    new RationalNumber(5,1)}));
    }
}