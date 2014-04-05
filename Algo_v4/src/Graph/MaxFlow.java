package Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import sun.awt.SunHints.Value;
import Bag.Bag;

class FlowEdge{
	 private final int v;
	 private final int w;
	 private final double capacity;
	 private double flow;
	 
	 public FlowEdge(int v, int w, double cap){
		 this.v = v;
		 this.w = w;
		 this.capacity = cap;
		 this.flow = 0.0;
	 }
	 
	 public int from()  		{return v;}
	 public int to()			{return w;}
	 public double capacity()	{return capacity;}
	 public double flow()		{return flow;}
	 
	 public int other(int vert) {
		if(vert == v) return w;
		else if(vert == w) return v;
		return -1;
	 }
	 
	 public double residualCapacityTo(int vert){
		 if( vert == v) return  flow;
		 else if (vert == w) return capacity - flow;
		 return -1;
	 }
	 
	 public void addResidualFlowTo(int vert, double delta){
		 if(vert == v) flow -= delta;
		 else if(vert == w) flow += delta;
	 }
	 
	 public String toString(){
		 return String.format("%d->%d %.2f %.2f", v, w, capacity, flow);
	 }
}

class FlowNetwork{
	private final int V;
	private int E;
	private Bag<FlowEdge>[] adj;
	
	@SuppressWarnings("unchecked")
	FlowNetwork(int V){
		this.V = V;
		this.E = 0;
		adj = (Bag<FlowEdge>[]) new Bag[V];
		for(int v = 0; v < V; v++)
			adj[v] = new Bag<FlowEdge>();
	}
	
	public int V() {return V;}
	public int E() {return E;}
	
	public void adgEdge(FlowEdge e){
		adj[e.from()].add(e);
		adj[e.to()].add(e);
		E++;
	}
	
	public Iterable<FlowEdge> adg(int v){
		return adj[v];
	}
}

class FordFulKerson{
	private static double EPSILON = 1E-11;
	private boolean[] marked;
	private FlowEdge[] edgeTo;
	private double value;
	
	private boolean hasAugmentingPath(FlowNetwork G, int s, int t){
		marked = new boolean[G.V()];
		edgeTo = new FlowEdge[G.V()];
		Queue<Integer> q = new LinkedList<Integer>();
		
		marked[s] = true;
		q.add(s);
		while(!q.isEmpty()){
			int v = q.poll();
			for (FlowEdge e : G.adg(v)) {
				int w = e.other(v);
				if(e.residualCapacityTo(w) > EPSILON && !marked[w]){
					edgeTo[w] = e;
					marked[w] = true;
					q.add(w);
				}
			}
		}
		return marked[t];
	}
	
	public FordFulKerson(FlowNetwork G, int s, int t){
		while(hasAugmentingPath(G, s, t)){
			double bottle = Double.POSITIVE_INFINITY;
			for(int v = t; v != s; v = edgeTo[v].other(v))
				bottle = Math.max(bottle, edgeTo[v].residualCapacityTo(v));
			for(int v = t; v != s; v = edgeTo[v].other(v))
				edgeTo[v].addResidualFlowTo(v, bottle);
			value += bottle;
		}
	}
}


public class MaxFlow{
	
}