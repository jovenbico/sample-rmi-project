package com.bicjo.rmi.jmeter;

public class StopWatch {

	private String name;
	private long startTime;
	private long endTime;

	public StopWatch() {
		this(null);
	}

	public StopWatch(String name) {
		this.name = name;
	}

	public void start() {
		this.startTime = System.currentTimeMillis();
	}

	public void end() {
		this.endTime = System.currentTimeMillis();
	}

	@Override
	public String toString() {
		StringBuffer stringBuf = new StringBuffer();
		if (name != null) {
			stringBuf.append("[").append(name).append("] ");
		}

		stringBuf.append("time-taken ").append(timeTaken());

		return super.toString();
	}

	private long timeTaken() {
		return endTime - startTime;
	}
}
