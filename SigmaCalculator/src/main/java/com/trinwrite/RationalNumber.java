package com.trinwrite;

import com.sun.xml.internal.bind.annotation.OverrideAnnotationOf;

import java.math.BigInteger;
import java.util.stream.IntStream;

public class RationalNumber {
    private BigInteger numerator;
    private BigInteger denominator;

    public static final RationalNumber ZERO = new RationalNumber(0,1);
    public static final RationalNumber ONE = new RationalNumber(1,1);

    public RationalNumber(BigInteger a, BigInteger b) {
        if (b.equals(BigInteger.ZERO)) {
            throw new IllegalArgumentException();
        }
        BigInteger gcd = a.gcd(b);
        numerator = a.divide(gcd);
        denominator = b.divide(gcd);
        if ((a.compareTo(BigInteger.ZERO) < 0 && b.compareTo(BigInteger.ZERO) < 0)
                || (a.compareTo(BigInteger.ZERO) >= 0 && b.compareTo(BigInteger.ZERO) < 0)) {
            numerator = new BigInteger("-1").multiply(numerator);
            denominator = new BigInteger("-1").multiply(denominator);
        }
    }

    public RationalNumber(BigInteger a) {
        this(a, BigInteger.ONE);
    }


    public RationalNumber(int a) {
        this(new BigInteger(String.valueOf(a)), BigInteger.ONE);
    }

    public RationalNumber(int a, int b) {
        this(new BigInteger(String.valueOf(a)), new BigInteger(String.valueOf(b)));
    }

    public BigInteger numerator() {
        return numerator;
    }

    public BigInteger denominator() {
        return denominator;
    }

    public RationalNumber add(RationalNumber augend) {
        return new RationalNumber(
                numerator.multiply(augend.denominator()).add(denominator.multiply(augend.numerator())),
                denominator.multiply(augend.denominator()));
    }

    public RationalNumber subtract(RationalNumber subtrahend) {
        return this.add(
                new RationalNumber(
                        new BigInteger("-1").multiply(subtrahend.numerator()),
                        subtrahend.denominator()));
    }

    public RationalNumber multiply(RationalNumber multiplicand) {
        return new RationalNumber(
                numerator.multiply(multiplicand.numerator()),
                denominator.multiply(multiplicand.denominator()));
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
        if (numerator.equals(BigInteger.ZERO)) return "0";
        if (denominator.equals(BigInteger.ONE)) return String.valueOf(numerator);
        return numerator + "/" + denominator;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof RationalNumber)) {
            return false;
        }
        RationalNumber that = (RationalNumber) object;
        return numerator.multiply(that.denominator()).equals(denominator.multiply(that.numerator()));
    }

    @Override
    public int hashCode() {
        return numerator.hashCode()*7 + denominator.hashCode()*31;
    }
}
