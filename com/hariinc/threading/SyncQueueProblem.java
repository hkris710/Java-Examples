package com.hariinc.threading;

import java.util.concurrent.SynchronousQueue;

public class SyncQueueProblem {
	/*/
	 * implementation of Runnable that takes a Synchronous queue. Prints "hello" and puts an object in the queue
	 */
	static class HelloRunnable implements Runnable{
		
		SynchronousQueue<Integer> syncQueue;
		
		public HelloRunnable(SynchronousQueue<Integer> syncQueue) {
			this.syncQueue = syncQueue;
		}
		
		public void run() {
			try {
				for (int i = 0; i < 10; i++) {
					System.out.println("Hello");
					syncQueue.put(i); // blocks execution until syncQueue can be filled
					Thread.sleep(1000);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	/*/
	 * implementation of Runnable that takes a Synchronous queue. Takes an object from queue and prints "World"
	 */
	static class WorldRunnable implements Runnable{
		
		SynchronousQueue<Integer> syncQueue;
		
		public WorldRunnable(SynchronousQueue<Integer> syncQueue) {
			this.syncQueue = syncQueue;
		}
		
		public void run() {
			try {
				for (int i = 0; i < 10; i++) {
					syncQueue.take(); // blocks execution until syncQueue can be emptied
					System.out.println("World");
					Thread.sleep(1000);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		public static void main(String[] args) {
			SynchronousQueue<Integer> queue = new SynchronousQueue<Integer>();
			Thread h = new Thread(new HelloRunnable(queue));
			Thread w = new Thread(new WorldRunnable(queue));
			h.start();
			w.start();
			
		}
	}
	

}
