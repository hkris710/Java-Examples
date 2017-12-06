package com.hariinc.doublelinkedlist;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoubleLinkedList2 {	
	static class DoubleLinkedList<T extends Comparable<T>> implements Iterable<T>{
		Node<T> head;
		Node<T> tail;
		static class Node<T extends Comparable<T>>{
			T value;
			Node<T> next;
			Node<T> previous;
			
			Node(T value){
				this.value = value;
			}
			
			public T getValue() {
				return this.value;
			}
			
			public void setNext(Node<T> node) {
				this.next = node; 
			}
			
			public void setPrevious(Node<T> node) {
				this.previous = node;
			}
			
			public Node<T> getPrevious(){
				return this.previous;
			}
			
			public Node<T> getNext(){
				return this.next;
			}
						
			public void printForwards() {
				System.out.print(value);
				if (next != null) {
					System.out.print(",");
					next.printForwards();
				}
			}
			
			public void printBackwards() {
				System.out.print(value);
				if (previous != null) {
					System.out.print(",");
					previous.printBackwards();
				}
			}
		}
		
		public DoubleLinkedList(T[] arr){
			for(int i = 0; i < arr.length; i++) {
				if (i == 0) { // create first node, set head and tail equal to first node
					this.head = this.tail = new Node<T>(arr[i]);
				} else {
					Node<T> temp = new Node<T>(arr[i]);
					tail.setNext(temp);
					temp.setPrevious(tail);
					tail = temp;
				}
			}
		}
		
		public void printListForwards() {
			if(head == null)
				return;
			head.printForwards();
		}
		
		public void printListBackwards() {
			if(tail == null)
				return;
			tail.printBackwards();
		}
		
		public void add(T value) {
			Node<T> insert = new Node<T>(value);
			Node<T> search = this.head;
			while(search.getValue().compareTo(insert.getValue()) < 0 && search.getNext() != null) { // find the first non null node where search value is greater than or equal to the insert value, if it exists.
					search = search.getNext();														
			}
			if (search == head) { // if that node is the head, then insert will be prepended to the list and becomes the new head
				insert.setNext(search);
				search.setPrevious(insert);
				this.head = insert;
			} else if(search.getValue().compareTo(insert.getValue()) > -1) { // if that node exists, the insert node is put in the list before that node
				insert.setPrevious(search.getPrevious());
				search.setPrevious(insert);
				insert.getPrevious().setNext(insert);
				insert.setNext(search);
			} else if(search.getValue().compareTo(insert.getValue()) < 0) { // if there is no node where the search value is greater than or equal to the insert value, the insert node is appended at the end of th list and becomes the new tail
				search.setNext(insert);
				insert.setPrevious(search);
				this.tail = insert;
			}
		}
		
		public void delete(T value) {
			Node<T> search = head;
			while(!search.getValue().equals(value) && search.getNext() != null) { // find the first non null node where the search value is equal to the target value
				search = search.getNext();
			}
			if (search == tail && !search.getValue().equals(value)) { // if search reaches tail and the search value is not equal to the target value, list does not contain target value
				return;
			} else if (search == tail && search.getValue().equals(value)) { // if search reaches tail and the search value is equal to the target value at the tail, set the tail equal to the tails previous value, and 
				this.tail = tail.getPrevious();								// then set the tails next to null
				this.tail.setNext(null);
			} else if (search == head && search.getValue().equals(value)) { // if search stops at head and search value is equal to the target value at the head, set the head equal to the heads next value, and 
				this.head = head.getNext();									// then set the heads next to null
				head.setPrevious(null);
			} else if(search.getValue().equals(value)) {					// if search stops at a node, and the search value is equal to the target value,
				search.getPrevious().setNext(search.getNext());				// set the previous nodes next to search's next,
				search.getNext().setPrevious(search.getPrevious());			// set the next nodes previous to search's previous
			}
		}

		
		public Iterator<T> iterator() {
			return new DllIterator(head);
		}
		
		private final class DllIterator implements Iterator<T> {
			Node<T> current;
			
			public DllIterator(Node<T> head) {
				this.current = head;
			}
			
			public boolean hasNext() {
				return current != null;
			}

			public T next() {
				if (!this.hasNext())
					throw new NoSuchElementException();
				T value = current.getValue();
				current = current.getNext();
				return value;
			}
			
		}
		
	}
	
	public static void main(String[] args) {
		String str = "1,2,3,4,5,6,7,8";
		String[] arr = str.split(",");
		DoubleLinkedList<String> dll = new DoubleLinkedList<String>(arr);
		dll.printListForwards();
		System.out.println();
		dll.printListBackwards();
		System.out.println();
		dll.add("7");
		dll.printListForwards();
		System.out.println();
		dll.printListBackwards();
		System.out.println();
		dll.delete("7");
		dll.printListForwards();
		System.out.println();
		dll.printListBackwards();
		System.out.println();
		for (String value : dll) {
			System.out.println(value);
		}
		
	}
}
