package com.hariinc.substring;

import java.util.ArrayList;
import java.util.Collections;

public class Substring {
	static class Match implements Comparable<Match>{
		ArrayList<Character> match;
		int length;
		
		public Match(char[] c1, char[] c2, int i, int j) {
			match = new ArrayList<Character>();
			while( i < c1.length && j < c2.length) {
				if (c1[i] == c2[j]) {
					match.add(c1[i]);
					i++;
					j++;
				} else {
					break;
				}
			}
			length = match.size();
		}

	
		@Override
		public int compareTo(Match m) {
			return Integer.compare(length, m.length);
		}
		
		public String toString() {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < match.size(); i++) {
				sb.append(match.get(i));
			}
			return sb.toString();
		}
		
		
	}
		public static void main(String[] args) {
			String s1 = "AGRESSIVE";
			String s2 = "PASSIVE";
			System.out.println(findSubstring(s1, s2));
			System.out.println(findSubstring("Salt Lake Tribune", "Fortune teller"));
			System.out.println(findSubstring("ant", "ant"));
			
		}
		
		public static String findSubstring(String str1, String str2) {
			char[] c1 = str1.toCharArray();
			char[] c2 = str2.toCharArray();
			ArrayList<Match> allMatches = new ArrayList<Match>();
			for (int i = 0; i < c1.length; i++) {
				for (int j = 0; j < c2.length; j++) {
					if (c1[i] == c2[j])
						allMatches.add(new Match(c1, c2, i, j));
				}
			}
			Collections.sort(allMatches);
			return allMatches.get((allMatches.size()-1)).toString();
		}
	

}
