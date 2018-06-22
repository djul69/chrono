package fr.wildcodeschool.quetes.chrono;


import java.util.Date;


public class CmoileDummy4 implements TimeProvider {
	private boolean testStart = false;
	private long duration;
	private long dateStockage;
	private long start;
	private long pause;


	public boolean isStarted() {

		return testStart;
	}

	/*
	 * @return true if the chrono is currently running
	 */

	public void startStop() {
		if (testStart) {
			testStart = false;
			dateStockage=getSecondsTotalRuntime();
		} else {
			testStart = true;
			Date myStart = new Date();
			start =myStart.getTime()/1000;
			
		}
	}
	/*
	 * Switch that starts the chrono if it was stopped, and stops (pauses actually)
	 * the chrono if it was running
	 */

	public void reset() {
		duration = 0;
		dateStockage = 0;
	}

	/*
	 * resets the amount of time since the chrono start, so that if you run
	 * getSecondsTotalRuntime() immediately after reset() you get OL
	 * 
	 * @param
	 * 
	 * @param b
	 * 
	 * @return
	 */

	public long getSecondsTotalRuntime() {
		if (testStart) {
			Date mypause = new Date();
			pause = mypause.getTime()/1000;
			duration = pause - start + dateStockage;
			}
		return duration;
	}

	/*
	 * @return total amount of seconds since the chrono's start
	 */

	public long getHoursRuntime() {

		long myHour = duration / 3600;
		return myHour;
	
	}

	/*
	 * @return amount of hours in the current hour since the chrono's start
	 */

	public long getMinutesRuntime() {

		long myMin = (duration % 3600) / 60;
		return myMin;	
	}

	/*
	 * @return amount of minutes in the current minute since the chrono's start
	 */

	public long getSecondsRuntime() {

		long mySec = duration % 60;
		return mySec;
	}

	/*
	 * @return amount of seconds in the current minute since the chrono's start
	 */
}
