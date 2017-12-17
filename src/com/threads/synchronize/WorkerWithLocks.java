package com.threads.synchronize;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.threads.java.ThreadHelper;

/*
 * To fix locking the complete object (which was the case in Worker.java), use different locks
 * for the different critical sections.
 * 
 * This will ensure that if thread t1 has locked stageOne, other threads are free to use the other
 * 'synchronized' functions of the class since the whole object is not locked.
 *   - instead, using lock1, only the synchronized block which uses lock1 is blocked for other threads
 *   - the synchronized keyword which uses lock2 at this time is free and any other thread can call that.
 *   
 *  Sequence would be:
 *  - t1 locks lock1
 *  - t2 waits on lock1
 *  
 *  - t1 release lock1 and moves to lock2
 *  - t2 locks lock1
 *  
 *  - t1 releases lock2 and moves to lock1
 *  - t2 releases lock1 and moves to lock2
 *  
 *  So, the above keeps on repeating and no-one gets blocked
 *  
 *  Much much faster than Worker.java which was locking the complete object
 */
public class WorkerWithLocks {

	private Random random = new Random();
	
	private Object lock1 = new Object();
	private Object lock2 = new Object();
	
	private List<Integer> list1 = new ArrayList<Integer>();
	private List<Integer> list2 = new ArrayList<Integer>();
	
	public void stageOne() {
		
		synchronized (lock1) {
			ThreadHelper.sleepThread(1);
			list1.add(random.nextInt(100));
		}
	}
	
	public void stageTwo() {
		synchronized (lock2) {
			ThreadHelper.sleepThread(1);
			list2.add(random.nextInt(100));
		}	}
	
	public void process() {
		for (int i = 0; i < 1000; i++) {
			stageOne();
			stageTwo();
		}
	}
	
	public void work() {
		
		System.out.println("Starting.........");
		
		long start = System.currentTimeMillis();

		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				process();
			}
			
		});
		
		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				process();
			}
			
		});


		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
		}
		
		long end = System.currentTimeMillis();		
		System.out.println("Total time taken :" + (end - start));
		System.out.println("Size of the list1  " + list1.size());
		System.out.println("Size of the list2 " + list2.size());
	}
}
