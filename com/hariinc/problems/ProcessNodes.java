package com.hariinc.problems;


public class ProcessNodes {
	static class ProcessNode{
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
		
		public void printTasks() {
			if (next != null) {
				System.out.print(task+"->");
				next.printTasks();
			} else
				System.out.print(task);
		}
		
		public void printNames() {
			if (next != null) {
				System.out.print(name+"->");
				next.printNames();
			} else
				System.out.print(name);
		}
		
		public void printBoth() {
			if (next != null) {
				System.out.print(name+","+task+"->");
				next.printBoth();
			} else
				System.out.print(name+","+task);
		}
		
	}
	public static void main(String[] args) {
		String[] splitList = args[0].split(";");
		ProcessNode head = null;
		ProcessNode next = null;
		for (int i = 0; i < splitList.length; i++) {
			String[] splitPair = splitList[i].split(",");
			if (i == 0) {
				head = new ProcessNode(splitPair[0], splitPair[1]);
			}
			else if (i == 1) {
				head.setNext(splitPair[0], splitPair[1]);
				next = head.getNext();
			}
			else {
				next.setNext(splitPair[0], splitPair[1]);
				next = next.getNext();
			}
		}
		System.out.println("");
		head.printTasks();
		System.out.println("");
		head.printNames();
		System.out.println("");
		head.printBoth();
	}
}
