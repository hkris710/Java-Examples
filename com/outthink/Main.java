package com.outthink;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		// scan in inputs, assume valid input so no checking done
		Scanner s = new Scanner(System.in);
		String in = s.nextLine();
		String[] input = in.split(" ");
		StringBuffer outString = new StringBuffer();
		// parse integer values for N, p, q from the split input string and store in their own variables
		int varN = Integer.parseInt(input[0]);
		int varP = Integer.parseInt(input[1]);
		int varQ = Integer.parseInt(input[2]);
		// build the output string, starting from i = 1 to N+1, as the output string starts at 1 and is inclusive of N
		for (int i = 1; i < varN+1; i++) {
			// the string to be added to the output string, can be replaced with 'OUT' or 'THINK' or 'OUTTHINK' if the specified condition is met
			String out = String.valueOf(i);	
			boolean isOut= false, isThink = false;
			if (out.contains(input[1]) || out.contains(input[2]))
				isOut = true;
			if ((i % varP == 0 || i % varQ == 0))
			    isThink = true;
			if (isOut && isThink) {
				out = "OUTTHINK";
			}
			else if (isOut) {
				out = "OUT";
			}
			else if (isThink) {
				out = "THINK";
			}
			// prepend the comma to String out before it is added to the output string, except the very first time
			if(outString.length() != 0) {
				outString.append(',');
			}
			outString.append(out);
		}
		System.out.print(outString);
		s.close();
	}
}
