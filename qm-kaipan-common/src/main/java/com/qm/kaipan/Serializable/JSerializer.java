package com.qm.kaipan.Serializable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class JSerializer implements Serializer{
    public byte[] serialize(Object obj) throws IOException {
        ObjectOutputStream oos = null;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(obj);
            return baos.toByteArray();
        } finally {
            if (oos != null)
                try {
                    oos.close();
                } catch (IOException e) {
                }
        }
    }

    @SuppressWarnings("unchecked")
    public <T> T deserialize(byte[] bits) throws IOException {
        if (bits == null || bits.length == 0)
            return null;
        ObjectInputStream ois = null;
        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(bits);
            ois = new ObjectInputStream(bais);
            return (T)ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new IOException("没有找到类型",e);
        } finally {
            try {
                if (ois != null)
                    ois.close();
            } catch (IOException e) {
                throw new IOException("deserialize关闭失败", e);
            }
        }
    }
}
