package com.hariinc.binarytree;

public class BinaryTree {
	static class TreeNode{
		int value;
		TreeNode right;
		TreeNode left;
		
		TreeNode(int value){
			this.value = value;
			right = null;
			left = null; 
		}
		
		public int getValue() {
			return value;
		}
		
		public void setLeft(TreeNode node) {
			this.left = node;
		}
		
		public void setRight(TreeNode node) {
			this.right = node;
		}
		
		public void print(int depth) {
			for (int i = 0; i < depth; i++) {
				System.out.print(" ");
			}
			System.out.print(value);
			System.out.println();
			if (left != null) {
				left.print(depth+1);
			}
			if (right != null) {
				right.print(depth+1);
			}
		}
	}
	
	public static void main(String[] args) {
		int[] arr = new int[20];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = i+1;
		}
		TreeNode root = buildTree(arr, 0, arr.length-1);
		root.print(0);
	}
	
	public static TreeNode buildTree(int[] arr, int start, int end) {
		if (start > end)
			return null;
		int rootIndex = (start + end) / 2;
		TreeNode root = new TreeNode(arr[rootIndex]);
		root.setLeft(buildTree(arr, start, rootIndex-1));
		root.setRight(buildTree(arr, rootIndex+1, end));
		return root;
	}

}
