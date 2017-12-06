package com.pharmacy.search;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

public class MainTrie {
	static class Trie{
		String name;
		int index;
		HashMap<String, Trie> subTries = new HashMap<String, Trie>();
		
		
		Trie(){}
		
		Trie(String name, int index){
			this.name = name;
			this.index = index;
		}
		
		public void addNode(String key, int index) {
			subTries.put(key, new Trie(key, index));
		}
		
		public HashMap<String,Trie> getMap() {
			return subTries;
		}
		
		public boolean hasNodes() {
			return subTries.isEmpty();
		}
		
		public String getName() {
			return name;
		}
		
		public int getIndex() {
			return index;
		}
	}
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		ArrayList<String> allDrugs = new ArrayList<String>(); // list of Drugs that the index of the trie references
		Trie base = new Trie(); // Create base, has no value;
		String check = "";
		while(s.hasNextLine()) {
			String temp = s.nextLine();
			if (!temp.isEmpty()) {
				allDrugs.add(temp); // assumes the input contains each drug only once
				char[] triebuilder = temp.toCharArray(); 
				Trie next = base; // trie node to build and access the structure
				for (int i = 0; i < triebuilder.length && i < 3; i++) { // iterate through the triebuilder, but only for the first 3 letters
					String letter = String.valueOf(triebuilder[i]);
					if (!next.getMap().containsKey(letter)) { 	// if the trie node doesn't contain this letter, add the node with the index of the most recent addition to the allDrugs list, and set next equal to this new node
						next.addNode(letter, allDrugs.size()-1);
						next = next.getMap().get(letter);	
					} else										// otherwise, set next equal to the node containing this letter	
						next = next.getMap().get(letter);
				}
				
			} else {
				check = s.nextLine();
				break;
			}
		}
		char[] checker = check.toCharArray(); // input to get preview
		Trie next = base; // trie node to access structure
		Integer index = null;
		for (int i = 0; i < checker.length; i++) { 
			String letter = String.valueOf(checker[i]);
			if (next.getMap().containsKey(letter)) { // if the node's map contains the letter, set next to that node and index to that node's index
				next = next.getMap().get(letter);
				index = next.getIndex();
			} else {
				index = null;
			}
		}
		if (index == null) {
			System.out.println("<NONE>");
		} else {
			for (int i = index; i < index + 3 && i < allDrugs.size(); i++) { // print the 3 drugs from the list starting at the matching index
				System.out.println(allDrugs.get(i));
			}
		}
		s.close();
	}

}
