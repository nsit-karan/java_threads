package com.threads.java;

import java.util.Scanner;

public class ThreadProcessor extends Thread {

	private boolean isRunning = true;
	
	public void run() {
		
		while (isRunning) {
			System.out.println("Hello....");
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void shutdown() {
		isRunning = false;
	}

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		ThreadProcessor processor1 = new ThreadProcessor();
		processor1.start();

		ThreadProcessor processor2 = new ThreadProcessor();
		processor2.start();
		
		ThreadProcessor processor3 = new ThreadProcessor();
		processor3.start();


		System.out.println("Press return to stop .....");
		
		Scanner scanner = new Scanner(System.in);
		scanner.nextLine();
		
		processor1.shutdown();
		processor2.shutdown();
		processor3.shutdown();

		
		scanner.close();
	}

}
