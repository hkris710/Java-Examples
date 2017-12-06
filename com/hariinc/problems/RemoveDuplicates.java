package com.hariinc.problems;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Collections;

public class RemoveDuplicates {
	public static void main(String[] args) {
		HashSet<Integer> removeDups = new HashSet<Integer>();
		for (int i = 0; i < args.length; i++) {
			String[] temp = args[i].split(",");
			for (int j = 0; j < temp.length; j++) {
				removeDups.add(Integer.parseInt(temp[j]));
			}
		}
		ArrayList<Integer> toBeSorted = new ArrayList<Integer>(removeDups);
		Collections.sort(toBeSorted, Collections.reverseOrder());
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < toBeSorted.size(); i++) {
			if (i != 0)
				sb.append(",");
			sb.append(toBeSorted.get(i));
		}
		System.out.print(sb);
	}

}
