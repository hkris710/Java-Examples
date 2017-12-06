package com.hariinc.threading;

import java.time.LocalDateTime;
import java.util.Random;

public class RandomThreading {
	static class HelloRandomRunnable implements Runnable{
		/*/
		 * Prints "Hello World" 5 times with a 1-3 second pause in between
		 * @see java.lang.Runnable#run()
		 */
		public void run() {
			Random rand = new Random();
			for (int i = 0; i < 5; i++) {
				int timeSleep = rand.nextInt(3) + 1;
				System.out.printf("%s: %s%n", LocalDateTime.now().toString(), "Hello World");
				try {
					Thread.sleep(timeSleep*1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	public static void main(String[] args) {
		Thread t = new Thread(new HelloRandomRunnable());
		t.start();
		try {
			t.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
