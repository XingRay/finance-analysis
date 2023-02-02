package com.xingray.finanace.analysis.indicator;


import com.xingray.finanace.analysis.model.data.DataList;
import com.xingray.java.collection.series.Series;

public interface Indicator<T, E> extends Series<T> {
    DataList<E> getDataList();
}
