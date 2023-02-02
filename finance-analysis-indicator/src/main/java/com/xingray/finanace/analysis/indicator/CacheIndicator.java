package com.xingray.finanace.analysis.indicator;


import com.xingray.finanace.analysis.model.data.DataChangedListener;
import com.xingray.finanace.analysis.model.data.DataList;

public abstract class CacheIndicator<T, E> implements Indicator<T, E> {

    private Object[] caches;
    private final Indicator<T, E> indicator;
    private final T defaultValue;

    public CacheIndicator(Indicator<T, E> indicator, T defaultValue) {
        this.indicator = indicator;
        this.defaultValue = defaultValue;
        updateCache();

        getDataList().addDataChangedListener(new DataChangedListener() {
            @Override
            public void onChanged() {
                updateCache();
            }
        });
    }

    protected Indicator<T, E> getIndicator() {
        return indicator;
    }

    @Override
    public DataList<E> getDataList() {
        return indicator.getDataList();
    }

    @Override
    public final T get(int index) {
        Object[] caches = this.caches;
        if (caches == null) {
            return defaultValue;
        }
        T cache = (T) caches[index];

        if (cache != null) {
            return cache;
        }
        cache = calculate(index);
        caches[index] = cache;
        return cache;
    }

    protected abstract T calculate(int index);

    @Override
    public int length() {
        return indicator.length();
    }

    private void updateCache() {
        int length = length();
        if (length > 0) {
            if (caches == null) {
                caches = new Object[length];
            } else {
                if (caches.length == length) {
                    for (int i = 0; i < length; i++) {
                        caches[i] = null;
                    }
                } else {
                    caches = new Object[length];
                }
            }
        } else {
            caches = null;
        }
    }
}
