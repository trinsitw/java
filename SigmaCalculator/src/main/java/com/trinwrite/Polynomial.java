package com.trinwrite;

import com.sun.istack.internal.NotNull;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Polynomial {
    private RationalNumber[] coefficients;

    public Polynomial(@NotNull RationalNumber[] coefficients) {
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

    public Polynomial add(Polynomial augend) {

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
        return new Polynomial(newCoefficients);
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
    public boolean equals(Object that) {
        if (!(that instanceof Polynomial)) {
            return false;
        }
        Polynomial thatPolynomial = (Polynomial) that;
        if (coefficients.length != thatPolynomial.coefficients().length) {
            return false;
        }
        return IntStream.range(0, coefficients.length)
                .allMatch(i -> coefficients[i].equals(thatPolynomial.coefficients()[i]));
    }
}
