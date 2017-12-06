package com.passage.text.filtering;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		String input = s.nextLine(); 												//scan in input as a single string
		String[] splitInput = input.split("\\|"); 									//split input using | delimiter and load into String array, to be used for logical comparisons
		String[] output = input.split("\\|");											//split input using | delimiter and load into String array, to be used to print output
		String[] modInput = new String[splitInput.length];
		for (int i = 0; i < splitInput.length; i++) { 								//this for loop modifies the passages following the rules specified
			modInput[i] = splitInput[i].toLowerCase(); 								//the case of alphabetic characters should be ignored
			modInput[i] = modInput[i].trim(); 										//leading and trailing whitespace should be ignored
			modInput[i] = modInput[i].replaceAll("\\s{2,}", " ");					//any other block of contiguous whitespace should be treated as a single space
			modInput[i] = modInput[i].replaceAll("[^a-zA-Z0-9\\s]", "");			//non-alphanumeric character should be ignored (read: deleted for the logical comparison), white space should be retained
		}

		for (int i = 1; i < modInput.length; i++) {
			for (int j = 0; j < i; j++) {
				 if (modInput[i].equals(modInput[j])) {
					 if (splitInput[i].length() < splitInput[j].length() ) {
						 output[j] = "";
					 } else {
						 output[i] = "";
					 }
				 } else if (modInput[i].contains(modInput[j])) {
					 output[j] = "";
				 } else if (modInput[j].contains(modInput[i])) {
					 output[i] = "";
				 }
			}
		}
		String out = "";
		for (int i = 0; i < output.length; i++) {
			if (output[i] != "") {
				if (out.length() > 0) {
					out += "|";
				}
				out += output[i];
			}
		}
		System.out.println(out);
		s.close();
	}

}
