package com.xingray.finanace.analysis.indicator;

import com.xingray.finanace.analysis.model.data.DataChangedListener;
import com.xingray.finanace.analysis.model.data.DataList;

public abstract class ObjectCacheIndicator<T, C extends Cache<T>, E> implements Indicator<T, E> {

    private Object[] caches;
    private final Indicator<C, E> indicator;
    private final T defaultValue;

    public ObjectCacheIndicator(Indicator<C, E> indicator, T defaultValue) {
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

    protected Indicator<C, E> getIndicator() {
        return indicator;
    }

    @Override
    public T get(int index) {
        C cache = getCache(index);
        if (cache == null) {
            return defaultValue;
        }
        return cache.getValue();
    }

    @Override
    public DataList<E> getDataList() {
        return indicator.getDataList();
    }

    @Override
    public int length() {
        return indicator == null ? 0 : indicator.length();
    }

    protected abstract C calculate(int index);

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

    private C getCache(int index) {
        Object[] caches = this.caches;
        if (caches == null) {
            return null;
        }
        //noinspection unchecked
        C cache = (C) caches[index];
        if (cache != null) {
            return cache;
        }
        cache = calculate(index);
        caches[index] = cache;
        return cache;
    }
}
