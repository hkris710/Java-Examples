package com.hariinc.pagerank;

import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;

public class PageRank {
	static class Page{
		double rank, tcr; // tcr = totalContriubutionRecieved
		String name;
		ArrayList<Page> linkedPages = new ArrayList<Page>();
		
		public Page(String name) {
			rank = 1.0;
			this.name = name;
		}
		
		public void addPage(Page page) {
			linkedPages.add(page);
		}
		
		public boolean hasPages() {
			return !linkedPages.isEmpty();
		}
		
		// will only be used after checking hasPages, to avoid dividing by 0
		public double getContribution() {
			return rank / linkedPages.size();
		}
		
		public void sendContribution(double contribution) {
			for (Page p : linkedPages) {
				p.updateContribution(contribution);
			}
		}
		
		public void updateContribution(double contribution) {
			tcr += contribution;
		}
		
		public void updateRank() {
			rank = .15 + .85*tcr;
			tcr = 0;
		}
		
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append(name);
			sb.append(", ");
			sb.append(rank);
			return sb.toString();
		}
	}
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		HashMap<String, Page> pageMap = new HashMap<String, Page>();
		while(s.hasNextLine()) {
			String input = s.nextLine();
			if (input.equals(""))
				break;
			String[] splitPairs = input.split("->");
			if(!pageMap.containsKey(splitPairs[0])) {
				pageMap.put(splitPairs[0], new Page(splitPairs[0]));
			}
			if(!pageMap.containsKey(splitPairs[1])) {
				pageMap.put(splitPairs[1], new Page(splitPairs[1]));
				pageMap.get(splitPairs[0]).addPage(pageMap.get(splitPairs[1]));
			} else {
				pageMap.get(splitPairs[0]).addPage(pageMap.get(splitPairs[1]));
			}
		}
		for (int i = 0; i < 10; i++) {
			for (Page p : pageMap.values()) {
				if (p.hasPages()) {
					p.sendContribution(p.getContribution());
				}
			}
			for (Page p : pageMap.values()) {
				p.updateRank();
			}
		}
		
		for (Page p : pageMap.values()) {
			System.out.println(p);
		}
		s.close();
	}
	

}
