package SymbleTable;

public class RBTree<Key extends Comparable<Key>, Value>{

	private static final boolean RED 	= true;
	private static final boolean BLACK  = false;
	private class Node{
		Key key;
		Value val;
		Node left, right;
		int N;
		boolean color;
		
		Node(Key key, Value val, int N, boolean color){
			this.key   = key;
			this.val   = val;
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
	
	public boolean isEmpty(){
		return root == null;
	}
	
	public int size(Node x){
		if(x == null)	return 0;
		return x.N;
	}
	
	public Key min(){
		return min(root).key;
	}
	
	private Node min(Node h){
		if(h.left == null) return h;
		return min(h.left);
	}
	
	public Key max(){
		return max(root).key;
	}
	
	private Node max(Node h){
		if(h.right == null) return h;
		return max(h.right);
	}
	
	//the largest one which is not larger than the main key
	public Key floor(Key key){
		Node h = floor(root, key);
		if(h == null) return null;
		return h.key;
	}
	
	private Node floor(Node h, Key key){
		if(h == null)	return null;
		int cmp = key.compareTo(h.key);
		if(cmp == 0) return h;
		if(cmp < 0) return floor(h.left, key);
		Node t = floor(h.right, key);
		if(t != null) return t;
		else return h;
	}
	
	//the smallest one which is not smaller than the main key
		public Key ceiling(Key key){
			Node h = ceiling(root, key);
			if(h == null) return null;
			return h.key;
		}
		
		private Node ceiling(Node h, Key key){
			if(h == null)	return null;
			int cmp = key.compareTo(h.key);
			if(cmp == 0) return h;
			if(cmp > 0) return ceiling(h.right, key);
			Node t = ceiling(h.left, key);
			if(t != null) return t;
			else return h;
		}
		
	/*if h's right child is red, exchange the relationship between h and this child
	 * eg. make right child the father and h the left child
	 */
	private Node rotateLeft(Node h){
		Node x = h.right;
		h.right = x.left;
		x.left = h;
		h.color = RED;
		x.N = h.N;
		h.N = 1 + size(h.left) + size(h.right);
		return x;
	}
	
	/*if h's left child is red, exchange the relationship between h and this child
	 * eg. make left child the father and h the right child
	 */
	private Node rotateRight(Node h){
		Node x = h.left;
		h.left = x.right;
		x.right = h;
		h.color = RED;
		x.N = h.N;
		h.N = 1 + size(h.left) + size(h.right);
		return x;
	}
	
	//if both of h's children are red, make them black and h red
	private void flipColors(Node h){
		 h.color = !h.color;
	     h.left.color = !h.left.color;
	     h.right.color = !h.right.color;
	}
	
	//get the value of the key
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
	
	//put a Node(key, val) into RBTree
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
		
		if(isRed(h.right) && !isRed(h.left)) 	h = rotateLeft(h);
		if(isRed(h.left) && isRed(h.left.left))	h = rotateRight(h);
		if(isRed(h.left) && isRed(h.right))		flipColors(h);
		
		h.N = 1 + size(h.left) + size(h.right);
		return h;
	}

	/*if h is Red, h.left and h.left.left is Black
	 *make h.left or one of its children red.
	 */
	private Node moveRedLeft(Node h){
		flipColors(h);
		if(isRed(h.right.left)){
			h.right = rotateRight(h.right);
			h = rotateLeft(h);
		}
		return h;
	}
	
	/*if h is Red and both h.right and h.right.left are Black
	 * make h.right or one of its children red
	 */
	private Node moveRedRight(Node h){
		flipColors(h);
		if(isRed(h.left.left))
			h = rotateRight(h);
		return h;
	}

	// delete the key-value pair with the minimum key
	public void deleteMin(){
		if(!isRed(root.left) && !isRed(root.right))
			root.color = RED;
		root = deleteMin(root);
		if(!isEmpty())	root.color = BLACK;
	}

    // delete the key-value pair with the minimum key rooted at h
	private Node deleteMin(Node h){
		if(h.left == null)
			return null;
		if(!isRed(h.left) && !isRed(h.left.left))
			h = moveRedLeft(h);
		h.left = deleteMin(h.left);
		return balance(h);
	}
	
	 // delete the key-value pair with the maximum key
    public void deleteMax() {
        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;
        root = deleteMax(root);
        if (!isEmpty()) root.color = BLACK;
    }

    // delete the key-value pair with the maximum key rooted at h
    private Node deleteMax(Node h) { 
        if (isRed(h.left))
            h = rotateRight(h);

        if (h.right == null)
            return null;
        if (!isRed(h.right) && !isRed(h.right.left))
            h = moveRedRight(h);
        h.right = deleteMax(h.right);
        return balance(h);
    }
	
	//balance the tree rooted h
	private Node balance(Node h){
		if(isRed(h.right)) h = rotateLeft(h);
		if(isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
		if(isRed(h.left) && isRed(h.right)) flipColors(h);
		
		h.N = 1 + size(h.left) + size(h.right);
		return h;
	}
	
	public void delete(Key key){
		if(!isRed(root.left) && !isRed(root.right))
			root.color = RED;
		root = delete(root, key);
		if(!isEmpty())	root.color = BLACK;
	}
	
	private Node delete(Node h, Key key){
		if(key.compareTo(h.key) < 0){
			if(!isRed(h.left) && !isRed(h.left.left))
				h = moveRedLeft(h);
			h.left = delete(h.left, key);
		}
		else{
			if(isRed(h.left))
				h = rotateRight(h);
			if(key.compareTo(h.key) == 0 && h.right == null)
				return null;
			if(!isRed(h.right) && !isRed(h.right.left))
				h = moveRedRight(h);
			
			if(key.compareTo(h.key) == 0){
				Node x = min(h.right);
				h.val = x.val;
				h.key = x.key;
				h.right = deleteMin(h.right);
			}
			else
				h.right = deleteMin(h.right);
		}
		return balance(h);
	}
	
	
}
