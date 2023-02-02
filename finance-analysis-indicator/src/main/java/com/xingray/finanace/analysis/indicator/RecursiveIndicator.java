package com.xingray.finanace.analysis.indicator;

import com.xingray.finanace.analysis.model.data.DataList;

public final class RecursiveIndicator<T, E> implements Indicator<T, E> {

    private int lastIndex = -1;
    private final Indicator<T, E> indicator;

    public RecursiveIndicator(Indicator<T, E> indicator) {
        this.indicator = indicator;
    }

    @Override
    public T get(int index) {
        Indicator<T, E> indicator = this.indicator;
        if (index > lastIndex) {
            int startIndex = lastIndex == -1 ? 0 : lastIndex;
            for (int i = startIndex; i < index; i++) {
                indicator.get(i);
            }
            lastIndex = index;
            return indicator.get(index);
        }
        return indicator.get(index);
    }

    @Override
    public int length() {
        return indicator == null ? 0 : indicator.length();
    }

    private void reset() {
        lastIndex = -1;
    }

    @Override
    public DataList<E> getDataList() {
        return indicator.getDataList();
    }
}
