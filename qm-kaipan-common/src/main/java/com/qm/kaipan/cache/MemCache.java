package com.qm.kaipan.cache;

import com.qm.kaipan.Serializable.Serializer;
import com.qm.kaipan.com.kaipan.common.cache.impl.MemorySerializeCache;


public class MemCache extends MemorySerializeCache implements Cache {
    public MemCache(Serializer serializer) {
        super(serializer);
    }
}
