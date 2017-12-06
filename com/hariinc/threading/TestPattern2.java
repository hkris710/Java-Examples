package com.hariinc.threading;

import java.time.LocalDateTime;
import java.util.Random;

public class TestPattern2 implements Runnable {
	public void run(){
		Random rand = new Random();
		for (int i = 0; i < 5; i++) {
			int msecs = (rand.nextInt(3) + 1)*1000;
			System.out.printf("%s: %s%n", Thread.currentThread().getId(), LocalDateTime.now().toString());	
			try {
				Thread.sleep(msecs);
			
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		Runnable r = new TestPattern2();
		Thread t1 = new Thread(r);
		Thread t2 = new Thread(r);
		Thread t3 = new Thread(r);
		Thread t4 = new Thread(r);
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		try {
			t1.join();
			t2.join();
			t3.join();
			t4.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
