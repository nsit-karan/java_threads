package com.threads.java;

public class RunnableImpl implements Runnable {

	@Override
	public void run() {
		ThreadHelper.ThreadRunLogic();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Thread thread1 = new Thread(new RunnableImpl());
		Thread thread2 = new Thread(new RunnableImpl());
		
		thread1.start();
		thread2.start();
		
	}


}
