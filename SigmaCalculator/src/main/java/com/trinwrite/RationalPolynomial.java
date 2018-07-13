package com.trinwrite;

import com.sun.istack.internal.NotNull;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RationalPolynomial {

    private RationalNumber[] coefficients;

    public static final RationalPolynomial ZERO = new RationalPolynomial(new RationalNumber[]{ RationalNumber.ZERO });
    public static final RationalPolynomial ONE = new RationalPolynomial(new RationalNumber[]{ RationalNumber.ONE });

    public RationalPolynomial(@NotNull RationalNumber[] coefficients) {
        if (coefficients.length == 0) {
            throw new IllegalArgumentException();
        }
        int degree = 0;
        for (int i = coefficients.length - 1; i >= 0; i--) {
            if (!coefficients[i].equals(RationalNumber.ZERO)) {
                degree = i;
                break;
            }
        }
        if (coefficients.length - 1 == degree) {
            this.coefficients = coefficients;
        } else {
            RationalNumber[] newCoefficients = new RationalNumber[degree + 1];
            for (int i = 0; i < newCoefficients.length; i++) {
                newCoefficients[i] = coefficients[i];
            }
            this.coefficients = newCoefficients;
        }
    }
    public RationalNumber[] coefficients() {
        return Arrays.copyOf(coefficients, coefficients.length);
    }

    public RationalPolynomial add(RationalPolynomial augend) {

        RationalNumber[] newCoefficients = new RationalNumber[Math.max(
                coefficients.length,
                augend.coefficients().length)];
        for (int i = 0; i < newCoefficients.length; i++) {
            RationalNumber thisCoefficient = null;
            try {
                thisCoefficient = coefficients[i];
            } catch(ArrayIndexOutOfBoundsException e) {
                thisCoefficient = RationalNumber.ZERO;
            }
            RationalNumber augendCoefficient = null;
            try {
                augendCoefficient = augend.coefficients()[i];
            } catch(ArrayIndexOutOfBoundsException e) {
                augendCoefficient = RationalNumber.ZERO;
            }
            newCoefficients[i] = thisCoefficient.add(augendCoefficient);
        }
        return new RationalPolynomial(newCoefficients);
    }

    public RationalPolynomial multiply(RationalNumber multiplicand) {
        return new RationalPolynomial(
                Arrays.asList(coefficients)
                        .stream()
                        .map(coefficient -> coefficient.multiply(multiplicand))
                        .collect(Collectors.toList())
                    .toArray(new RationalNumber[0]));
    }

    public RationalPolynomial multiply(RationalPolynomial multiplicand) {
        RationalNumber[] productCoefficients = IntStream
                .rangeClosed(0, this.coefficients.length+multiplicand.coefficients().length)
                .mapToObj(i -> RationalNumber.ZERO)
                .collect(Collectors.toList())
                .toArray(new RationalNumber[0]);
        for (int i = 0; i < coefficients.length; i++) {
            for (int j = 0; j < multiplicand.coefficients().length; j++) {
                productCoefficients[i+j] = productCoefficients[i+j].add(
                        this.coefficients[i].multiply(multiplicand.coefficients()[j]));
            }
        }
        return new RationalPolynomial(productCoefficients);
    }

    @Override
    public String toString() {
        if (Arrays.asList(coefficients).stream().allMatch(q -> q.equals(RationalNumber.ZERO))) {
            return "0";
        }
        StringBuilder builder = new StringBuilder();
        for (int i = coefficients.length-1; i >= 0; i--) {
            if (coefficients[i].equals(RationalNumber.ZERO)) {
                continue;
            }
            builder = builder.append(((i!=coefficients.length-1)?" + ":"") + "(" + coefficients[i].toString() + ")" + ((i > 0)?"x":"") + ((i > 1)?"^" + i:""));
        }
        return builder.toString();
    }
    
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof RationalPolynomial)) {
            return false;
        }
        RationalPolynomial polynomial = (RationalPolynomial) object;
        if (coefficients.length != polynomial.coefficients().length) {
            return false;
        }
        return IntStream.range(0, coefficients.length)
                .allMatch(i -> coefficients[i].equals(polynomial.coefficients()[i]));
    }
}