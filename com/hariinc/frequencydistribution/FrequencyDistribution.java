package com.hariinc.frequencydistribution;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class FrequencyDistribution {
	static class Range{
		int count, lower, upper;
		
		public Range(int lower, int upper) {
			this.lower = lower;
			this.upper = upper;
		}
		
		public void count(int i) {
			if (i >= lower && i <= upper)
				count++;
		}
		
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append(lower);
			sb.append(" - ");
			sb.append(upper);
			sb.append("\t");
			sb.append(count);
			return sb.toString();
		}
	}
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		String input = s.nextLine();
		String[] splitInput = input.split(",");
		// make a new ArrayList of Integers and fill it from the splitInput String array
		ArrayList<Integer> intList = new ArrayList<Integer>();
		for(int i = 0; i < splitInput.length; i++) {
			intList.add(Integer.parseInt(splitInput[i]));
		}
		// Sort list of Integers
		Collections.sort(intList);
		// Calculate the number of ranges, by taking the square root of the total number of numbers
		int numOfRanges = (int) Math.sqrt(intList.size());
		// Calculate the width of the ranges, by dividing the difference of the maximum of the numbers and the minimum of the numbers by the number of ranges
		int rangeWidth = (intList.get(intList.size()-1) - intList.get(0)) / numOfRanges;
		int lower = intList.get(0);
		int upper = lower + rangeWidth;
		ArrayList<Range> allRanges = new ArrayList<Range>();
		for (int i = 0; i < numOfRanges; i++) {
			allRanges.add(new Range(lower, upper));
			lower = upper + 1;
			upper = lower + rangeWidth;
		}
		for (int i = 0; i < intList.size(); i++) {
			for (Range r : allRanges) {
				r.count(intList.get(i));
			}
		}
		for (Range r : allRanges) {
			System.out.println(r);
		}
		s.close();
	}

}
