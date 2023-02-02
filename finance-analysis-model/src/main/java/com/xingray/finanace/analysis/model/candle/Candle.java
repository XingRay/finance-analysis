package com.xingray.finanace.analysis.model.candle;




import com.xingray.finance.analysis.number.Num;

import java.time.Duration;

public interface Candle {

    Num getOpen();

    Num getClose();

    Num getHigh();

    Num getLow();

    Num getVolume();

    Num getAmount();

    Long getBeginTimeSecond();

    Long getEndTimeSecond();

    Duration getTimePeriod();
}
