package org.digitalsnake.cubz.utils.debug;

import java.util.Calendar;

import org.jungle.game.Context;

public class DebugLogger {

	public static void logInfo(String msg) {
		
		System.out.println("[" + Calendar.getInstance().getTime() + "] INFO: " + msg);
		
	}
	
	public static void logError(String msg) {
		
		System.out.println("[" + Calendar.getInstance().getTime() + "] ERROR: " + msg);
		
	}
	
}