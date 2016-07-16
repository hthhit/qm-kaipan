package com.qm.kaipan.cache;

import com.qm.kaipan.Serializable.SerializerManage;
import com.qm.kaipan.com.kaipan.common.cache.impl.MemorySerializeCache;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by yahets on 2016/7/9.
 */
public class CacheManager {
    private static ConcurrentHashMap<String, Cache> cacheManager = new ConcurrentHashMap<String, Cache>();
    static ICacheManager _CreateCache;

    protected CacheManager() {
    }

    static {
        _CreateCache = new ICacheManager() {
            public Cache getCache() {
                return new MemorySerializeCache(SerializerManage.get("java"));
            }
        };
    }

    public static void setCache(ICacheManager thisCache) {
        _CreateCache = thisCache;
    }

    public static Cache get(String name) {
        Cache cache = cacheManager.get(name);
        if (cache == null) {
            synchronized (cacheManager) {
                cache = cacheManager.get(name);
                if (cache == null) {
                    cache = _CreateCache.getCache();
                    cache.name(name);
                    cacheManager.put(name, cache);
                }
            }
        }
        return cache;
    }

    public static int size() {
        return cacheManager.size();
    }

    public static Collection<Cache> values() {
        return cacheManager.values();
    }

    public static Set<String> keys() {
        return cacheManager.keySet();
    }
}
