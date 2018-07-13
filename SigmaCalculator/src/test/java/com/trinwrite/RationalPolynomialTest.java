package com.trinwrite;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class RationalPolynomialTest {

    @Test
    public void equalsTest() {

        assertFalse(RationalPolynomial.ONE.equals(null));

        Arrays.asList(new RationalPolynomial(new RationalNumber[]{
                RationalNumber.ZERO,
                RationalNumber.ZERO,
                RationalNumber.ZERO}).coefficients()).stream().forEach(System.out::println);
        assertEquals(
                new RationalPolynomial(new RationalNumber[]{
                    RationalNumber.ZERO,
                    RationalNumber.ZERO,
                    RationalNumber.ZERO}),
                new RationalPolynomial(new RationalNumber[] {RationalNumber.ZERO}));
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

    @Test
    public void multiplyRationalNumberTest() {
        assertEquals(
                new RationalPolynomial(new RationalNumber[] {
                        new RationalNumber(-1, 1),
                        new RationalNumber(0, 1),
                        new RationalNumber(1,1),
                        new RationalNumber(2, 1),
                        new RationalNumber(3, 1),
                        RationalNumber.ZERO})
                    .multiply(new RationalNumber(2,1)),
                new RationalPolynomial(new RationalNumber[] {
                    new RationalNumber(-2, 1),
                    new RationalNumber(0, 1),
                    new RationalNumber(2,1),
                    new RationalNumber(4, 1),
                    new RationalNumber(6, 1)}));
        assertEquals(
                new RationalPolynomial(new RationalNumber[] {
                        new RationalNumber(-1, 1),
                        new RationalNumber(0, 1),
                        new RationalNumber(1,1),
                        new RationalNumber(2, 1),
                        new RationalNumber(3, 1),
                        RationalNumber.ZERO})
                        .multiply(new RationalNumber(0,1)),
                new RationalPolynomial(new RationalNumber[] {
                        RationalNumber.ZERO}));
        RationalPolynomial p1 = new RationalPolynomial(new RationalNumber[] {
                new RationalNumber(-1, 1),
                new RationalNumber(0, 1),
                new RationalNumber(1,1),
                new RationalNumber(2, 1),
                new RationalNumber(3, 1),
                RationalNumber.ZERO});
        assertEquals(p1.multiply(RationalNumber.ZERO), RationalPolynomial.ZERO);
        assertEquals(p1.multiply(RationalNumber.ONE), p1);
    }

    @Test
    public void multiplyRationalPolynomialTest() {
        RationalPolynomial p1 = new RationalPolynomial(new RationalNumber[] {
                new RationalNumber(-1, 1),
                new RationalNumber(0, 1),
                new RationalNumber(1,1),
                new RationalNumber(2, 1),
                new RationalNumber(3, 1),
                RationalNumber.ZERO});
        assertEquals(p1.multiply(RationalPolynomial.ZERO), RationalPolynomial.ZERO);
        assertEquals(p1.multiply(RationalPolynomial.ONE), p1);
        RationalPolynomial p2 = new RationalPolynomial(new RationalNumber[] {
                new RationalNumber(1, 1),
                new RationalNumber(2, 1),
                new RationalNumber(3,1)});
        RationalPolynomial p3 = new RationalPolynomial(new RationalNumber[] {
                new RationalNumber(1, 1),
                new RationalNumber(2, 1)});
        RationalPolynomial p4 = new RationalPolynomial(new RationalNumber[] {
                new RationalNumber(1, 1),
                new RationalNumber(4, 1),
                new RationalNumber(7,1),
                new RationalNumber(6,1)});
        assertEquals(p2.multiply(p3), p4);

    }
}