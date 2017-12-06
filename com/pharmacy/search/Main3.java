package com.pharmacy.search;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

/*/
public class Main {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		ArrayList<String> allDrugs = new ArrayList<String>(); // already sorted as input is sorted
		HashMap<String, ArrayList<String>> previewMap = new HashMap<String, ArrayList<String>>();
		String prefix = "";
		String check = "";
		while (s.hasNextLine()) {
			String temp = s.nextLine();
			if (!temp.isEmpty()) {
				allDrugs.add(temp);
				if (prefix.equals(temp.substring(0,3))) {
					previewMap.get(prefix).add(temp);
				} else {
					prefix = temp.substring(0,3);
					previewMap.put(prefix, new ArrayList<String>());
					previewMap.get(prefix).add(temp);
				}
			} else {
				check = s.nextLine();
				break;
			}
		}
		if (previewMap.containsKey(check)){
			for (int i = 0; i < previewMap.get(check).size() && i < 2; i++) {
				System.out.println(previewMap.get(check).get(i));
			}
		} else {
			System.out.println("<NONE>");
		}
	}

}
/*/
/*/
public class Main {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		ArrayList<String> allDrugs = new ArrayList<String>();
		String check = "";
		int counter = 0;
		while (s.hasNextLine()) {
			String temp = s.nextLine();
			if (!temp.isEmpty()) {
				allDrugs.add(temp);
			} else {
				check = s.nextLine();
				break;
			}
		}
		for (int i = 0; i < allDrugs.size(); i++) {
			if (check.equals(allDrugs.get(i).substring(0,check.length()))) {
				System.out.println(allDrugs.get(i));
				counter++;
			}
			if (counter == 2) {
				break;
			}
		}
		if (counter == 0) {
			System.out.println("<NONE>");
		}
		
	}
}
/*/

public class Main3 {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		ArrayList<String> allDrugs = new ArrayList<String>();
		HashMap<String, ArrayList<String>> previewOne = new HashMap<String, ArrayList<String>>();
		HashMap<String, ArrayList<String>> previewTwo = new HashMap<String, ArrayList<String>>();
		HashMap<String, ArrayList<String>> previewThree = new HashMap<String, ArrayList<String>>();
		String check = "";
		while (s.hasNextLine()) {
			String temp = s.nextLine(); 
			if (!temp.isEmpty()) {
				allDrugs.add(temp);
				if (!previewOne.containsKey(temp.substring(0,1))){
					previewOne.put(temp.substring(0,1), new ArrayList<String>());
					previewOne.get(temp.substring(0,1)).add(temp);
				} else {
					previewOne.get(temp.substring(0,1)).add(temp);
				}
				if (!previewTwo.containsKey(temp.substring(0,2))){
					previewTwo.put(temp.substring(0,2), new ArrayList<String>());
					previewTwo.get(temp.substring(0,2)).add(temp);
				} else {
					previewTwo.get(temp.substring(0,2)).add(temp);
				}
				if (!previewThree.containsKey(temp.substring(0,3))){
					previewThree.put(temp.substring(0,3), new ArrayList<String>());
					previewThree.get(temp.substring(0,3)).add(temp);
				} else {
					previewThree.get(temp.substring(0,3)).add(temp);
				}
			}
			else {
				check = s.nextLine();
				break;
			}
		}
		if (previewOne.containsKey(check)) {
			for (int i = 0; i < previewOne.get(check).size(); i++) {
				System.out.println(previewOne.get(check).get(i));
			}
		} else if (previewTwo.containsKey(check)) {
			for (int i = 0; i < previewTwo.get(check).size(); i++) {
				System.out.println(previewTwo.get(check).get(i));
			}
		} else if (previewThree.containsKey(check)) {
			for (int i = 0; i < previewThree.get(check).size(); i++) {
				System.out.println(previewThree.get(check).get(i));
			}
		} else {
			System.out.println("<NONE>");
		}
		s.close();
	}
}