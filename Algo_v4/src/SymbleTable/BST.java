package SymbleTable;

import sun.launcher.resources.launcher;

public class BST<Key extends Comparable<Key>, Value>{
	
	private Node root;
	
	private class Node{
		private Key key;
		private Value val;
		private Node left, right;
		private int N;
		
		public Node(Key key, Value val, int N){
			this.key = key;this.val = val; this.N = N;
		}
	}
	
	public int size(){
		return size(root);
	}
	
	public boolean isEmpty(){
		return root == null;
	}
	
	public int size(Node x){
		if(x == null)	return 0;
		return x.N;
	}
	
	public Value get(Key key){
		return get(root, key);
	}
	
	private Value get(Node x, Key key){
		if(x == null) return null;
		int cmp = key.compareTo(x.key);
		if(cmp < 0) return get(x.left,key);
		else if(cmp > 0) return get(x.right, key);
		else return x.val;		
	}
	
	public void put(Key key, Value val){
		root = put(root, key, val);
	}
	
	private Node put(Node x, Key key, Value val){
		if(x == null) return new Node(key, val, 1);
		int cmp = key.compareTo(x.key);
		if(cmp  < 0) x.left = put(x.left, key, val);
		else if(cmp > 0) x.right = put(x.right, key, val);
		else x.val = val;
		x.N = 1 + size(x.left) + size(x.right);
		return x;
	}
	
	public Node min(){
		return min(root);
	}
	
	private Node min(Node x){
		if(x.left == null)	return null;
		return min(x.left);
	}
	
	public void deleteMin(){
		root = deleteMin(root);
	}
	
	private Node deleteMin(Node x){
		if(x.left == null) return x.right;
		x.left = deleteMin(x.left);
		x.N = 1 + size(x.left) + size(x.right);
		return x;
	}
	
	public void delete(Key key){
		root = delete(root, key);
	}
	
	private Node delete(Node x, Key key){
		if(x == null) return null;
		int cmp = key.compareTo(x.key);
		if(cmp < 0)		 x.left = delete(x.left, key);
		else if(cmp > 0) x.right = delete(x.right, key);
		else{
			if(x.right == null) return x.left;
			Node t = x;
			x = min(t.right);
			x.left = t.left;
			x.right = deleteMin(t.right);
		}
		x.N = 1 + size(x.left) + size(x.right);
		return x;
	}
}