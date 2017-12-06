package com.streaming.anlytics;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
	static class DateResult implements Comparable<DateResult> {
		String dateStr; //no need to create a date object for this problem
		int totalSold;
		HashSet<String> allItems = new HashSet<String>();
		
		DateResult(String dateStr, String totalSold, String item){ //constructor used first time DateResult object is created for that date
			this.dateStr = dateStr;
			this.totalSold = Integer.parseInt(totalSold);
			allItems.add(item);
		}
		
		public void addItem(String totalSold, String item) { //method used to add an item to an existing DateResult object
			this.totalSold += Integer.parseInt(totalSold);
			allItems.add(item);
		}
		
		public String toString() { // how the DateResult object will get printed when called
			StringBuffer out = new StringBuffer();
			out.append(dateStr);
			out.append(",");
			out.append(Integer.toString(totalSold));
			out.append(",");
			out.append(String.format("%.2f",((double) totalSold) / allItems.size()));
			out.append(",");
			out.append(Integer.toString(allItems.size()));
			return out.toString();
		}

		@Override
		public int compareTo(DateResult o) {
			return this.dateStr.compareTo(o.dateStr);
		}
	}
	

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		HashMap<String, DateResult> allDates = new HashMap<String, DateResult>(); // Map to store each days DateResult object mapped to that days date string
		ArrayList<DateResult> results = new ArrayList<DateResult>(); // an arraylist containing the date strings of all the days, to be sorted to print the output in sorted order
		while(s.hasNextLine()) {
			String input = s.nextLine();
			if (input.equals("")) {
				break;
			}
			String[] splitInput = input.split(",");
			String date = splitInput[0];
			String quantity = splitInput[1];
			String productId = splitInput[2];
			if (allDates.containsKey(date)) {
				allDates.get(date).addItem(quantity, productId);
			} else {
				DateResult result = new DateResult(date, quantity, productId);
				allDates.put(date, result);
				results.add(result);
			}	
		}
		Collections.sort(results); //sort the arraylist to access and print the mapped DateResults object in the correct order
		for(int i = 0; i < results.size(); i++ ) {
			System.out.println(results.get(i));
		}
		s.close();
	}
}
