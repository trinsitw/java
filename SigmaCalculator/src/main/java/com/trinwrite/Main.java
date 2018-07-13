package com.trinwrite;

public class Main {

    public static void main(String[] args) {
        System.out.println(new RationalNumber(1,1));
        System.out.println(new RationalPolynomial(new RationalNumber[]{
                new RationalNumber(3,1),
                new RationalNumber(1,2),
                new RationalNumber(3,-4)}));
        System.out.println(new RationalPolynomial(new RationalNumber[]{
                new RationalNumber(3,1),
                new RationalNumber(0,2),
                new RationalNumber(3,4)}));
        System.out.println(new RationalPolynomial(new RationalNumber[]{
                new RationalNumber(0,1),
                new RationalNumber(0,1),
                new RationalNumber(0,1)}));
        System.out.println(new RationalPolynomial(new RationalNumber[]{
                new RationalNumber(1,1),
                new RationalNumber(1,-1),
                new RationalNumber(-3,2),
                RationalNumber.ZERO,
                RationalNumber.ONE,
                new RationalNumber(5,1)}));
        System.out.println(new RationalPolynomial(new RationalNumber[]{
                new RationalNumber(-1,1),
                new RationalNumber(1,1),
                new RationalNumber(-3,2),
                RationalNumber.ZERO,
                RationalNumber.ONE,
                new RationalNumber(5,1)}));
    }
}
