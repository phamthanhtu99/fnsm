package com.sct.Utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class SessionUtils {
	public static void setmaxInactival(int time,HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(10*60);
	}

	public static Object getSession(String key,HttpServletRequest request) {
		HttpSession session = request.getSession();
		return session.getAttribute(key);
	}
	
	public static void setSessionValue(String key, Object value,HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute(key, value);
	}
	
	public static void removeSession(String key,HttpServletRequest request ){
		HttpSession session = request.getSession();
		session.removeAttribute(key);
	}
	
    public static void setTimeOut(int time,HttpServletRequest request) {
    	HttpSession session = request.getSession();
		session.setMaxInactiveInterval(time);
	}
}
