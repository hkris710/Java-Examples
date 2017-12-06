package com.hariinc.doublelinkedlist;

public class DoubleLinkedList {
	static class Node<T>{
		T value;
		Node<T> previous;
		Node<T> next;
		
		Node(T value){
			this.value = value;
			this.previous = null;
			this.next = null;
		}
		
		public T getValue() {
			return value;
		}
		
		public void setNext(Node<T> node) {
			this.next = node;
		}
		
		public void setPrevious(Node<T> node) {
			this.previous = node;
		}
		
		public Node<T> getNext(){
			return this.next;
		}
		
		public Node<T> getPrevious(){
			return this.previous;
		}
		
		public void printForward() {
			System.out.print(value);
			if (next != null) {
				System.out.print(",");
				next.printForward();
			}
		}
		
		public void printBackwards() {
			System.out.print(value);
			if (previous != null) {
				System.out.print(",");
				previous.printBackwards();
			}
		}
		
		public static <T> Node<T> buildNode(Node<T> node, T[] arr, int index) {
			Node<T> out = null;
			if (index == 0 && node == null) { // first time 
				Node<T> first = new Node<T>(arr[index]);
				out = buildNode(first, arr, index+1);
				return out;
			} else if (index == arr.length-1) {
				Node<T> last = new Node<T>(arr[index]);
				last.setPrevious(node);
				last.getPrevious().setNext(last);
				return last;
			} else {
				Node<T> next = new Node<T>(arr[index]);
				next.setPrevious(node);
				next.getPrevious().setNext(next);
				out = buildNode(next, arr, index+1);
				return out;
			}
		}
		
		public Node<T> getFirst() {
			Node<T> out = null;
			if (this.previous != null)
				out = previous.getFirst();
			else {
				return this;
			}
			return out;
		}
		
		public Node<T> getLast() {
			Node<T> out = null;
			if (this.next != null)
				out = next.getLast();
			else {
				return this;
			}
			return out;
		}
	}
	
	/*/
	public static void main(String[] args) {
		String str = "1,2,3,4,5,6,7";
		String[] arr = str.split(",");
		Integer[] arrInt = new Integer[arr.length];
		for (int i = 0; i < arr.length; i++) {
			arrInt[i] = Integer.parseInt(arr[i]);
			
		}
		Node<String> last = Node.buildNode(null, arr, 0);
		Node<String> first = last.getFirst();
		first.printForward();
		System.out.println();
		last.printBackwards();
		System.out.println();
		Node<Integer> lastI = Node.buildNode(null, arrInt, 0);
		Node<Integer> firstI = lastI.getFirst();
		firstI.printForward();
		System.out.println();
		lastI.printBackwards();
	}
/*/
	public static void main(String[] args) {
		String str = "1,2,3,4,5,6,7";
		String[] arr = str.split(",");
		Node<String> first = new Node<String>(arr[0]);
		Node<String> next = null;
		for(int i = 1; i < arr.length; i++) {
			if(i == 1) {
				next = new Node<String>(arr[i]);
				first.setNext(next);
				next.setPrevious(first);
			} else {
				Node<String> temp = new Node<String>(arr[i]);
				next.setNext(temp);
				temp.setPrevious(next);
				next = temp;
			}
		}
		Node<String> last = first.getLast();
		first.printForward();
		System.out.println();
		last.printBackwards();
		
	}
}
