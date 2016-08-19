package org.ahu.edu.bigdatalab.scm.util;

import java.util.Random;

public class TextUtil {

	/**
	 * 剪短文本长度
	 * @param text
	 * @param lenth
	 * @return
	 */
	public static String cutText(String text ,int lenth) {
		if(text == null ) {
			return null;
		}
		if(text.length() > lenth){
			text = text.substring(0, lenth)+"...";
		}
		return text;
	}


	/**
	 * 获取指定长度的字符串
	 * @param length
	 * @return
	 */
	public static String getRandomString(int length) { //length表示生成字符串的长度
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	/**
	 * 判断字符串是否是数字
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str){
		if(str.matches("\\d*")){
			return true;
		}else{
			return false;
		}
	}

}
