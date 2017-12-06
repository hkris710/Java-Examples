package com.hariinc.deepcopy;

import java.util.HashMap;
import java.util.Map;

public class DeepCopy {
	static class Node{
		String value, name;
		Node next, random = null;
		int randomIndex;
		
		public Node(String value, String name) {
			this.value = value;
			this.name = name;
		}
		
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public void setNext(Node next) {
			this.next = next;
		}
		
		public void setRandom(Node random) {
			this.random = random;
		}
		
		public Node getNext() {
			return next;
		} 
		
		public Node getRandom() {
			return random;
		}
		
		public String getValue() {
			return value;
		}
		
		public boolean hasNext() {
			return !(next == null);
		}
		
		public boolean hasRandom() {
			return !(random == null);
		}
	}
	
	public static void main(String[] args) {
		Node head = new Node("A", "A"); // create A
		head.setNext(new Node("D", "D")); // create D and set A's next to D
		head.getNext().setNext(new Node("G", "G")); // create G and set D's next to G
		head.setRandom(head.getNext().getNext()); // set A's random to G
		head.getNext().setRandom(head); // set D's random to A
		head.getNext().getNext().setRandom(head.getNext()); // set G's random to D
		printList(head);
		System.out.println();
		Node newHead = deepCopy(head);
		printList(newHead);
	}
	
	public static Node deepCopy(Node oldHead) {
		Map<String, Node> oldNodeValue2NewNode = new HashMap<String, Node>(); // Assume node value is unique.
		Node newHead = null;
		Node temp = oldHead;
		Node temp2 = null;
		while (temp != null) {
			Node node = new Node(temp.getValue(), temp.getValue() + "copy");
			if (newHead == null) {
				newHead = node;
			}
			if (temp2 != null) {
				temp2.setNext(node);
			}
			temp2 = node;
			oldNodeValue2NewNode.put(temp.getValue(), node);
			temp = temp.getNext();
		}
		temp = oldHead;
		temp2 = newHead;
		
		while (temp != null) {
			Node oldRandom = temp.getRandom();
			temp2.setRandom(oldNodeValue2NewNode.get(oldRandom.getValue()));
			temp = temp.getNext();
			temp2 = temp2.getNext();
		}
		return newHead;
	}
	
	public static void printList(Node head) {
		Node cur = head;
		while (cur != null) {
			System.out.print(cur.getRandom().getName());// + "," + head.getRandom().getValue());
			cur = cur.getNext();
			if (cur != null) {
				System.out.print("->");
			}
		}
		System.out.println();
	}	
}
