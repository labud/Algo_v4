package Sort;

import java.util.Random;


public class Quick extends Sort{
	private static int threshold = 28;
	
	public static void sort(Comparable[] a){
		sort(a, 0, a.length - 1);
	}
	
	public static void sort(Comparable[] a, int lo, int hi){
		if(hi - lo <= threshold){
			Insertion.sort(a, lo, hi);
			return ;
		}
		
		int mid = partition(a, lo, hi);
		sort(a, lo, mid - 1);
		sort(a, mid+1, hi);
	}
	
	protected static int partition(Comparable[] a, int lo, int hi){
		int i = lo , j = hi + 1;
		while(true){
			while(less(a[++i],a[lo])) if(i == hi) break;
			while(less(a[lo],a[--j])) if(j == lo) break;
			if(i >= j)
				break;
			exch(a,i,j);
		}
		exch(a,lo,j);
		return j;
	}
	
	protected static void partition3way(Comparable[] a, int lo, int hi){
		int lt = lo, i = lo +1 , gt = hi;
		Comparable v = a[lo];
		while( i <= gt){
			int cmp = a[i].compareTo(v);
			if		(cmp < 0)	exch(a,lt++,i++);
			else if (cmp > 0)	exch(a,i,gt--);
			else 				i++;
		}
		//sort(a,lo,lt-1);
		//sort(a,gt+1,hi);
	}
	
	public static void main(String[] args){
		Integer[] a = new Integer[]{5,1,8,2,6,3,8,9,0,4,7};
		System.out.print("Before QcuikSorting:");
		show(a);
		sort(a);
		System.out.print("After sorting:");
		show(a);
	}
}