package com.threads.synchronize;

public class App {

	public static void main(String[] args) {
		
		System.out.println("Runtime by locking the complete object :");
		new Worker().work();
		
		System.out.println("Now comparing runtime of worker with separate locks for the critical sections");
		new WorkerWithLocks().work();
	}

}
