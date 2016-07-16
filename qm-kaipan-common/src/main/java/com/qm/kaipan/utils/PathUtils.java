package com.qm.kaipan.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;


public class PathUtils {
    private static final String DEFAULT_CHARSET = "UTF-8";

    public static String rebuild(String path) {
        String newPath = path;
        try {
            newPath =  URLDecoder.decode(newPath, DEFAULT_CHARSET);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        newPath = rebuildNoDecode(newPath);

        return newPath;
    }

    public static String rebuildNoDecode(String path) {
        String newPath = path;
        newPath = newPath.replaceAll("\\\\", "/");
        newPath = newPath.replaceAll("//", "/");

        return newPath;
    }
}
