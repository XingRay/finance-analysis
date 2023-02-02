package com.xingray.finanace.analysis.model.data;


import com.xingray.java.collection.series.Series;

import java.util.List;

public interface DataList<T> extends Series<T> {

    List<T> asList();

    void add(T t);

    void addAll(List<? extends T> list);

    void clear();

    void addDataChangedListener(DataChangedListener listener);

    void removeDataChangedListener(DataChangedListener listener);
}
