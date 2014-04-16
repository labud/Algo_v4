package	BinaryTree;

import java.util.Stack;


class Node<Value>{
	private Value val;
	private Node<Value> left;
	private Node<Value> right;	
	private Node<Value> p;
	
	Node(Value v){ 
		val = v; 
		left = right = p = null;
	}
	
	public void visit(){
		System.out.print(val.toString());
	}
	
	public Value getVal()			{ return val;}	
	public void setVal(Value v)		{ val = v;}	
	public Node getLeft()			{ return left;}
	public void setLeft(Node h)		{ left = h;}
	public Node getRight()			{ return right;}
	public void setRight(Node h)	{ right = h;}
	public Node getParent()			{ return p;}
	public void setParent(Node h)	{ p = h;}
}

public class BinaryTree{
	Node root;
	
	public int size(){	return size(root); }	
	public int size(Node h){
		if(h == null)
			return 0;
		return 1 + size(h.getLeft()) + size(h.getRight());
	}
	
	public void PreOder_recur(){ PreOder_recur(root);}	
	public void PreOder_recur(Node h){
		if( h == null)
			return;
		/*
		 * do something with h
		 */
		h.visit();
		PreOder_recur(h.getLeft());
		PreOder_recur(h.getRight());
	}
	
	public void InOder_recur(){ InOder_recur(root);}	
	public void InOder_recur(Node h){
		if( h == null)
			return;
		InOder_recur(h.getLeft());
		/*
		 * do something with h
		 */
		h.visit();
		InOder_recur(h.getRight());
	}
	
	public void PostOder_recur(){ PostOder_recur(root);}	
	public void PostOder_recur(Node h){
		if( h == null)
			return;
		PostOder_recur(h.getLeft());
		PostOder_recur(h.getRight());
		/*
		 * do something with h
		 */
		h.visit();
	}
	
	public void PreOrder1(){
		Stack<Node> s = new Stack<Node>();
		Node tmp = root;
		while(tmp != null || !s.isEmpty()){
			while(tmp != null){
				s.push(tmp);
				tmp.visit();
				tmp = tmp.getLeft();
			}
			if(!s.isEmpty()){
				tmp = s.pop();
				tmp = tmp.getRight();
			}
		}			
	}
	
	public void InOrder1(){
		Stack<Node> s = new Stack<Node>();
		Node tmp = root;
		while(tmp != null || !s.isEmpty()){
			while(tmp != null){
				s.push(tmp);
				tmp = tmp.getLeft();
			}
			if(!s.isEmpty()){
				tmp = s.pop();
				tmp.visit();
				tmp = tmp.getRight();
			}
		}			
	}
	
	public void PreOrder2(){
		if(root == null)
			return ;
		Stack<Node> s = new Stack<Node>();
		s.push(root);
		while(!s.isEmpty()){
			Node tmp = s.pop();
			/*
			 * do something with h
			 */
			tmp.visit();
			if(tmp.getLeft() != null) s.push(tmp.getLeft());
			if(tmp.getRight() != null) s.push(tmp.getRight());
		}
	}
	

	public void PostOrder2(){
		if(root == null)
			return ;
		Stack<Node> s = new Stack<Node>();
		s.push(root);
		boolean isChidrenVisited;
		Node cur, pre = null;
		while(!s.isEmpty()){
			cur = s.peek();
			isChidrenVisited = false;
			if(cur.getRight() != null){
				//Rchild was visited 
				if(pre == cur.getRight())
					isChidrenVisited = true;
			}
			else{ //cur doesn't have Rchild
				if(cur.getLeft() == null)
					isChidrenVisited = true;
				else if(cur.getLeft() == pre)
					isChidrenVisited = true;
			}
			
			if(isChidrenVisited){
				cur.visit();
				s.pop();
				pre = cur;
			}
			else{
				if(cur.getRight() != null)
					s.push(cur.getRight());
				if(cur.getLeft() != null)
					s.push(cur.getLeft());
			}
		}
	}
	
}