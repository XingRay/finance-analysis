package com.xingray.finance.analysis.number;


import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.Objects;
import java.util.function.Function;

public final class PrecisionNum implements Num {

    private static final long serialVersionUID = 785564782721079992L;

    private static final int DEFAULT_PRECISION = 32;
    private final MathContext mathContext;
    private final BigDecimal delegate;

    private PrecisionNum(String val) {
        delegate = new BigDecimal(val);
        int precision = Math.max(delegate.precision(), DEFAULT_PRECISION);
        mathContext = new MathContext(precision, RoundingMode.HALF_UP);
    }

    private PrecisionNum(String val, int precision) {
        mathContext = new MathContext(precision, RoundingMode.HALF_UP);
        delegate = new BigDecimal(val, new MathContext(precision, RoundingMode.HALF_UP));
    }

    private PrecisionNum(short val) {
        mathContext = new MathContext(DEFAULT_PRECISION, RoundingMode.HALF_UP);
        delegate = new BigDecimal(val, mathContext);
    }

    private PrecisionNum(int val) {
        mathContext = new MathContext(DEFAULT_PRECISION, RoundingMode.HALF_UP);
        delegate = BigDecimal.valueOf(val);
    }

    private PrecisionNum(long val) {
        mathContext = new MathContext(DEFAULT_PRECISION, RoundingMode.HALF_UP);
        delegate = BigDecimal.valueOf(val);
    }

    private PrecisionNum(float val) {
        mathContext = new MathContext(DEFAULT_PRECISION, RoundingMode.HALF_UP);
        delegate = new BigDecimal(val, mathContext);
    }

    private PrecisionNum(double val) {
        mathContext = new MathContext(DEFAULT_PRECISION, RoundingMode.HALF_UP);
        delegate = BigDecimal.valueOf(val);
    }

    private PrecisionNum(BigDecimal val, int precision) {
        mathContext = new MathContext(precision, RoundingMode.HALF_UP);
        delegate = Objects.requireNonNull(val);
    }

    public static PrecisionNum valueOf(String val) {
        if (val.equalsIgnoreCase("NAN")) {
            throw new NumberFormatException();
        }
        return new PrecisionNum(val);
    }

    public static PrecisionNum valueOf(String val, int precision) {
        if (val.equalsIgnoreCase("NAN")) {
            throw new NumberFormatException();
        }
        return new PrecisionNum(val, precision);
    }

    public static PrecisionNum valueOf(short val) {
        return new PrecisionNum(val);
    }

    public static PrecisionNum valueOf(int val) {
        return new PrecisionNum(val);
    }

    public static PrecisionNum valueOf(long val) {
        return new PrecisionNum(val);
    }

    public static PrecisionNum valueOf(float val) {
        if (Float.isNaN(val)) {
            throw new NumberFormatException();
        }
        return new PrecisionNum(val);
    }

    public static PrecisionNum valueOf(BigDecimal val) {
        return new PrecisionNum(val, val.precision());
    }

    public static PrecisionNum valueOf(BigDecimal val, int precision) {
        return new PrecisionNum(val, precision);
    }

    public static PrecisionNum valueOf(double val) {
        if (Double.isNaN(val)) {
            throw new NumberFormatException();
        }
        return new PrecisionNum(val);
    }

    public static PrecisionNum valueOf(PrecisionNum val) {
        return val;
    }

    public static PrecisionNum valueOf(Number val) {
        return new PrecisionNum(val.toString());
    }

    @Override
    public Function<Number, Num> function() {
        return (number -> PrecisionNum.valueOf(number.toString(), mathContext.getPrecision()));
    }

    @Override
    public BigDecimal getDelegate() {
        return delegate;
    }

    public MathContext getMathContext() {
        return mathContext;
    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public Num plus(Num num) {
        if (num.isNaN()) {
            return NaN.NaN;
        }
        BigDecimal bigDecimal = ((PrecisionNum) num).delegate;
        int precision = mathContext.getPrecision();
        BigDecimal result = delegate.add(bigDecimal, mathContext);
        return new PrecisionNum(result, precision);
    }

    @Override
    public Num minus(Num num) {
        if (num.isNaN()) {
            return NaN.NaN;
        }
        BigDecimal bigDecimal = ((PrecisionNum) num).delegate;
        int precision = mathContext.getPrecision();
        BigDecimal result = delegate.subtract(bigDecimal, mathContext);
        return new PrecisionNum(result, precision);
    }

    @Override
    public Num multipliedBy(Num num) {
        if (num.isNaN()) {
            return NaN.NaN;
        }
        BigDecimal bigDecimal = ((PrecisionNum) num).delegate;
        int precision = mathContext.getPrecision();
        BigDecimal result = delegate.multiply(bigDecimal, new MathContext(precision, RoundingMode.HALF_UP));
        return new PrecisionNum(result, precision);
    }

    @Override
    public Num dividedBy(Num num) {
        if (num.isNaN() || num.isZero()) {
            return NaN.NaN;
        }
        BigDecimal bigDecimal = ((PrecisionNum) num).delegate;
        int precision = mathContext.getPrecision();
        BigDecimal result = delegate.divide(bigDecimal, new MathContext(precision, RoundingMode.HALF_UP));
        return new PrecisionNum(result, precision);
    }

    @Override
    public Num remainder(Num num) {
        BigDecimal bigDecimal = ((PrecisionNum) num).delegate;
        int precision = mathContext.getPrecision();
        BigDecimal result = delegate.remainder(bigDecimal, new MathContext(precision, RoundingMode.HALF_UP));
        return new PrecisionNum(result, precision);
    }

    public Num floor() {
        int precision = Math.max(mathContext.getPrecision(), DEFAULT_PRECISION);
        return new PrecisionNum(delegate.setScale(0, RoundingMode.FLOOR), precision);
    }

    public Num ceil() {
        int precision = Math.max(mathContext.getPrecision(), DEFAULT_PRECISION);
        return new PrecisionNum(delegate.setScale(0, RoundingMode.CEILING), precision);
    }

    @Override
    public Num pow(int n) {
        int precision = mathContext.getPrecision();
        BigDecimal result = delegate.pow(n, new MathContext(precision, RoundingMode.HALF_UP));
        return new PrecisionNum(result, precision);
    }

    public Num sqrt() {
        return sqrt(DEFAULT_PRECISION);
    }

    @Override
    public Num sqrt(int precision) {
        int comparedToZero = delegate.compareTo(BigDecimal.ZERO);
        switch (comparedToZero) {
            case -1 -> {
                return NaN.NaN;
            }
            case 0 -> {
                return PrecisionNum.valueOf(0);
            }
        }

        MathContext precisionContext = new MathContext(precision, RoundingMode.HALF_UP);
        BigDecimal estimate = new BigDecimal(delegate.toString(), precisionContext);
        String string = String.format(Locale.ROOT, "%1.1e", estimate);
        if (string.contains("e")) {
            String[] parts = string.split("e");
            BigDecimal mantissa = new BigDecimal(parts[0]);
            BigDecimal exponent = new BigDecimal(parts[1]);
            if (exponent.remainder(new BigDecimal(2)).compareTo(BigDecimal.ZERO) > 0) {
                exponent = exponent.subtract(BigDecimal.ONE);
                mantissa = mantissa.multiply(BigDecimal.TEN);
            }
            BigDecimal estimatedMantissa = mantissa.compareTo(BigDecimal.TEN) < 0 ? new BigDecimal(2)
                    : new BigDecimal(6);
            BigDecimal estimatedExponent = exponent.divide(new BigDecimal(2));
            String estimateString = String.format("%sE%s", estimatedMantissa, estimatedExponent);
            DecimalFormat format = new DecimalFormat();
            format.setParseBigDecimal(true);
            try {
                estimate = (BigDecimal) format.parse(estimateString);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        BigDecimal delta;
        BigDecimal test;
        BigDecimal sum;
        BigDecimal newEstimate;
        BigDecimal two = new BigDecimal(2);
        do {
            test = delegate.divide(estimate, precisionContext);
            sum = estimate.add(test);
            newEstimate = sum.divide(two, precisionContext);
            delta = newEstimate.subtract(estimate).abs();
            estimate = newEstimate;
        } while (delta.compareTo(BigDecimal.ZERO) > 0);
        return PrecisionNum.valueOf(estimate, precision);
    }

    public Num log() {
        Num logx;
        if (isNegativeOrZero()) {
            return NaN.NaN;
        }

        if (delegate.equals(BigDecimal.ONE)) {
            logx = PrecisionNum.valueOf(BigDecimal.ZERO, mathContext.getPrecision());
        } else {
            long ITER = 1000;
            BigDecimal x = delegate.subtract(BigDecimal.ONE);
            BigDecimal ret = new BigDecimal(ITER + 1);
            for (long i = ITER; i >= 0; i--) {
                BigDecimal N = new BigDecimal(i / 2 + 1).pow(2);
                N = N.multiply(x, mathContext);
                ret = N.divide(ret, mathContext);

                N = new BigDecimal(i + 1);
                ret = ret.add(N, mathContext);

            }
            ret = x.divide(ret, mathContext);

            logx = PrecisionNum.valueOf(ret, mathContext.getPrecision());
        }
        return logx;
    }

    @Override
    public Num abs() {
        return new PrecisionNum(delegate.abs(), mathContext.getPrecision());
    }

    @Override
    public boolean isZero() {
        return delegate.signum() == 0;
    }

    @Override
    public boolean isPositive() {
        return delegate.signum() > 0;
    }

    @Override
    public boolean isPositiveOrZero() {
        return delegate.signum() >= 0;
    }

    @Override
    public boolean isNegative() {
        return delegate.signum() < 0;
    }

    @Override
    public boolean isNegativeOrZero() {
        return delegate.signum() <= 0;
    }

    @Override
    public boolean isEqual(Num other) {
        return !other.isNaN() && compareTo(other) == 0;
    }

    public boolean matches(Num other, int precision) {
        Num otherNum = PrecisionNum.valueOf(other.toString(), precision);
        Num thisNum = PrecisionNum.valueOf(this.toString(), precision);
        return thisNum.toString().equals(otherNum.toString());
    }

    public boolean matches(Num other, Num delta) {
        Num result = this.minus(other);
        return !result.isGreaterThan(delta);
    }

    @Override
    public boolean isGreaterThan(Num other) {
        return !other.isNaN() && compareTo(other) > 0;
    }

    @Override
    public boolean isGreaterThanOrEqual(Num other) {
        return !other.isNaN() && compareTo(other) > -1;
    }

    @Override
    public boolean isLessThan(Num other) {
        return !other.isNaN() && compareTo(other) < 0;
    }

    @Override
    public boolean isLessThanOrEqual(Num other) {
        return !other.isNaN() && delegate.compareTo(((PrecisionNum) other).delegate) < 1;
    }

    @Override
    public int compareTo(Num other) {
        return other.isNaN() ? 0 : delegate.compareTo(((PrecisionNum) other).delegate);
    }

    @Override
    public Num min(Num other) {
        return other.isNaN() ? NaN.NaN : (compareTo(other) <= 0 ? this : other);
    }

    @Override
    public Num max(Num other) {
        return other.isNaN() ? NaN.NaN : (compareTo(other) >= 0 ? this : other);
    }

    @Override
    public int hashCode() {
        return Objects.hash(delegate);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof PrecisionNum)) {
            return false;
        }
        return this.delegate.compareTo(((PrecisionNum) obj).delegate) == 0;
    }

    @Override
    public String toString() {
        return delegate.toString();
    }

    @Override
    public Num pow(Num n) {
        BigDecimal aPlusB = (((PrecisionNum) n).delegate);
        BigDecimal b = aPlusB.remainder(BigDecimal.ONE);
        double bDouble = b.doubleValue();
        BigDecimal a = aPlusB.subtract(b);
        int aInt = a.intValueExact();
        BigDecimal xPowA = delegate.pow(aInt);
        double xPowB = Math.pow(delegate.doubleValue(), bDouble);
        BigDecimal result = xPowA.multiply(new BigDecimal(xPowB));
        return new PrecisionNum(result.toString());
    }
}
