package org.cubz.utils;

import java.util.Calendar;

public interface DebugLogger {
	
	public static void debugMessage(String msg) {
		
		System.out.println("[" + Calendar.getInstance().getTime() + "] INFO: " + msg);
		
	}
	
	public static void debugError(String msg) {
		
		System.err.println("[" + Calendar.getInstance().getTime() + "] ERROR: " + msg);
		
	}
	
}