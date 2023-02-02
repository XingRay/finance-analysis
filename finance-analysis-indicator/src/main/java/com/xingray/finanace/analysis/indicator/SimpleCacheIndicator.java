package com.xingray.finanace.analysis.indicator;

public final class SimpleCacheIndicator<T, E> extends CacheIndicator<T, E> {

    public SimpleCacheIndicator(Indicator<T, E> indicator, T defaultValue) {
        super(indicator, defaultValue);
    }

    @Override
    protected T calculate(int index) {
        return getIndicator().get(index);
    }
}
