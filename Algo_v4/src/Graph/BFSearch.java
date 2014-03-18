package Graph;

import java.util.LinkedList;
import java.util.Queue;

public class BFSearch{
	private boolean[] marked;
	private int[] edgeTo;
	
	public BFSearch(Graph G, int s){
		marked = new boolean[G.V()];
		edgeTo = new int[G.V()];
		bfs(G,s);
	}
	
	private void bfs(Graph G, int s){
		Queue<Integer> q = new LinkedList<Integer>();
		marked[s] = true;
		while(!q.isEmpty()){
			int v = q.poll();
			/*
			 do something
			 */
			for(int w : G.adg(v))
				if(!marked[w]){
					edgeTo[w] = v;
					marked[w] = true;
					q.add(w);
				}
		}
	}
	
}