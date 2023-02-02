package com.xingray.finance.analysis.number;

import java.util.function.Function;

public class NaN implements Num {

    public static final Num NaN = new NaN();

    private NaN() {
    }

    @Override
    public int compareTo(Num o) {
        return 0;
    }

    @Override
    public int intValue() {
        throw new UnsupportedOperationException("No NaN represantation for int");
    }

    @Override
    public long longValue() {
        throw new UnsupportedOperationException("No NaN represantation for long");
    }

    @Override
    public float floatValue() {
        return Float.NaN;
    }

    @Override
    public double doubleValue() {
        return Double.NaN;
    }

    @Override
    public Number getDelegate() {
        return null;
    }

    @Override
    public String getName() {
        return toString();
    }

    @Override
    public String toString() {
        return "NaN";
    }

    @Override
    public Num plus(Num num) {
        return this;
    }

    @Override
    public Num minus(Num num) {
        return this;
    }

    @Override
    public Num multipliedBy(Num num) {
        return this;
    }

    @Override
    public Num dividedBy(Num num) {
        return this;
    }

    @Override
    public Num remainder(Num num) {
        return this;
    }

    @Override
    public Num pow(int n) {
        return this;
    }

    @Override
    public Num pow(Num n) {
        return this;
    }

    @Override
    public Num log() {
        return this;
    }

    @Override
    public Num sqrt() {
        return this;
    }

    @Override
    public Num sqrt(int precision) {
        return this;
    }

    @Override
    public Num abs() {
        return this;
    }

    @Override
    public boolean isZero() {
        return false;
    }

    @Override
    public boolean isPositive() {
        return false;
    }

    @Override
    public boolean isPositiveOrZero() {
        return false;
    }

    @Override
    public boolean isNegative() {
        return false;
    }

    @Override
    public boolean isNegativeOrZero() {
        return false;
    }

    /**
     * NaN.isEqual(NaN) -> true
     *
     * @param other the other value, not null
     * @return flase if both values are not NaN
     */
    @Override
    public boolean isEqual(Num other) {
        return other != null && other.equals(NaN);
    }

    @Override
    public boolean isGreaterThan(Num other) {
        return false;
    }

    @Override
    public boolean isGreaterThanOrEqual(Num other) {
        return false;
    }

    @Override
    public boolean isLessThan(Num other) {
        return false;
    }

    @Override
    public boolean isLessThanOrEqual(Num other) {
        return false;
    }

    @Override
    public Num min(Num other) {
        return this;
    }

    @Override
    public Num max(Num other) {
        return this;
    }

    @Override
    public Function<Number, Num> function() {
        return number -> NaN;
    }

    @Override
    public boolean isNaN() {
        return true;
    }
}
