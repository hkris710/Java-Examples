package com.hariinc.problems;

public class ArrayAndStringReverse {
	public static int[] arrayReverse(int[] arr) {
		for (int i = 0; i < arr.length / 2; i++) {
			int temp = arr[i];
			arr[i] = arr[arr.length-i-1];
			arr[arr.length-i-1] = temp;
		}
		return arr;
	}
	
	public static String stringReverse(String str) {
		char[] arr = str.toCharArray();
		for (int i = 0; i < arr.length / 2; i++) {
			char temp = arr[i];
			arr[i] = arr[arr.length - i - 1];
			arr[arr.length - i - 1] = temp;
		}
		return new String(arr);
	}
	
	public static void main(String[] args) {
		int[] arr = new int[10];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = i;
		}
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
		int[] reverseArr = arrayReverse(arr);
		for (int i = 0; i < reverseArr.length; i++) {
			System.out.print(reverseArr[i] + " ");
		}
		System.out.println();
		String str = "A group of 44 Senate Democrats led by Sens. Kamala Harris, Patty Murray, and Al Franken urged the Justice Department on Thursday to reinstate its interpretation of existing civil rights law to prohibit anti-transgender discrimination.";
		System.out.print(str);
		System.out.println();
		System.out.print(stringReverse(str));
	}

}
