package com.qm.kaipan.utils;

import java.util.UUID;


public class UuidUtils {
    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();// 标准UUID
    }

    public static String getUUID2() {
        String str = getUUID();
        // 去掉"-"符号
        String temp = str.substring(0, 8) + str.substring(9, 13)
                + str.substring(14, 18) + str.substring(19, 23)
                + str.substring(24);
        return temp;
    }

    // 获得指定数量的UUID
    public static String[] getUUID2(int number) {
        if (number < 1) {
            return null;
        }
        String[] ss = new String[number];
        for (int i = 0; i < number; i++) {
            ss[i] = getUUID2();
        }
        return ss;
    }
}
