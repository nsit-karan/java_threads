package com.threads.countdownLatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.threads.java.ThreadHelper;

public class Processor implements Runnable {
	
	private CountDownLatch countDownLatch;
	
	public Processor(CountDownLatch countDownLatch) {
		this.countDownLatch = countDownLatch;
	}

	@Override
	public void run() {
		System.out.println("Thread started ....");
		ThreadHelper.sleepThread(3000);
		
		/*
		 * countDown decrements count by 1
		 */
		countDownLatch.countDown();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		CountDownLatch latch = new CountDownLatch(3);
		ExecutorService service = Executors.newFixedThreadPool(3);
		
		for (int i = 0; i < 3; i++) {
			service.submit(new Processor(latch));
		}
		
		try {
			
			/*
			 * This waits for the count to go to 0 and only then comes out of this calls.
			 * Only after this call does the rest of the code executes
			 */
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Completed");
	}


}
