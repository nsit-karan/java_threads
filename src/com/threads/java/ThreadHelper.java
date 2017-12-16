package com.threads.java;

public class ThreadHelper {
	
	public static void ThreadRunLogic() {
		for (int i = 0; i < 10; i++) {
			System.out.println("Runner instance " + i);
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


}
