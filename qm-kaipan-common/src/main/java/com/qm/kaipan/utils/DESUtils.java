package com.qm.kaipan.utils;

import org.apache.tomcat.util.codec.binary.Base64;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class DESUtils {
    // 指定DES加密解密所用的密钥
    private static Key key;

    /**
     * 加密key为空
     */
    public DESUtils() {
        setkey("flyoffox");
    }

    /**
     * 设置加密key
     *
     * @param keyStr 加密key值
     */
    public DESUtils(String keyStr) {
        setkey(keyStr);
    }

    /**
     * 设置加密的校验码
     *
     * @Date:2012-10-16 下午04:25:13
     * @ClassDescription:
     */
    private void setkey(String keyStr) {
        try {
            DESKeySpec objDesKeySpec = new DESKeySpec(keyStr.getBytes("UTF-8"));
            SecretKeyFactory objKeyFactory = SecretKeyFactory.getInstance("DES");
            key = objKeyFactory.generateSecret(objDesKeySpec);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // 对字符串进行DES加密，返回BASE64编码的加密字符串
    public final String encryptString(String str) {

        byte[] bytes = str.getBytes();
        try {
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encryptStrBytes = cipher.doFinal(bytes);
            return new String(Base64.encodeBase64(encryptStrBytes), "UTF-8");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // 对BASE64编码的加密字符串进行解密，返回解密后的字符串
    public final String decryptString(String str) {
        try {
            byte[] bytes = Base64.decodeBase64(str.getBytes("UTF-8"));
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            bytes = cipher.doFinal(bytes);
            return new String(bytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 测试
     * <p>
     *
     * @param args
     */
    public static void main(String[] args) {
        DESUtils des = new DESUtils("aaaaaaaa");
        String test = des.encryptString("你好123123asdasdasd阿三大声大声道，。，，。，");
        System.out.println(test);
        System.out.println(des.decryptString(test));
    }
}
