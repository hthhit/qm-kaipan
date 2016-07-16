package com.qm.kaipan.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class DateUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(DateUtils.class);

    static interface FMT {
        public static final String YYYYMMDD = "yyyyMMdd";
        public static final String YYYY_MM_DD = "yyyy-MM-dd";
        public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
        public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    }

    public static String dateToStr(Date date, String fmt) {
        return new SimpleDateFormat(fmt).format(date);
    }

    public static Date strToDate(String date, String fmt) {
        try {
            return new SimpleDateFormat(fmt).parse(date);
        } catch (Exception e) {

        }
        return null;
    }

    /**
     * 尝试解析时间字符串 ,if failed return null;
     *
     * @param time 2014年5月5日 下午12:00:00
     *             flyfox 330627517@qq.com
     * @return
     * @author wangp
     * @since 2008.12.20
     */
    public static Date parseByAll(String time) {
        Date stamp = null;
        if (time == null || "".equals(time))
            return null;
        Pattern p3 = Pattern.compile("\\b\\d{2}[.-]\\d{1,2}([.-]\\d{1,2}){0,1}\\b");
        if (p3.matcher(time).matches()) {
            time = (time.charAt(0) == '1' || time.charAt(0) == '0' ? "20" : "19") + time;
        }

        stamp = DateUtils.strToDate(time, "yyyy-MM-ddHH:mm:ss");
        if (stamp == null)
            stamp = DateUtils.strToDate(time, "yyyy-MM-dd");
        if (stamp == null)
            stamp = DateUtils.strToDate(time, "yyyy.MM.dd");
        if (stamp == null)
            stamp = DateUtils.strToDate(time, "yyyy-MM");
        if (stamp == null)
            stamp = DateUtils.strToDate(time, "yyyy.MM");
        if (stamp == null)
            stamp = DateUtils.strToDate(time, "yyyy-MM-dd");
        if (stamp == null)
            stamp = DateUtils.strToDate(time, "yy-MM-dd");
        if (stamp == null)
            stamp = DateUtils.strToDate(time, "yyyy.MM.dd");
        if (stamp == null)
            stamp = DateUtils.strToDate(time, "yyyy-MM.dd");
        if (stamp == null)
            stamp = DateUtils.strToDate(time, "yyyy.MM-dd");
        if (stamp == null)
            stamp = DateUtils.strToDate(time, "yyyyMMdd");
        if (stamp == null)
            stamp = DateUtils.strToDate(time, "yyyy年MM月dd日");
        if (stamp == null)
            stamp = DateUtils.strToDate(time, "yyyyMM");
        if (stamp == null)
            stamp = DateUtils.strToDate(time, "yyyy年MM月");
        if (stamp == null)
            stamp = DateUtils.strToDate(time, "yyyy");
        if (stamp == null)
            stamp = DateUtils.strToDate(time, "yyyy年");
        return stamp;
    }
}
