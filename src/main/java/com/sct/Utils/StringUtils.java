package com.sct.Utils;

import java.util.Random;

public class StringUtils {
	
	public static String Otp(){
		Random rand = new Random();  
	    return  String.valueOf(rand.nextInt(10000));
	}
   
	public static  String getUserId () {
		long number = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
		return String.valueOf(number);
	}
	
}
