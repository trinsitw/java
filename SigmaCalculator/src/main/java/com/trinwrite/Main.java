package com.trinwrite;

public class Main {

    public static void main(String[] args) {
        int a = 27;
        int b = 9;
	    System.out.println(gcd(a, b));
        System.out.println(new RationalNumber(1,1));
        System.out.println(new Polynomial(new RationalNumber[]{
                new RationalNumber(3,1),
                new RationalNumber(1,2),
                new RationalNumber(3,4)}));
        System.out.println(new Polynomial(new RationalNumber[]{
                new RationalNumber(3,1),
                new RationalNumber(0,2),
                new RationalNumber(3,4)}));
        System.out.println(new Polynomial(new RationalNumber[]{
                new RationalNumber(0,1),
                new RationalNumber(0,1),
                new RationalNumber(0,1)}));
    }

    public static int gcd(int a, int b) {
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
