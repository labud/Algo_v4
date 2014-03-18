package Graph;

public class DFSearch{
	private boolean[] marked;
	//private int count;
	
	public DFSearch(Graph G, int s){
		marked = new boolean[G.V()];
		dfs(G,s);
	}
	
	public void dfs(Graph G, int v){
		marked[v] = true;
		//count++;
		for(int w : G.adg(v))
			if(!marked[w])	dfs(G,w);
		/*
		 do something
		 */	
	}
	
	/*public boolean marked(int w){
		return marked[w];
	}
	
	public int count(){
		return count;
	}*/
}