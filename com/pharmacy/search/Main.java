package com.pharmacy.search;
import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		ArrayList<String> allDrugs = new ArrayList<String>(); // list of all drugs names as Strings
		HashMap<String, Integer> mappings = new HashMap<String, Integer>(); //map of preview strings to location in the list of the first occurrence of a drug name containing that preview string
																			//contains mappings for 1 char, 2 char, and 3 char preview strings
		String check = "";
		while (s.hasNextLine()) {
			String temp = s.nextLine();
			if (!temp.isEmpty()) {
				allDrugs.add(temp);
				// Assume all product names are at least 3 characters long.
				String oneChar = temp.substring(0,1);
				String twoChar = temp.substring(0,2);
				String threeChar = temp.substring(0,3);
				if (!mappings.containsKey(oneChar)) { // if the mappings does not contain the specified substring, put it in the map along with the int index of allDrugs corresponding to the first occurence of that substring in the drug names in allDrugs
					mappings.put(oneChar, allDrugs.size()-1);
				}
				if (!mappings.containsKey(twoChar)) {
					mappings.put(twoChar, allDrugs.size()-1);
				}
				if (!mappings.containsKey(threeChar)) {
					mappings.put(threeChar, allDrugs.size()-1);
				}
			} else {
				check = s.nextLine();
				break;
			}
		}
		if (mappings.containsKey(check)) {
			System.out.println(allDrugs.get(mappings.get(check)));
			if(check.equals(allDrugs.get(mappings.get(check)+1).substring(0,check.length()))) {
				System.out.println(allDrugs.get(mappings.get(check)+1));
			}
		} else {
			System.out.println("<NONE>");
		}
		s.close();
	}

}
