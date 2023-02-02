package com.xingray.finanace.analysis.model.candle;


import com.xingray.finance.analysis.number.Num;
import com.xingray.java.base.interfaces.Mapper;

import java.time.Duration;

public abstract class ValueCandleMapper<T> implements CandleMapper<T> {

    private final Mapper<Number, Num> mapper;

    public ValueCandleMapper(Mapper<Number, Num> mapper) {
        this.mapper = mapper;
    }

    public abstract double getOpenValue(T t);

    public abstract double getCloseValue(T t);

    public abstract double getHighValue(T t);

    public abstract double getLowValue(T t);

    public abstract long getVolumeValue(T t);

    public abstract long getAmountValue(T t);

    public abstract long getBeginTimeSecondValue(T t);

    public abstract long getEndTimeSecondValue(T t);

    public abstract Duration getTimePeriodValue(T t);

    @Override
    public final Num getOpen(T t) {
        return mapper.map(getOpenValue(t));
    }

    @Override
    public final Num getClose(T t) {
        return mapper.map(getCloseValue(t));
    }

    @Override
    public final Num getHigh(T t) {
        return mapper.map(getHighValue(t));
    }

    @Override
    public final Num getLow(T t) {
        return mapper.map(getLowValue(t));
    }

    @Override
    public final Num getVolume(T t) {
        return mapper.map(getVolumeValue(t));
    }

    @Override
    public final Num getAmount(T t) {
        return mapper.map(getAmountValue(t));
    }

    @Override
    public final Long getBeginTimeSecond(T t) {
        return getBeginTimeSecondValue(t);
    }

    @Override
    public final Long getEndTimeSecond(T t) {
        return getEndTimeSecondValue(t);
    }

    @Override
    public final Duration getTimePeriod(T t) {
        return getTimePeriodValue(t);
    }
}
