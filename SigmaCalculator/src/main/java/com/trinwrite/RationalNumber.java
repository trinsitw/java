package com.trinwrite;

import com.sun.xml.internal.bind.annotation.OverrideAnnotationOf;

import java.util.stream.IntStream;

public class RationalNumber {
    private int numerator;
    private int denominator;

    public static final RationalNumber ZERO = new RationalNumber(0,1);
    public static final RationalNumber ONE = new RationalNumber(1,1);

    public RationalNumber(int a) {
        this(a, 1);
    }

    public RationalNumber(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException();
        }
        int gcd = gcd(Math.abs(a), Math.abs(b));
        numerator = a / gcd;
        denominator = b / gcd;
        if ((a < 0 && b < 0) || (a >= 0 && b < 0)) {
            numerator = -1*numerator;
            denominator = -1*denominator;
        }
    }

    public int numerator() {
        return numerator;
    }

    public int denominator() {
        return denominator;
    }

    public RationalNumber add(RationalNumber augend) {
        return new RationalNumber(
                numerator*augend.denominator() + denominator*augend.numerator(),
                denominator*augend.denominator());
    }

    public RationalNumber subtract(RationalNumber subtrahend) {
        return this.add(
                new RationalNumber(
                        -1*subtrahend.numerator(),
                        subtrahend.denominator()));
    }

    public RationalNumber multiply(RationalNumber multiplicand) {
        return new RationalNumber(
                numerator*multiplicand.numerator(),
                denominator*multiplicand.denominator());
    }

    public RationalNumber pow(int exponent) {
        if (exponent < 0) throw new IllegalArgumentException();
        return IntStream.range(0, exponent)
                .mapToObj(i -> this)
                .reduce(RationalNumber.ONE, (a,b) -> a.multiply(b));
    }

    public RationalNumber divide(RationalNumber divisor) {
        return this.multiply(
                new RationalNumber(
                        divisor.denominator(),
                        divisor.numerator()));
    }

    @Override
    public String toString() {
        if (numerator == 0) return "0";
        if (denominator == 1) return String.valueOf(numerator);
        return numerator + "/" + denominator;
    }

    @Override
    public boolean equals(Object that) {
        if (!(that instanceof RationalNumber)) {
            return false;
        }
        RationalNumber another = (RationalNumber) that;
        return numerator*another.denominator() == denominator*another.numerator();
    }

    @Override
    public int hashCode() {
        return numerator*7 + denominator*31;
    }

    protected static int gcd(int a, int b) {
        if (a < b) {
            return gcd(b, a);
        }
        if (b < 0) {
            throw new IllegalArgumentException();
        }
        if (b == 0) {
            return a;
        }
        return gcd(b, a%b);
    }
}
