package org.ahu.edu.bigdatalab.scm.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	public static void main(String[] args){
		System.out.println(getCurrentDate());
	}
	
	public static String getCurrentDate(){
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
		return dateFormat.format(date);
	}

	/**
	 * 时间格式yyyyMMddHHmmss
	 * @return
     */
	public static String getCurrentDateSimple(){
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		return dateFormat.format(date);
	}

	/**
	 * 时间格式yyyy/MM/dd HH:mm:ss
	 * @return
     */
	public static String getCurrentDateSpan(){
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		return dateFormat.format(date);
	}

    /**
     *获取本月第一天时间
     * 时间格式yyyy/MM/dd HH:mm:ss
     * @return
     */
    public static String getThisMonthDate(){
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/");
        return dateFormat.format(now) + "01 00:00:00";
    }

}
