package com.intellectworld.graphtheory.forex;

import java.math.BigDecimal;
import java.util.Objects;

public class ForexPair {
    private String currency1;
    private String currency2;
    private BigDecimal rate;

    public ForexPair(String currency1, String currency2, Double rate) {
        this.currency1 = currency1;
        this.currency2 = currency2;
        this.rate = new BigDecimal(String.valueOf(rate));
    }

    public String currency1() {
        return currency1;
    }

    public String currency2() {
        return currency2;
    }

    public BigDecimal rate() {
        return rate;
    }

    @Override
    public String toString() {
        return "[" + currency1 + "->" + currency2 + ":" + rate + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ForexPair that = (ForexPair) o;
        return Objects.equals(currency1, that.currency1) &&
                Objects.equals(currency2, that.currency2) &&
                Objects.equals(rate, that.rate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currency1, currency2, rate);
    }
}
