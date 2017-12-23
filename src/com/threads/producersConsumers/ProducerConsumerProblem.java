package com.threads.producersConsumers;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import com.threads.java.ThreadHelper;

public class ProducerConsumerProblem {

	/*
	 * Once queue is full (i.e, size is 10), put blocks till consumer doesn't read the value
	 * Once queue is empty(i.e, size is 0), consumer waits until value is present in the queue
	 */
	private static BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(10);
	
	private static void producer() throws InterruptedException {
		
		Random random = new Random();
		while (true) {
			queue.put(random.nextInt(100));
		}
	}
	
	private static void consumer() throws InterruptedException {
		
		Random random = new Random();
		
		/*
		 * It doesn't dequeu in every iteration. Instead, only in case the random value is '0'.
		 * This simulates real world scenario where it doesn't consume in every iteration.
		 */
		while (true) {
			ThreadHelper.sleepThread(100);
			
			if (random.nextInt(10) == 0) {
				Integer value = queue.take();
				System.out.println("Taken value from the queue is " + value + ", queue size is " + queue.size());
			}
		}
		
	}

	public static void main(String[] args) throws InterruptedException {
		Thread producerThread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					producer();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		Thread consumerThread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					consumer();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		producerThread.start();
		consumerThread.start();
		
		producerThread.join();
		consumerThread.join();
		
		/*
		 * Goes never-ending since while (true) is put :)
		 */
	}

}
