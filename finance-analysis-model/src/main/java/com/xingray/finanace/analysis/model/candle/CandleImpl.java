package com.xingray.finanace.analysis.model.candle;




import com.xingray.finance.analysis.number.Num;

import java.time.Duration;
import java.util.Objects;

public class CandleImpl implements Candle {

    private final Num openPrice;

    private final Num closePrice;

    private final Num highPrice;

    private final Num lowPrice;

    private final Num amount;

    private final Num volume;

    private final Long beginTimeSecond;

    private final Long endTimeSecond;

    private final Duration timePeriod;

    public CandleImpl(Num openPrice, Num closePrice, Num highPrice, Num lowPrice, Num amount, Num volume, Long beginTimeSecond, Long endTimeSecond, Duration timePeriod) {
        this.openPrice = openPrice;
        this.closePrice = closePrice;
        this.highPrice = highPrice;
        this.lowPrice = lowPrice;
        this.amount = amount;
        this.volume = volume;
        this.beginTimeSecond = beginTimeSecond;
        this.endTimeSecond = endTimeSecond;
        this.timePeriod = timePeriod;
    }

    @Override
    public Num getOpen() {
        return openPrice;
    }

    @Override
    public Num getClose() {
        return closePrice;
    }

    @Override
    public Num getHigh() {
        return highPrice;
    }

    @Override
    public Num getLow() {
        return lowPrice;
    }

    @Override
    public Num getVolume() {
        return volume;
    }

    @Override
    public Num getAmount() {
        return amount;
    }

    @Override
    public Long getBeginTimeSecond() {
        return beginTimeSecond;
    }

    @Override
    public Long getEndTimeSecond() {
        return endTimeSecond;
    }

    @Override
    public Duration getTimePeriod() {
        return timePeriod;
    }

    @Override
    public int hashCode() {
        return Objects.hash(openPrice, closePrice, highPrice, lowPrice, volume, amount, beginTimeSecond, endTimeSecond, timePeriod);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CandleImpl)) {
            return false;
        }
        CandleImpl candle = (CandleImpl) o;
        return Objects.equals(openPrice, candle.openPrice) &&
                Objects.equals(closePrice, candle.closePrice) &&
                Objects.equals(highPrice, candle.highPrice) &&
                Objects.equals(lowPrice, candle.lowPrice) &&
                Objects.equals(amount, candle.amount) &&
                Objects.equals(volume, candle.volume) &&
                Objects.equals(beginTimeSecond, candle.beginTimeSecond) &&
                Objects.equals(endTimeSecond, candle.endTimeSecond) &&
                Objects.equals(timePeriod, candle.timePeriod);
    }

    @Override
    public String toString() {
        return "CandleImpl{" +
                "openPrice=" + openPrice +
                ", closePrice=" + closePrice +
                ", highPrice=" + highPrice +
                ", lowPrice=" + lowPrice +
                ", amount=" + amount +
                ", volume=" + volume +
                ", beginTimeSecond=" + beginTimeSecond +
                ", endTimeSecond=" + endTimeSecond +
                ", timePeriod=" + timePeriod +
                '}';
    }
}
