package com.xingray.finance.analysis.number;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.function.Function;

public interface Num extends Comparable<Num>, Serializable {

    Number getDelegate();

    String getName();

    Num plus(Num num);

    Num minus(Num num);

    Num multipliedBy(Num num);

    Num dividedBy(Num num);

    Num remainder(Num num);

    Num pow(int n);

    Num pow(Num n);

    Num log();

    Num sqrt();

    Num sqrt(int precision);

    Num abs();

    boolean isZero();

    boolean isPositive();

    boolean isPositiveOrZero();

    boolean isNegative();

    boolean isNegativeOrZero();

    boolean isEqual(Num other);

    boolean isGreaterThan(Num other);

    boolean isGreaterThanOrEqual(Num other);

    boolean isLessThan(Num other);

    boolean isLessThanOrEqual(Num other);

    Num min(Num other);

    Num max(Num other);

    Function<Number, Num> function();

    default Num numOf(Number value) {
        return function().apply(value);
    }

    default Num numOf(String value, int precision) {
        MathContext mathContext = new MathContext(precision, RoundingMode.HALF_UP);
        return this.numOf(new BigDecimal(value, mathContext));
    }

    default boolean isNaN() {
        return false;
    }

    default double doubleValue() {
        return getDelegate().doubleValue();
    }

    default int intValue() {
        return getDelegate().intValue();
    }

    default long longValue() {
        return getDelegate().longValue();
    }

    default float floatValue() {
        return getDelegate().floatValue();
    }

    @Override
    int hashCode();

    @Override
    String toString();

    @Override
    boolean equals(Object obj);

}
