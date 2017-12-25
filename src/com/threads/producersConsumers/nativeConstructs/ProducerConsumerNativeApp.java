package com.threads.producersConsumers.nativeConstructs;

public class ProducerConsumerNativeApp {

	public static void main(String[] args) throws InterruptedException {
		
		final NativeProcessor processor = new NativeProcessor();
		
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					processor.produce();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					processor.consume();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		t1.start();
		t2.start();
		
		t1.join();
		t2.join();
	}

}
