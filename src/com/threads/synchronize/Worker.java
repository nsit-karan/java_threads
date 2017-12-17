package com.threads.synchronize;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.threads.java.ThreadHelper;

/*
 * Without synchronized in place, list1 and list2 size cannot be predicted since 2 threads are updated in parallel.
 * Plus, it throws 'arrayIndexBound Exception'
 * 
 * After putting the 'scnchronized' keyword, the O/P is predictable (100%) and is the same always
 * But, the time taken to complete the program increases
 * 
 * Problem with the code:
 * - stageOne and stageTwo are independent of each other and can be run in parallel
 * - but, since process calls stageOne and stageTwo in sequence and if stageOne is locked by thread T1,
 *   thread T2 gets blocked on T1 on stageOne itself and no one can execute stageTwo
 * - hence, synchronized slows down the overall runtime here since stageOne and stageTwo are forced to
 *   go in sequnce even though both can be run in parallel
 */
public class Worker {

	private Random random = new Random();
	
	private List<Integer> list1 = new ArrayList<Integer>();
	private List<Integer> list2 = new ArrayList<Integer>();
	
	public synchronized void stageOne() {
		ThreadHelper.sleepThread(1);
		list1.add(random.nextInt(100));
	}
	
	public synchronized void stageTwo() {
		ThreadHelper.sleepThread(1);
		list2.add(random.nextInt(100));
	}
	
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
