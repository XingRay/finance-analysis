package com.xingray.finanace.analysis.model.candle;

import java.util.List;

public class CandleSeriesBuilder {

    public static <T> CandleList build(List<T> list, CandleMapper<T> mapper) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        CandleList candleList = new CandleList(list.size());
        for (T t : list) {
            CandleImpl candle = new CandleImpl(
                    mapper.getOpen(t),
                    mapper.getClose(t),
                    mapper.getHigh(t),
                    mapper.getLow(t),
                    mapper.getVolume(t),
                    mapper.getAmount(t),
                    mapper.getBeginTimeSecond(t),
                    mapper.getEndTimeSecond(t),
                    mapper.getTimePeriod(t));
            candleList.add(candle);
        }
        return candleList;
    }
}
