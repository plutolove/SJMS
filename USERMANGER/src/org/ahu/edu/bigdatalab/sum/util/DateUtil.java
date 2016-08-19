package org.ahu.edu.bigdatalab.sum.util;

import org.ahu.edu.bigdatalab.sum.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static void main(String[] args) {
        System.out.println(getCurrentDate());
    }

    public static String getCurrentDate() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        return dateFormat.format(date);
    }

    /**
     * 时间格式yyyyMMddHHmmss
     *
     * @return
     */
    public static String getCurrentDateSimple() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        return dateFormat.format(date);
    }

    /**
     * 时间格式yyyy/MM/dd HH:mm:ss
     *
     * @return
     */
    public static String getCurrentDateSpan() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return dateFormat.format(date);
    }

    /**
     * 获取本月第一天时间
     * 时间格式yyyy/MM/dd HH:mm:ss
     *
     * @return
     */
    public static String getThisMonthDate() {
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/");
        return dateFormat.format(now) + "01 00:00:00";
    }

    /**
     * 将字符串转化成日期
     * 时间格式2016-08-04 15:13:56
     *
     * @return
     */
    public static Date transformStringToDate(String dateString) {
        Date date;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            date = simpleDateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            date = new Date();
        }
        return date;
    }


    //1 minute = 60 seconds
    //1 hour = 60 x 60 = 3600
    //1 day = 3600 x 24 = 86400

    /**
     * 将毫秒转化为时间，XX小时XX分钟XX秒
     *
     * @return
     */
    public static String getDateDuration(Date startDate, Date endDate) {
        //milliseconds
        long different = endDate.getTime() - startDate.getTime();

        System.out.println("startDate : " + startDate);
        System.out.println("endDate : " + endDate);
        System.out.println("different : " + different);
        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;

        long elapsedDays = different / daysInMilli;
        different = different % daysInMilli;

        long elapsedHours = different / hoursInMilli;
        different = different % hoursInMilli;

        long elapsedMinutes = different / minutesInMilli;
        different = different % minutesInMilli;

        long elapsedSeconds = different / secondsInMilli;

        return elapsedDays + "天 " + elapsedHours + "小时 " + elapsedMinutes + "分钟 " + elapsedSeconds + "秒";
    }

}
