package com.qm.kaipan.com.kaipan.common.cache.impl;

import com.qm.kaipan.Serializable.Serializer;
import com.qm.kaipan.cache.Cache;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class MemorySerializeCache implements Cache {
    protected Serializer serializer;
    protected String name;
    protected Map<String, byte[]> map = new ConcurrentHashMap<String, byte[]>();

    public MemorySerializeCache(Serializer serializer) {
        this.serializer = serializer;
    }

    public String name() {
        return name;
    }

    public MemorySerializeCache name(String name) {
        this.name = name;
        return this;
    }

    public MemorySerializeCache add(String key, Object value) {
        try {
            map.put(key, serializer.serialize(value));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }

    @SuppressWarnings("unchecked")
    public <T> T get(String key) {
        try {
            return (T) serializer.deserialize(map.get(key));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
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
