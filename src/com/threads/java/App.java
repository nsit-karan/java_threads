package com.threads.java;


public class App {

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				ThreadHelper.ThreadRunLogic();

			}
		});
		
		thread.start();
	}

}
