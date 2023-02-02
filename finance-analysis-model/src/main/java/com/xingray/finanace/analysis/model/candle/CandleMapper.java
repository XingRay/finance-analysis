package com.xingray.finanace.analysis.model.candle;



import com.xingray.finance.analysis.number.Num;

import java.time.Duration;

public interface CandleMapper<T> {
    Num getOpen(T t);

    Num getClose(T t);

    Num getHigh(T t);

    Num getLow(T t);

    Num getVolume(T t);

    Num getAmount(T t);

    Long getBeginTimeSecond(T t);

    Long getEndTimeSecond(T t);

    Duration getTimePeriod(T t);
}
