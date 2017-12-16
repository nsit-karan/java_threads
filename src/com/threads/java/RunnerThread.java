package com.threads.java;

public class RunnerThread extends Thread {

	@Override
	public void run() {		
		ThreadHelper.ThreadRunLogic();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		RunnerThread runner1 = new RunnerThread();
		runner1.start();

		
	}
	
}