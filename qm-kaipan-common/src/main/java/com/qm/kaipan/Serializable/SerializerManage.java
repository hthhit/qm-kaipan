package com.qm.kaipan.Serializable;

import java.util.HashMap;
import java.util.Map;


public class SerializerManage {
    private static final Map<String, Serializer> map = new HashMap<String, Serializer>();

    static {
        map.put("java", new JSerializer());
    }

    public static void add(String key, Serializer serializer) {
        map.put(key, serializer);
    }

    public static Serializer get(String key) {
        return map.get(key);
    }
}
