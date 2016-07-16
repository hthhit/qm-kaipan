package com.qm.kaipan.Serializable;

import java.io.IOException;


public interface Serializer {
    public byte[] serialize(Object obj) throws IOException ;

    public <T> T deserialize(byte[] bytes) throws IOException;
}
