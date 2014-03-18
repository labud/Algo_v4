package Bag;

import java.util.Iterator;

public class Bag<Item> implements Iterable<Item>{
	private Node first;
	private class Node{
		Item item;
		Node next;
	}
	
	public void add(Item item){
		Node old = first;
		first = new Node();
		first.item = item;
		first.next = old;
	}
	
	public Iterator<Item> iterator(){
		return new ListIterator();
	}
	
	private class ListIterator implements Iterator<Item>{
		private Node current = first;
	
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return current != null;
		}

		@Override
		public Item next() {
			// TODO Auto-generated method stub
			Item item = current.item;
			current = current.next;
			return item;
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			
		}
		
	}
}