package com.trinwrite;

import java.util.Objects;

public class IndeterminateExponent implements Comparable<IndeterminateExponent> {
    private char indeterminate;
    private int exponent;

    public IndeterminateExponent(char indeterminate, int exponent) {
        if (indeterminate < 'a' || 'z' < indeterminate) {
            throw new IllegalArgumentException("Invalid indeterminate. Only a-z is allowed.");
        }
        if (exponent < 1) {
            throw new IllegalArgumentException("Invalid exponent. Only positive is allowed.");
        }
        this.indeterminate = indeterminate;
        this.exponent = exponent;
    }

    public char indeterminate() {
        return indeterminate;
    }

    public int exponent() {
        return exponent;
    }

    @Override
    public int compareTo(IndeterminateExponent that) {
        return Character.compare(indeterminate, that.indeterminate);
    }

    @Override
    public String toString() {
        return "(" + indeterminate + "^" + exponent + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IndeterminateExponent that = (IndeterminateExponent) o;
        return indeterminate == that.indeterminate &&
                exponent == that.exponent;
    }

    @Override
    public int hashCode() {
        return Objects.hash(indeterminate, exponent);
    }
}
