package fr.wildcodeschool.quetes.chrono;

public interface TimeProvider {
	/**
	 * Switch that starts the chrono if it was stopped, and stops (pauses actually)
	 * the chrono if it was running
	 */
	void startStop();

	/**
	 * resets the amount of time since the chrono start, so that if you run
	 * getSecondsTotalRuntime() immediately after reset() you get OL
	 */
	void reset();

	/**
	 * @return true if the chrono is currently running
	 */
	boolean isStarted();

	/**
	 * @return total amount of seconds since the chrono's start
	 */
	long getSecondsTotalRuntime();

	/**
	 * @return amount of hours since the chrono's start
	 */
	long getHoursRuntime();

	/**
	 * @return amount of minutes in the current hour
	 */
	long getMinutesRuntime();

	/**
	 * @return amount of seconds in the current minute
	 */
	long getSecondsRuntime();
}