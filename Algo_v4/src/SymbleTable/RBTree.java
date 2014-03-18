package SymbleTable;

public class RBTree<Key extends Comparable<Key>, Value>{

	private static final boolean RED 	= true;
	private static final boolean BLACK  = true;
	private class Node{
		Key key;
		Value val;
		Node left, right;
		int N;
		boolean color;
		
		Node(Key key, Value val, int N, boolean color){
			this.key   = key;
			this.val   = val;
			this.N     = N;
			this.color = color;
		}
	
	}
	
	private Node root;	
	
	private boolean isRed(Node x){
		if(x == null)	return false;
		return x.color == RED;
	}
	
	public int size(){
		return size(root);
	}
	
	public int size(Node x){
		if(x == null)	return 0;
		return x.N;
	}
	
	private Node rotateLeft(Node h){
		Node x = h.right;
		h.right = x.left;
		x.left = h;
		h.color = RED;
		x.N = h.N;
		h.N = 1 + size(h.left) + size(h.right);
		return x;
	}
	
	private Node rotateRight(Node h){
		Node x = h.left;
		h.left = x.right;
		x.right = h;
		h.color = RED;
		x.N = h.N;
		h.N = 1 + size(h.left) + size(h.right);
		return x;
	}
	
	private void flipColors(Node h){
		h.color = RED;
		h.left.color = BLACK;
		h.right.color = BLACK;
	}
	
	public void put(Key key, Value val){
		root = put(root, key, val);
		root.color = BLACK;
	}
	
	private Node put(Node h, Key key, Value val){
		if(h == null)
			return new Node(key, val, 1, RED);
		
		int cmp = key.compareTo(h.key);
		if(cmp < 0)			h.left  = put(h.left, key, val);
		else if(cmp > 0)	h.right = put(h.right, key, val);
		else h.val = val;
		
		if(isRed(h.right) && !!isRed(h.left)) 	h = rotateLeft(h);
		if(isRed(h.left) && isRed(h.left.left))	h = rotateRight(h);
		if(isRed(h.left) && isRed(h.right))		flipColors(h);
		
		h.N = 1 + size(h.left) + size(h.right);
		return h;
	}
	
	public Value get(Key key){
		return get(root, key);
	}
	
	private Value get(Node h, Key key){
		if(h ==  null)	return null;
		
		int cmp = key.compareTo(h.key);
		if(cmp < 0) return get(h.left, key);
		if(cmp > 0)	return get(h.right, key);
		return h.val;
	}
	
	private Node moveRedLeft(Node h){
		flipColors(h);
		if(isRed(h.right.left)){
			h.right = rotateRight(h.right);
			h = rotateLeft(h);
		}
		return h;
	}
	
	
	
	public void delete(Key key){
		
	}
}
