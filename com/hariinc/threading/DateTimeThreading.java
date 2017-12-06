package com.hariinc.threading;

import java.time.LocalDateTime;

public class DateTimeThreading {
	static class HelloRunnable implements Runnable{
		/*/
		 * Prints "Hello" 10 times with a 1 second pause in between
		 * @see java.lang.Runnable#run()
		 */
		public void run() {
			for (int i = 0; i < 20; i++) {
				System.out.printf("%s: %s%n", LocalDateTime.now().toString(), "Hello");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	static class WorldRunnable implements Runnable{
		/*/
		 * Prints "World" 10 times with a 1 second pause in between
		 * @see java.lang.Runnable#run()
		 */
		public void run() {
			for (int i = 0; i < 20; i++) {
				System.out.printf("%s: %s%n", LocalDateTime.now().toString(), "World");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		Thread h = new Thread(new HelloRunnable());
		Thread t = new Thread(new WorldRunnable());
		h.start();
		t.start();
		try {
			h.join();
			t.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
