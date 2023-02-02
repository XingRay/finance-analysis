package com.xingray.finanace.analysis.model.candle;



import com.xingray.finanace.analysis.model.data.DataChangedListener;
import com.xingray.finanace.analysis.model.data.DataList;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CandleList implements DataList<Candle> {

    private final List<Candle> candleList;
    private List<DataChangedListener> dataChangedListenerList;

    public CandleList() {
        this(0);
    }

    public CandleList(int size) {
        candleList = new ArrayList<>(size);
    }

    @Override
    public Candle get(int i) {
        return candleList.get(i);
    }

    @Override
    public int length() {
        return candleList.size();
    }

    @Override
    public List<Candle> asList() {
        return new ArrayList<>(candleList);
    }

    @Override
    public void add(Candle candle) {
        if (candleList.isEmpty()) {
            candleList.add(candle);
            notifyDataChanged();
            return;
        }

        int lastIndex = candleList.size() - 1;
        Candle lastCandle = candleList.get(lastIndex);
        if (lastCandle.getBeginTimeSecond().longValue() == candle.getBeginTimeSecond().longValue()) {
            candleList.set(lastIndex, candle);
        } else {
            candleList.add(candle);
        }
        notifyDataChanged();
    }


    @Override
    public void addAll(List<? extends Candle> candleList) {
        this.candleList.addAll(candleList);
        notifyDataChanged();
    }

    @Override
    public void clear() {
        candleList.clear();
        notifyDataChanged();
    }


    @Override
    public void addDataChangedListener(DataChangedListener listener) {
        if (dataChangedListenerList == null) {
            dataChangedListenerList = new LinkedList<>();
        }
        dataChangedListenerList.add(listener);
    }

    @Override
    public void removeDataChangedListener(DataChangedListener listener) {
        if (dataChangedListenerList == null) {
            return;
        }
        dataChangedListenerList.remove(listener);
    }

    private void notifyDataChanged() {
        if (dataChangedListenerList == null) {
            return;
        }
        for (DataChangedListener listener : dataChangedListenerList) {
            listener.onChanged();
        }
    }
}
