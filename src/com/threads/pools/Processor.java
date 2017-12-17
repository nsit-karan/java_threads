package com.threads.pools;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.threads.java.ThreadHelper;

/*
 * - basic thread-pool which starts max 2 threads in parallel
 * - awaitTermination puts a overall timeout for all the threads to finish
 */
public class Processor implements Runnable {

	private int id;
		
	public Processor(int id) {
		this.id = id;
	}
	
	@Override
	public void run() {
		System.out.println("Starting id : " + id);
		ThreadHelper.sleepThread(5000);
		System.out.println("Ending id : " + id);
	}
	
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		
		for (int i = 0; i < 5; i++) {
			executorService.submit(new Processor(i));
		}
		
		executorService.shutdown();
		System.out.println("All tasks submitted. Now waiting to complete");
		
		/*
		 * Specify the total timeout to wait for all the tasks to finish
		 */
		try {
			executorService.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
		}
	}



}
