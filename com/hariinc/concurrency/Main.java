package com.hariinc.concurrency;

public class Main {
	public static void main(String[] args) {
		new Thread(new MyRunnable()).start();
	}
	
	static public class MyRunnable implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			
		}
		
	}
}
