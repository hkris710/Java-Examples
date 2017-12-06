package com.pharmacy.search;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

public class MainTrieRVK2 {
	/**
	 * Holds 1) index to main drug array and 2) map of child nodes by their prefix letter.
	 * @author Hari
	 *
	 */
	static class TrieNode{
		int index;
		HashMap<Character, TrieNode> subTries = new HashMap<Character, TrieNode>();
		
		TrieNode(int index){
			this.index = index;
		}
		
		/**
		 * Build tree structure recursively using each letter in the drug name.
		 * @param name Drug name.
		 * @param index Index into main drug list.
		 */
		public void addNode(String name, int index) {
			if (name.length() == 0)
				return;
			
			// Get the first letter, if a node is present hand over remaining
			// string to the child node. Otherwise, create first.
			char[] letters = name.toCharArray();
			TrieNode node = this;
			for (int ix=0; ix < letters.length; ix++) {
				if (node.subTries.containsKey(letters[ix])) {
					node = node.subTries.get(letters[ix]);
				}
				else {
					TrieNode child = new TrieNode(index);
					node.subTries.put(letters[ix], child);
					node = child;
				}
			}
		}
		
		public int findIndex(String name) {
			char[] letters = name.toCharArray();
			TrieNode node = this;
			for (int ix=0; ix < letters.length && node != null; ix++) {
				node = node.subTries.get(letters[ix]);
			}
			return (node != null) ? node.index : -1;
		}
	}
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		ArrayList<String> allDrugs = new ArrayList<String>();
		TrieNode root = new TrieNode(-1);
		String check = "";
		while(s.hasNextLine()) {
			String name = s.nextLine();
			if (!name.isEmpty()) {
				allDrugs.add(name);
				root.addNode(name, allDrugs.size()-1);
			} else {
				check = s.nextLine();
				break;
			}
		}
		int index = root.findIndex(check);
		if (index == -1) {
			System.out.println("<NONE>");
		} else {
			for (int i = index; i < index + 3 && i < allDrugs.size(); i++) {
				System.out.println(allDrugs.get(i));
			}
		}
		s.close();
	}

}
