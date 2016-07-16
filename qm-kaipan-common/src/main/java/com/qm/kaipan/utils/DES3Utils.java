package com.qm.kaipan.utils;

import org.apache.tomcat.util.codec.binary.Base64;

import java.security.Key;
import java.security.MessageDigest;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
public class DES3Utils {
    // 指定DES加密解密所用的密钥
    private static Key key;

    // 初始化向量
    private static byte[] iv = {1,2,3,4,5,6,7,8};

    /**
     * 加密key为空
     */
    public DES3Utils() {
        setkey("flyoffox");
    }

    /**
     * 设置加密key
     *
     * @param keyStr
     *            加密key值
     */
    public DES3Utils(String keyStr) {
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
            final MessageDigest md = MessageDigest.getInstance("md5");
            final byte[] digestOfPassword = md.digest(keyStr
                    .getBytes("utf-8"));
            final byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
            for (int j = 0, k = 16; j < 8;) {
                keyBytes[k++] = keyBytes[j++];
            }

            key = new SecretKeySpec(keyBytes, "DESede");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // 对字符串进行DES加密，返回BASE64编码的加密字符串
    public final String encryptString(String str) {
        byte[] bytes = str.getBytes();
        try {
            Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
            //cipher.init(Cipher.ENCRYPT_MODE, key);
            IvParameterSpec zeroIv = new IvParameterSpec(iv);
            cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);
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
            Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");

            IvParameterSpec zeroIv = new IvParameterSpec(iv);
            cipher.init(Cipher.DECRYPT_MODE, key, zeroIv);
            bytes = cipher.doFinal(bytes);
            return new String(bytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 测试
     *
     * @param args
     */
    public static void main(String[] args) {
        DES3Utils des = new DES3Utils("2b7e151628aed2a6abf71589");
        String test = des.encryptString("你好123123asdasdasd阿三大声大声道，。，，。，");
        System.out.println(test);
        System.out.println(des.decryptString(test));
    }
}
