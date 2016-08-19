package org.ahu.edu.bigdatalab.sum.util;

public class TestUtil {
	
	public static void log(Object object){
		if (object != null) {
			System.out.println("-------------------------------------------");
			System.out.println(object.toString());
			System.out.println("-------------------------------------------");
		} else {
			System.out.println("-------------------------------------------");
			System.out.println("null");
			System.out.println("-------------------------------------------");
		}
	}

}
