package com.trinwrite;

import com.sun.istack.internal.NotNull;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RationalPolynomial {

    private RationalNumber[] coefficients;

    public static final RationalPolynomial ZERO = new RationalPolynomial(RationalNumber.ZERO);
    public static final RationalPolynomial ONE = new RationalPolynomial(RationalNumber.ONE);
    public static final RationalPolynomial X = new RationalPolynomial(RationalNumber.ZERO, RationalNumber.ONE);

    public RationalPolynomial(@NotNull RationalNumber... coefficients) {
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

    public int degree() {
        return coefficients.length - 1;
    }

    public RationalNumber leadingCoefficient() {
        return coefficients[coefficients.length -1];
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

    public RationalPolynomial subtract(RationalPolynomial subtrahend) {
        return this.add(subtrahend.multiply(new RationalNumber(-1,1)));
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

    public RationalPolynomial pow(int exponent) {
        if (exponent < 0) throw new IllegalArgumentException();
        return IntStream.range(0, exponent)
                .mapToObj(i -> this)
                .reduce(RationalPolynomial.ONE, (a, b) -> a.multiply(b));
    }

    public RationalPolynomial divide(RationalNumber divisor) {
        return this.multiply(RationalNumber.ONE.divide(divisor));
    }

    public RationalPolynomial[] divide(RationalPolynomial divisor) {
        if (divisor.degree() == 0) {
            return new RationalPolynomial[] {
                    this.divide(divisor.leadingCoefficient()),
                    RationalPolynomial.ZERO};
        }
        RationalPolynomial quotient = RationalPolynomial.ZERO;
        RationalPolynomial remainder = new RationalPolynomial(this.coefficients);
        while (remainder.degree() >= divisor.degree()) {
            RationalPolynomial s = RationalPolynomial.X.pow(remainder.degree() - divisor.degree())
                    .multiply(remainder.leadingCoefficient().divide(divisor.leadingCoefficient()));
            quotient = quotient.add(s);
            remainder = remainder.subtract(s.multiply(divisor));
        }
        return new RationalPolynomial[]{ quotient, remainder};
    }

    public RationalNumber evaluate(RationalNumber x) {
        RationalNumber p = RationalNumber.ZERO;
        for (int i = coefficients.length -1; i >= 0; i--) {
            p = coefficients[i].add(x.multiply(p));
        }
        return p;
    }

    public RationalNumber evaluate2(RationalNumber x) {
        return IntStream.range(0, coefficients.length)
                .mapToObj(i -> coefficients[i].multiply(x.pow(i)))
                .reduce(RationalNumber.ZERO, (a,b)-> a.add(b));
    }

    @Override
    public String toString() {
        if (this.equals(RationalPolynomial.ZERO)) {
            return "0";
        }
        if (coefficients.length == 1) {
            return coefficients[0].toString();
        }
        StringBuilder builder = new StringBuilder();
        for (int i = coefficients.length - 1; i >= 0; i--) {
            if (coefficients[i].equals(RationalNumber.ZERO)) {
                continue;
            }
            if (coefficients[i].numerator().compareTo(BigInteger.ZERO) < 0) {
                builder.append("-");
            } else if (i != coefficients.length -1) {
                builder.append("+");
            }
            if (coefficients[i].equals(RationalNumber.ONE)
                    || coefficients[i].equals(new RationalNumber(-1,1))) {
                if (i == 0) {
                    builder.append("1");
                }
            } else  {
                if (coefficients[i].denominator().equals(BigInteger.ONE)) {
                    builder.append(coefficients[i].numerator().abs());
                } else {
                    builder.append("(" + coefficients[i].numerator().abs()
                            + "/" + coefficients[i].denominator() + ")");
                }
            }
            if (i > 0) {
                builder.append("x");
            }
            if (i > 1) {
                builder.append("^" + i);
            }
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

    @Override
    public int hashCode() {
        return Arrays.asList(coefficients)
                .stream()
                .map(coefficient -> coefficient.hashCode())
                .reduce(0, (a,b) -> a+b);
    }
}
