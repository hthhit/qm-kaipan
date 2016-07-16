package com.qm.kaipan.com.kaipan.common.cache.impl;

import com.qm.kaipan.cache.Cache;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MemoryCache implements Cache {
    protected String name;
    protected Map<String, Object> map = new ConcurrentHashMap<String, Object>();

    public MemoryCache() {
    }

    public String name() {
        return name;
    }

    public MemoryCache name(String name) {
        this.name = name;
        return this;
    }

    public MemoryCache add(String key, Object value) {
        map.put(key, value);
        return this;
    }

    @SuppressWarnings("unchecked")
    public <T> T get(String key) {
        return (T) map.get(key);
    }

    public Object remove(String key) {
        return map.remove(key);
    }

    public void clear() {
        map.clear();
    }

    public int size() {
        return map.size();
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> list() {
        if (map.size() == 0) {
            return null;
        }

        List<T> list = new ArrayList<T>();
        for (Object obj : map.values()) {
            list.add((T) obj);
        }
        return list;
    }
}
