package Sort;

public class PriorityQueue<Key extends Comparable<Key>>{
	private Key[] pq;
	private int N = 0;
	private int maxN;
	public PriorityQueue(int maxN){
		this.maxN = Math.max(1, maxN);
		pq =(Key[]) new Comparable[maxN+1];
	}
	
	public boolean isEmpty(){
		return N == 0;
	}
	
	public int size(){
		return N;
	}
	
	private void resize(int T){
		maxN = T;
		N = Math.max(T, N);
		Key[] tmp = (Key[])new Comparable[T+1];
		for(int i = 1 ; i <= N ; i++)
			tmp[i] = pq[i];		
	}
	
	public void insert(Key v){
		/*
		if(N + 1 >= maxN/2)
			resize(maxN*2);
		 */
		pq[++N] = v;
		swim(N);
	}
	
	public void Top(Key v){
		v = pq[1];
	}
	
	public Key delete(){
		/*
		if(N - 1 < maxN/4)
			resize(maxN/2);
		 */
		Key max = pq[1];
		exch(1,N);
		pq[N--] = null;
		sink(1);
		return max;
	}
	
	private boolean less(int i, int j){
		return pq[i].compareTo(pq[j]) < 0;
	}
	
	private void exch(int i, int j){
		Key t = pq[i];
		pq[i] = pq[j];
		pq[j] = t;
	}
	
	private void swim(int i){
		if(i > N )
			return ;
		while(i > 1 && less(i/2, i))
			exch(i, i/2);
	}
	
	private void sink(int i){
		while(2 * i <= N){
			int j = 2 * i;
			if(j < N && less(j,j+1)) 
				j++;
			if(!less(i,j))
				break;
			exch(i,j);
			i = j;
		}
	}
	
	public static void sort(Comparable[] a,int lo, int hi){
		int N = hi - lo + 1;
		PriorityQueue tmp = new PriorityQueue(N);
		for(int i = 1 ; i <= N ;i++)
			tmp.pq[i] = a[lo + i - 1];
		tmp.N = N;
		for(int i = N/2 ; i >= 1 ; i--)
			tmp.sink(i);
		while(tmp.N > 1){
			tmp.exch(1, tmp.N--);
			tmp.sink(1);
		}
		for(int i = 1 ; i <= N ;i++)
			 a[lo + i - 1] = tmp.pq[i];
	}
	
	public static void sort(Comparable[] a){
		sort(a, 0, a.length - 1);
	}
	
	protected static void show(Comparable[] a){
		for(int i = 0 ; i < a.length ; i++)
			System.out.print(a[i] + " ");
		System.out.println();
	}
	
	public static void main(String[] args){
		Integer[] a = new Integer[]{5,1,8,2,6,3,8,9,0,4,7};
		System.out.print("Before HeapSorting:");
		show(a);
		sort(a);
		System.out.print("After sorting:");
		show(a);
	}
}