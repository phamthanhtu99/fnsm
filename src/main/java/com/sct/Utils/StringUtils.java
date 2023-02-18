package com.sct.Utils;

import java.util.Random;

public class StringUtils {
	
	public static String Otp(){
		Random rand = new Random();  
	    return  String.valueOf(rand.nextInt(10000));
	}
}
