package com.xingray.finanace.analysis.model.candle;



import com.xingray.finance.analysis.number.Num;
import com.xingray.java.base.interfaces.Mapper;

import java.time.Duration;

public abstract class WrapperCandleMapper<T> implements CandleMapper<T> {

    private final Mapper<Number, Num> mapper;

    public WrapperCandleMapper(Mapper<Number, Num> mapper) {
        this.mapper = mapper;
    }

    public abstract Double getOpenValue(T t);

    public abstract Double getCloseValue(T t);

    public abstract Double getHighValue(T t);

    public abstract Double getLowValue(T t);

    public abstract Long getVolumeValue(T t);

    public abstract Long getAmountValue(T t);

    public abstract Long getBeginTimeSecondValue(T t);

    public abstract Long getEndTimeSecondValue(T t);

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
