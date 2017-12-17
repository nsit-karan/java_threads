package com.threads.java;

/*
 * Original code:
 * - if 2 threads are updating the same variable 'count' in a loop, since they are updating
 *   it parallely, the O/P cannot be predicted
 *   Strange : in this case, the count is always 200. It is never going out of sync.
 *             Maybe later versions of java have fixed this code ?
 *             
 *  Fixed code:
 *  - Use synchronized keyword which ensures that a single thread only can call the method
 *    (this internally uses the inherent lock that all java objects possess) and ensures
 *    predictability/consistency.
 *  - also, with a synchronized method, all threads are guaranteed to see the updated value
 *    no explicit volatile is necessary for the variable
 */
public class SynchronizedApp {

	private int count = 0;
	
	public void doWork() {
		
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 10; i++) {
					count++;
				}
			}
			
		});
		
		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 10; i++) {
					count++;
				}
			}
			
		});
		
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Count is : " + count);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SynchronizedApp app = new SynchronizedApp();
		app.doWork();
	}
	

}
