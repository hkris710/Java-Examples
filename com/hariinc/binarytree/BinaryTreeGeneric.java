package com.hariinc.binarytree;

import java.util.Iterator;
import java.util.ArrayList;

public class BinaryTreeGeneric {
	static class TreeNode<T extends Comparable<T>> implements Iterable<T> {
		T value;
		TreeNode<T> right;
		TreeNode<T> left;
		
		TreeNode(T value){
			this.value = value;
			right = null;
			left = null; 
		}
		
		public T getValue() {
			return value;
		}
		
		public void setLeft(TreeNode<T> node) {
			this.left = node;
		}
		
		public void setRight(TreeNode<T> node) {
			this.right = node;
		}
		
		public void print(int depth) {
			System.out.printf("%d: %s%n", depth, value.toString());
			if (left != null) {
				left.print(depth+1);
			}
			if (right != null) {
				right.print(depth+1);
			}
		}
		
		/**
		 * Prints all the values in ascending order i.e. left most node to right most node.
		 */
		public void printMinToMax() {
			if(left != null) {
				left.printMinToMax();
			}
			System.out.print(value.toString()+" ");
			if(right != null) {
				right.printMinToMax();
			}
		}
		
		public void printMaxtoMin() {
			if (right != null) {
				right.printMaxtoMin();
			}
			System.out.print(value.toString()+ " ");
			if (left != null) {
				left.printMaxtoMin();
			}
		}
		
		public ArrayList<T> fillArrayList(ArrayList<T> arr){
			if(left != null) {
				left.fillArrayList(arr);
			}
			arr.add(value);
			if(right != null) {
				right.fillArrayList(arr);
			}
			return arr;
		}
		
		public T search(T target) {
			int result = target.compareTo(value);
			if ( result == 0) {
				return value;
			} 
			else if (result < 0 && left != null) {
				return left.search(target);
			}
			else if (result > 0 && right != null) {
				return right.search(target);
			} 
			return null;
		}

		public static <T extends Comparable<T>> TreeNode<T> buildTree(T[] arr, int start, int end) {
			if (start > end)
				return null;
			int rootIndex = (start + end) / 2;
			TreeNode<T> root = new TreeNode<T>(arr[rootIndex]);
			root.setLeft(buildTree(arr, start, rootIndex-1));
			root.setRight(buildTree(arr, rootIndex+1, end));
			return root;
		}

		@Override
		public Iterator<T> iterator() {
			ArrayList<T> arr = new ArrayList<T>();
			this.fillArrayList(arr);
			return arr.iterator();
		}
		
	}
	
	public static void main(String[] args) {
		Integer[] arr = new Integer[20];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = i+1;
		}
		TreeNode<Integer> root = TreeNode.buildTree(arr, 0, arr.length-1);
		root.print(0);
		
		System.out.println();
		
		Character[] carr = new Character[9];
		for (int i = 0; i < carr.length; i++) {
			carr[i] = (char) ('A'+i);
		}
		TreeNode<Character> croot = TreeNode.buildTree(carr, 0, carr.length-1);
		croot.print(0);
		System.out.println();
		System.out.print(root.search(2));
		System.out.println();
		root.printMinToMax();
		System.out.println();
		root.printMaxtoMin();
		System.out.println();
		for(Integer i : root) {
			System.out.print(i);
		}
		System.out.println();
		for(Character c : croot) {
			System.out.print(c);
		}
	}
}
