package com.hariinc.problems;

import java.util.Scanner;

public class MatrixProblem {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		String input = s.nextLine();
		String[] splitInput = input.split(",");
		int m = Integer.parseInt(splitInput[0]);
		int n = Integer.parseInt(splitInput[1]);
		String[][] arr = new String[m][n];
		int i = 2;
		for (int mx = 0; mx < m; mx++) {
			for (int nx = 0; nx < n; nx++) {
					arr[mx][nx] = splitInput[i];
					i++;
			}
		}
		printMatrix(arr);
		System.out.println();
		printMatrix(transposeMatrix(arr));
		s.close();
	}
	/*/
	 * Prints a matrix passed as the parameter
	 */
	public static void printMatrix(String[][] arr) {
		for (int m = 0; m < arr.length; m++) {
			for (int n = 0; n < arr[m].length; n++) {
				System.out.print(arr[m][n] + " ");
			}
			System.out.println();
		}
	}
	/*/
	 * Transposes a matrix passed as the parameter, and returns the transposed matrix
	 */
	public static String[][] transposeMatrix(String[][] arr){
		int mNew = arr[0].length; // assume all rows have the same length
		int nNew = arr.length;
		String[] temp = new String[mNew*nNew];
		int i = 0;
		// store contents of input matrix in a temporary array
		for (int m = 0; m < arr.length; m++) {
			for (int n = 0; n < arr[m].length; n++) {
				temp[i] = arr[m][n];
				i++;
			}
		}
		i = 0;
		// declare and fill new array with the contents of the temporary array
		String[][] out = new String[mNew][nNew];
		for (int m = 0; m < mNew; m++) {
			for (int n = 0; n < nNew; n++) {
				out[m][n] = temp[i];
				i++;
			}
		}
		return out;
	}

}
