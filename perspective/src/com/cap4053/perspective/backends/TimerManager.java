package com.cap4053.perspective.backends;

public class TimerManager {
	   private static TimerManager instance = null;
	   public static boolean shouldRunTimer;
	   
	   protected TimerManager() {}
	   public static TimerManager getInstance() {
	      if(instance == null) {
	         instance = new TimerManager();
	         shouldRunTimer = true;
	      }
	      return instance;
	   }
}