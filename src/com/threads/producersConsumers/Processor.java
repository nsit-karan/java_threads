package com.threads.producersConsumers;

import java.util.Scanner;

import com.threads.java.ThreadHelper;

public class Processor {

	/*
	 * time1) Calls 'wait' which releases the lock.
	 *        Consumer can go at this point
	 * time4) Producer can continue processing after 'concumer' has called 'notify'
	 */
	public void produce() throws InterruptedException {
		synchronized (this) {
			System.out.println("Producer thread running....");
			wait();
			System.out.println("Producer can continue processing now since consumer has released the lock");
		}
		
	}
	
	/*
	 * Time2) Once producer calls 'wait', control is able to proceed here
	 * Time3) Calls notify() which wakes up 'producer' thread
	 */
	public void consume() throws InterruptedException {
		Scanner scanner = new Scanner(System.in);
		ThreadHelper.sleepThread(2000);
		
		synchronized (this) {
			System.out.println("Waiting for return key to consume data");
			scanner.nextLine();
			
			System.out.println("Return key pressed... lock released");
			notify();
		}
		
		scanner.close();
	}

}
