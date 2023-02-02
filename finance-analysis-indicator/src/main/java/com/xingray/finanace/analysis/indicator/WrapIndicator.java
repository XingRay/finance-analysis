package com.xingray.finanace.analysis.indicator;

import com.xingray.finanace.analysis.model.data.DataList;

public abstract class WrapIndicator<T, E> implements Indicator<T, E> {

    private final Indicator<T, E> indicator;

    public WrapIndicator(Indicator<T, E> indicator) {
        this.indicator = indicator;
    }

    @Override
    public int length() {
        return indicator.length();
    }

    @Override
    public DataList<E> getDataList() {
        return indicator.getDataList();
    }
}
