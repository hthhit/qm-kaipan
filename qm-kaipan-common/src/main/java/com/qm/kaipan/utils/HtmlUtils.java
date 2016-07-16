package com.qm.kaipan.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlUtils {
    private static final String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // 定义script的正则表达式
    private static final String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // 定义style的正则表达式
    private static final String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式

    /**
     * 去除Html标签
     * <p>
     * 2015年2月2日 下午3:40:49 flyfox 330627517@qq.com
     *
     * @param htmlStr
     * @return
     */
    public static String delHTMLTag(String htmlStr) {
        Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
        Matcher m_script = p_script.matcher(htmlStr);
        htmlStr = m_script.replaceAll(""); // 过滤script标签

        Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
        Matcher m_style = p_style.matcher(htmlStr);
        htmlStr = m_style.replaceAll(""); // 过滤style标签

        Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
        Matcher m_html = p_html.matcher(htmlStr);
        htmlStr = m_html.replaceAll(""); // 过滤html标签

        return htmlStr.trim(); // 返回文本字符串
    }

    /**
     * 修改标签特殊字符
     *
     * @param content
     * @return
     * @请使用changeSpecialCode 2015年5月18日 下午2:00:35 flyfox 330627517@qq.com
     */
    public static String changeTag(String content) {
        return changeSpecialCode(content);
    }

    /**
     * 修改标签特殊字符
     * <p>
     * 2015年5月18日 下午2:00:35 flyfox 330627517@qq.com
     *
     * @param content
     * @return
     */
    public static String changeSpecialCode(String content) {
        content = content.replaceAll("&", "&amp;");
        content = content.replaceAll("<", "&lt;");
        content = content.replaceAll(">", "&gt;");
        content = content.replaceAll("\"", "&quot;");
        content = content.replaceAll("'", "&#x27;");
        content = content.replaceAll("/", "&#x2f;");
        return content;
    }

    /**
     * 删除标签特殊字符
     * <p>
     * 2015年6月9日 下午9:30:16 flyfox 330627517@qq.com
     *
     * @param content
     * @return
     */
    public static String delSpecialCode(String content) {
        content = content.replaceAll("&", "");
        content = content.replaceAll("<", "");
        content = content.replaceAll(">", "");
        content = content.replaceAll("\"", "");
        content = content.replaceAll("'", "");
        content = content.replaceAll("/", "");
        return content;
    }
}
