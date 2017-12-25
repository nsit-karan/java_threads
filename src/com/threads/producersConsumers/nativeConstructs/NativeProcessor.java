package com.threads.producersConsumers.nativeConstructs;

import java.util.LinkedList;
import java.util.Random;

import com.threads.java.ThreadHelper;

public class NativeProcessor {
	
	/*
	 * Making the linked list size limit to 10 and no more.
	 */
	private LinkedList<Integer> list = new LinkedList<Integer>();
	private static final int LIMIT = 10;
	
	/*
	 * Can also use 'this'. But, going with explicit object to
	 * use as a lock
	 */
	private Object lock = new Object();

	public void produce() throws InterruptedException {
		
		int value = 0;
		
		while (true) {
			
			synchronized (lock) {
				
				/*
				 * Since we want to limit the list size, we invoke
				 * lock.wait so that the consumer removes an item
				 * and wakes this up via call to notify
				 * 
				 * Note : could have used a IF itself since
				 * wait blocks this thread execution there (in
				 * true sense, thread goes to 'WAITING" state.
				 * 
				 * Just to make sure that size goes back to within limits,
				 * we use the while loop
				 */
				while (list.size() == LIMIT) {
					lock.wait();
				}
				list.add(value++);
				
				/*
				 * Wake up the consumer in case it was stuck on
				 * the empty list condition
				 */
				lock.notify();
			}
		}
		
	}
	
	public void consume() throws InterruptedException {
		
		Random random = new Random();
		
		while (true) {
			
			synchronized (lock) {
				
				while (list.isEmpty()) {
					lock.wait();
				}
				System.out.print("List size is : " + list.size());
				int value = list.removeFirst();
				System.out.println(", Item removed is : " + value);
				
				lock.notify();
			}
			
			ThreadHelper.sleepThread(random.nextInt(1000));
		}
	}
}
