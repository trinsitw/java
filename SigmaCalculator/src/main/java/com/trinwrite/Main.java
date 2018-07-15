package com.trinwrite;

public class Main {

    public static void main(String[] args) {

        RationalPolynomial p = RationalPolynomial.X
                    .multiply(new RationalPolynomial(
                        new RationalNumber(1,1),
                        new RationalNumber(1,1))
                    .multiply(new RationalPolynomial(
                        new RationalNumber(1,1),
                        new RationalNumber(2,1))))
                    .multiply(new RationalNumber(1,6));
        System.out.println(p);

    }
}
