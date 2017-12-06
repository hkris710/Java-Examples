package com.hariinc.problems;


public class ProcessNodesUpdated {
	static class ProcessNode {
		public enum PrintMode {Task, Owner, Both}
		String task;
		String name;
		ProcessNode next;
		
		ProcessNode(String task, String name){
			this.task = task;
			this.name = name;
		}
		
		public ProcessNode getNext() {
			return next;
		}
		
		public void setNext(String task, String name) {
			this.next = new ProcessNode(task, name);
		}
		
		void print(PrintMode mode) {
			if (mode == PrintMode.Task || mode == PrintMode.Both) {
				System.out.print(task);				
			}
			if (mode == PrintMode.Both) {
				System.out.print(",");
			}
			if (mode == PrintMode.Owner || mode == PrintMode.Both) {
				System.out.print(name);				
			}
			if (next != null) {
				System.out.print("->");
				next.print(mode);
			}
			else {
				System.out.println();
			}
		}
	}
	public static void main(String[] args) {
		String processStr = "Peel,John;Chop,Bob;Microwave,Emily;Cook,Jill;Serve,Ashley;Clean,Tom";
		String[] splitList = processStr.split(";");
		ProcessNode head = null;
		ProcessNode tail = null;
		for (String pair : splitList) {
			String[] splitPair = pair.split(",");
			if (head == null) {
				head = tail = new ProcessNode(splitPair[0], splitPair[1]);
			}
			else {
				tail.setNext(splitPair[0], splitPair[1]);
				tail = tail.getNext();
			}
		}
		head.print(ProcessNode.PrintMode.Task);
		head.print(ProcessNode.PrintMode.Owner);
		head.print(ProcessNode.PrintMode.Both);
	}
}
