package com.xingray.finanace.analysis.indicator;

import com.xingray.finanace.analysis.model.data.DataList;

public abstract class MultiWrapIndicator<T, E> implements Indicator<T, E> {

    private final Indicator<T, E> indicator;
    private final Indicator<T, E>[] indicatorArray;

    public MultiWrapIndicator(Indicator<T, E>... indicators) {
        this.indicatorArray = indicators;
        this.indicator = indicators[0];
    }

    @Override
    public DataList<E> getDataList() {
        return indicator.getDataList();
    }

    @Override
    public int length() {
        return indicator.length();
    }
}
