package com.xingray.finanace.analysis.indicator;


import com.xingray.finanace.analysis.model.candle.Candle;
import com.xingray.finanace.analysis.model.candle.CandleList;
import com.xingray.finanace.analysis.model.data.DataList;
import com.xingray.finance.analysis.number.Num;

public abstract class CandleIndicator implements Indicator<Num, Candle> {

    private final DataList<Candle> candleList;

    public CandleIndicator() {
        this(new CandleList());
    }

    public CandleIndicator(DataList<Candle> candleList) {
        this.candleList = candleList;
    }

    @Override
    public DataList<Candle> getDataList() {
        return candleList;
    }

    @Override
    public final int length() {
        return candleList == null ? 0 : candleList.length();
    }
}
